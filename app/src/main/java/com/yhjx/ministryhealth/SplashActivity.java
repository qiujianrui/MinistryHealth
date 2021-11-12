package com.yhjx.ministryhealth;

import android.content.Intent;

import com.library.basemodule.util.SPUtils;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.ui.login.LoginActivity;


public class SplashActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        gotoActivity();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    private void gotoActivity() {

        startActivity(new Intent(this, LoginActivity.class));
    }
}