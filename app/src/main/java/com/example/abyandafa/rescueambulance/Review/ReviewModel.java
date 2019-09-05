package com.example.abyandafa.rescueambulance.Review;

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

public class ReviewModel extends BaseModel implements ReviewMVP.Model{

    private  ReviewMVP.ModelPresenter mPresenter;

    public ReviewModel(ReviewMVP.ModelPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void uploadToServer(String reviewText, String ratingValue, String token, String idAmbulan, String idTransaksi) {
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.reviewPengguna(token, idTransaksi, idAmbulan,
                reviewText, ratingValue);

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse> call, Response<APIResponse> response) {
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
            public void onFailure(retrofit2.Call<APIResponse> call, Throwable t) {

                mPresenter.onRequestFailed(t.getMessage());
            }
        });
    }
}
