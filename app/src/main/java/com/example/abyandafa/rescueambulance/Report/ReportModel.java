package com.example.abyandafa.rescueambulance.Report;

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

public class ReportModel extends BaseModel implements ReportMVP.Model {

    private ReportMVP.ModelPresenter mPresenter;

    public ReportModel(ReportMVP.ModelPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void uploadToServer(String token, String reportText) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);

        Call<APIResponse> result = apiClient.uploadPengaduan(token, reportText);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code()==200)
                {
                    mPresenter.onUploadSuccess();
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
