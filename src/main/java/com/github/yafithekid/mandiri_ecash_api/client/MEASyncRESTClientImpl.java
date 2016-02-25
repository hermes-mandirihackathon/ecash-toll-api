package com.github.yafithekid.mandiri_ecash_api.client;

import com.github.yafithekid.mandiri_ecash_api.MEAURLFactory;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.requests.MEAAccountHistoryRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEABalanceInquiryRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEALoginRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEALogoutRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEAOnStorePurchaseRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberInquiryRequest;
import com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberPaymentRequest;
import com.github.yafithekid.mandiri_ecash_api.responses.MEAAccountHistoryResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEABalanceInquiryResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEALoginResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEALogoutResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEAOnStorePurchaseResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEATransferMemberInquiryResponse;
import com.github.yafithekid.mandiri_ecash_api.responses.MEATransferMemberPaymentResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MEASyncRESTClientImpl implements MEASyncRESTClient {
    public final static String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    OkHttpClient okHttpClient = new OkHttpClient();
    MEAURLFactory meaurlFactory = new MEAURLFactory();
    private Gson gson = new Gson();

    @Override
    public MEALoginResponse login(MEALoginRequest meaLoginRequest) throws MEAIOException,MEAHttpException {
        Response response;
        try {
            String url = meaurlFactory.login(meaLoginRequest.getUid(),meaLoginRequest.getMsisdn(),meaLoginRequest.getCredentials());
            System.out.println(url);
            response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(url)
                            .build()).
                    execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            return gson.fromJson(responseBody, MEALoginResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest) throws MEAIOException, MEAHttpException {
        try {
            String url = meaurlFactory.logout(meaLogoutRequest.getToken(), meaLogoutRequest.getMsisdn());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            return gson.fromJson(responseBody, MEALogoutResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            String url = meaurlFactory.transferMemberInquiry(
                    request.getMsisdn(), request.getTo(), request.getAmount(), request.getToken());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            MEATransferMemberInquiryResponse meaResponse = gson.fromJson(responseBody, MEATransferMemberInquiryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            String url = meaurlFactory.transferMemberPayment(
                    request.getFrom(),request.getTo(),request.getAmount(),
                    request.getDescription(),request.getCredentials(),request.getToken());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            MEATransferMemberPaymentResponse meaResponse = gson.fromJson(responseBody, MEATransferMemberPaymentResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            String url = meaurlFactory.balanceInquiry(meaRequest.getToken(),meaRequest.getMsisdn());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            MEABalanceInquiryResponse meaResponse = gson.fromJson(responseBody, MEABalanceInquiryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            String url = meaurlFactory.accountHistory(request.getPagesize(),request.getToken(),
                    request.getMsisdn(),request.getOnpage());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            MEAAccountHistoryResponse meaResponse = gson.fromJson(responseBody, MEAAccountHistoryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            String url = meaurlFactory.onStorePurchase(request.getAmount(),request.getToken(),
                    request.getMsisdn(),request.getCredentials());
            System.out.println(url);
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(url)
                    .build()
            ).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),responseBody);
            }
            MEAOnStorePurchaseResponse meaResponse = gson.fromJson(responseBody,MEAOnStorePurchaseResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }

    }

}
