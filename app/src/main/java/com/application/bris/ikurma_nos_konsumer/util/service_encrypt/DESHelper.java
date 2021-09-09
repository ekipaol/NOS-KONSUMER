package com.application.bris.ikurma_nos_konsumer.util.service_encrypt;

import android.util.Base64;

import com.application.bris.ikurma_nos_konsumer.BuildConfig;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESHelper {
    private String _secretKey;

    public DESHelper() {
        _secretKey = BuildConfig.SECRET_KEY;
    }

    public String encrypt(String message) throws Exception {

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digestOfPassword = md.digest(_secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = message.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
//                byte [] base64Bytes = Base64.encodeBase64(buf);
//                String base64EncryptedString = new String(base64Bytes);
            String base64EncryptedString;

            base64EncryptedString = android.util.Base64.encodeToString(buf, android.util.Base64.NO_WRAP);

            return base64EncryptedString;

        }
        catch(Exception e){
            return null;
        }
    }

    public String decrypt(String encryptedText) throws Exception {
        //byte[] message = Base64.decodeBase64(encryptedText.getBytes("utf-8"));
        byte[] message ;


        message = android.util.Base64.decode(encryptedText, Base64.NO_WRAP);

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digestOfPassword = md.digest(_secretKey.getBytes("utf-8"));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        SecretKey key = new SecretKeySpec(keyBytes, "DESede");

        Cipher decipher = Cipher.getInstance("DESede");
        decipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }
}