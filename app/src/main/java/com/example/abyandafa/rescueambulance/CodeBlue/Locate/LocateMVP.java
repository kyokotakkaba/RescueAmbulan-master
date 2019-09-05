package com.example.abyandafa.rescueambulance.CodeBlue.Locate;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;

/**
 * Created by Abyan Dafa on 26/04/2018.
 */

public interface LocateMVP {

    interface View extends BaseMVP.View{
        void goToFollowUp();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void getVerify();
        void logoutAmbulan();
        Double getLatitude();
        Double getLongitude();
        String getLocation();
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onVerifSuccess(boolean isStillLogin);

    }

    interface Model{
        void uploadVerifLocation(String token, String idAmbulan, String idTransaction);
    }

}
