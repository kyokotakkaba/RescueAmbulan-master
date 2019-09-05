package com.example.abyandafa.rescueambulance.CodeBlue.Locate;

import android.content.Context;

import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;

/**
 * Created by Abyan Dafa on 26/04/2018.
 */

public class LocatePresenter implements LocateMVP.ModelPresenter, LocateMVP.ViewPresenter {

    private LocateMVP.View view;
    private LocateMVP.Model model;
    private SPTransaksi spTransaksi;
    private SPAmbulan spAmbulan;
    private Context context;

    public LocatePresenter(LocateMVP.View view) {
        this.view = view;
        this.context = view.getContext();
        this.spAmbulan = new SPAmbulan(context);
        this.spTransaksi = new SPTransaksi(context);
        this.model = new LocateModel(this);

    }

    @Override
    public void start() {
        view.initView();
        view.initContent();
    }

    @Override
    public void onRequestFailed(String message) {
        this.view.dismissProgressBar();
        this.view.showToast(message);

    }

    @Override
    public void getVerify() {
        this.view.showProgressBar();
        this.model.uploadVerifLocation(spAmbulan.getSpToken(), String.valueOf(spAmbulan.getID()), spTransaksi.getID());
    }

    @Override
    public void logoutAmbulan() {
        this.spAmbulan.logout(1);
    }

    @Override
    public Double getLatitude() {
        return Double.valueOf(spTransaksi.getSpLatitude());
    }

    @Override
    public Double getLongitude() {
        return Double.valueOf(spTransaksi.getSpLongitude());
    }

    @Override
    public String getLocation() {
        return spTransaksi.getSpLokasi();
    }

    @Override
    public void onVerifSuccess(boolean isStillLogin) {
        if(!isStillLogin) logoutAmbulan();
        else
        {
            this.view.dismissProgressBar();
            this.view.goToFollowUp();
        }

    }
}
