package com.example.demo.LambdaFunctions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.Entity.Project;
import com.example.demo.Service.DynamoDBService;
import com.example.demo.Service.DynamoDbServiceImpl;

public class writeToDb implements RequestHandler<Project,String> {

    private DynamoDBService dynamoDBServiceImpl = new DynamoDbServiceImpl();

    @Override
    public String handleRequest(Project input, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("Writing Item to Table. ");

        return dynamoDBServiceImpl.writeItemToTable(input, logger);

    }
}
