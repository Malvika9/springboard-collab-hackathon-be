package com.hackathon.tsc.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "CaseWorker")
public class CaseWorker {

    @DynamoDBHashKey
    @DynamoDBAttribute
    private String caseWorkerID;

    @DynamoDBAttribute
    private String organisationName;

    @DynamoDBAttribute
    private String organisationType;

    @DynamoDBAttribute
    private List<String> serviceID;

}
