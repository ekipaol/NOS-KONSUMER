package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReqDataPribadiLainya {
    @SerializedName("data_pribadi_ext")
    @Expose
    private DataPribadiExt DataPribadiExt;
    @SerializedName("document_umum_list")
    @Expose
    private List<UploadDokumenTambahan> UploadDokumenTambahan;

    public com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPribadiExt getDataPribadiExt() {
        return DataPribadiExt;
    }

    public void setDataPribadiExt(com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.DataPribadiExt dataPribadiExt) {
        DataPribadiExt = dataPribadiExt;
    }

    public List<com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumenTambahan> getUploadDokumenTambahan() {
        return UploadDokumenTambahan;
    }

    public void setUploadDokumenTambahan(List<com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.UploadDokumenTambahan> uploadDokumenTambahan) {
        UploadDokumenTambahan = uploadDokumenTambahan;
    }
}
