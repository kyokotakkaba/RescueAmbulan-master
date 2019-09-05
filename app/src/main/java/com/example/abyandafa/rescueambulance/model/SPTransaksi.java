package com.example.abyandafa.rescueambulance.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abyan Dafa on 06/12/2017.
 */

public class SPTransaksi {

    public static final String SPTRANSAKSI = "spTransaksi";
    public static final String SP_ID = "spID";
    public static final String SP_TIPE = "spTipe";
    public static final String SP_LATITUDE = "spLatitude";
    public static final String SP_LONGITUDE = "spLongitude";
    public static final String SP_LOKASI = "spLokasi";

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private Context context;

    public SPTransaksi(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SPTRANSAKSI, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString (String keySP, String value)
    {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value)
    {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value)
    {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getID()
    {

        return sp.getString(SP_ID, "");
    }
    public String getSpTipe()
    {
        return sp.getString(SP_TIPE, "");
    }
    public String getSpLatitude()
    {
        return sp.getString(SP_LATITUDE, "");
    }
    public String getSpLongitude()
    {
        return sp.getString(SP_LONGITUDE, "");
    }
    public String getSpLokasi()
    {
        return sp.getString(SP_LOKASI, "");
    }

    public void selesai()
    {
        spEditor.clear();
        spEditor.commit();
    }

}
