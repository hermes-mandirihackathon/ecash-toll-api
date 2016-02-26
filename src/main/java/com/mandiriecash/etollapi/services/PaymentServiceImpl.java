package com.mandiriecash.etollapi.services;

import com.github.yafithekid.mandiri_ecash_api.client.MEASyncRESTClient;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberPaymentRequest;
import com.github.yafithekid.mandiri_ecash_api.responses.MEATransferMemberPaymentResponse;
import com.mandiriecash.etollapi.exceptions.PaymentErrorException;
import org.springframework.beans.factory.annotation.Autowired;


public class PaymentServiceImpl implements PaymentService {
    public static final String TARGET_PAYMENT_PHONE_NUMBER = "085729592442";

    @Autowired
    MEASyncRESTClient meaSyncRESTClient;

    public void payToll(String msisdn, String credentials, String token, int activityId,int sourceTollId, int destTollId,
                        int price,String plateNo) throws PaymentErrorException {
        String desc = (new ECashTollDescription(activityId,sourceTollId,destTollId,plateNo)).toString();
        MEATransferMemberPaymentRequest request = new MEATransferMemberPaymentRequest.Builder()
                .amount(price)
                .token(token)
                .credentials(credentials)
                .from(msisdn)
                .to(TARGET_PAYMENT_PHONE_NUMBER)
                .description(desc)
                .build();
        try {
            MEATransferMemberPaymentResponse response = meaSyncRESTClient.transferMemberPayment(request);
            //TODO sure the result from server is ok string?
            if (!response.getStatus().equalsIgnoreCase("processed")){
                throw new PaymentErrorException("Unknown error : "+ response.getStatus());
            }
        } catch (MEAIOException e) {
            e.printStackTrace();
            throw new PaymentErrorException(e);
        } catch (MEATokenExpiredException e) {
            throw new PaymentErrorException(e);
        } catch (MEAHttpException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void payToll(String msisdn, String credentials, String token, int sourceTollId, int destTollId, int price) throws PaymentErrorException {
        String desc = (new ECashTollGTODescription(sourceTollId,destTollId)).toString();
        MEATransferMemberPaymentRequest request = new MEATransferMemberPaymentRequest.Builder()
                .amount(price)
                .token(token)
                .credentials(credentials)
                .from(msisdn)
                .to(TARGET_PAYMENT_PHONE_NUMBER)
                .description(desc)
                .build();
        try {
            MEATransferMemberPaymentResponse response = meaSyncRESTClient.transferMemberPayment(request);
            //TODO sure the result from server is ok string?
            if (!response.getStatus().equalsIgnoreCase("processed")){
                throw new PaymentErrorException("Unknown error : "+ response.getStatus());
            }
        } catch (MEAIOException e) {
            e.printStackTrace();
            throw new PaymentErrorException(e);
        } catch (MEATokenExpiredException e) {
            throw new PaymentErrorException(e);
        } catch (MEAHttpException e) {
            e.printStackTrace();
        }
    }
}
