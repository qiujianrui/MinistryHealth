package com.yhjx.ministryhealth.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText editPhone;
    private EditText editPaw;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvResetPaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        editPhone = findViewById(R.id.edit_phone);
        editPaw = findViewById(R.id.edit_paw);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        tvResetPaw = findViewById(R.id.tv_reset_paw);
        tvResetPaw.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (editPhone.getText().length()<11){
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
                if (editPaw.getText().length()==0){
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_reset_paw:
                startActivity(new Intent(this, ResetPAWActivity.class));

                break;
        }
    }
}