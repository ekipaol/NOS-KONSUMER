package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqAcctNumber {
    @SerializedName("AccountNo")
    private String AccountNo;

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }
}
