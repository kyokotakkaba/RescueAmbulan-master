package com.example.abyandafa.rescueambulance.service;

import android.util.Log;

import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Abyan Dafa on 06/12/2017.
 */

public class TokenService extends FirebaseInstanceIdService {

    SPAmbulan spAmbulan;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        spAmbulan = new SPAmbulan(this);
        spAmbulan.saveSpTokenFCM(refreshedToken);
        updateFCM(refreshedToken);
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        super.onTokenRefresh();
    }


    private void updateFCM(String token) {

        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.updateFCM(spAmbulan.getSpToken(), token);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.code() == 200) {
                    Log.d("fcm", "onResponse: ");
                } else if (response.code() == 202) {
                    spAmbulan.logout(1);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("gagal", "onFailure: " + t.getMessage());
            }
        });
    }
}
