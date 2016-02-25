package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClient;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.responses.MEABalanceInquiryResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEALoginResponse;
import com.mandiriecash.etollapi.dao.UserDAO;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.mandiriecash.etollapi.exceptions.UserNotFoundException;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.exceptions.MEAUnknownErrorException;
import com.mandiriecash.etollapi.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throws MEAIOException, MEALoginFailedException, MEAHttpException {
        User user;
        String token;
        try {
            user = getUserByMsisdn(msisdn);
        } catch (UserNotFoundException e) {
            System.out.println("user not exist");
            user = new User();
            user.setMsisdn(msisdn);
            user.setCredentials(credentials);
            user.setToken("");
            createUser(user);
        }
        token = user.getToken();
        //kalo token null, atau password baru panggil mandiri api
        if (token == null || token.equals("") || user.getCredentials() == null || user.getCredentials().equals("")){
            token = refreshUserTokenAndPassword(uid, user,credentials);
        }
        return token;
    }

    @Override
    public String refreshUserTokenAndPassword(String uid, User user, String newPassword) throws MEAIOException, MEAHttpException, MEALoginFailedException {
        MEALoginRequest request = (new MEALoginRequest.Builder())
                .uid(uid)
                .msisdn(user.getMsisdn())
                .credentials(newPassword)
                .build();
        MEALoginResponse meaLoginResponse = meaSyncRESTClient.login(request);
        if (meaLoginResponse.getStatus().equals(MEALoginResponse.LOGIN_FAILED)){
            throw new MEALoginFailedException("Invalid username");
        } else if (meaLoginResponse.getStatus().equals(MEALoginResponse.BLOCKED)){
            throw new MEALoginFailedException(user.getMsisdn() + " blocked");
        } else if (meaLoginResponse.getToken() == null || meaLoginResponse.getToken().isEmpty()){
            throw new MEALoginFailedException("Invalid password");
        }
        //update user password and token
        user.setCredentials(newPassword);
        user.setToken(meaLoginResponse.getToken());
        userDAO.updateUser(user);
        return meaLoginResponse.getToken();
    }


    public MEABalanceInquiryResponse balanceInquiry(String token, String msisdn)
            throws MEAIOException, MEAUnknownErrorException, MEATokenExpiredException, MEAHttpException {
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