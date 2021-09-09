package com.application.bris.ikurma_nos_konsumer.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.Constants;
import com.application.bris.ikurma_nos_konsumer.util.magiccrypt.MagicCrypt;

/**
 * Created by PID on 06/04/2019.
 */

public class AppPreferences {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor spEditor;
    private Context mContext;

    private MagicCrypt magicCrypt = new MagicCrypt();

    public AppPreferences(Context context) {
        this.mContext = context;
        sharedPref = mContext.getSharedPreferences(Constants.Preferences.MAIN_CONFIG, Context.MODE_PRIVATE);
        spEditor = sharedPref.edit();
    }

    public void setRoleType (String data){
        spEditor.putString(Constants.Login.ROLE_TYPE, data);
        spEditor.commit();
    }

    public int getRoleType(){
        String data = AppUtil.decrypt(sharedPref.getString(Constants.Login.ROLE_TYPE, "0"));
        return AppUtil.parseIntWithDefault(data, 0);

    }

    public void setJabatan (String data){
        spEditor.putString(Constants.Login.JABATAN, data);
        spEditor.commit();
    }

    public String getJabatan (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.JABATAN, "jabatan"));
    }

    public void setNamaKantor (String data){
        spEditor.putString(Constants.Login.NAMA_KANTOR, data);
        spEditor.commit();
    }

    public String getNamaKantor (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.NAMA_KANTOR, "nama_kantor"));
    }

    public void setKodeKantor (String data){
        spEditor.putString(Constants.Login.KODE_KANTOR, data);
        spEditor.commit();
    }

    public String getKodeKantor (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.KODE_KANTOR, "kode_kantor"));
    }

    public void setNamaKanwil (String data){
        spEditor.putString(Constants.Login.NAMA_KANWIL, data);
        spEditor.commit();
    }

    public String getNamaKanwil (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.NAMA_KANWIL, "nama_kanwil"));
    }

    public void setKodeKanwil (String data){
        spEditor.putString(Constants.Login.KODE_KANWIL, data);
        spEditor.commit();
    }

    public String getKodeKanwil (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.KODE_KANWIL, "kode_kanwil"));
    }

    public void setFidRole (String data){
        spEditor.putString(Constants.Login.FID_ROLE, data);
        spEditor.commit();
    }

    public int getFidRole(){
        String data = AppUtil.decrypt(sharedPref.getString(Constants.Login.FID_ROLE, "0"));
        return AppUtil.parseIntWithDefault(data, 0);

    }

    public void setNik (String data){
        spEditor.putString(Constants.Login.NIK, data);
        spEditor.commit();
    }

    public String getNik (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.NIK, "nik"));
    }

    public void setNama (String data){
        spEditor.putString(Constants.Login.NAMA, data);
        spEditor.commit();
    }

    public String getNama (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.NAMA, "nama"));
    }

    public void setKodeAo (String data){
        spEditor.putString(Constants.Login.KODE_AO, data);
        spEditor.commit();
    }

    public String getKodeAo (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.KODE_AO, "kode_ao"));
    }

    public void setCbAmanah (String data){
        spEditor.putString(Constants.Login.CB_AMANAH, data);
        spEditor.commit();
    }

    public String getCbAmanah (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.CB_AMANAH, "false"));
    }

    public void setToken (String token){
        spEditor.putString(Constants.Login.TOKEN, token);
        spEditor.commit();
    }

    public String getToken (){
        return sharedPref.getString(Constants.Login.TOKEN, "token");
    }


    public void setUidAppraisal (String status){
        spEditor.putString(Constants.Preferences.UID_APPRAISAL, status);
        spEditor.commit();
    }

    public String getUidAppraisal (){
        return sharedPref.getString(Constants.Preferences.UID_APPRAISAL, "");
    }

    public void setNamaAppraisal (String status){
        spEditor.putString(Constants.Preferences.NAMA_APPRAISAL, status);
        spEditor.commit();
    }

    public String getNamaAppraisal (){
        return sharedPref.getString(Constants.Preferences.NAMA_APPRAISAL, "");
    }

    public void setStatusAppraise (String status){
        spEditor.putString(Constants.Preferences.STATUS_APPRAISE, status);
        spEditor.commit();
    }

    public String getStatusAppraise (){
        return sharedPref.getString(Constants.Preferences.STATUS_APPRAISE, "");
    }

    public void setStatusAplikasiAgunan (String status){
        spEditor.putString(Constants.Preferences.STATUS_APLIKASI_AGUNAN, status);
        spEditor.commit();
    }

    public String getStatusAplikasiAgunan (){
        return sharedPref.getString(Constants.Preferences.STATUS_APLIKASI_AGUNAN, "status");
    }

    public void setUid(String uid){
        spEditor.putString(Constants.Login.UID, uid);
        spEditor.commit();
    }

    public int getUid(){
        String data = AppUtil.decrypt(sharedPref.getString(Constants.Login.UID, "0"));
        return AppUtil.parseIntWithDefault(data, 0);
    }


    public void setKodeCabang (String data){
        spEditor.putString(Constants.Login.KODE_CABANG, data);
        spEditor.commit();
    }

    public String getKodeCabang (){
        return AppUtil.decrypt(sharedPref.getString(Constants.Login.KODE_CABANG, "kode_cabang"));
    }


    public void setRememberMe(String flag){
        spEditor.putString(Constants.Preferences.REMEMBER_ME, flag);
        spEditor.commit();
    }

    public String getRembemberMe(){
        return sharedPref.getString(Constants.Preferences.REMEMBER_ME, "");
    }

    public void setUsername(String username){
        spEditor.putString(Constants.Preferences.USERNAME, username);
        spEditor.commit();
    }

    public String getUsername(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.USERNAME, ""));
    }

    public void setNamaPengguna(String nama){
        spEditor.putString(Constants.Preferences.NAMA_PENGGUNA, nama);
        spEditor.commit();
    }

    public String getNamaPengguna(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.NAMA_PENGGUNA, ""));
    }

    public void setNotificationVersion(String versionNumber){
        spEditor.putString(Constants.Preferences.NOTIFICATION_VERSION, versionNumber);
        spEditor.commit();
    }

    public String getNotificationVersion(){
        return sharedPref.getString(Constants.Preferences.NOTIFICATION_VERSION, "0");
    }

    public void setUserId(String id){
        spEditor.putString(Constants.Preferences.USER_ID, id);
        spEditor.commit();
    }

    public String getUserId(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.USER_ID, ""));
    }

    public void setKodePdam(String kodePdam){
        spEditor.putString(Constants.Preferences.KODE_PDAM, kodePdam);
        spEditor.commit();
    }

    public String getKodePdam(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.KODE_PDAM, ""));
    }

    public void setFotoNasabahName(String filename){
        spEditor.putString(Constants.Preferences.FOTO_NASABAH_NAME, filename);
        spEditor.commit();
    }

    public String getFotoNasabahName(){
        return sharedPref.getString(Constants.Preferences.FOTO_NASABAH_NAME, "");
    }

    public void setFotoKelengkapanDokumen(String filename){
        spEditor.putString(Constants.Preferences.FOTO_KELENGKAPAN_DOKUMEN, filename);
        spEditor.commit();
    }

    public String getFotoKelengkapanDokumen(){
        return sharedPref.getString(Constants.Preferences.FOTO_KELENGKAPAN_DOKUMEN, "");
    }

    public void setFotoAgunan(String filename){
        spEditor.putString(Constants.Preferences.FOTO_AGUNAN, filename);
        spEditor.commit();
    }

    public String getFotoAgunan(){
        return sharedPref.getString(Constants.Preferences.FOTO_AGUNAN, "");
    }

    public void setIsActivated(String flag){
        spEditor.putString(Constants.Preferences.IS_ACTIVATED, flag);
        spEditor.commit();
    }

    public String getIsActivated(){
        return sharedPref.getString(Constants.Preferences.IS_ACTIVATED, "");
    }

    public void setImageProfilBase64(String data){
        spEditor.putString(Constants.Preferences.IMAGE_PROFIL, data);
        spEditor.commit();
    }

    public String getImageProfilBase64(){
        return sharedPref.getString(Constants.Preferences.IMAGE_PROFIL, "");
    }

    public void setFotoRumah(String filename){
        spEditor.putString(Constants.Preferences.FOTO_RUMAH, filename);
        spEditor.commit();
    }

    public String getFotoRumah(){
        return sharedPref.getString(Constants.Preferences.FOTO_RUMAH, "");
    }

    public void setFotoKantor(String filename){
        spEditor.putString(Constants.Preferences.FOTO_KANTOR, filename);
        spEditor.commit();
    }

    public String getFotoKantor(){
        return sharedPref.getString(Constants.Preferences.FOTO_KANTOR, "");
    }

    public void setUpdateNotification(String filename){
        spEditor.putString(Constants.Preferences.UPDATE_NOTIFICATION, filename);
        spEditor.commit();
    }

    public String isUpdateNotificationOn(){
        return sharedPref.getString(Constants.Preferences.UPDATE_NOTIFICATION, "true");
    }


    public static void clearAll(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.Preferences.MAIN_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public void removeByKey(String key) {
        sharedPref.edit().remove(key).commit();
    }
}
