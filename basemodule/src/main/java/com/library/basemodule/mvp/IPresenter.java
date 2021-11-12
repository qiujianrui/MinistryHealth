package com.library.basemodule.mvp;

import android.app.Activity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author 坚锐
 * @create 2019/7/18
 * @Describe
 */
public interface IPresenter<V extends IView> extends LifecycleObserver {
    /**
     * 绑定 View ，一般在初始化时调用
     *
     * @param view
     */
    void attachView(V view);
    /**
     * 绑定 Activity ，一般在初始化时调用
     *
     * @param activity
     */
    void attachActivity(Activity activity);
    /**
     * 解除绑定 View,一般在 onDestroy 中调用
     */
    void detachView();
    /**
     * 解除绑定 Activity,一般在 onDestroy 中调用
     */
    void detachActivity();
    /**
     * 是否绑定了View,一般在发起网络请求之前调用
     *
     * @return
     */
    boolean isViewAttached();
    /**
     * 是否绑定了Activity,一般在发起网络请求之前调用
     *
     * @return
     */
    boolean isActivityAttached();

    V getView();


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner);
}
