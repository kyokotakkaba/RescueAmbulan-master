package com.example.abyandafa.rescueambulance.CodeBlue.FollowUp;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public interface FollowUpMVP {

    interface View extends BaseMVP.View{
        void finishTransaction();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void getfollowUp(String followUp, String picnum);
        void logoutAmbulan();
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onUploadSuccess(boolean isStillLogin);

    }

    interface Model{
        void uploadFollowUp(String followUp, String picnum, String token, String idTransaction);
    }

}
