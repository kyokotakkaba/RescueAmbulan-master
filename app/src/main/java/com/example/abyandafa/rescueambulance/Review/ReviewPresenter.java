package com.example.abyandafa.rescueambulance.Review;

import android.util.Log;

import com.example.abyandafa.rescueambulance.Report.ReportMVP;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public class ReviewPresenter implements ReviewMVP.ViewPresenter, ReviewMVP.ModelPresenter {

    private ReviewMVP.Model model;
    private ReviewMVP.View view;
    private SPAmbulan spAmbulan;
    private SPTransaksi spTransaksi;

    public ReviewPresenter(ReviewMVP.View view) {
        this.view = view;
        this.spAmbulan = new SPAmbulan(view.getContext());
        this.spTransaksi = new SPTransaksi(view.getContext());
        this.model = new ReviewModel(this);


    }

    @Override
    public void start() {
        view.initView();
        view.initContent();
    }

    @Override
    public void onRequestFailed(String message) {
        view.showToast(message);
        view.dismissProgressBar();
    }

    @Override
    public void uploadRating(String reviewText, String ratingValue) {
        view.showProgressBar();
        Log.d("debugasd", "uploadRating: " + reviewText);
        Log.d("debugasd", "uploadRating: " + ratingValue);
        Log.d("debugasd", "uploadRating: " + spAmbulan.getSpToken());
        Log.d("debugasd", "uploadRating: " + String.valueOf(spAmbulan.getID()));
        Log.d("debugasd", "uploadRating: " + String.valueOf(spTransaksi.getID()));
        model.uploadToServer(reviewText, ratingValue,
                spAmbulan.getSpToken(),
                String.valueOf(spAmbulan.getID()),
                String.valueOf(spTransaksi.getID()));
    }

    @Override
    public void onUploadSuccess() {
        view.showToast("Berhasil");
        view.backToHome();
    }
}
