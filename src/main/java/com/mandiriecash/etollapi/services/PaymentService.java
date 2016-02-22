package com.mandiriecash.etollapi.services;

import com.mandiriecash.etollapi.exceptions.PaymentErrorException;

public interface PaymentService {
    void payToll(String msisdn, String credentials, String token, int activityId, int sourceTollId, int destTollId,
                 int price, String plateNo) throws PaymentErrorException;

}
