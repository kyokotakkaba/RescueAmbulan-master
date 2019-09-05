package com.example.abyandafa.rescueambulance.Login;

import android.content.Context;
import android.util.Log;

import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.Ambulan;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 24/04/2018.
 */

public class LogPresenter implements  LogRegMVP.ModelPresenter, LogRegMVP.ViewPresenter {

    private LogRegMVP.View view;
    private LogRegMVP.Model model;
    private SPAmbulan spAmbulan;
    private Context context;

    public LogPresenter(LogRegMVP.View view) {
        this.view = view;
        this.context = view.getContext();
        spAmbulan = new SPAmbulan(context);

        if(spAmbulan.getSPSudahLogin())
        {
            view.gotoMain();
        }
        else
        {
            model = new LogModel(this);
        }

    }

    @Override
    public void start() {
        view.initView();
        view.initContent();
    }

    @Override
    public void onRequestFailed(String message) {
        view.dismissProgressBar();
        view.showToast(message);
    }

    @Override
    public void loginUser(String username, String password) {

        view.showProgressBar();
        model.authenticateUser(username, password);

    }

    @Override
    public void onLoginSuccess(Ambulan ambulan) {
        spAmbulan.saveAmbulan(ambulan);

        //tambahan benny
        updateFCM(spAmbulan.getSpTokenFCM());
        view.gotoMain();


    }

    private void updateFCM(String token) {

        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.updateFCM(spAmbulan.getSpToken(), token);
        Log.d("fcmrefreshtoken", "FCM refresh token: "+token);
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
