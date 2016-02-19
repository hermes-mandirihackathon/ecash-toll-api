package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClient;
import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClientImpl;
import com.github.yafithekid.mandiri_ecash_api.responses.MEABalanceInquiryResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEALoginResponse;
import com.google.gson.Gson;
import com.mandiriecash.etollapi.dao.UserDAO;
import com.github.yafithekid.mandiri_ecash_api.MEAURLFactory;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.exceptions.MEAUnknownErrorException;
import com.mandiriecash.etollapi.models.User;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User getUserByMsisdn(String msisdn) {
        return userDAO.getUserByMsisdn(msisdn);
    }

    public void createUser(User user) {
       if(userDAO.getUserByMsisdn(user.getMsisdn()).getId() == 0){
           userDAO.createUser(user);
       }
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
        MEALoginResponse meaLoginResponse = meaSyncRESTClient.login((new MEALoginRequest.Builder())
                .uid(uid)
                .msisdn(msisdn)
                .credentials(credentials)
                .build());
        if (meaLoginResponse.getStatus().equals(MEALoginResponse.LOGIN_FAILED)){
            throw new MEALoginFailedException("Invalid username");
        } else if (meaLoginResponse.getStatus().equals(MEALoginResponse.BLOCKED)){
            throw new MEALoginFailedException(msisdn + " blocked");
        } else if (meaLoginResponse.getToken() == null || meaLoginResponse.getToken().isEmpty()){
            throw new MEALoginFailedException("Invalid password");
        }
        //TODO Ichwan call database (done)
        String token = meaLoginResponse.getToken();
        if(userDAO.getUserByMsisdn(msisdn).getId() == 0){
            //register
            User user = new User();
            user.setMsisdn(msisdn);
            user.setCredentials(credentials);
            user.setToken(token);
            createUser(user);
        }
        return token;
    }

    public MEABalanceInquiryResponse balanceInquiry(String token, String msisdn)
            throws MEAIOException, MEAUnknownErrorException {
        MEABalanceInquiryResponse response = meaSyncRESTClient.balanceInquiry((new MEABalanceInquiryRequest.Builder()
                .msisdn(msisdn)
                .token(token)
                .build()));
        if (!response.getStatus().equals(MEABalanceInquiryResponse.SUCCESS)) {
            throw new MEAUnknownErrorException();
        }
        return response;
    }
}