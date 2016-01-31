package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.Toll;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
public interface TollDAO {
    public List<Toll> getTolls();
}
