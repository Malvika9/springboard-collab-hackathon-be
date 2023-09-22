package com.hackathon.tsc.controller;

import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.pojo.Task;
import com.hackathon.tsc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{beneficiaryId}")
    public ResponseEntity<List<Task>> getAllTaskByBeneficiaryId(@PathVariable String beneficiaryId) {
        List<Task> tasks = taskService.getAllTaskByBeneficiaryId(beneficiaryId);
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        try {
            return new ResponseEntity(taskService.saveTask(task), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        try {
            Task data = taskService.updateTask(task);
            return new ResponseEntity(task, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/task")
    public ResponseEntity<Boolean> deleteTask(@RequestBody Task task) {
        try {
            boolean flag = taskService.deleteTask(task);
            return new ResponseEntity(flag, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
