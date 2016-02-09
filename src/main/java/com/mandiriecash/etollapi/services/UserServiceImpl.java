package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.google.gson.Gson;
import com.mandiriecash.etollapi.dao.UserDAO;
import com.mandiriecash.etollapi.mea.MEAURLFactory;
import com.mandiriecash.etollapi.mea.exceptions.MEAIOException;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.exceptions.MEAUnknownErrorException;
import com.mandiriecash.etollapi.mea.requests.MEABalanceInquiryRequest;
import com.mandiriecash.etollapi.mea.responses.MEABalanceInquiryResponse;
import com.mandiriecash.etollapi.mea.responses.MEALoginResponse;
import com.mandiriecash.etollapi.mea.client.MEASyncRESTClient;
import com.mandiriecash.etollapi.mea.client.MEASyncRESTClientImpl;
import com.mandiriecash.etollapi.mea.requests.MEALoginRequest;
import com.mandiriecash.etollapi.models.User;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
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
        //TODO Ichwan call database
        return meaLoginResponse.getToken();
    }

    public MEABalanceInquiryResponse balanceInquiry(String token,String msisdn)
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