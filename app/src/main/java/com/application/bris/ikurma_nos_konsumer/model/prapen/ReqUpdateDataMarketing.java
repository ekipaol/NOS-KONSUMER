package com.application.bris.ikurma_nos_konsumer.model.prapen;

import com.google.gson.annotations.SerializedName;

public class ReqUpdateDataMarketing {
    @SerializedName("DataMitraFronting")
    private DataMarketing DataMitraFronting;

    public DataMarketing getDataMitraFronting() {
        return DataMitraFronting;
    }

    public void setDataMitraFronting(DataMarketing dataMitraFronting) {
        DataMitraFronting = dataMitraFronting;
    }
}
