package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.hackathon.tsc.entity.Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NavigatorRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Optional<Navigator> getNavigatorByUserID(String userID) {
        Navigator navigator = new Navigator();
        navigator.setNavigatorID(userID);
        Navigator result = dynamoDBMapper.load(navigator,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.ofNullable(result);
    }

    public Navigator save(Navigator navigator) {
        dynamoDBMapper.save(navigator);
        return navigator;
    }

}
