package com.yhjx.ministryhealth.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.library.basemodule.dialog.LoadingDialog;
import com.library.basemodule.mvp.IView;
import com.library.basemodule.widget.pageLayout.PageLayout;

import com.yhjx.ministryhealth.manager.AppManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * <pre>
 *     author:
 *     desc  :  基类Activity
 *              备注:所有的Activity都继承自此Activity
 *              1.规范团队开发
 *              2.统一处理Activity所需配置,初始化
 *              Activity栈管理
 * </pre>
 */

public abstract class BaseActivity extends RxAppCompatActivity  implements IView{

    protected Context mContext;
    protected FragmentActivity mActivity;
    protected PageLayout mPageLayout;//状态显示控制
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() == 0) {
            throw new RuntimeException("layout布局文件不能为空");
        }
        setContentView(getLayoutId());
        //ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        //BarUtils.setStatusBarLightMode(mActivity,false);
        if (isImmersionBar()) {
            ImmersionBar.with(this).statusBarDarkFont(true).init();

        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        if (isShowPageLayout()) {
            initPageLayout();
        }
        initView();
        initData();
        //设置监听
        setListener();

        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

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
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().popActivity(this);
    }

    protected boolean isImmersionBar() {
        return false;
    }

    // 获取布局控件
    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView()  ;

    protected abstract void initData();

    protected abstract void setListener() ;
    /**
     *是否显示状态页面 默认false 重写该方法
     */
    protected boolean isShowPageLayout() {
        return false;
    }
    private void initPageLayout(){
        mPageLayout = new PageLayout.Builder(this)
                .initPage(this)
                .setOnRetryListener(new PageLayout.OnRetryClickListener() {
                    @Override
                    public void onRetry() {
                        reload();
                    }
                })
                .create();
    }

    /**
     * PageLayout状态页 重试按钮点击回调事件
     */
    protected  void reload(){
            initData();
    }
    @Override
    public void showLoading() {
        if (loadingDialog==null){
            loadingDialog=new LoadingDialog(this);
        }
        loadingDialog.show();
//        if (mPageLayout==null){
//            initPageLayout();
//        }
//        mPageLayout.showLoading();
    }

    @Override
    public void hideLoading() {
        if (loadingDialog==null){
            loadingDialog=new LoadingDialog(this);
        }
        loadingDialog.dismiss();
//        if (mPageLayout==null){
//            initPageLayout();
//        }
//        mPageLayout.hide();
    }

    @Override
    public void loadFailure(int errCode, String msg, String action) {
        //Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
