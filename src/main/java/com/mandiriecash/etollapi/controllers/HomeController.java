package com.mandiriecash.etollapi.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @RequestMapping("/woi")
    public String greeting() {
        return "coeg";
    }
}
