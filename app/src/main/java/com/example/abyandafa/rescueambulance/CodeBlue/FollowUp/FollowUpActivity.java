package com.example.abyandafa.rescueambulance.CodeBlue.FollowUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abyandafa.rescueambulance.R;
import com.example.abyandafa.rescueambulance.Review.ReviewActivity;
import com.example.abyandafa.rescueambulance.model.APIResponse;
import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;
import com.example.abyandafa.rescueambulance.service.APIClient;
import com.example.abyandafa.rescueambulance.service.BaseRestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUpActivity extends AppCompatActivity implements FollowUpMVP.View{

    private EditText followup, nomFollowup;
    private Button selesai;
    private ProgressDialog progressDialog;

    private FollowUpMVP.ViewPresenter vPresenter;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);
        vPresenter = new FollowUpPresenter(this);
        vPresenter.start();
    }

    @Override
    public void initView() {
        followup = (EditText) findViewById(R.id.followupTransaksi);
        nomFollowup = (EditText) findViewById(R.id.nofollowup);
        selesai = (Button) findViewById(R.id.selesaiTransaksi);
    }

    @Override
    public void initContent() {
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(followup.getText().toString().isEmpty() || nomFollowup.getText().toString().isEmpty())
                {
                    showToast("Isi Follow up terlebih dahulu");
                }
                else
                {
                    vPresenter.getfollowUp(followup.getText().toString(), nomFollowup.getText().toString());
                    finish();
                }
            }
        });
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
    public void finishTransaction() {
        Intent intent = new Intent(FollowUpActivity.this, ReviewActivity.class);
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
