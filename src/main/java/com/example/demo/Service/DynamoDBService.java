package com.example.demo.Service;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.example.demo.Entity.Project;

public interface DynamoDBService {

     String writeItemToTable(Project project, LambdaLogger logger);

     String readItemFromTable(Integer projectId, LambdaLogger logger);
}
