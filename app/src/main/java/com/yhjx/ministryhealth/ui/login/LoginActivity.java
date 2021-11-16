package com.yhjx.ministryhealth.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.MainActivity;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.LoginBean;
import com.yhjx.ministryhealth.constants.SpConstants;
import com.yhjx.ministryhealth.mvp.contract.LoginContract;
import com.yhjx.ministryhealth.mvp.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    private EditText editPhone;
    private EditText editPaw;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvResetPaw;
    private TextView tvProtocol;

    private LoginPresenter loginPresenter;
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
        tvProtocol = findViewById(R.id.tv_protocol);
        tvProtocol.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loginPresenter=new LoginPresenter(this,this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (editPhone.getText().length() < 11) {
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
                if (editPaw.getText().length() == 0) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                loginPresenter.login(editPhone.getText().toString(),editPaw.getText().toString());
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_reset_paw:
                startActivity(new Intent(this, ResetPAWActivity.class));
                break;
            case R.id.tv_protocol:
                startActivity(new Intent(this, ProtocolActivity.class));
                break;
        }
    }

    @Override
    protected boolean isImmersionBar() {
        return true;
    }

    @Override
    public void loginSuccess(LoginBean data) {
        CloudPushService mPushService = PushServiceFactory.getCloudPushService();
        mPushService.bindAccount(data.getPhone(), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.d("推送", "onSuccess: 绑定账号成功");
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.d("推送", "onFailed: "+s+"----"+s1);
            }
        });
        SPUtils.getInstance().put(SpConstants.SP_KEY_PHONE,data.getPhone());
        SPUtils.getInstance().put(SpConstants.SP_KEY_USER_TOKEN,data.getToken());
        SPUtils.getInstance().put(SpConstants.SP_KEY_USER_DATA, JSON.toJSONString(data));
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}