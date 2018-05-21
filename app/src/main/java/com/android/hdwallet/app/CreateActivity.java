package com.android.hdwallet.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hdwallet.R;
import com.android.hdwallet.Util;
import com.android.hdwallet.storage.CommonStorage;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageView mBack;
    private EditText mName, mPws, mPwsConfirm;
    private CheckBox mCheckBox;
    private TextView mJumpLicence, mNext;
    private AlertDialog mAlertDialog;

    private static final int PWS_LENGTH = 8;
    private static final int VALUE_TYPE_NULL = 1;
    private static final int VALUE_TYPE_NO_MATCH = 2;
    private static final int VALUE_TYPE_PWS_LENGTH = 3;
    private static final int VALUE_TYPE_OK = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.common_back);
        mBack.setOnClickListener(this);
        mName = findViewById(R.id.create_wallet_name);
        mPws = findViewById(R.id.create_wallet_pws);
        mPwsConfirm = findViewById(R.id.create_wallet_pws_confirm);
        mCheckBox = findViewById(R.id.create_wallet_checbox);
        mCheckBox.setOnCheckedChangeListener(this);
        mJumpLicence = findViewById(R.id.create_wallet_to_licence);
        mJumpLicence.setOnClickListener(this);
        mNext = findViewById(R.id.create_next);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.common_back:
                finish();
                break;
            case R.id.create_wallet_to_licence:
                Intent i = new Intent();
                i.setClass(this, LicenceSimpleActivity.class);
                startActivity(i);
                break;
            case R.id.create_next:
                int type = checkValueAvailable();
                switch (type) {
                    case VALUE_TYPE_NULL:
                        showWaringDialog(R.string.create_wallet_null);
                        break;
                    case VALUE_TYPE_PWS_LENGTH:
                        showWaringDialog(R.string.create_wallet_pws_length);
                        break;
                    case VALUE_TYPE_NO_MATCH:
                        showWaringDialog(R.string.create_wallet_pws_not_match);
                        break;
                    case VALUE_TYPE_OK:
                        CommonStorage.getInstance().saveDataToPreference("wallet_name",
                                mName.getText().toString());
                        CommonStorage.getInstance().saveDataToPreference("wallet_pws",
                                mPws.getText().toString());
                        Intent intent = new Intent();
                        intent.setClass(this, BackUpActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == mCheckBox.getId()) {
            mNext.setEnabled(isChecked ? true : false);
            mNext.setBackgroundColor(isChecked ? getResources().getColor(R.color.sky_blue)
                    : getResources().getColor(R.color.dark_gray));
        }
    }

    private int checkValueAvailable() {
        String name = mName.getText().toString();
        String pws = mPws.getText().toString();
        String pwsConfirm = mPwsConfirm.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pws) || TextUtils.isEmpty(pwsConfirm)) {
            return VALUE_TYPE_NULL;
        }

        if (pws.length() != PWS_LENGTH) {
            return VALUE_TYPE_PWS_LENGTH;
        }

        if (!pws.equals(pwsConfirm)) {
            return VALUE_TYPE_NO_MATCH;
        }

        return VALUE_TYPE_OK;
    }

    private void showWaringDialog(int id) {
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.create_wallet_dialog_title)
                .setMessage(id)
                .setPositiveButton(R.string.create_wallet_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAlertDialog.dismiss();
                    }
                }).create();
        mAlertDialog.show();
    }
}
