package com.mandiriecash.etollapi.services;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.mandiriecash.etollapi.exceptions.UserNotFoundException;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.exceptions.MEAUnknownErrorException;
import com.github.yafithekid.mandiri_ecash_api.responses.MEABalanceInquiryResponse;
import com.mandiriecash.etollapi.models.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */

public interface UserService {
    public User getUserById(int id) throws UserNotFoundException;
    public User getUserByMsisdn(String msisdn) throws UserNotFoundException;
    public void createUser(User user);
    public String loginUser(String uid,String msisdn,String credentials) throws IOException, MEALoginFailedException, MEAHttpException;
    public MEABalanceInquiryResponse balanceInquiry(String token, String msisdn)
            throws MEAIOException, MEAUnknownErrorException, MEATokenExpiredException, MEAHttpException;
}
