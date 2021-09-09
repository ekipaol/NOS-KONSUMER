package com.application.bris.ikurma_nos_konsumer.api.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by PID on 4/6/2019.
 */

public class ParseResponseError {
    public static Error confirmEror(ResponseBody responseBody){
        Gson gson = new GsonBuilder().create();
        Error error = new Error();
        try {
            error = gson.fromJson(responseBody.string(), Error.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }

    public static ParseResponseGmapsV3 errorGeocoding(ResponseBody responseBody){
        Gson gson = new GsonBuilder().create();
        ParseResponseGmapsV3 error = new ParseResponseGmapsV3();
        try {
            error = gson.fromJson(responseBody.string(), ParseResponseGmapsV3.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }
}
