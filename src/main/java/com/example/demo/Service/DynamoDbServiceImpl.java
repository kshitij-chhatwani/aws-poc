package com.example.demo.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.example.demo.Entity.Project;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
public class DynamoDbServiceImpl implements DynamoDBService {

    private AmazonDynamoDB client;
    private String table_name;


    public DynamoDbServiceImpl() {
        client = AmazonDynamoDBClientBuilder.standard().build();
        table_name = System.getenv("Table_Name");

    }

    public String writeItemToTable(Project project, LambdaLogger logger){

        Map<String, AttributeValue> itemMap = new HashMap<>();

        itemMap.put("projectId",new AttributeValue(String.valueOf(project.getProjectId())));
        itemMap.put("name",new AttributeValue(project.getName()));
        itemMap.put("active", new AttributeValue(String.valueOf(project.isActive())));
        itemMap.put("description",new AttributeValue(project.getDescription()));

        this.client.putItem(table_name,itemMap);

        return "Write Success";
    }


    public String readItemFromTable(Integer projectId, LambdaLogger logger){

        logger.log("Calling Table to Read Item from.");

        Map<String, AttributeValue> attributeValueMap = new HashMap<>();
        attributeValueMap.put("projectId", new AttributeValue(String.valueOf(projectId)));
        GetItemResult getItemResult = this.client.getItem(table_name,attributeValueMap);
        logger.log("GetItemResult : "+getItemResult.toString());

        return "Read Success";
    }
}
