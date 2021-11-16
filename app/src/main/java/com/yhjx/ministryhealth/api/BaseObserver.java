package com.yhjx.ministryhealth.api;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.lifecycle.LifecycleOwner;

import com.google.gson.JsonParseException;
import com.library.basemodule.dialog.LoadingDialog;
import com.library.basemodule.dialog.LoadingDialogUtils;
import com.library.basemodule.entity.BaseEntity;
import com.library.basemodule.listener.LifecycleBaseObserver;
import com.library.basemodule.mvp.IView;
import com.library.basemodule.net.ApiException;
import com.library.basemodule.util.LogUtils;
import com.library.basemodule.util.ToastUtils;
import com.yhjx.ministryhealth.manager.AppManager;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T extends BaseEntity> implements Observer<T>, DialogInterface.OnDismissListener, LifecycleBaseObserver {
    private IView baseView;
    //  Activity 是否在执行onStop()时取消订阅
    private LoadingDialogUtils dialogUtils;

    private Disposable mDisposable;

    private WeakReference<Activity> activityWeakReference;
    private boolean isToast;

    public BaseObserver(Activity activity) {
        this(activity, true, true);
    }

    public BaseObserver(Activity activity, IView baseView, boolean isShowToast) {
        this(activity, false, isShowToast);
        this.baseView = baseView;
        if (baseView != null) {
            baseView.showLoading();
        }
    }


    public BaseObserver(Activity activity, boolean isShowLoading, boolean isShowToast) {
        isToast = isShowToast;
        dialogUtils = new LoadingDialogUtils(activity);
        activityWeakReference = new WeakReference<Activity>(activity);
        if (activityWeakReference.get() != null ) {
            ((LifecycleOwner) activityWeakReference.get()).getLifecycle().addObserver(this);
        }
        if (isShowLoading) {
            dialogUtils.showProgress();
            dialogUtils.getProgressDialog()
                    .setOnDismissListener(this);
        }

    }



    public BaseObserver(Activity activity, boolean isShowLoading, boolean isShowToast, boolean isCancelable) {
        dialogUtils = new LoadingDialogUtils(activity);
        if (isShowLoading) {
            dialogUtils.showProgress();
            LoadingDialog progressDialog = dialogUtils.getProgressDialog();
            progressDialog.setOnDismissListener(this);
            progressDialog.setCancelable(isCancelable);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T response) {
        dismissProgress();
        if (response.isOk(response.getCode())) {
            //0为成功的状态
            onSuccess(response);
        } else {
            if (isToast) {
                ToastUtils.showShort(response.getMsg());
            }
            onFailure(response.getCode(), response.getMsg());

        }
    }

    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.dismissProgress();
        }
        if (baseView != null) {
            baseView.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissProgress();
        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            switch (exception.getCode()) {// 登录失效
                default:
                    if (isToast) {
                        ToastUtils.showShort(exception.getMsg());
                    }
                    onFailure(exception.getCode(), exception.getMsg());
                    break;
                case BaseEntity.LOGOUT:
                    //处理登录失效
                    ToastUtils.showLong(exception.getMsg());
                    AppManager.getAppManager().Logout();
            }
        } else if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
        dismissProgress();
    }

    /**
     * 请求成功
     *
     * @param result 服务器返回的数据
     */
    public abstract void onSuccess(T result);

    /**
     * 请求失败
     *
     * @param msg 服务器返回的数据
     */
    public abstract void onFailure(int errCode, String msg);

    /**
     * 请求异常
     *
     * @param reason 异常的tag
     */
    private void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.showShort(com.library.basemodule.R.string.connect_error);
                onFailure(0, activityWeakReference.get().getResources().getString(com.library.basemodule.R.string.connect_error));
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.showShort(com.library.basemodule.R.string.connect_timeout);
                onFailure(0, activityWeakReference.get().getResources().getString(com.library.basemodule.R.string.connect_timeout));
                break;

            case BAD_NETWORK:
                ToastUtils.showShort(com.library.basemodule.R.string.bad_network);
                onFailure(0, activityWeakReference.get().getResources().getString(com.library.basemodule.R.string.bad_network));
                LogUtils.e("服务器异常");
                break;
            case PARSE_ERROR:
                ToastUtils.showShort(com.library.basemodule.R.string.parse_error);
                onFailure(0, activityWeakReference.get().getResources().getString(com.library.basemodule.R.string.parse_error));
                break;
            case UNKNOWN_ERROR:
            default:
                ToastUtils.showShort(com.library.basemodule.R.string.unknown_error);
                onFailure(0, activityWeakReference.get().getResources().getString(com.library.basemodule.R.string.unknown_error));
                break;

        }

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (mDisposable != null && !mDisposable.isDisposed()) { // 加载框消失的时候取消订阅
            mDisposable.dispose();
        }

    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        if (mDisposable != null && !mDisposable.isDisposed()) { // Activity关闭时取消订阅
            mDisposable.dispose();
            LogUtils.d("BaseObserver", "onDestroy: 取消订阅");
        }
    }
}

