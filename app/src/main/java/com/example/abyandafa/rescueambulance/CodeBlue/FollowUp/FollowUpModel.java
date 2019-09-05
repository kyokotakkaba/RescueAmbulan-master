package com.example.abyandafa.rescueambulance.CodeBlue.FollowUp;

import com.example.abyandafa.rescueambulance.baseclass.BaseModel;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public class FollowUpModel extends BaseModel implements FollowUpMVP.Model {

    private FollowUpMVP.ModelPresenter mPresenter;

    public FollowUpModel(FollowUpMVP.ModelPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }


    @Override
    public void uploadFollowUp(String followUp, String picnum, String token, String idTransaction) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.selesaiTransaksi(token, idTransaction,
                followUp+ ", " + picnum);
        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code()==200)
                {
                    mPresenter.onUploadSuccess(true);
                }
                else if(response.code()==202)
                {
                    mPresenter.onUploadSuccess(false);
                }
                else
                {
                    mPresenter.onRequestFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                mPresenter.onRequestFailed(t.getMessage());
            }
        });
    }
}
