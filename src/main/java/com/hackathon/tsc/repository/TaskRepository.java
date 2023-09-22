package com.hackathon.tsc.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.hackathon.tsc.exception.TaskNotFoundException;
import com.hackathon.tsc.pojo.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hackathon.tsc.constant.ExceptionMessages.TASK_NOT_FOUND;

@Service
@Slf4j
public class TaskRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<Task> getAllTaskByBeneficiaryId(String beneficiaryId) {
        Task task = new Task();
        task.setBeneficiaryID(beneficiaryId);
        DynamoDBQueryExpression<Task> queryExpression =
                new DynamoDBQueryExpression<Task>()
                        .withHashKeyValues(task)
                        .withLimit(20)
                        .withIndexName("beneficiaryID-index")
                        .withConsistentRead(false);
        List<Task> queryResult = dynamoDBMapper.query(Task.class, queryExpression);
        queryResult.forEach(System.out::println);
        return queryResult;
    }

    public Task saveTask(Task task) {
        dynamoDBMapper.save(task);
        return task;
    }

    public Task updateTask(Task task) throws TaskNotFoundException{
        Task dbTask = dynamoDBMapper.load(Task.class, task.getTaskID());
        if(dbTask == null) {
            throw new TaskNotFoundException(TASK_NOT_FOUND);
        }
        else {
            dbTask.setStartTime(task.getStartTime());
            dbTask.setEndTime(task.getEndTime());
            dbTask.setDescription(task.getDescription());
            dynamoDBMapper.save(task);
            return dbTask;
        }
    }

    public boolean deleteTask(Task task) {
        Task service = dynamoDBMapper.load(Task.class, task.getTaskID());
        dynamoDBMapper.delete(service);
        return true;
    }
}
