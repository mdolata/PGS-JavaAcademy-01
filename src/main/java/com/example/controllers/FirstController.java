package com.example.controllers;

import com.example.Entity.User;
import com.example.Entity.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mateu on 07.03.2017.
 *
 * My first Controller
 * It will be a controller for tests
 */
@RestController
@RequestMapping("world")
public class FirstController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("hello")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("hello2")
    public String helloWorld2() {
        return "Hello world 2";
    }

    @RequestMapping("tryDatabase")
    public String tryDataBase() {
        User user = userDao.findByName("mateusz");
        if (user == null)
            return "User mateusz is not exists in database :(";
        System.out.println("user " + user.getId());
        return user.getName();
    }

    @RequestMapping("saveUser")
    public String saveUser(){
        User user = new User("mateusz", "123");
        userDao.save(user);
        return "created " + user.toString();
    }

    @RequestMapping("saveManyUser")
    public String saveManyUsers(){
        List<User> listOfUsers = Arrays.asList(
                new User("marysia","143"),
                new User("olek","123"));

        listOfUsers.forEach(s -> userDao.save(s));

        String result = "";

        for(User u : listOfUsers){
            User user = userDao.findByName(u.getName());
            result += user.getName();
        }
        return "created few users" + result;
    }


}
