package com.example.abyandafa.rescueambulance.Notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.abyandafa.rescueambulance.CodeBlue.Locate.MenujuLokasi;
import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity  implements NotificationMVP.View{


    private TextView nilaiJarak, nilaiLokasi;
    private ImageView terima, tolak;
    private ImageView fotokejadian;
    private ImageView defaultphoto;

    private NotificationMVP.ViewPresenter vPresenter;
    private ProgressDialog progressDialog;


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        vPresenter = new NotificationPresenter(this);
        vPresenter.start();


    }

    @Override
    public void initView() {
        nilaiJarak = (TextView) findViewById(R.id.textView10);

        nilaiLokasi = (TextView) findViewById(R.id.lokasikorban);

        terima = (ImageView) findViewById(R.id.imageView3);
        tolak = (ImageView) findViewById(R.id.imageView4);
        fotokejadian = (ImageView) findViewById(R.id.code_blue_photo);
        defaultphoto = (ImageView) findViewById(R.id.logo_notif);

    }

    @Override
    public void initContent() {
        nilaiLokasi.setText(vPresenter.getLocation());
        terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPresenter.acceptNotification();

            }
        });
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        if(intent.getStringExtra("path") != null)
        {
            Log.d("Logbyandafaganteng", "onCreate: " +intent.getStringExtra("path"));
            Glide.with(this).load(intent.getStringExtra("path"))
                    .apply(new RequestOptions().override(100, 100).placeholder(R.drawable.dekat_logo).error(R.drawable.dekat_logo)).into(fotokejadian);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);


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
    public void goLocate() {
        Intent intent = new Intent(Notification.this, MenujuLokasi.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPanicView() {
        nilaiJarak.setText("Panic Button");
        fotokejadian.setVisibility(View.GONE);
        defaultphoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCodeBlueView() {
        fotokejadian.setVisibility(View.VISIBLE);
        defaultphoto.setVisibility(View.GONE);
        nilaiJarak.setText("Code Blue");
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
