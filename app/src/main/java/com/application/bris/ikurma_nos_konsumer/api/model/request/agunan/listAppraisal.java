package com.application.bris.ikurma_nos_konsumer.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class listAppraisal {
    @SerializedName("uid")
    private String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }
}
