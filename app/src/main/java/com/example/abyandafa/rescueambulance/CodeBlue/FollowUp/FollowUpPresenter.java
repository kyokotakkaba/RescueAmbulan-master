package com.example.abyandafa.rescueambulance.CodeBlue.FollowUp;

import android.content.Context;

import com.example.abyandafa.rescueambulance.model.SPAmbulan;
import com.example.abyandafa.rescueambulance.model.SPTransaksi;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public class FollowUpPresenter implements FollowUpMVP.ModelPresenter, FollowUpMVP.ViewPresenter {

    private SPAmbulan spAmbulan;
    private SPTransaksi spTransaksi;
    private FollowUpMVP.View view;
    private FollowUpMVP.Model model;
    private Context context;


    public FollowUpPresenter(FollowUpMVP.View view) {
        this.view = view;
        this.context = view.getContext();
        spAmbulan = new SPAmbulan(context);
        spTransaksi = new SPTransaksi(context);

        model = new FollowUpModel(this);

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
    public void getfollowUp(String followUp, String picnum) {
        view.showProgressBar();
        model.uploadFollowUp(followUp, picnum, spAmbulan.getSpToken(), spTransaksi.getID());
    }

    @Override
    public void logoutAmbulan() {
        spAmbulan.logout(1);
    }

    @Override
    public void onUploadSuccess(boolean isStillLogin) {
        if(isStillLogin) view.finishTransaction();
        else logoutAmbulan();
    }
}
