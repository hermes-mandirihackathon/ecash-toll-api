package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.models.Price;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface PriceService {
    public List<Price> getPrices();
    public List<Price> getPriceById(int id);
}
