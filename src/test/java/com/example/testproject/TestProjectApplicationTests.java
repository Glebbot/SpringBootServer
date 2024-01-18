package com.example.testproject;

import com.example.testproject.model.user.User;
import com.example.testproject.model.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestProjectApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void addUserTest() {
        User user=new User();
        user.setFull_name("Gleb");
        user.setEmail("mail");
        user.setPassword("123");
        userService.save(user);
    }

}
