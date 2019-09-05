package com.example.abyandafa.rescueambulance.CodeBlue.Locate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.CodeBlue.FollowUp.FollowUpActivity;
import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeBlue extends AppCompatActivity implements OnMapReadyCallback, LocateMVP.View{

    private GoogleMap gMap;
    private SupportMapFragment fragment;
    private Button verifBenar, verifSalah;

    private LocateMVP.ViewPresenter vPresenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_blue);

        vPresenter = new LocatePresenter(this);
        vPresenter.start();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng latLng = new LatLng(Double.valueOf(vPresenter.getLatitude()), vPresenter.getLongitude());
        Marker marker = gMap.addMarker(new MarkerOptions().position(latLng).title(vPresenter.getLocation()));

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void initView() {
        verifBenar = (Button) findViewById(R.id.verifBenar);
        verifSalah = (Button) findViewById(R.id.verifSalah);

        fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.getMapAsync(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);

    }

    @Override
    public void initContent() {
        verifBenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vPresenter.getVerify();
            }
        });

        verifSalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
    public void goToFollowUp() {
        Intent intent = new Intent(CodeBlue.this, FollowUpActivity.class);
        startActivity(intent);
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
