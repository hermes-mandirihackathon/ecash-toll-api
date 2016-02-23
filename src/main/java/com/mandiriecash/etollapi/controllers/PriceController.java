package com.mandiriecash.etollapi.controllers;

import com.mandiriecash.etollapi.models.Price;
import com.mandiriecash.etollapi.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
@Controller
@RequestMapping(value = "/prices")
public class PriceController {
    public final static String OK = "ok";
    public final static String ERROR = "error";
    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    PriceResponse getPrice(@PathVariable int id){
        return new PriceResponse(OK, "", priceService.getPriceById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    PriceResponse getPrices(){
        return new PriceResponse(OK, "", priceService.getPrices());
    }
}

class PriceResponse{
    public String status;
    public String message;
    public List<Price> prices;

    public PriceResponse(String status, String message, List<Price> prices){
        this.status = status;
        this.message = message;
        this.prices = prices;
    }
}
