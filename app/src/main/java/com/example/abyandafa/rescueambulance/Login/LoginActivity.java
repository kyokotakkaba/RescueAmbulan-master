package com.example.abyandafa.rescueambulance.Login;

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
import com.example.abyandafa.rescueambulance.Review.ReviewList;

public class LoginActivity extends AppCompatActivity implements  LogRegMVP.View{



    private EditText username, password;
    private Button login;
    private ProgressDialog progressDialog;
    private LogRegMVP.ViewPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LogPresenter(this);
        presenter.start();
    }

    @Override
    public void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Harap menunggu");
        progressDialog.setCancelable(false);
    }

    @Override
    public void initContent() {
        username = (EditText) findViewById(R.id.nama_akun);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginUser(username.getText().toString(), password.getText().toString());
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
    public void gotoMain() {
        Intent intent = new Intent(LoginActivity.this, ReviewList.class);
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
