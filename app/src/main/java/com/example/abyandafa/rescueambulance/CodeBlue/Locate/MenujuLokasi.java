package com.example.abyandafa.rescueambulance.CodeBlue.Locate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class MenujuLokasi extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap gMap;
    private RelativeLayout layoutKonfirmasi;
    private SupportMapFragment fragment;
    private Button verifBenar, verifSalah, konfirmasi;
    private ImageView cancelKonfir;
    SPTransaksi spTransaksi;
    SPAmbulan spAmbulan;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuju_lokasi);

        spTransaksi = new SPTransaksi(this);
        spAmbulan = new SPAmbulan(this);

        verifBenar = (Button) findViewById(R.id.verifBenar);
        verifSalah = (Button) findViewById(R.id.verifSalah);
        layoutKonfirmasi = (RelativeLayout) findViewById(R.id.layoutKonfirmasi);
        konfirmasi = (Button) findViewById(R.id.konfirmasiAction);
        cancelKonfir = (ImageView) findViewById(R.id.cancelKonfir);

        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasi.setVisibility(View.GONE);
                layoutKonfirmasi.setVisibility(View.VISIBLE);
            }
        });

        cancelKonfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutKonfirmasi.setVisibility(View.GONE);
                konfirmasi.setVisibility(View.VISIBLE);
            }
        });

        verifBenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasiTransaksi();
            }
        });

        verifSalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.getMapAsync(this);

    }

    private void konfirmasiTransaksi() {

        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.verifikasiTransaksi(spAmbulan.getSpToken(), spTransaksi.getID(), spAmbulan.getID().toString());
        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code()==200)
                {
                    Intent intent = new Intent(MenujuLokasi.this, FollowUpActivity.class);
                    startActivity(intent);
                    finish();

                }
                else if(response.code()==202)
                {
                    spAmbulan.logout(1);
                }
            }



            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MenujuLokasi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng latLng = new LatLng(Double.valueOf(spTransaksi.getSpLatitude()), Double.valueOf(spTransaksi.getSpLongitude()));
        Marker marker = gMap.addMarker(new MarkerOptions().position(latLng).title(spTransaksi.getSpLokasi()));

        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
