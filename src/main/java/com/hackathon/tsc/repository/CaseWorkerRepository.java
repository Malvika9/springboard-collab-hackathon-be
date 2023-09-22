package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.hackathon.tsc.entity.CaseWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class CaseWorkerRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public CaseWorker save(CaseWorker caseWorker) {
        dynamoDBMapper.save(caseWorker);
        return caseWorker;
    }

    public Optional<CaseWorker> getCaseWorkerByUserID(String userID) {
        CaseWorker caseWorker = new CaseWorker();
        caseWorker.setCaseWorkerID(userID);
        CaseWorker result = dynamoDBMapper.load(caseWorker,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.of(result);
    }
}
