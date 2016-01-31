package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mandiriecash.etollapi.dao.UserDAO;

import java.io.IOException;

@RestController
public class HomeController {
    @RequestMapping("/woi")
    public String greeting() {
        return "coeg";
    }
}
