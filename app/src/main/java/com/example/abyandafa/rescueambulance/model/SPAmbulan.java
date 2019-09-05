package com.example.abyandafa.rescueambulance.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.Login.LoginActivity;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 05/12/2017.
 */

public class SPAmbulan {

    public static final String SP_AMBULAN = "spAmbulan";
    public static final String SP_IDAMBULAN = "spID";
    public  static final String SP_USERNAME = "spUsername";
    public static final String SP_TOKEN = "spToken";
    public static final String SP_NOPOL = "spNopol";
    public static final String SP_ALAMAT = "spAlamat";
    public static final String SP_NAMARS = "spNamaRS";

    //tambahan
    public static final String SP_TOKENFCM = "spTokenFCM";

    private Context context = null;

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SPAmbulan(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SP_AMBULAN, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void logout(Integer integer)
    {
        spEditor.clear();
        spEditor.commit();


        if(integer==20)
        {
            Toast.makeText(context, "Akun anda diblokir", Toast.LENGTH_SHORT).show();
        }
        else if(integer ==12)
        {

            Toast.makeText(context, "Berhasil keluar", Toast.LENGTH_SHORT).show();
        }

        else
        {

            Toast.makeText(context, "Akun anda login di perangkat lain", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }

    public void logout(String token)
    {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.logout(token);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                if(response.code() == 200) Toast.makeText(context, "Berhasil keluar", Toast.LENGTH_SHORT).show();
                else if(response.code()==202) Toast.makeText(context, "Akun ada masuk di perangkat lain", Toast.LENGTH_SHORT).show();
                else Toast.makeText(context, "Terjadi kesalahan sistem", Toast.LENGTH_SHORT).show();
                spEditor.clear();
                spEditor.commit();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(context, "Jaringan anda bermasalah", Toast.LENGTH_SHORT).show();

            }
        });






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

    public Integer getID()
    {

        return sp.getInt(SP_IDAMBULAN, -1);

    }

    public String getSpNamars()
    {
        return sp.getString(SP_NAMARS, "");
    }
    public String getSpUsername()
    {
        return sp.getString(SP_USERNAME, "");
    }
    public String getSpToken()
    {
        return sp.getString(SP_TOKEN, "");
    }
    public String getSpAlamat()
    {
        return sp.getString(SP_ALAMAT, "");
    }
    public String getSpNopol()
    {
        return sp.getString(SP_NOPOL, "");
    }
    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
    public void saveAmbulan(Ambulan ambulan) {
        saveSPBoolean(SPAmbulan.SP_SUDAH_LOGIN, true);
        saveSPInt(SPAmbulan.SP_IDAMBULAN, ambulan.getId_ambulan());
        saveSPString(SPAmbulan.SP_USERNAME, ambulan.getUsername());
        saveSPString(SPAmbulan.SP_TOKEN, ambulan.getToken());
        saveSPString(SPAmbulan.SP_NOPOL, ambulan.getNo_pol_ambulan());
        saveSPString(SPAmbulan.SP_ALAMAT, ambulan.getAlamat_rs());
    }

    //tambahan
    public String getSpTokenFCM()
    {
        return sp.getString(SP_TOKENFCM, "");
    }
    public void saveSpTokenFCM(String tokenFCM) { saveSPString(SPAmbulan.SP_TOKENFCM, tokenFCM); }

}
