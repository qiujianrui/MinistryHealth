package com.yhjx.ministryhealth.ui.me;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.library.basemodule.dialog.CommonDialog;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.listener.OnConfirmClickListener;
import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseFragment;
import com.yhjx.ministryhealth.manager.AppManager;
import com.yhjx.ministryhealth.mvp.contract.LoginOutContract;
import com.yhjx.ministryhealth.mvp.presenter.LoginOutPresenter;
import com.yhjx.ministryhealth.ui.login.ChangePasswordActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener, LoginOutContract.View {

    private ImageView imgHead;
    private TextView tvName;
    private TextView tvPhone;
    private LinearLayout llResetPaw;
    private LinearLayout llUpdate;
    private TextView tvLogout;

    private LoginOutPresenter loginOutPresenter;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        imgHead = view.findViewById(R.id.img_head);
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);
        llResetPaw = view.findViewById(R.id.ll_reset_paw);
        llResetPaw.setOnClickListener(this);

        llUpdate = view.findViewById(R.id.ll_update);
        tvLogout = view.findViewById(R.id.tv_logout);
        tvLogout.setOnClickListener(this);
    }

    private void initData() {
        loginOutPresenter=new LoginOutPresenter(this,getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_reset_paw:
                startActivity(new Intent(getContext(), ChangePasswordActivity.class ));
                break;
            case R.id.tv_logout:
                CommonDialog commonLogoutDialog=new CommonDialog(getContext());
                commonLogoutDialog.setMessage("确定要退出登录?").setCancel("取消").setConfirm("确定")
                        .setOnConfirmClickListener(new OnConfirmClickListener() {
                            @Override
                            public void onConfirmClick(Dialog dialog, View view) {
                                loginOutPresenter.loginOut();
                            }
                        });
                commonLogoutDialog.show();
                break;
        }
    }

    @Override
    public void loginOutSuccess(BaseEntity data) {
        AppManager.getAppManager().Logout();
        ToastUtils.showShort("退出登录成功");
    }
}