package com.yhjx.ministryhealth.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.mvp.contract.ChangePAWContract;
import com.yhjx.ministryhealth.mvp.presenter.ChangePAWPresenter;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener, ChangePAWContract.View {

    private ImageView imgBack;
    private EditText editOldPaw;
    private EditText editNewPaw;
    private EditText editNewPaw2;
    private Button tvSave;

    private ChangePAWPresenter changePAWPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        editOldPaw = findViewById(R.id.edit_old_paw);
        editNewPaw = findViewById(R.id.edit_new_paw);
        editNewPaw2 = findViewById(R.id.edit_new_paw2);
        tvSave = findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        changePAWPresenter=new ChangePAWPresenter(this,this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                if (editOldPaw.getText().length() ==0) {
                    ToastUtils.showShort("请输入正确旧密码");
                    return;
                }

                if (editNewPaw.getText().length() ==0) {
                    ToastUtils.showShort("请输入新密码");
                    return;
                }

                if (editNewPaw.getText().length() <6) {
                    ToastUtils.showShort("新密码不能少于6位数");
                    return;
                }

                if (editNewPaw2.getText().length() ==0) {
                    ToastUtils.showShort("请输入确认密码");
                    return;
                }

                if (editNewPaw2.getText().length() <6) {
                    ToastUtils.showShort("新密码不能少于6位数");
                    return;
                }

//                if (editNewPaw.getText().toString().equals(editNewPaw2.getText().toString())){
//                    ToastUtils.showShort("密码不一致,请重新输入");
//                    return;
//                }
                changePAWPresenter.changePassword(editNewPaw.getText().toString()
                ,editNewPaw.getText().toString()
                ,editNewPaw2.getText().toString());
                break;
        }
    }

    @Override
    public void changePasswordSuccess(BaseEntity data) {
        ToastUtils.showShort(data.getMsg());
        finish();
    }
}