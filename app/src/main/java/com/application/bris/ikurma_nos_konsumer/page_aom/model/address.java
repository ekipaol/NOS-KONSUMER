package com.application.bris.ikurma_nos_konsumer.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 07/05/2019.
 */

public class address {
    @SerializedName("PROPINSI")
    private String provinsi;
    @SerializedName("KOTA")
    private String kota;
    @SerializedName("KECAMATAN")
    private String kecamatan;
    @SerializedName("KELURAHAN")
    private String kelurahan;
    @SerializedName("KODE_POS")
    private String kodepos;

    public address(String provinsi, String kota, String kecamatan, String kelurahan, String kodepos) {
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.kodepos = kodepos;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }
}
