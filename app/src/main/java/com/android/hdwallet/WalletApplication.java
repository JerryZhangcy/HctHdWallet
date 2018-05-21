package com.android.hdwallet;

import android.app.Application;
import android.content.Context;

public class WalletApplication extends Application {
    public static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }
}
