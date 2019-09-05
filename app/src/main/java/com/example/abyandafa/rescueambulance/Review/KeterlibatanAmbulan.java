package com.example.abyandafa.rescueambulance.Review;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeterlibatanAmbulan extends AppCompatActivity {

    private String idTransaksi;
    private SPTransaksi spTransaksi;
    private SPAmbulan spAmbulan;
    private DetailTransaksiResponse detail;
    private TextView lokasi, waktuKejadian, waktuAwam, waktuAmbulan, waktuSelesai, tanggalKejadian, tanggalAwam, tanggalAmbulan
            , tanggalSelesai, nopol, followup, komentar;
    private RatingBar rating;
    private ImageView kembali;
    private Button fillRating;
    private ProgressDialog progressDialog;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        callDetailTransaction();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterlibatan_ambulan);
        spTransaksi = new SPTransaksi(this);
        spAmbulan = new SPAmbulan(this);
        lokasi = (TextView) findViewById(R.id.detail_lokasi);
        waktuKejadian = (TextView) findViewById(R.id.detail_waktu_kejadian);
        waktuAmbulan = (TextView) findViewById(R.id.detail_waktu_ambulan);
        waktuAwam = (TextView)findViewById(R.id.detail_waktu_awam);
        waktuSelesai = (TextView) findViewById(R.id.detail_waktu_selesai);
        tanggalSelesai = (TextView) findViewById(R.id.detail_tanggal_selesai);
        tanggalAmbulan = (TextView) findViewById(R.id.detail_tanggal_ambulan);
        tanggalAwam  =(TextView) findViewById(R.id.detail_tanggal_awam);
        tanggalKejadian  = (TextView) findViewById(R.id.detail_tanggal_kejadian);
        nopol = (TextView) findViewById(R.id.detail_ambulan_nopol);
        followup = (TextView) findViewById(R.id.detail_followup);
        komentar = (TextView) findViewById(R.id.detail_komentar);
        kembali = (ImageView) findViewById(R.id.detail_kembali);
        fillRating = (Button) findViewById(R.id.detail_isi_review);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rating = (RatingBar) findViewById(R.id.detail_rating);
        rating.setEnabled(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);


        spTransaksi.selesai();

        Intent intent = getIntent();
        idTransaksi  = intent.getStringExtra("transaksi");

        spTransaksi.saveSPString(SPTransaksi.SP_ID, idTransaksi);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        fillRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reviewIntent = new Intent(KeterlibatanAmbulan.this, ReviewActivity.class);
                startActivity(reviewIntent);
            }
        });

        callDetailTransaction();

    }

    private void callDetailTransaction() {
        progressDialog.show();
        APIClient apiClient = new BaseRestService().initializeRetrofit().create(APIClient.class);
        Call<APIResponse> result = apiClient.transaksiDetail(spAmbulan.getSpToken(), String.valueOf(spTransaksi.getID()));

        result.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code() ==200)
                {
                    Gson gson = new Gson();
                    detail = gson.fromJson(gson.toJson(response.body().getData()), DetailTransaksiResponse.class);
                    setDetail();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("callDetailTransaction", "onFailure: " + t.getMessage());
                Toast.makeText(KeterlibatanAmbulan.this, "Kesalahan System", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void setDetail() {
        lokasi.setText(detail.getLokasi());
        String[] parts;
        String part1; // 004
        String part2; // 034556
        if(detail.getWaktu_kejadian() !=null)
        {
            parts = detail.getWaktu_kejadian().split(" ");
            part1 = parts[0]; // 004
            part2 = parts[1]; // 034556
            tanggalKejadian.setText(part1);
            waktuKejadian.setText(part2);
        }
        else
        {
            tanggalKejadian.setText("ada");
            waktuKejadian.setText("tidak");
        }
        if(detail.getWaktu_pertolongan_awam() !=null)
        {
            parts = detail.getWaktu_pertolongan_awam().split(" ");
            part1 = parts[0]; // 004
            part2 = parts[1]; // 034556
            tanggalAwam.setText(part1);
            waktuAwam.setText(part2);
        }
        else
        {
            tanggalAwam.setText("ada");
            waktuAwam.setText("tidak");
        }

        if(detail.getWaktu_pertolongan_ambulan()!= null)
        {
            parts = detail.getWaktu_pertolongan_ambulan().split(" ");
            part1 = parts[0]; // 004
            part2 = parts[1]; // 034556
            tanggalAmbulan.setText(part1);
            waktuAmbulan.setText(part2);
        }
        else
        {
            tanggalAmbulan.setText("ada");
            waktuAmbulan.setText("tidak");
        }

        if(detail.getWaktu_selesai() !=null)
        {
            parts = detail.getWaktu_pertolongan_ambulan().split(" ");
            part1 = parts[0]; // 004
            part2 = parts[1]; // 034556
            tanggalSelesai.setText(part1);
            waktuSelesai.setText(part2);
        }
        else
        {
            tanggalSelesai.setText("ada");
            waktuSelesai.setText("tidak");
        }

        if(detail.getFollowup() !=null)
        {
            followup.setText(detail.getFollowup());
        }
        else followup.setText("Kosong");
        if(detail.getRating() !=null)
        {
            Float rat = Float.valueOf(detail.getRating());
            rating.setRating(rat);
            fillRating.setVisibility(View.GONE);
        }
        else
        {
            fillRating.setVisibility(View.VISIBLE);
        }
        if(detail.getKomentar() !=null)
        {
            komentar.setText(detail.getKomentar());
        }
        else komentar.setText("kosong");
        if(detail.getPlat_ambulan() !=null)
        {
            nopol.setText(detail.getPlat_ambulan());
        }
        else nopol.setText("Tidak ada");


    }
}
