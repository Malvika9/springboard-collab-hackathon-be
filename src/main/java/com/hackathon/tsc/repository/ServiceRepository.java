package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.hackathon.tsc.constant.UserType;
import com.hackathon.tsc.exception.ServiceNotFoundException;
import com.hackathon.tsc.entity.Service;
import com.hackathon.tsc.pojo.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Slf4j
@Component
public class ServiceRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<Service> getServicesForBeneficiaryID(String beneficiaryID) {
        Service service = new Service();
        service.setBeneficiaryID(beneficiaryID);

        DynamoDBQueryExpression<Service> queryExpression =
                new DynamoDBQueryExpression<Service>()
                        .withHashKeyValues(service)
                        .withLimit(20)
                        .withIndexName("beneficiaryID-index")
                        .withConsistentRead(false);

        List<Service> queryResult = dynamoDBMapper.query(Service.class, queryExpression);
        queryResult.forEach(System.out::println);
        return queryResult;
    }

    public Optional<Service> getServiceById(String id) {
        Service service = dynamoDBMapper.load(Service.class, id);
        return Optional.ofNullable(service);
    }

    public Service addService(Service service) {
        dynamoDBMapper.save(service);
        return service;
    }

    public Boolean deleteService(String id) {
        Service service = dynamoDBMapper.load(Service.class, id);
        dynamoDBMapper.delete(service);
        return true;
    }

    public void updateService(Service updateService) throws ServiceNotFoundException {
        Service service = dynamoDBMapper.load(Service.class, updateService.getServiceID());
        if(service != null){
            List<Activity> updatedActivity = updateService.getActivityLog();
            List<Activity> currentActivity = service.getActivityLog();
            currentActivity.addAll(updatedActivity);
            updateService.setActivityLog(currentActivity);
            dynamoDBMapper.save(service);
        }
        else {
            throw new ServiceNotFoundException("Service not found");
        }
    }

    public List<Service> getServicesForId(String userType, String userId, String beneficiaryID) {
        if(userType.equals(UserType.NAVIGATOR)) {
            Service service = new Service();
            service.setBeneficiaryID(beneficiaryID);
            service.setNavigatorID(userId);
            DynamoDBQueryExpression<Service> queryExpression =
                    new DynamoDBQueryExpression<Service>()
                            .withHashKeyValues(service)
                            .withLimit(20)
                            .withIndexName("beneficiaryID-navigatorID-index")
                            .withConsistentRead(false);
            List<Service> queryResult = dynamoDBMapper.query(Service.class, queryExpression);
            queryResult.forEach(System.out::println);
            return queryResult;
        } else if (userType.equals(UserType.CASE_WORKER)) {
            Service service = new Service();
            service.setBeneficiaryID(beneficiaryID);
            service.setCaseWorkerID(userId);
            DynamoDBQueryExpression<Service> queryExpression =
                    new DynamoDBQueryExpression<Service>()
                            .withHashKeyValues(service)
                            .withLimit(20)
                            .withIndexName("beneficiaryID-navigatorID-index")
                            .withConsistentRead(false);
            List<Service> queryResult = dynamoDBMapper.query(Service.class, queryExpression);
            queryResult.forEach(System.out::println);
            return queryResult;
        }
        else {
            return new ArrayList<>();
        }
    }

    public Service addDefaultService(String navigatorID, String beneficiaryID){
        Service service = new Service();
        service.setServiceID(String.valueOf(System.currentTimeMillis()));
        service.setStatus("IN PROGRESS");
        service.setServiceName("TSC Service");
        String comment = "Service " + service.getServiceName() + " is " + service.getStatus();
        service.setActivityLog(List.of(new Activity(comment, Instant.now().toString())));
        service.setBeneficiaryID(beneficiaryID);
        service.setNavigatorID(navigatorID);
        service.setOrganizationName("The SpringBoard Collaborative");
        dynamoDBMapper.save(service);
        return service;
    }
}
