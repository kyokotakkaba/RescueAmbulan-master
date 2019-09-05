package com.example.abyandafa.rescueambulance.Report;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanActivity extends AppCompatActivity implements ReportMVP.View{

    private ReportMVP.ViewPresenter vPresenter;

    private EditText pesanPengaduan;
    private ImageView tutupPengaduan, simpanPengaduan;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        vPresenter = new ReportPresenter(this);
        vPresenter.start();

    }



    @Override
    public void initView() {
        pesanPengaduan = (EditText) findViewById(R.id.isi_pengaduan);
        tutupPengaduan = (ImageView) findViewById(R.id.tutup_pengaduan);
        simpanPengaduan = (ImageView) findViewById(R.id.simpan_pengaduan);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);
    }

    @Override
    public void initContent() {
        tutupPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        simpanPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vPresenter.uploadReport(pesanPengaduan.getText().toString());
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
    public void goToHome() {
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
