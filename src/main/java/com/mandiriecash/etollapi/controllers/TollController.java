package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Toll;
import com.mandiriecash.etollapi.services.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */

@Controller
@RequestMapping(value = "/tolls")
public class TollController {
    @Autowired
    private TollService tollService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody TollResponse getTolls(){
        TollResponse response = new TollResponse("OK", "", tollService.getTolls());
        return response;
    }
}

class TollResponse{
    public String status;
    public String message;
    public List<Toll> tolls;

    public TollResponse(String status, String message, List<Toll> tolls){
        this.status = status;
        this.message = message;
        this.tolls = tolls;
    }
}
