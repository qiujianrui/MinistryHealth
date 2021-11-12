package com.yhjx.ministryhealth.ui.login;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.yhjx.ministryhealth.R;
import com.yhjx.ministryhealth.base.BaseActivity;
import com.yhjx.ministryhealth.bean.ProtocolPrivacyBean;
import com.yhjx.ministryhealth.mvp.contract.ProtocolAndPrivacyContract;
import com.yhjx.ministryhealth.mvp.presenter.ProtocolAndPrivacyPresenter;


/**
 * @author
 * @time 2020/6/9
 * 说明：协议界面
 */
public class ProtocolActivity extends BaseActivity implements ProtocolAndPrivacyContract.View, View.OnClickListener {


    private String htmlUserProtocol, htmlPrivacy;
    private ImageView imgBack;
    private View viewUserProtocol;
    private View viewPrivacy;
    private TextView tvBtnUserProtocol;
    private TextView tvBtnPrivacy;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initView() {

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        viewUserProtocol = findViewById(R.id.view_userProtocol);
        viewPrivacy = findViewById(R.id.view_privacy);
        tvBtnUserProtocol = findViewById(R.id.tv_btn_userProtocol);
        tvBtnUserProtocol.setOnClickListener(this);
        tvBtnPrivacy = findViewById(R.id.tv_btn_privacy);
        tvBtnPrivacy.setOnClickListener(this);
        webView = findViewById(R.id.webView);
    }

    @Override
    protected void initData() {
        ProtocolAndPrivacyPresenter privacyPresenter = new ProtocolAndPrivacyPresenter(this, mActivity);
        privacyPresenter.getProtocolAndPrivacy();
    }

    @Override
    protected void setListener() {

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.clearHistory();
            webView.clearCache(true);
            webView.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            webView.freeMemory();
            webView.pauseTimers();
            webView = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }

    }

    @Override
    public void protocolAndPrivacySuccess(ProtocolPrivacyBean data) {
        htmlUserProtocol = data.getProtocolContent().replace("\n", "<br>");
        htmlPrivacy = data.getPrivacyContent().replace("\n", "<br>");;
        webView.loadData(htmlUserProtocol, "text/html; charset=UTF-8", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_btn_userProtocol:
                tvBtnUserProtocol.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                tvBtnUserProtocol.setTextColor(Color.parseColor("#000000"));
                tvBtnUserProtocol.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                viewUserProtocol.setVisibility(View.VISIBLE);

                tvBtnPrivacy.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tvBtnPrivacy.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tvBtnPrivacy.setTextColor(Color.parseColor("#8595A6"));
                viewPrivacy.setVisibility(View.INVISIBLE);


                webView.loadData(htmlUserProtocol, "text/html; charset=UTF-8", null);
                break;
            case R.id.tv_btn_privacy:
                tvBtnPrivacy.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                tvBtnPrivacy.setTextColor(Color.parseColor("#000000"));
                tvBtnPrivacy.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                viewPrivacy.setVisibility(View.VISIBLE);

                tvBtnUserProtocol.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tvBtnUserProtocol.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tvBtnUserProtocol.setTextColor(Color.parseColor("#8595A6"));
                viewUserProtocol.setVisibility(View.INVISIBLE);


                webView.loadData(htmlPrivacy, "text/html; charset=UTF-8", null);

                break;
        }
    }
}
