package com.mandiriecash.etollapi.mea.client;

import com.mandiriecash.etollapi.mea.exceptions.MEAIOException;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.mea.requests.*;
import com.mandiriecash.etollapi.mea.responses.*;


public interface MEASyncRESTClient {
    public MEALoginResponse login(MEALoginRequest meaLoginRequest) throws MEAIOException, MEALoginFailedException;

    public MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest) throws MEAIOException;

    public MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request) throws MEAIOException;

    public MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request) throws MEAIOException;

    public MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest) throws MEAIOException;

    public MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request) throws MEAIOException;

    public MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request) throws MEAIOException;
}
