package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mateu on 07.03.2017.
 */
@RestController
@RequestMapping("/world")
public class FirstController {

    @RequestMapping("hello")
    public String helloWorld(){
        return "Hello World";
    }


}
