package com.example.abyandafa.rescueambulance.Review;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public interface ReviewMVP {


    interface View extends BaseMVP.View{
        void backToHome();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void uploadRating(String reviewText, String ratingValue);
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onUploadSuccess();
    }

    interface Model{
        void uploadToServer(String reviewText, String ratingValue, String token, String idAmbulan, String idTransaksi);
    }

}
