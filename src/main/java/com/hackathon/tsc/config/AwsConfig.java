package com.hackathon.tsc.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;

@Configuration
public class AwsConfig {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.us-east-1.amazonaws.com",
                                "us-east-1"
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        "AKIAWQYQP5GC75FMBL66",
                                        "xMcWS/ouUqS3ojsVhWHXXRoTemkjpZgwHkPSI59p"
                                )
                        )
                )
                .build();
    }

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials=new BasicAWSCredentials("AKIAWQYQP5GCZUBHLGUU",
                "h0gGtdMpA99pgx2qeo+eFEVtcY51clfdx8sGUr8/");
        return AmazonS3ClientBuilder.standard().withRegion("ap-southeast-2").withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }
}