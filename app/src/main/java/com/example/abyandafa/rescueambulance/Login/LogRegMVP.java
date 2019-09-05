package com.example.abyandafa.rescueambulance.Login;

import com.example.abyandafa.rescueambulance.baseclass.BaseMVP;
import com.example.abyandafa.rescueambulance.model.Ambulan;

/**
 * Created by Abyan Dafa on 24/04/2018.
 */

public interface LogRegMVP {

    interface View extends BaseMVP.View{
        void gotoMain();
        void showProgressBar();
        void dismissProgressBar();
    }

    interface ViewPresenter extends BaseMVP.ViewPresenter{
        void loginUser(String username, String password);
    }

    interface ModelPresenter extends BaseMVP.ModelPresenter{
        void onLoginSuccess(Ambulan user);
    }

    interface Model{
        void authenticateUser(String username, String password);
    }

}
