package com.application.bris.ikurma_nos_konsumer.util;

/**
 * Created by idong on 29/05/2019.
 */

public class Validator {
    public static boolean validateKtp(String data){
        if (data.length() == 0 || data.isEmpty() || data.equalsIgnoreCase("")){
            return true;
        }

        if (data.length() > 0 && data.length() < 16 ){
            return false;
        }
        String str = data.substring(6, 12);
        int tgl = Integer.parseInt(str.substring(0, 2));
        int bln = Integer.parseInt(str.substring(2, 4));
        int thn = Integer.parseInt(str.substring(4, 6));
        if (tgl > 40){
            tgl = tgl - 40;
            if (tgl > 31){
                return false;
            }
        }
        if (bln > 12){
            return false;
        }
        return true;
    }

    public static boolean validateKtpRequired(String data){
        if (data.length() == 0 || data.isEmpty() || data.equalsIgnoreCase("")){
            return false;
        }

        if (data.length() > 0 && data.length() < 16 ){
            return false;
        }
        String str = data.substring(6, 12);
        int tgl = Integer.parseInt(str.substring(0, 2));
        int bln = Integer.parseInt(str.substring(2, 4));
        int thn = Integer.parseInt(str.substring(4, 6));
        if (tgl > 40){
            tgl = tgl - 40;
            if (tgl > 31){
                return false;
            }
        }
        if (bln > 12){
            return false;
        }
        return true;
    }

    public static boolean validateNpwpRequired(boolean isMust, String data){
        if (isMust)
        {
            if (data.length() == 0 || data.isEmpty() || data.equalsIgnoreCase("")){
                return false;
            }

            else if (data.length() > 0 && data.length() < 20 ){
                return false;
            }

            else if (data.charAt(2) != '.' || data.charAt(6) != '.' || data.charAt(10) != '.' || data.charAt(12) != '-' || data.charAt(16) != '.')
            {
                return false;
            }
            return true;
        }
        else{
            if (data.length() == 0 || data.isEmpty() || data.equalsIgnoreCase("")){
                return true;
            }
            else{
                if (data.length() > 0 && data.length() < 20 ){
                    return false;
                }
                else if (data.charAt(2) != '.' || data.charAt(6) != '.' || data.charAt(10) != '.' || data.charAt(12) != '-' || data.charAt(16) != '.')
                {
                    return false;
                }
                return true;
            }
        }
    }

    public static boolean validateNomorHp(String data){
        if (data.length() < 10){
            return false;
        }
        String prefix = data.substring(0, 2);
        if (!prefix.equalsIgnoreCase("08")){
            return false;
        }
        return true;
    }
}
