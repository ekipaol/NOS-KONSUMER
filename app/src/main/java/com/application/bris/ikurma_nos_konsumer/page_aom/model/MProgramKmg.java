package com.application.bris.ikurma_nos_konsumer.page_aom.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/26/2019.
 */

public class MProgramKmg {

    @SerializedName("GIMMICK_ID")
    private int GIMMICK_ID;
    @SerializedName("PROGRAM_NAME")
    private String PROGRAM_NAME;
    @SerializedName("LOAN_TYPE")
    private String LOAN_TYPE;

    public int getGIMMICK_ID() {
        return GIMMICK_ID;
    }

    public String getPROGRAM_NAME() {
        return PROGRAM_NAME;
    }

    public String getLOAN_TYPE() {
        return LOAN_TYPE;
    }
}
