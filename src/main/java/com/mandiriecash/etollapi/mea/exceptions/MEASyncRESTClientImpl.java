package com.mandiriecash.etollapi.mea.exceptions;

import com.google.gson.Gson;
import com.mandiriecash.etollapi.mea.MEALoginResponse;
import com.mandiriecash.etollapi.mea.requests.MEALoginRequest;
import com.mandiriecash.etollapi.services.MEAURLFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MEASyncRESTClientImpl implements MEASyncRESTClient{
    OkHttpClient okHttpClient = new OkHttpClient();
    MEAURLFactory meaurlFactory = new MEAURLFactory();
    private Gson gson = new Gson();

    public MEALoginResponse loginMember(MEALoginRequest meaLoginRequest) throws MEAIOException, MEALoginFailedException {
        Response response;
        try {
            response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(meaurlFactory.login(meaLoginRequest.getUid(),meaLoginRequest.getMsisdn(),meaLoginRequest.getCredentials()))
                            .build()).
                    execute();
            MEALoginResponse loginResponse = gson.fromJson(response.body().charStream(), MEALoginResponse.class);
            if (loginResponse.getStatus().equals(MEALoginResponse.LOGIN_FAILED)){
                //TODO error yang dikasih si mandiri ecash API ini gak jelas.
                throw new MEALoginFailedException("Invalid username");
            } else if (loginResponse.getToken().isEmpty()) {
                throw new MEALoginFailedException("Invalid password");
            } else {
                return loginResponse;
            }
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }
}
