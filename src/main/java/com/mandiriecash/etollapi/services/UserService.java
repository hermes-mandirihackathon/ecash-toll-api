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

    /**
     * Ambil token dari database. Kalo gak ada, refresh token
     * @param uid
     * @param msisdn
     * @param credentials
     * @return
     * @throws IOException
     * @throws MEALoginFailedException
     * @throws MEAHttpException
     */
    public String loginUser(String uid,String msisdn,String credentials) throws IOException, MEALoginFailedException, MEAHttpException;

    /**
     * Call mandiri API and update the token in db. return the token
     * @param user
     * @param newPassword
     */
    String refreshUserTokenAndPassword(String uid, User user, String newPassword) throws MEAIOException, MEAHttpException, MEALoginFailedException;

    public MEABalanceInquiryResponse balanceInquiry(String token, String msisdn)
            throws MEAIOException, MEAUnknownErrorException, MEATokenExpiredException, MEAHttpException;
}
