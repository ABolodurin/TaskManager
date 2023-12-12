package ru.abolodurin.taskmanager.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.abolodurin.taskmanager.service.UserService;

@Configuration
@ComponentScan("ru.abolodurin.taskmanager")
@RequiredArgsConstructor
public class AppConfig {
    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userService::findByUsername;
    }

}
