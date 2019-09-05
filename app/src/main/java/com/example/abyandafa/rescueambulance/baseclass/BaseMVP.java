package com.example.abyandafa.rescueambulance.baseclass;

import android.content.Context;

public interface BaseMVP {
    interface View{
        void initView();
        void initContent();
        Context getContext();
        void showToast(String message);
    }

    interface ViewPresenter{
        void start();
    }

    interface ModelPresenter{
        void onRequestFailed(String message);
    }

}
