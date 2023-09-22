package com.hackathon.tsc.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Beneficiary")
public class Beneficiary {

    @DynamoDBHashKey
    @DynamoDBAttribute
    private String beneficiaryID;

    @DynamoDBAttribute
    private String cabinNo;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    private List<String> mhProvider;

    @DynamoDBAttribute
    private List<String> sudProvider;

    @DynamoDBAttribute
    private List<String> need;

    @DynamoDBAttribute
    private String navigatorID;

    @DynamoDBAttribute
    private List<String> documents;

    @DynamoDBAttribute
    private List<String> serviceRequestId;

}
