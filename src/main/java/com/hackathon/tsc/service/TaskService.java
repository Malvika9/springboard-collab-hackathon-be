package com.hackathon.tsc.service;

import com.hackathon.tsc.exception.TaskNotFoundException;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.pojo.Task;
import com.hackathon.tsc.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hackathon.tsc.constant.ExceptionMessages.USER_NOT_LOGGED_IN;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LoginService loginService;

    public List<Task> getAllTaskByBeneficiaryId(String beneficiaryId) {
            return taskRepository.getAllTaskByBeneficiaryId(beneficiaryId);
    }

    public Task saveTask(Task task) throws UserNotFoundException {
            return taskRepository.saveTask(task);
    }

    public Task updateTask(Task task) throws TaskNotFoundException {
            return taskRepository.updateTask(task);
    }

    public boolean deleteTask(Task task) throws TaskNotFoundException {
            return taskRepository.deleteTask(task);
    }
}
