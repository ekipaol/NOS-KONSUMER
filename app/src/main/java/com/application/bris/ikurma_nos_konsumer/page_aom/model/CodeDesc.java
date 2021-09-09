package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 08/05/2019.
 */

public class CodeDesc {
    @SerializedName("DESC1")
    private String desc1;
    @SerializedName("DESC2")
    private String desc2;

    public CodeDesc(String desc1, String desc2) {
        this.desc1 = desc1;
        this.desc2 = desc2;
    }

    public String getDesc1() {
        return desc1;
    }

    public String getDesc2() {
        return desc2;
    }
}
