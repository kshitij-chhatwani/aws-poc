package com.example.demo.LambdaFunctions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.Service.DynamoDBService;
import com.example.demo.Service.DynamoDbServiceImpl;

public class readFromDb implements RequestHandler<Integer,String> {

    private DynamoDBService dynamoDBServiceImpl = new DynamoDbServiceImpl();

    @Override
    public String handleRequest(Integer input, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("Reading from Database.");

        return dynamoDBServiceImpl.readItemFromTable(input,logger);

    }
}
