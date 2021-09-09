package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class AgunanTanahKosongFront {

    @SerializedName("STATUS")
    private int STATUS;
    @SerializedName("KATEGORI")
    private String KATEGORI;
    @SerializedName("FID_AGUNAN")
    private String FID_AGUNAN;
    @SerializedName("NO_BUKTI_HAK")
    private String NO_BUKTI_HAK;
    @SerializedName("NAMA_PEMEGANG_HAK")
    private String NAMA_PEMEGANG_HAK;
    @SerializedName("NILAI_PASAR")
    private String NILAI_PASAR;
    @SerializedName("ID")
    private String ID;
    @SerializedName("JENIS_DOKUMEN")
    private String JENIS_DOKUMEN;
    @SerializedName("ALAMAT_JAMINAN")
    private String ALAMAT_JAMINAN;

    public int getSTATUS() {
        return STATUS;
    }

    public String getKATEGORI() {
        return KATEGORI;
    }

    public String getFID_AGUNAN() {
        return FID_AGUNAN;
    }

    public String getNO_BUKTI_HAK() {
        return NO_BUKTI_HAK;
    }

    public String getNAMA_PEMEGANG_HAK() {
        return NAMA_PEMEGANG_HAK;
    }

    public String getNILAI_PASAR() {
        return NILAI_PASAR;
    }

    public String getID() {
        return ID;
    }

    public String getJENIS_DOKUMEN() {
        return JENIS_DOKUMEN;
    }

    public String getALAMAT_JAMINAN() {
        return ALAMAT_JAMINAN;
    }
}
