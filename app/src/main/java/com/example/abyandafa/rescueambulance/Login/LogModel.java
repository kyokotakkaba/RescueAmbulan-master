package com.example.abyandafa.rescueambulance.Login;

import android.util.Log;

import com.example.abyandafa.rescueambulance.baseclass.BaseModel;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.Ambulan;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 24/04/2018.
 */

public class LogModel extends BaseModel implements LogRegMVP.Model {

    private LogRegMVP.ModelPresenter model;

    public LogModel(LogRegMVP.ModelPresenter model) {
        this.model = model;
    }

    @Override
    public void authenticateUser(String username, String password) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.loginApi(username, password);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                Log.d(response.message(), "onResponse: ");
                if(response.code()==200)
                {
                    Ambulan ambulan = gson.fromJson(gson.toJson(response.body().getData()), Ambulan.class);
                    model.onLoginSuccess(ambulan);
                }
                else
                {
                    model.onRequestFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d(t.getMessage(), "onFailure: ");
                model.onRequestFailed(t.getMessage());
            }
        });

    }
}
