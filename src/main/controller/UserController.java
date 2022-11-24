package main.controller;

import main.common.R;
import main.entity.Person;
import main.service.UserService;

import java.io.IOException;

public class UserController {
    private UserService userService = new UserService();

    public R register(Person person) throws IOException, ClassNotFoundException {
        if(person.account .equals("root")) return R.error("禁止注册超级管理员");
        return userService.register(person);
    }

    public R login(Person person) throws IOException, ClassNotFoundException {
        return userService.login(person);
    }

}
