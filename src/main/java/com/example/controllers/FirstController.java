package com.example.controllers;

import com.example.Entity.User;
import com.example.Entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Date;
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
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(FirstController.class);

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
        User user = userRepository.findByName("mateusz");
        if (user == null)
            return "User mateusz is not exists in database :(";
        System.out.println("user " + user.getId());
        return user.getName();
    }

    @RequestMapping("saveUser")
    public String saveUser(){
        User user = new User("mateusz", "123");


        try {
            userRepository.save(user);
        } catch (ConstraintViolationException e){
            return "User " + user.getName() + " is already exists!";
        }


        return "created " + user.toString();
    }

    @RequestMapping("saveManyUser")
    public String saveManyUsers(){
        List<User> listOfUsers = Arrays.asList(
                new User("marysia","143"),
                new User("olek","123"));

        listOfUsers.forEach(s -> userRepository.save(s));

        return "created few users";
    }

    @RequestMapping("getAllUsers")
    public String getAllUsers(){
        Iterable<User> integrableUsers = userRepository.findAll();
        StringBuilder builder = new StringBuilder("");
        for(User user : integrableUsers){
            builder.append(user.toString())
                .append("</br>\n");
        }

        return builder.toString();
    }

    @RequestMapping("changePassword/{name}")
    public String changePassword(@PathVariable String name,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        User user = userRepository.findByName(name);

        if (user == null) {
            return "User named " + name + " doesn't exists!";
        }

        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            user.setUpdateDate(new Date());
            userRepository.save(user);
            return "User " + name + " just change his password";
        }

        return "Wrong password for user " + name;
    }



    @RequestMapping(value = "usersCount")
    public Long howManyUsers(){
        return userRepository.count();
    }

}
