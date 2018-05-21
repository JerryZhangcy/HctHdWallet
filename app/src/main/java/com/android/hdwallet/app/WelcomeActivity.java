package com.android.hdwallet.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.android.hdwallet.storage.CommonStorage;

public class WelcomeActivity extends AppCompatActivity {
    private static final int MSG_NEXT = 0x100;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_NEXT) {
                toLicenceIfNecessary();
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessageDelayed(MSG_NEXT, 1000);
    }

    private void toLicenceIfNecessary() {
        CommonStorage commonStorage = CommonStorage.getInstance();
        if (!commonStorage.getBooleanFromPreference("wallet_licence")) {
            Intent i = new Intent();
            i.setClass(this, LicenceActivity.class);
            startActivity(i);
        }
    }
}
