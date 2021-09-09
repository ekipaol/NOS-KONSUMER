package com.application.bris.ikurma_nos_konsumer.util;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wildanafifi on 06/03/2018.
 */

public class DictionaryHelper {

    public static final List<JsonObject> dictFilterKodePos()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "PROPINSI");
        j1.addProperty("val", "Provinsi");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "KOTA");
        j2.addProperty("val", "Kota");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "KECAMATAN");
        j3.addProperty("val", "Kecamatan");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "KELURAHAN");
        j4.addProperty("val", "Kelurahan");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "KODE_POS");
        j5.addProperty("val", "Kode Pos");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        return listJenis;
    }

    public static final List<JsonObject> dictStatusNikah()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "1");
        j1.addProperty("val", "Tidak Menikah");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "2");
        j2.addProperty("val", "Menikah");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "3");
        j3.addProperty("val", "Duda / Janda");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);

        return listJenis;
    }

    public static final List<JsonObject> dictAgama()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "BUD");
        j1.addProperty("val", "Budha");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "HIN");
        j2.addProperty("val", "Hindu");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "ISL");
        j3.addProperty("val", "Islam");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "KAT");
        j4.addProperty("val", "Katolik");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "KRI");
        j5.addProperty("val", "Kristen");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        return listJenis;
    }

    public static final List<JsonObject> dictJenisPekerjaan()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j0 = new JsonObject();
        j0.addProperty("key", "");
        j0.addProperty("val", "--");
        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "1");
        j1.addProperty("val", "Karyawan / PNS");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "2");
        j2.addProperty("val", "Karyawan / PNS Program FLPP");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "3");
        j3.addProperty("val", "Karyawan / PNS Program EMBP");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "4");
        j4.addProperty("val", "Karyawan BRIS");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "5");
        j5.addProperty("val", "Wiraswasta");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "6");
        j6.addProperty("val", "Profesional");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "7");
        j7.addProperty("val", "Karyawan / PNS + Wiraswasta");
        JsonObject j8 = new JsonObject();
        j8.addProperty("key", "8");
        j8.addProperty("val", "Karyawan / PNS + Profesional");
        JsonObject j9 = new JsonObject();
        j9.addProperty("key", "9");
        j9.addProperty("val", "Profesional + Wiraswasta");

        listJenis.add(j0);

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        listJenis.add(j6);
        listJenis.add(j7);
        listJenis.add(j8);
        listJenis.add(j9);

        return listJenis;
    }

    public static final List<JsonObject> dictJenisUsaha()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j0 = new JsonObject();
        j0.addProperty("key", "");
        j0.addProperty("val", "--");
        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "SEM");
        j1.addProperty("val", "Sembako/Kelontong");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "PAK");
        j2.addProperty("val", "Pakaian/Alas Kaki");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "RON");
        j3.addProperty("val", "Rongsokan/Barang Bekas");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "RMK");
        j4.addProperty("val", "Rumah Makan/Katering");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "ELK");
        j5.addProperty("val", "Elektronik");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "DAG");
        j6.addProperty("val", "Daging/Unggas/Ikan");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "PRT");
        j7.addProperty("val", "Peralatan Rumah Tangga");
        JsonObject j8 = new JsonObject();
        j8.addProperty("key", "IND");
        j8.addProperty("val", "Industri Rumahan");
        JsonObject j9 = new JsonObject();
        j9.addProperty("key", "BAH");
        j9.addProperty("val", "Bahan Bangunan/Material");
        JsonObject j10 = new JsonObject();
        j10.addProperty("key", "TRS");
        j10.addProperty("val", "Transportasi");
        JsonObject j11 = new JsonObject();
        j11.addProperty("key", "MEU");
        j11.addProperty("val", "Meubel");
        JsonObject j12 = new JsonObject();
        j12.addProperty("key", "SUP");
        j12.addProperty("val", "Supplier(pemasok)");
        JsonObject j13 = new JsonObject();
        j13.addProperty("key", "BEN");
        j13.addProperty("val", "Bengkel");
        JsonObject j14 = new JsonObject();
        j14.addProperty("key", "USH");
        j14.addProperty("val", "Usaha Isi Ulang");
        JsonObject j15 = new JsonObject();
        j15.addProperty("key", "SYR");
        j15.addProperty("val", "Sayur-mayur/Buah-buahan/Beras");
        JsonObject j16 = new JsonObject();
        j16.addProperty("key", "LAN");
        j16.addProperty("val", "Lainnya");

        listJenis.add(j0);

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        listJenis.add(j6);
        listJenis.add(j7);
        listJenis.add(j8);
        listJenis.add(j9);
        listJenis.add(j10);

        listJenis.add(j11);
        listJenis.add(j12);
        listJenis.add(j13);
        listJenis.add(j14);
        listJenis.add(j15);

        listJenis.add(j16);

        return listJenis;
    }

    public static final List<JsonObject> dictJenisProduk()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "127");
        j1.addProperty("val", "KUR Mikro iB");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "131");
        j2.addProperty("val", "Mikro 25 iB");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "136");
        j3.addProperty("val", "Mikro 75 iB");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "141");
        j4.addProperty("val", "Mikro 200 iB");
