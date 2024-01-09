package edu.fdu.se;

import edu.fdu.se.domain.UserService;

public class Main {
    public static void main(String[] args) {
        int x = 5;
        UserService userService = new UserService();
        Integer integer = userService.calcAge(x);
        String userName = userService.getUserName();
        System.out.println(integer + " " + userName);
    }
}
