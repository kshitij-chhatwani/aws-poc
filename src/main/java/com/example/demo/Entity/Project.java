package com.example.demo.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "sampleDb")
public class Project {

    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Id
    int projectId;

    String name;

    boolean active;

    String description;

}
