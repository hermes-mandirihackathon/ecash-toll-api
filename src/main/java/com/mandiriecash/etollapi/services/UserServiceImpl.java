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
import com.mandiriecash.etollapi.exceptions.UserNotFoundException;
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
    private MEASyncRESTClient meaSyncRESTClient;

    public User getUserById(int id) throws UserNotFoundException {
        User user = userDAO.getUserById(id);
        if (user == null) throw new UserNotFoundException(id);
        return user;
    }

    @Override
    public User getUserByMsisdn(String msisdn) throws UserNotFoundException {
        User user = userDAO.getUserByMsisdn(msisdn);
        if (user == null) throw new UserNotFoundException(msisdn);
        return user;
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
        System.out.println("sampe sini + token : " + token);
        try {
            this.getUserByMsisdn(msisdn);
        } catch (UserNotFoundException e) {
            System.out.println("user not exist");
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