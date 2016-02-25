package com.github.yafithekid.mandiri_ecash_api;

public class MEAURLFactory {
    public static final String BASE_URL
            = "https://api.apim.ibmcloud.com/ex-icha-fmeirisidibmcom-ecash-be/sb/emoney/v1";
    /**
     * login
     * @param uid //TODO buat apaan
     * @param msisdn phone number
     * @param credentials password
     * @return url
     */
    public String login(String uid,String msisdn,String credentials){
        return String.format("%s/%s?msisdn=%s&uid=%s&credentials=%s",BASE_URL,"loginMember",msisdn,uid,credentials);
    }

    /**
     * logout
     * @param token auth token
     * @param msisdn phone number
     * @return url
     */
    public String logout(String token,String msisdn){
        return String.format("%s/%s?msisdn=%s&token=%s",BASE_URL,"logoutMember",msisdn,token);
    }

    /**
     * account history
     * @param pageSize pagesize
     * @param token auth token
     * @param msisdn phone no
     * @param onPage current page displayed
     * @return url
     */
    public String accountHistory(int pageSize,String token,String msisdn,int onPage){
        return String.format("%s/%s?msisdn=%s&onpage=%s&pagesize=%s&token=%s",BASE_URL,"accountHistory",msisdn,onPage,pageSize,token);
    }

    /**
     * balance Inquiry
     * @param token auth token
     * @param msisdn phone number
     * @return url
     */
    public String balanceInquiry(String token,String msisdn){
        return String.format("%s/%s?token=%s&msisdn=%s",BASE_URL,"balanceInquiry",token,msisdn);
    }

    /**
     * Transfer	Member Inquiry
     * @param msisdn phone number
     * @param to target phone number
     * @param amount amount
     * @param token auth token
     * @return url
     */
    public String transferMemberInquiry(String msisdn,String to,int amount,String token){
        return String.format("%s/%s?msisdn=%s&to=%s&amount=%s&token=%s",BASE_URL,"inquiryTransferMember",msisdn,to,amount,token);
    }

    public String transferMemberPayment(String from,String to,int amount,String description,String credentials,String token){
        return String.format("%s/%s?from=%s&to=%s&amount=%s&credentials=%s&description=%s&token=%s",
                BASE_URL,"transferMember",from,to,amount,credentials,description,token);
    }

    public String onStorePurchase(int amount, String token, String msisdn, String credentials){
        return String.format("%s/%s?msisdn=%s&credentials=%s&amount=%s&token=%s",BASE_URL,"purchase",msisdn,credentials,amount,token);
    }
}
