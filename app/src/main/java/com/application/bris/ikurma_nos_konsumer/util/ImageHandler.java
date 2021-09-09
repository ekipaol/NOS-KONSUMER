package com.application.bris.ikurma_nos_konsumer.util;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Credit vale
 */

public class ImageHandler {
    private static int compressQuality = 88;

    public static void saveImageToCache(Context context, Bitmap bitmap, String image_name){
        int imageWidth = 100;
        int imageHeight = 100;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bytes);
        bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight,true);

        File destination = new File(context.getCacheDir(),image_name);

        FileOutputStream fo;
        try {
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
