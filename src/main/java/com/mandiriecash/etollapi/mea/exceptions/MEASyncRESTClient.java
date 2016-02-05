package com.mandiriecash.etollapi.mea.exceptions;

import com.mandiriecash.etollapi.mea.MEALoginResponse;
import com.mandiriecash.etollapi.mea.requests.MEALoginRequest;
import okhttp3.OkHttpClient;

public interface MEASyncRESTClient {
    public MEALoginResponse loginMember(MEALoginRequest meaLoginRequest) throws MEAIOException, MEALoginFailedException;
}
