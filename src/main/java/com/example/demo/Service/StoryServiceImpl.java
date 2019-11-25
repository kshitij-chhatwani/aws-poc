package com.example.demo.Service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.Entity.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class StoryServiceImpl implements RequestHandler<Project,String> {

    private DynamoDBService dynamoDBServiceImpl = new DynamoDbServiceImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handleRequest(Project input, Context context) {

        LambdaLogger logger = context.getLogger();

        dynamoDBServiceImpl.writeItemToTable(input, logger);

        return "write successful";
    }


}
