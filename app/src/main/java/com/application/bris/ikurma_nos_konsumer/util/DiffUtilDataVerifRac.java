package com.application.bris.ikurma_nos_konsumer.util;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.application.bris.ikurma_nos_konsumer.page_aom.model.DataVerifikasiRac;

import java.util.List;

public class DiffUtilDataVerifRac extends DiffUtil.Callback{

    List<DataVerifikasiRac> oldData;
    List<DataVerifikasiRac> newData;

    public DiffUtilDataVerifRac(List<DataVerifikasiRac> newData, List<DataVerifikasiRac> oldData) {
        this.newData = newData;
        this.oldData = oldData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getNamaRac() == newData.get(newItemPosition).getNamaRac();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}