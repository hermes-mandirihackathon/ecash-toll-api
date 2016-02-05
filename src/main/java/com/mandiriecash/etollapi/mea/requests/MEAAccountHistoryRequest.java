package com.mandiriecash.etollapi.mea.requests;

public class MEAAccountHistoryRequest {
    String msisdn;
    String token;
    int onpage;
    int pagesize;

    public MEAAccountHistoryRequest(){
        this.onpage = 0;
        this.pagesize = 5;
    }

    public static class Builder {
        MEAAccountHistoryRequest request = new MEAAccountHistoryRequest();

        public Builder msisdn(String msisdn){ request.setMsisdn(msisdn); return this; }

        public Builder token(String token){ request.setToken(token); return this; }

        public Builder onpage(int onpage){ request.setOnpage(onpage); return this;}

        public MEAAccountHistoryRequest build(){ return request; }
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOnpage() {
        return onpage;
    }

    public void setOnpage(int onpage) {
        this.onpage = onpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
