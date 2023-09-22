package com.hackathon.tsc.repository;

import com.hackathon.tsc.pojo.Navigator;
import com.hackathon.tsc.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavigatorRepository {

    List<Navigator> dummyNavigator;

    public NavigatorRepository() {
        dummyNavigator = List.of(new Navigator(3L, List.of(1L)));
    }

    public Optional<Navigator> getNavigatorById(Long id) {
        return dummyNavigator.stream().filter(navigator ->
                navigator.getId().equals(id)
        ).findFirst();
    }

}
