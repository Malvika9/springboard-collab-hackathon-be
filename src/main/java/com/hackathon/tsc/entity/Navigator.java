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
@DynamoDBTable(tableName = "Navigator")
public class Navigator {

    @DynamoDBHashKey
    @DynamoDBAttribute
    private String navigatorID;

    @DynamoDBAttribute
    List<String> assignedBeneficiariesId;
}
