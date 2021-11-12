package com.library.basemodule.mvp;

import android.app.Activity;
import androidx.lifecycle.LifecycleOwner;

import com.library.basemodule.util.LogUtils;

import java.lang.ref.WeakReference;

/**
 * @author 坚锐
 * @create 2019/7/18
 * @Describe
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    //    protected V view;
    protected WeakReference<V> viewRef;
    protected WeakReference<Activity> activityRef;

    public BasePresenter(V iView,Activity activity) {
       attachView(iView);
       attachActivity(activity);
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void attachActivity(Activity activity) {
        activityRef = new WeakReference<Activity>(activity);
        if (activityRef.get() != null ) {
            ((LifecycleOwner) activityRef.get()).getLifecycle().addObserver(this);
        }
    }


    @Override
    public void detachView() {
        if (isViewAttached()) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void detachActivity() {
        if (isActivityAttached()){
            activityRef.clear();
            activityRef=null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return viewRef!=null&&viewRef.get() != null;
    }

    @Override
    public boolean isActivityAttached() {
        return activityRef != null && activityRef.get() != null;
    }

    @Override
    public V getView() {
        return viewRef.get();
    }


    @Override
    public void onDestroy(LifecycleOwner owner) {
        detachActivity();
        detachView();
        LogUtils.d("BasePresenter", "onDestroy: 取消订阅");
    }
}
