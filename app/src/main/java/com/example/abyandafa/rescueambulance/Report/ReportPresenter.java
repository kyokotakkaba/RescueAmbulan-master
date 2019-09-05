package com.example.abyandafa.rescueambulance.Report;

import android.content.Context;

import com.example.abyandafa.rescueambulance.model.SPAmbulan;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public class ReportPresenter implements ReportMVP.ViewPresenter, ReportMVP.ModelPresenter {
    private ReportMVP.View view;
    private ReportMVP.Model model;
    private SPAmbulan spAmbulan;
    private Context context;

    public ReportPresenter(ReportMVP.View view) {
        this.view = view;
        this.model = new ReportModel(this);
        this.context = view.getContext();
        spAmbulan = new SPAmbulan(context);
    }

    @Override
    public void start() {
        view.initView();
        view.initContent();
    }

    @Override
    public void onRequestFailed(String message) {
        view.dismissProgressBar();
        view.showToast(message);
    }

    @Override
    public void uploadReport(String reportText) {
        view.showProgressBar();
        model.uploadToServer(spAmbulan.getSpToken(), reportText);
    }

    @Override
    public void onUploadSuccess() {
        view.showToast("Berhasil mengirim pengaduan");
        view.goToHome();
    }
}
