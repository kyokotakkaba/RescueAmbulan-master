package com.example.abyandafa.rescueambulance.CodeBlue.Locate;

import android.content.Intent;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.CodeBlue.FollowUp.FollowUpActivity;
import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;
import com.example.abyandafa.rescueambulance.baseclass.BaseModel;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 26/04/2018.
 */

public class LocateModel extends BaseModel implements LocateMVP.Model{

    private LocateMVP.ModelPresenter mPresenter;

    public LocateModel(LocateMVP.ModelPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void uploadVerifLocation(String token, String idAmbulan, String idTransaction) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.verifikasiTransaksi(token, idTransaction, idAmbulan);
        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code()==200)
                {
                    mPresenter.onVerifSuccess(true);
                }
                else if(response.code()==202)
                {
                    mPresenter.onVerifSuccess(false);
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
