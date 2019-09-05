package com.example.abyandafa.rescueambulance.Review;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.Report.ReportMVP;
import com.example.abyandafa.rescueambulance.Report.ReportPresenter;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity implements ReviewMVP.View{


    private ReviewMVP.ViewPresenter vPresenter;

    private TextView reviewIsi;
    private Button reviewTombol;
    private RatingBar ratingBar;
    private TextView batalReview;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        vPresenter = new ReviewPresenter(this);
        vPresenter.start();

    }


    @Override
    public void initView() {
        reviewIsi = (TextView) findViewById(R.id.review_pengguna);
        reviewTombol = (Button) findViewById(R.id.review_tombol);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        batalReview = (TextView) findViewById(R.id.review_batal);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);
    }

    @Override
    public void initContent() {
        batalReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        reviewTombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reviewIsi.getText().toString() == null || String.valueOf(ratingBar.getRating())==null)
                    Toast.makeText(ReviewActivity.this, "Harap isi semua form", Toast.LENGTH_SHORT).show();
                else vPresenter.uploadRating(reviewIsi.getText().toString(), String.valueOf(ratingBar.getRating()));
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backToHome() {
        finish();
    }

    @Override
    public void showProgressBar() {
        progressDialog.show();
    }

    @Override
    public void dismissProgressBar() {
        progressDialog.dismiss();

    }
}
