package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Price;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
public interface PriceDAO {
    public List<Price> getPrices();
    public Price getPriceById(int id);
}
