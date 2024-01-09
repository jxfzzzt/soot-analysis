package edu.fdu.se.domain;

import java.util.Random;
import java.util.UUID;

public class UserService {

    public Integer getUserId() {
        Random random = new Random();
        return random.nextInt();
    }

    public String getUserName() {
        return UUID.randomUUID().toString();
    }

    public String processUserName(String userName) {
        return userName.replaceAll("-", "");
    }

    public Integer calcAge(int age) {
        int x;
        if (age > 20) {
            x = age + 2;
        } else {
            x = age + 1;
        }
        return x;
    }
}
