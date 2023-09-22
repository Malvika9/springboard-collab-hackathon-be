package com.hackathon.tsc.repository;

import com.hackathon.tsc.Enum.UserType;
import com.hackathon.tsc.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {

    List<User> dummyUser;

    public UserRepository() {
        dummyUser = List.of(new User(1L, "B1", "B1", "B1", "B1", UserType.BENEFICIARY.toString(), "B1"),
                new User(2L, "B2", "B2", "B2", "B2", UserType.BENEFICIARY.toString(), "B2"),
                new User(3L, "N1", "N1", "N1", "N1", UserType.NAVIGATOR.toString(), "N1"),
                new User(4L, "N2", "N2", "N2", "N2", UserType.NAVIGATOR.toString(), "N2"),
                new User(5L, "C1", "C1", "C1", "C1", UserType.CASE_WORKER.toString(), "C1"));
    }

    public Optional<User> getUserByNameAndPassword(String userName, String password) {
        return dummyUser.stream().filter(user ->
            user.getUserId().equals(userName) && user.getPassword().equals(password)
        ).findFirst();
    }
}
