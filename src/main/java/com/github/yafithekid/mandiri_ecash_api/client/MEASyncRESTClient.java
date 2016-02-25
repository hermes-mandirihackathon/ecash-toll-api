package com.github.yafithekid.mandiri_ecash_api.client;


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

public interface MEASyncRESTClient {
    MEALoginResponse login(MEALoginRequest meaLoginRequest)
            throws MEAIOException,MEAHttpException;

    MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest)
            throws MEAIOException,MEAHttpException;

    MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;
}
