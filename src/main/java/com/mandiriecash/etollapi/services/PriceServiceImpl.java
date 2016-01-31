package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.PriceDAO;
import com.mandiriecash.etollapi.models.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDAO priceDAO;

    public List<Price> getPrices() {
        return priceDAO.getPrices();
    }

    public List<Price> getPriceById(int id) {
        List prices = new ArrayList<Price>();
        prices.add(priceDAO.getPriceById(id));
        return prices;
    }
}
