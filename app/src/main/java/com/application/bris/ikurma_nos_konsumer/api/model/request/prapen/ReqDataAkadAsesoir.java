package com.application.bris.ikurma_nos_konsumer.api.model.request.prapen;

import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqKendaraan;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqLainnya;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqTanah;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMmqTempatTinggal;
import com.application.bris.ikurma_nos_konsumer.model.prapen.DataMurabahah;
import com.google.gson.annotations.SerializedName;

public class ReqDataAkadAsesoir {
    @SerializedName("MMQAsetKendaraan")
    private DataMmqKendaraan mmqKendaraan;
    @SerializedName("MMQAsetLainnya")
    private DataMmqLainnya mmqLainnya;
    @SerializedName("MMQAsetTanah")
    private DataMmqTanah mmqTanah;
    @SerializedName("MMQAsetTempatTinggal")
    private DataMmqTempatTinggal mmqTempatTinggal;
    @SerializedName("MRBHIJRHAset")
    private DataMurabahah murabahah;
    @SerializedName("FotoAset")
    private UploadImage dokumenAset;

    public UploadImage getDokumenAset() {
        return dokumenAset;
    }

    public void setDokumenAset(UploadImage dokumenAset) {
        this.dokumenAset = dokumenAset;
    }

    public DataMmqKendaraan getMmqKendaraan() {
        return mmqKendaraan;
    }

    public void setMmqKendaraan(DataMmqKendaraan mmqKendaraan) {
        this.mmqKendaraan = mmqKendaraan;
    }

    public DataMmqLainnya getMmqLainnya() {
        return mmqLainnya;
    }

    public void setMmqLainnya(DataMmqLainnya mmqLainnya) {
        this.mmqLainnya = mmqLainnya;
    }

    public DataMmqTanah getMmqTanah() {
        return mmqTanah;
    }

    public void setMmqTanah(DataMmqTanah mmqTanah) {
        this.mmqTanah = mmqTanah;
    }

    public DataMmqTempatTinggal getMmqTempatTinggal() {
        return mmqTempatTinggal;
    }

    public void setMmqTempatTinggal(DataMmqTempatTinggal mmqTempatTinggal) {
        this.mmqTempatTinggal = mmqTempatTinggal;
    }

    public DataMurabahah getMurabahah() {
        return murabahah;
    }

    public void setMurabahah(DataMurabahah murabahah) {
        this.murabahah = murabahah;
    }
}
