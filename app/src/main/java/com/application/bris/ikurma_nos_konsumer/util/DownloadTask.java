package com.application.bris.ikurma_nos_konsumer.util;

/**
 * Created by idong on 02/08/2018.
 */

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.prescreening.PrescreeningActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DownloadTask{
    private static final String TAG = "Download Task";
    private Context context;
    private String downloadFileName = "";
    private String sourceFrom = "";
    private byte[] data = null;
    File apkStorage = null;
    File SlikDirectory = null;
    public DownloadTask(Context context, byte[] data, String downloadFileName, String sourceFrom) {
        this.context = context;
        this.data = data;
        this.downloadFileName = downloadFileName;
        this.sourceFrom = sourceFrom;
        //Start Downloading Task
        new DownloadingTask().execute();
    }

    public void hideLoading(){
        switch (sourceFrom){
            case "hotprospek":
                ((PrescreeningActivity)context).loading.setVisibility(View.GONE);
                break;
            case "approved":
                ((PrescreeningActivity)context).loading.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> implements ConfirmListener {
        File outputFile = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    hideLoading();
                    AppUtil.showToastShort(context, "Download Berhasil, Silahkan buka folder Download/HasilSlik");
                    CustomDialog.DialogAction(context, "Buka folder Download Hasil SLIK sekarang?", this);






                } else {
                    hideLoading();
                    AppUtil.showToastShort(context, "Download Gagal");
                    Log.d("GAGAL DONWLOAD", "2");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (new CheckForSDCard().isSDCardPresent()) {
                    apkStorage = new File(
                            Environment.getExternalStorageDirectory().toString(), "Download/HasilSlik");
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();
                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }
                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File
//                outputFile = new File(apkStorage, "Test Extract");//Create Output file in Main File
                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location
                fos.write(data);
                fos.close();
                if (unzip(apkStorage.getCanonicalPath()+"/", downloadFileName)){
                    outputFile.delete();
                }

                Log.d("nikorang",downloadFileName.substring(14,30));
                Log.d("nikorang2",downloadFileName.substring(0,downloadFileName.length()-4));
                Log.d("nikorang2",downloadFileName);

                //buka otomatis PDFnya
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+downloadFileName.substring(0,downloadFileName.length()-4)+"/"+downloadFileName.substring(14,30)+".pdf");

                if (file.exists()) {

                    Log.d("diaeksis","file exis bro");

                    Intent intent ;
                    Uri pdfPath = Uri.fromFile(file);

                    //android nougat keatas harus pake fileprovider kalo mau buka buka file
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Log.d("diaeksis","file exis nougat");
                        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        context.startActivity(intent);
                    } else {
                        Log.d("diaeksis","file exis tidak nougat");
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(pdfPath, "application/pdf");
                        intent = Intent.createChooser(intent, "Open File");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                    }

//                    Uri pdfPath = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(pdfPath, "application/pdf");

                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        AppUtil.showToastLong(context, "Anda belum punya aplikasi untuk melihat PDF, harap download terlebih dahulu");
                    }


                }
                else {
                    File fileNotFound = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS+"/HasilSlik")+"/"+downloadFileName.substring(0,downloadFileName.length()-4)+"/NO FOUND (KTP).pdf");

                    if (fileNotFound.exists()) {

                        Log.d("diaeksis","file exis bro");

                        Intent intent ;
                        Uri pdfPath = Uri.fromFile(fileNotFound);

                        //android nougat keatas harus pake fileprovider kalo mau buka buka file
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Log.d("diaeksis","file exis nougat");
                            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", fileNotFound);
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        context.startActivity(intent);
                        } else {
                            Log.d("diaeksis","file exis tidak nougat");
                            intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(pdfPath, "application/pdf");
                            intent = Intent.createChooser(intent, "Open File");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
                        }

//                    Uri pdfPath = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(pdfPath, "application/pdf");

                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            AppUtil.showToastLong(context, "Anda belum punya aplikasi untuk melihat PDF, harap download terlebih dahulu");
                        }


                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void success(boolean val) {

        }

        @Override
        public void confirm(boolean val) {
            if (val){
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(SlikDirectory.toString()+File.separator);
                    intent.setDataAndType(uri, DocumentsContract.Document.MIME_TYPE_DIR);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(intent,"Open Folder"));
                }
                catch (Exception e){
                    AppUtil.showToastShort(context, e.getMessage());
                }
            }
        }
    }

    public boolean unzip(String path, String zipname){
        InputStream is;
        ZipInputStream zis;
        try
        {
            String fileNameWithoutExt = "";
            if (zipname.indexOf(".") > 0){
                fileNameWithoutExt = zipname.substring(0, zipname.lastIndexOf("."));
            }

            SlikDirectory = new File(apkStorage.getCanonicalPath(), fileNameWithoutExt);

            if (!SlikDirectory.exists()) {
                SlikDirectory.mkdir();
                Log.e(TAG, "Directory Slik Created.");
            }

            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null)
            {
                filename = ze.getName();
                File outFileExtracted = new File(SlikDirectory, filename);
                try {
                    ensureZipPathSafety(outFileExtracted, SlikDirectory.getCanonicalPath());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }
                FileOutputStream fout = new FileOutputStream(outFileExtracted);

                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }
                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private void ensureZipPathSafety(final File outputFile, final String destDirectory) throws Exception {
        String destDirCanonicalPath = (new File(destDirectory)).getCanonicalPath();
        String outputFileCanonicalPath = outputFile.getCanonicalPath();
        if (!outputFileCanonicalPath.startsWith(destDirCanonicalPath)) {
            throw new Exception(String.format("Found Zip Path Traversal Vulnerability with %s", outputFileCanonicalPath));
        }
    }
}