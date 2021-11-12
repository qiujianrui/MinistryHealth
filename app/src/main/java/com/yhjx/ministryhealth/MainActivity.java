package com.yhjx.ministryhealth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.ui.consult.ConsultMainActivity;
import com.yhjx.ministryhealth.ui.home.HomeFragment;
import com.yhjx.ministryhealth.ui.me.MeFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final ArrayList<Fragment> fragments=new ArrayList<>(2);
    private static final String[] TAGS = {"HOME", "ME"};
    private int prePos = 0;
    private FrameLayout flNvFragment;
    private ImageView imgBarHome;
    private ImageView imgBarMe;
    private ImageView imgBarConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildFragmentList();
        setDefaultFragment(prePos);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        flNvFragment = findViewById(R.id.fl_nv_fragment);
        imgBarHome = findViewById(R.id.img_bar_home);
        imgBarHome.setOnClickListener(this);
        imgBarMe = findViewById(R.id.img_bar_me);
        imgBarMe.setOnClickListener(this);
        imgBarConsult = findViewById(R.id.img_bar_consult);
        imgBarConsult.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    private void buildFragmentList() {
        HomeFragment homeFragment = new HomeFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(meFragment);
    }

    private void setDefaultFragment(int pos) {
        Fragment now = fragments.get(pos);
        if (!now.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_nv_fragment, fragments.get(prePos), TAGS[pos])
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(now)
                    .commitAllowingStateLoss();
        }
        resetMenuState(pos);
    }

    private void switchFragment(int pos) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment from = fragments.get(prePos);
        Fragment to = fragments.get(pos);
        if (!to.isAdded()) {
            transaction.hide(from)
                    .add(R.id.fl_nv_fragment, fragments.get(pos), TAGS[pos])
                    .commitAllowingStateLoss();
        } else {
            transaction.hide(from)
                    .show(to)
                    .commitAllowingStateLoss();
        }
        prePos=pos;
        resetMenuState(pos);
    }

    private void resetMenuState(int prePos) {
        imgBarHome.setSelected(false);
        imgBarMe.setSelected(false);
        switch (prePos){
            case 0:
                imgBarHome.setSelected(true);
                break;
            case 1:
                imgBarMe.setSelected(true);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_bar_home:
                switchFragment(0);
                break;
            case R.id.img_bar_me:
                switchFragment(1);
                break;
            case R.id.img_bar_consult:
                startActivity(new Intent(this, ConsultMainActivity.class));
                break;
        }
    }
}