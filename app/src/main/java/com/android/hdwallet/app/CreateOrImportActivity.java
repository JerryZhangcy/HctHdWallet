package com.android.hdwallet.app;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.android.hdwallet.R;

public class CreateOrImportActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitleView;
    private Button mCreateBtn;
    private Button mImportBtn;
    private ObjectAnimator mTitleViewAnimator;
    private ObjectAnimator mCreateBtnAnimator;
    private ObjectAnimator mImportBtnAnimator;
    private int mScreenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_import_layout);
        mTitleView = findViewById(R.id.create_import_title);

        mCreateBtn = findViewById(R.id.create_wallet);
        mCreateBtn.setOnClickListener(this);
        mImportBtn = findViewById(R.id.import_wallet);
        mImportBtn.setOnClickListener(this);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenHeight = dm.heightPixels;

        initAnimation();
        startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_wallet:
                Intent createIntent = new Intent();
                createIntent.setClass(this, CreateActivity.class);
                startActivity(createIntent);
                break;
            case R.id.import_wallet:

                break;

        }
    }

    private void initAnimation() {
        titleAnimation();
        createAnimation();
        importAnimation();
    }

    private void startAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.play(mCreateBtnAnimator).with(mImportBtnAnimator).after(mTitleViewAnimator);
        animatorSet.start();
    }

    private void titleAnimation() {
        mTitleViewAnimator = ObjectAnimator.ofFloat(mTitleView, "translationY",
                mScreenHeight, 0);
        mTitleViewAnimator.setDuration(500);
        mTitleViewAnimator.setInterpolator(new DecelerateInterpolator());
        mTitleViewAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
                mTitleView.setTranslationY(mScreenHeight);
                mTitleView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                mTitleView.setTranslationY(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void createAnimation() {
        mCreateBtnAnimator = ObjectAnimator.ofFloat(mCreateBtn, "translationY",
                mScreenHeight, 0);
        mCreateBtnAnimator.setDuration(800);
        mCreateBtnAnimator.setInterpolator(new DecelerateInterpolator());
        mCreateBtnAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
                mCreateBtn.setTranslationY(mScreenHeight);
                mCreateBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                mCreateBtn.setTranslationY(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void importAnimation() {
        mImportBtnAnimator = ObjectAnimator.ofFloat(mImportBtn, "translationY",
                mScreenHeight, 0);
        mImportBtnAnimator.setDuration(800);
        mImportBtnAnimator.setInterpolator(new DecelerateInterpolator());
        mImportBtnAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
                mImportBtn.setTranslationY(mScreenHeight);
                mImportBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                mImportBtn.setTranslationY(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub

            }
        });
    }
}
