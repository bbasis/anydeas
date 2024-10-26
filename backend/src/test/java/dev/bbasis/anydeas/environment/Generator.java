package dev.bbasis.anydeas.environment;

import dev.bbasis.anydeas.model.User;

import java.util.Random;

public class Generator {

    private static Random random = new Random();

    public static User generateUser(){
        User user = new User();
        user.setUsername("username" + random.nextInt(10));
        user.setPassword("password" + random.nextInt(10));
        user.setEmail("email" + random.nextInt(10) + "@email.com");
        user.setFirstName("first" + random.nextInt(10));
        user.setLastName("last" + random.nextInt(10));
        user.setNickname("nick" + random.nextInt(10));
        return user;
    }
}
