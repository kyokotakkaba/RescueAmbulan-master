package com.example.abyandafa.rescueambulance.Notification;

import android.content.Context;

import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;

/**
 * Created by Abyan Dafa on 28/04/2018.
 */

public class NotificationPresenter implements  NotificationMVP.ModelPresenter, NotificationMVP.ViewPresenter {

    private NotificationMVP.View view;
    private NotificationMVP.Model model;

    private SPTransaksi spTransaksi;
    private SPAmbulan spAmbulan;
    private Context context;

    @Override
    public void start() {
        view.initView();
        view.initContent();
        this.setNotificationType();
    }

    public NotificationPresenter(NotificationMVP.View view) {
        this.view = view;
        this.context = view.getContext();
        spAmbulan = new SPAmbulan(this.context);
        spTransaksi = new SPTransaksi(this.context);
        this.model = new NotificationModel(this);
    }

    @Override
    public void onRequestFailed(String message) {
        view.dismissProgressBar();
        view.showToast(message);
    }

    @Override
    public void acceptNotification() {
        view.showProgressBar();
        model.uploadToServer(spAmbulan.getSpToken(), String.valueOf(spAmbulan.getID()), spTransaksi.getID());
    }

    @Override
    public void setNotificationType() {
        if(spTransaksi.getSpTipe().equals("1")) view.onPanicView();
        else view.onCodeBlueView();
    }

    @Override
    public String getLocation() {
        return spTransaksi.getSpLokasi();
    }

    @Override
    public void onAcceptSuccess() {
        view.goLocate();
    }

    @Override
    public void onNotLogin() {
        spAmbulan.logout(1);
    }
}
