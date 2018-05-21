package com.android.hdwallet.storage;

import android.content.Context;
import android.content.SharedPreferences;

import static com.android.hdwallet.WalletApplication.mAppContext;

public class CommonStorage {
    private static CommonStorage mInstance;
    public static final String PREFERENCE_NAME = "wallet_common";

    public synchronized static CommonStorage getInstance() {
        if (mInstance == null)
            return new CommonStorage();
        return mInstance;
    }

    public void saveDataToPreference(String key, boolean value) {
        SharedPreferences sharedPreferences = mAppContext.
                getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void saveDataToPreference(String key, String value) {
        SharedPreferences sharedPreferences = mAppContext.
                getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getBooleanFromPreference(String key) {
        SharedPreferences sharedPreferences = mAppContext.
                getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public String getStringFromPreference(String key) {
        SharedPreferences sharedPreferences = mAppContext.
                getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
}
