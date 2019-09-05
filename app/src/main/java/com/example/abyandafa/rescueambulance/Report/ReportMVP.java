package com.example.abyandafa.rescueambulance.Report;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;

/**
 * Created by Abyan Dafa on 25/04/2018.
 */

public interface ReportMVP {

    interface View extends BaseMVP.View{
        void goToHome();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void uploadReport(String reportText);
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onUploadSuccess();
    }

    interface Model{
        void uploadToServer(String token, String reportText);
    }
}
