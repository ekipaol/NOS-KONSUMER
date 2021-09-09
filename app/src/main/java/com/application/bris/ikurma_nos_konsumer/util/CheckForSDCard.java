package com.application.bris.ikurma_nos_konsumer.util;

/**
 * Created by idong on 02/08/2018.
 */
import android.os.Environment;

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
