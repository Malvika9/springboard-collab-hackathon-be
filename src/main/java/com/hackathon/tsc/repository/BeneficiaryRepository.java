package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BeneficiaryRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Optional<Beneficiary> getBeneficiaryByUserID(String userID) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBeneficiaryID(userID);
        Beneficiary result = dynamoDBMapper.load(beneficiary,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.of(result);
    }

    public Beneficiary save(Beneficiary beneficiary) {
        dynamoDBMapper.save(beneficiary);
        return beneficiary;
    }

    public List<Beneficiary> getBeneficiariesByIdList(List<String> userID) {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        for (String beneficiaryID : userID) {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setBeneficiaryID(beneficiaryID);
            beneficiaries.add(beneficiary);
        }
        Map<String, List<Object>> batchResult = dynamoDBMapper.batchLoad(beneficiaries);
        if (batchResult.containsKey("Beneficiary")
                && batchResult.get("Beneficiary") != null) {
            List<Object> articles = batchResult.get("Beneficiary");
            //Convert the list of Objects into "ArticlesDao
            @SuppressWarnings("unchecked")
            List<Beneficiary> articlesDaos = (List<Beneficiary>) (List<?>) articles;
            return articlesDaos;
        }
        return null;
    }
    public boolean assignNavigator(String beneficiaryID, String navigatorID) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setBeneficiaryID(beneficiaryID);
        Beneficiary result = dynamoDBMapper.load(beneficiary,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        if(navigatorID == null) {
            result.setNavigatorID(navigatorID);
        }
        else {
            result.setNavigatorID(navigatorID);
        }
        dynamoDBMapper.save(result);
        return true;
    }

    public Boolean deleteBeneficiary(String id) {
        Beneficiary beneficiary = dynamoDBMapper.load(Beneficiary.class, id);
        dynamoDBMapper.delete(beneficiary);
        return true;
    }
}


