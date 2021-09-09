package com.application.bris.ikurma_nos_konsumer.api.model.request.pipeline;

import com.google.gson.annotations.SerializedName;

public class inquiryListKateg {
    @SerializedName("loan_type")
    private String loan_type;

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }
}
