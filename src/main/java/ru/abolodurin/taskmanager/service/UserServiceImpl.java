package ru.abolodurin.taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.abolodurin.taskmanager.model.entity.CommonException;
import ru.abolodurin.taskmanager.model.entity.User;
import ru.abolodurin.taskmanager.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new CommonException("user " + username + " not found"));
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }


    @Override
    public void update(String username, User updatedUser) {
        User user = findByUsername(username);

        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        userRepository.save(updatedUser);
    }

    @Override
    public boolean isExist(String username) {
        return userRepository.existsByUsername(username);
    }

}
