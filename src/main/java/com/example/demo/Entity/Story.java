package com.example.demo.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@DynamoDBTable(tableName = "Story")
public class Story {

    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Id
    int projectId;

    String name;

    String message;
}
