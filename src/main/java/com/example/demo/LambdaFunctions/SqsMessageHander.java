package com.example.demo.LambdaFunctions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.demo.Entity.Project;
import com.example.demo.Service.DynamoDBService;
import com.example.demo.Service.DynamoDbServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

//On Message written to SQS, Trigger Lambda function to write message to DynamoDb.
public class SqsMessageHander implements RequestHandler<SQSEvent, String> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private DynamoDBService dynamoDBServiceImpl = new DynamoDbServiceImpl();

    @Override
    public String handleRequest(SQSEvent input, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("Received Message from Queue");

        try {

            for (SQSEvent.SQSMessage message : input.getRecords()) {
                Project project = objectMapper.readValue(message.getBody(), Project.class);
                return dynamoDBServiceImpl.writeItemToTable(project,logger);
            }
        }
         catch( IOException e){
            logger.log("Error reading Message!");
         }

        return "Success";
    }
}
