package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.hackathon.tsc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public boolean delete(String userID) {
        User user = dynamoDBMapper.load(User.class, userID);
        dynamoDBMapper.delete(user);
        return true;
    }

    public Optional<String> getUserTypeByUserID(String userID) {
        User user = new User();
        user.setUserID(userID);
        User result = dynamoDBMapper.load(user,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.ofNullable(result.getUserType());

    }

    public Optional<User> getUserByNameAndPassword(String userID, String password) {
        User user = new User();
        user.setUserID(userID);
        user.setPassword(password);
        User result = dynamoDBMapper.load(user,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.ofNullable(result);
    }
}
