package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.dao.TollDAO;
import com.mandiriecash.etollapi.models.Toll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
@Service
@Transactional
public class TollServiceImpl implements TollService {
    @Autowired
    private TollDAO tollDAO;

    public List<Toll> getTolls() {
        return tollDAO.getTolls();
    }
}
