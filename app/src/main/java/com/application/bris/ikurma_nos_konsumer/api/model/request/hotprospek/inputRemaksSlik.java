package com.application.bris.ikurma_nos_konsumer.api.model.request.hotprospek;

import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by idong on 16/09/2019.
 */

public class inputRemaksSlik {
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("dtMemoSales")
    private List<ModelRemaksSlik> dtMemoSales = null;
    @SerializedName("dtMemoSalesPasangan")
    private List<ModelRemaksSlik> dtMemoSalesPasangan = null;

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public List<ModelRemaksSlik> getDtMemoSales() {
        return dtMemoSales;
    }

    public void setDtMemoSales(List<ModelRemaksSlik> dtMemoSales) {
        this.dtMemoSales = dtMemoSales;
    }

    public List<ModelRemaksSlik> getDtMemoSalesPasangan() {
        return dtMemoSalesPasangan;
    }

    public void setDtMemoSalesPasangan(List<ModelRemaksSlik> dtMemoSalesPasangan) {
        this.dtMemoSalesPasangan = dtMemoSalesPasangan;
    }
}
