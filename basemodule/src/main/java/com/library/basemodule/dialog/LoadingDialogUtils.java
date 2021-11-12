package com.library.basemodule.dialog;

import android.app.Activity;
import android.content.Context;

import com.library.basemodule.util.LogUtils;

import java.lang.ref.WeakReference;


public class LoadingDialogUtils {
    public static final int TYPE_1 = 1;//默认加载框
    //  加载进度的dialog
    private LoadingDialog mLoadingDialog;
    private WeakReference<Context> contextWeakReference;
    public LoadingDialog getProgressDialog() {
        return mLoadingDialog;
    }
    public LoadingDialogUtils(Context context) {
        contextWeakReference=new WeakReference<Context>(context);
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(contextWeakReference.get());
        }
    }
    /**
     * 显示ProgressDialog
     */
    public void showProgress() {
            if (mLoadingDialog != null) {
                if (isValidContext() && !mLoadingDialog.isShowing()) {
                    mLoadingDialog.show();
                } else {
                    mLoadingDialog.dismiss();
                }
            }
    }


    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
            if (mLoadingDialog != null && mLoadingDialog.isShowing() && isValidContext()) {
                mLoadingDialog.dismiss();
            }
    }


    /**
     * @return Activity 是否可用
     */
    private boolean isValidContext() {

        if (contextWeakReference.get() instanceof Activity) {
            Activity a = (Activity) contextWeakReference.get() ;
            if (a.isDestroyed() || a.isFinishing()) {
                LogUtils.v("Activity is invalid." + " isDestoryed-->" + a.isDestroyed() + " isFinishing-->" + a.isFinishing());
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
