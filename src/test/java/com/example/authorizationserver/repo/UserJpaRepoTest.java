package com.example.authorizationserver.repo;

import com.example.authorizationserver.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserJpaRepoTest {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertNewUser() {
        userJpaRepo.save(User.builder()
                .uid("lyutvs@gmail.copm")
                .password(passwordEncoder.encode("1234"))
                .name("lyutvs")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

    }
}
