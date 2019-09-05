package com.example.abyandafa.rescueambulance.Notification;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;

/**
 * Created by Abyan Dafa on 28/04/2018.
 */

public interface NotificationMVP {
    interface View extends BaseMVP.View{
        void goLocate();
        void onPanicView();
        void onCodeBlueView();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void acceptNotification();
        void setNotificationType();
        String getLocation();
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onAcceptSuccess();
        void onNotLogin();
    }

    interface Model{
        void uploadToServer(String token, String idAmbulan, String idTransaksi);
    }
}
