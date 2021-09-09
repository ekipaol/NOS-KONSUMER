package com.application.bris.ikurma_nos_konsumer.baseapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by PID on 02/06/2019.
 * Credit Vale.
 */

public class RouteApp {
    private Context mContext;
    private Intent mIntent;

    public RouteApp(Context context) {
        this.mContext = context;
    }

    public void openActivity(Class<?> nextClass) {
        try {
            mIntent = new Intent(mContext, nextClass);
            mContext.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openActivity(Class<?> nextClass, Bundle mbundle) {
        try {
            mIntent = new Intent(mContext, nextClass);
            mIntent.putExtras(mbundle);
            mContext.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openActivityAndClearAllPrevious(Class<?> nextClass) {
        try {
            mIntent = new Intent(mContext, nextClass);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(mIntent);
            ((AppCompatActivity) mContext).finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openActivityWithDataAndClearAllPrevious(Class<?> nextClass, Bundle mbundle) {
        try {
            mIntent = new Intent(mContext, nextClass);
            mIntent.putExtras(mbundle);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(mIntent);
            ((AppCompatActivity) mContext).finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}