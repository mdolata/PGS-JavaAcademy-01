package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mateu on 07.03.2017.
 *
 * My first Controller
 * It will be a controller for tests
 */
@RestController
@RequestMapping("/world")
public class FirstController {

    @RequestMapping("hello")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/hello2")
    public String helloWorld2() {
        return "Hello world 2";
    }


}
