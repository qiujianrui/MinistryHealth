package com.yhjx.ministryhealth.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.library.basemodule.widget.TextViewCountDownView;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.mvp.contract.RegisterContract;
import com.yhjx.ministryhealth.mvp.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterContract.View {

    private ImageView imgBack;
    private EditText editPhone;
    private TextViewCountDownView btnCode;
    private EditText editCode;
    private EditText editPaw;
    private Button tvBtnLogin;
    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        editPhone = findViewById(R.id.edit_phone);
        btnCode = findViewById(R.id.btn_code);
        btnCode.setOnClickListener(this);
        editCode = findViewById(R.id.edit_code);
        editPaw = findViewById(R.id.edit_paw);
        tvBtnLogin = findViewById(R.id.tv_btn_login);
        tvBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        registerPresenter=new RegisterPresenter(this,this);
    }

    @Override
    protected void setListener() {
        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editPhone.getText().length()==11&&!btnCode.isCountDown()){
                    btnCode.setSelected(true);
                    btnCode.setEnabled(true);
                }else {
                    btnCode.setSelected(false);
                    btnCode.setEnabled(false);
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_code:
                if (editPhone.getText().length() < 11) {
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
                registerPresenter.registerVerification(editPhone.getText().toString());
                btnCode.startCountDown(60*1000);
                break;
            case R.id.tv_btn_login:
                if (editPhone.getText().length() < 11) {
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
                if (editCode.getText().length() == 0) {
                    ToastUtils.showShort("请输入验证码");
                    return;
                }
                if (editPaw.getText().length() == 0) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (editPaw.getText().length() < 6) {
                    ToastUtils.showShort("密码位数少于6位");
                    return;
                }
                registerPresenter.addLogin(editPhone.getText().toString(),
                        editPaw.getText().toString(),editCode.getText().toString());
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void registerVerificationSuccess(BaseEntity result) {
            ToastUtils.showShort(result.getMsg());
    }

    @Override
    public void addLoginSuccess(BaseEntity result) {
        ToastUtils.showShort(result.getMsg());
        finish();
    }
}