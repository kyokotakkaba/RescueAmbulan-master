package com.example.abyandafa.rescueambulance.Notification;

import android.content.Intent;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.CodeBlue.Locate.MenujuLokasi;
import com.example.abyandafa.rescueambulance.baseclass.BaseModel;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 28/04/2018.
 */

public class NotificationModel extends BaseModel implements NotificationMVP.Model {

    private NotificationMVP.ModelPresenter mPresenter;

    public NotificationModel(NotificationMVP.ModelPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void uploadToServer(String token, String idAmbulan, String idTransaksi) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.terimaTransaksi(token, idTransaksi,
                idAmbulan);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code()==200)
                {
                    mPresenter.onAcceptSuccess();
                }
                else if(response.code()==201)
                {
                    mPresenter.onRequestFailed("Sudah ada yang menolong");
                }
                else if(response.code()==202)
                {
                    mPresenter.onNotLogin();
                }
                else mPresenter.onRequestFailed(response.message());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                mPresenter.onRequestFailed(t.getMessage());
            }
        });
    }
}
