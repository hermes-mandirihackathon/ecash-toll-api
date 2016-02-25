package com.mandiriecash.etollapi.controllers;

import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClient;
import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClientImpl;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.github.yafithekid.mandiri_ecash_api.responses.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
/**
 * Controller for testing mandiri ecash API
 */
public class TestController {
    MEASyncRESTClient meaSyncRESTClient = new MEASyncRESTClientImpl();

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public @ResponseBody MEALoginResponse login(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="uid") String uid,
            @RequestParam(name="credentials") String credentials
            ) throws MEAIOException, MEAHttpException {
        MEALoginRequest loginRequest = (new MEALoginRequest.Builder())
                .msisdn(msisdn)
                .uid(uid)
                .credentials(credentials)
                .build();
        return meaSyncRESTClient.login(loginRequest);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public @ResponseBody MEALogoutResponse logout(
        @RequestParam(name="msisdn") String msisdn,
        @RequestParam(name="token") String token
    ) throws MEAIOException, MEAHttpException {
        MEALogoutRequest logoutRequest = new MEALogoutRequest.Builder()
            .msisdn(msisdn)
            .token(token)
            .build();
        return meaSyncRESTClient.logout(logoutRequest);
    }

    @RequestMapping(value="/balanceInquiry",method = RequestMethod.GET)
    public @ResponseBody MEABalanceInquiryResponse balanceInquiry(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token
    ) throws MEAIOException, MEATokenExpiredException, MEAHttpException {
        MEABalanceInquiryRequest request = (new MEABalanceInquiryRequest.Builder())
                .msisdn(msisdn)
                .token(token)
                .build();
        return meaSyncRESTClient.balanceInquiry(request);
    }

    @RequestMapping(value="/accountHistory",method = RequestMethod.GET)
    public @ResponseBody MEAAccountHistoryResponse accountHistory(
            @RequestParam(name="msisdn") String msisdn,
            @RequestParam(name="token") String token
    ) throws MEAIOException, MEATokenExpiredException, MEAHttpException {
        MEAAccountHistoryRequest request = (new MEAAccountHistoryRequest.Builder())
                .msisdn(msisdn)
                .token(token)
                .build();
        return meaSyncRESTClient.accountHistory(request);
    }

    @RequestMapping(value="/transferMemberInquiry",method=RequestMethod.GET)
    public @ResponseBody MEATransferMemberInquiryResponse transferMemberInquiry(
        @RequestParam(name="amount") int amount,
        @RequestParam(name="msisdn") String msisdn,
        @RequestParam(name="to") String to,
        @RequestParam(name="token") String token
    ) throws MEAIOException, MEATokenExpiredException, MEAHttpException {
        MEATransferMemberInquiryRequest request = new MEATransferMemberInquiryRequest.Builder()
            .amount(amount)
            .msisdn(msisdn)
            .to(to)
            .token(token)
            .build();
        return meaSyncRESTClient.transferMemberInquiry(request);
    }

    @RequestMapping(value="/transferMemberPayment",method=RequestMethod.GET)
    public @ResponseBody MEATransferMemberPaymentResponse transferMemberPayment(
        @RequestParam(name="amount") int amount,
        @RequestParam(name="credentials") String credentials,
        @RequestParam(name="description") String description,
        @RequestParam(name="from") String from,
        @RequestParam(name="to") String to,
        @RequestParam(name="token") String token
    ) throws MEAIOException, MEATokenExpiredException, MEAHttpException {
        MEATransferMemberPaymentRequest request = new MEATransferMemberPaymentRequest.Builder()
            .amount(amount)
            .credentials(credentials)
            .description(description)
            .from(from)
            .to(to)
            .token(token)
            .build();
        return meaSyncRESTClient.transferMemberPayment(request);
    }

    @RequestMapping(value="/onStorePurchase",method = RequestMethod.GET)
    public @ResponseBody  MEAOnStorePurchaseResponse onStorePurchase(
        @RequestParam(name="amount") int amount,
        @RequestParam(name="credentials") String credentials,
        @RequestParam(name="msisdn") String msisdn,
        @RequestParam(name="token") String token
    ) throws MEAIOException, MEATokenExpiredException, MEAHttpException {
        MEAOnStorePurchaseRequest request = new MEAOnStorePurchaseRequest.Builder()
            .amount(amount)
            .credentials(credentials)
            .msisdn(msisdn)
            .token(token)
            .build();
        return meaSyncRESTClient.onStorePurchaseRequest(request);
    }
}
