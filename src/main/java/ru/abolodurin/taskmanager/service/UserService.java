package ru.abolodurin.taskmanager.service;

import ru.abolodurin.taskmanager.model.entity.User;

public interface UserService {
    User findByUsername(String username);

    void add(User user);

    void update(String username, User updatedUser);

    boolean isExist(String username);

}
