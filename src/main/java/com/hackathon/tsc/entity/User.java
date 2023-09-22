package com.hackathon.tsc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.beans.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "User")
public class User {

    @DynamoDBHashKey
    @DynamoDBAttribute
    private String userID;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    @JsonIgnore
    private String password;

    @DynamoDBAttribute
    private String userType;

    @DynamoDBAttribute
    private String contactNo;
}