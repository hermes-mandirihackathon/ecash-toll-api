package com.mandiriecash.etollapi.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mandiriecash.etollapi.model.*;
import com.mandiriecash.etollapi.model.dao.UserDAO;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@RestController
public class HomeController {
    @RequestMapping("/woi")
    public String greeting() {
        return "coeg";
    }

    @RequestMapping("/test")
    public String test(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("database/Spring-Datasource.xml");

        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        User user = userDAO.findUserById(1);

        ObjectMapper mapper = new ObjectMapper();
        try{
            // Convert object to JSON string and pretty print
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            return jsonInString;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return "error";
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
