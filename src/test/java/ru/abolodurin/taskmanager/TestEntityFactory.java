package ru.abolodurin.taskmanager;

import ru.abolodurin.taskmanager.model.dto.LoginRequest;
import ru.abolodurin.taskmanager.model.dto.RegisterRequest;
import ru.abolodurin.taskmanager.model.entity.CommonException;
import ru.abolodurin.taskmanager.model.entity.Role;
import ru.abolodurin.taskmanager.model.entity.User;

//TODO заполнить тестовые сушности после добавления бизнес логики
public class TestEntityFactory {
    private static TestEntityFactory instance;

    private TestEntityFactory() {
    }

    public static TestEntityFactory get() {
        if (instance == null) instance = new TestEntityFactory();
        return instance;
    }

    public User getUser() {
        String name = "user_" + getIdentifier();

        return User
                .builder()
                .username(name)
                .email(name + "@example.com")
                .password(name)
                .role(Role.USER)
//                .posts(new ArrayList<>())
//                .subscriptions(new ArrayList<>())
//                .subscribers(new ArrayList<>())
                .build();
    }

    //    public UserRequest getUserRequest() {
//        String name = "userRequest_" + getIdentifier();
//        return UserRequest.of(name);
//    }
//
//    public UserResponse getUserResponse() {
//        String name = "userResponse_" + getIdentifier();
//        return UserResponse.of(name);
//    }
//
    public CommonException getCommonException() {
        String message = "message_" + getIdentifier();
        return new CommonException(message);
    }

    public RegisterRequest getRegisterRequest() {
        String name = "regUsername_" + getIdentifier();
        return RegisterRequest
                .builder()
                .username(name)
                .email(name + "@example.com")
                .password(name)
                .build();
    }

    public LoginRequest getLoginRequest() {
        String name = "loginUsername_" + getIdentifier();
        return LoginRequest
                .builder()
                .username(name)
                .password(name)
                .build();
    }

    private static String getIdentifier() {
        Object o = new Object();
        return o.toString()
                .replace("@", "")
                .replace("java.lang.", ""); //example "Object782cc5fa"
    }

}
