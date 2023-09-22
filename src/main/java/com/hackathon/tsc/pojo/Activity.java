package com.hackathon.tsc.pojo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Activity {

    @DynamoDBAttribute
    private String comment;

    @DynamoDBAttribute
    private Timestamp timeStamp;

}