//        JsonObject j5 = new JsonObject();
//        j5.addProperty("key", "FUN");
//        j5.addProperty("val", "Funding");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
//        listJenis.add(j5);

        return listJenis;
    }

    public static final List<JsonObject> dictStatusTerakhirHotProspek()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "FOL");
        j1.addProperty("val", "Follow Up AOM");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "CD");
        j2.addProperty("val", "Collect Document");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "BIC");
        j3.addProperty("val", "BI Checking");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "NAS");
        j4.addProperty("val", "Nasabah Isi Aplikasi");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "SURV");
        j5.addProperty("val", "Survey PUK");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "JFW");
        j6.addProperty("val", "Join Field Work");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "REJ");
        j7.addProperty("val", "Reject");
        JsonObject j8 = new JsonObject();
        j8.addProperty("key", "CAN");
        j8.addProperty("val", "Cancel");
        JsonObject j9 = new JsonObject();
        j9.addProperty("key", "PEN");
        j9.addProperty("val", "Pending");
        JsonObject j10 = new JsonObject();
        j10.addProperty("key", "APRJ");
        j10.addProperty("val", "Aplikasi Masuk RJ");
        JsonObject j11 = new JsonObject();
        j11.addProperty("key", "CAIR");
        j11.addProperty("val", "Cair");
        JsonObject j12 = new JsonObject();
        j12.addProperty("key", "NEW");
        j12.addProperty("val", "New Visit");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        listJenis.add(j6);
        listJenis.add(j7);
        listJenis.add(j8);
        listJenis.add(j9);
        listJenis.add(j10);

        listJenis.add(j11);
        listJenis.add(j12);

        return listJenis;
    }

    public static final List<JsonObject> dictAlasan()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "REBI");
        j1.addProperty("val", "Reject BI Checking");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "REKA");
        j2.addProperty("val", "Reject karakter");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "REJA");
        j3.addProperty("val", "Reject Jaminan");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "RERPC");
        j4.addProperty("val", "Reject RPC");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "CAN");
        j5.addProperty("val", "Cancel keinginan pribadi calon Nasabah");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "PEN");
        j6.addProperty("val", "Pending keinginan pribadi calon Nasabah");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "TAKE");
        j7.addProperty("val", "Takeover Bank Lain");
        JsonObject j8 = new JsonObject();
        j8.addProperty("key", "SLA");
        j8.addProperty("val", "SLA (Proses terlalu lama)");
        JsonObject j9 = new JsonObject();
        j9.addProperty("key", "PADB");
        j9.addProperty("val", "Proses administrasi berbelit-belit");
        JsonObject j10 = new JsonObject();
        j10.addProperty("key", "PADM");
        j10.addProperty("val", "Proses ADP lama");
        JsonObject j11 = new JsonObject();
        j11.addProperty("key", "TPT");
        j11.addProperty("val", "Tempat usaha diluar radius");
        JsonObject j12 = new JsonObject();
        j12.addProperty("key", "USAH");
        j12.addProperty("val", "Usaha bukan milik sendiri");
        JsonObject j13 = new JsonObject();
        j13.addProperty("key", "USAL");
        j13.addProperty("val", "Usaha kurang laku");
        JsonObject j14 = new JsonObject();
        j14.addProperty("key", "FAS");
        j14.addProperty("val", "Kendala System (FAS)");
        JsonObject j15 = new JsonObject();
        j15.addProperty("key", "EFOS");
        j15.addProperty("val", "Kendala System (eFOS)");
        JsonObject j16 = new JsonObject();
        j16.addProperty("key", "T24");
        j16.addProperty("val", "Kendala System (T24)");
        JsonObject j17 = new JsonObject();
        j17.addProperty("key", "LAN");
        j17.addProperty("val", "Lainnya (wajib jelaskan)");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        listJenis.add(j6);
        listJenis.add(j7);
        listJenis.add(j8);
        listJenis.add(j9);
        listJenis.add(j10);

        listJenis.add(j11);
        listJenis.add(j12);
        listJenis.add(j13);
        listJenis.add(j14);
        listJenis.add(j15);

        listJenis.add(j16);
        listJenis.add(j17);

        return listJenis;
    }

    public static final List<JsonObject> dictStatusTerakhirCollection()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "BAY");
        j1.addProperty("val", "Bayar Sesuai Tunggakan");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "FOL");
        j2.addProperty("val", "Follow Up Janji Bayar");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "SP1");
        j3.addProperty("val", "Surat Peringatan I");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "SP2");
        j4.addProperty("val", "Surat Peringatan II");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "SP3");
        j5.addProperty("val", "Surat Peringatan III");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "JAM");
        j6.addProperty("val", "Jual Jaminan");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "LL1");
        j7.addProperty("val", "Lelang I");
        JsonObject j8 = new JsonObject();
        j8.addProperty("key", "LL2");
        j8.addProperty("val", "Lelang II");
        JsonObject j9 = new JsonObject();
        j9.addProperty("key", "LL3");
        j9.addProperty("val", "Lelang III");
        JsonObject j10 = new JsonObject();
        j10.addProperty("key", "SLS");
        j10.addProperty("val", "Tunggakan Selesai/BTC");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);

        listJenis.add(j6);
        listJenis.add(j7);
        listJenis.add(j8);
        listJenis.add(j9);
        listJenis.add(j10);

        return listJenis;
    }

    public static final List<JsonObject> dictJenisFunding()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "TAB");
        j1.addProperty("val", "Tabungan");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "DEP");
        j2.addProperty("val", "Deposito");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "GIR");
        j3.addProperty("val", "Giro");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);

        return listJenis;
    }

    public static final List<JsonObject> dictMemilikiFasilitas()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "SUD");
        j1.addProperty("val", "Sudah");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "BEL");
        j2.addProperty("val", "Belum");

        listJenis.add(j1);
        listJenis.add(j2);

        return listJenis;
    }

    public static final List<JsonObject> dictSumberFunding()
    {
        List<JsonObject> listJenis = new ArrayList<JsonObject>();

        JsonObject j1 = new JsonObject();
        j1.addProperty("key", "PAS");
        j1.addProperty("val", "Serbu Pasar");
        JsonObject j2 = new JsonObject();
        j2.addProperty("key", "KAN");
        j2.addProperty("val", "Kanvas");
        JsonObject j3 = new JsonObject();
        j3.addProperty("key", "REF");
        j3.addProperty("val", "Referensi Calon Nasabah/Nasabah Exist");
        JsonObject j4 = new JsonObject();
        j4.addProperty("key", "PAM");
        j4.addProperty("val", "Pameran");
        JsonObject j5 = new JsonObject();
        j5.addProperty("key", "KER");
        j5.addProperty("val", "Kerjasama/Program Sales");
        JsonObject j6 = new JsonObject();
        j6.addProperty("key", "MAS");
        j6.addProperty("val", "Masjid");
        JsonObject j7 = new JsonObject();
        j7.addProperty("key", "LAN");
        j7.addProperty("val", "Lain-lain");

        listJenis.add(j1);
        listJenis.add(j2);
        listJenis.add(j3);
        listJenis.add(j4);
        listJenis.add(j5);
        listJenis.add(j6);
        listJenis.add(j7);

        return listJenis;
    }

    public static String getVal(List<JsonObject> list, String key)
    {
        for(int i=0; i<list.size();i++)
        {
            JsonObject o = list.get(i);
            if(((String)o.get("key").getAsString()).equals(key))
                return (String)o.get("val").getAsString();
        }

        return "";
    }
}
