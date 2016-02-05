package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.google.gson.Gson;
import com.mandiriecash.etollapi.dao.UserDAO;
import com.mandiriecash.etollapi.mea.exceptions.MEAIOException;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.MEALoginResponse;
import com.mandiriecash.etollapi.mea.exceptions.MEASyncRESTClient;
import com.mandiriecash.etollapi.mea.exceptions.MEASyncRESTClientImpl;
import com.mandiriecash.etollapi.mea.requests.MEALoginRequest;
import com.mandiriecash.etollapi.models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Gson gson;

    MEASyncRESTClient meaSyncRESTClient = new MEASyncRESTClientImpl();
    private OkHttpClient okHttpClient = new OkHttpClient();

    @Autowired
    private MEAURLFactory mandiriECashAPIURLFactory;

    public List<User> getUserById(int id) {
        // TODO Auto-generated method stub
        List users = new ArrayList<User>();
        User user = userDAO.getUserById(id);
        System.out.println(user.toString());
        users.add(user);
        return users;
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    /**
     * Call mandiri login API and return result
     * @param uid device id
     * @param msisdn phone number
     * @param credentials password
     * @return token
     */
    public String loginUser(String uid,String msisdn,String credentials)
            throws MEAIOException, MEALoginFailedException {
        MEALoginResponse meaLoginResponse = meaSyncRESTClient.loginMember((new MEALoginRequest.Builder())
                .uid(uid)
                .msisdn(msisdn)
                .credentials(credentials)
                .build());
        return meaLoginResponse.getToken();
    }

    public long balanceInquiry(User user, String token) {
        //tembak ke server mandiri hackathon
        return 0;
    }
}