package com.yhjx.ministryhealth.manager;

import android.app.Activity;


import com.library.basemodule.util.SPUtils;
import com.library.basemodule.util.ToastUtils;


import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;

public class AppManager {

    // Activity栈
    private static LinkedList<WeakReference<Activity>> activityStack;
    // 单例模式
    private static AppManager instance;

    private AppManager() {
        if (activityStack == null) {
            activityStack = new LinkedList<>();
        }
    }

    /**
     * 单一实例
     */
    public static synchronized AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(new WeakReference<>(activity));
    }

    public void popActivity(Activity activity) {
        if (activityStack != null && !activityStack.isEmpty()) {
            for (Iterator<WeakReference<Activity>> iterator = activityStack.iterator(); iterator.hasNext(); ) {
                WeakReference<Activity> weakReference = iterator.next();
                Activity a = weakReference.get();
                if (activity != null && a.equals(activity)) {
                    iterator.remove();
                    return;
                }
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : activityStack) {
            if (activity.get() != null && activity.get().getClass().equals(cls)) {
                finishActivity(activity.get());
                break;
            }
        }
    }

    /**
     * 该Activity 是否被finish
     *
     * @return
     */
    public boolean isFinishActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : activityStack) {
            if (activity.get() != null && activity.get().getClass().equals(cls)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                Activity a = activityStack.get(i).get();
                if (a != null) {
                    a.finish();
                }
            }
        }
        activityStack.clear();
    }

    public void finishBeforeActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                Activity a = activityStack.get(i).get();
                if (a != null) {
                    a.finish();
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            //退出程序
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(520);
        } catch (Exception e) {
        }
    }
    /**
     * 退出登录
     * */
    public void Logout(){
//        CloudPushService mPushService = PushServiceFactory.getCloudPushService();
//        mPushService.unbindAccount(new CommonCallback() {
//            @Override
//            public void onSuccess(String s) {
//                Log.d("推送", "onSuccess: 解绑成功");
//            }
//
//            @Override
//            public void onFailed(String s, String s1) {
//                Log.d("推送", "onSuccess: 解绑失败"+s+"---"+s1);
//            }
//        });
        finishAllActivity();
        SPUtils.getInstance().clear();
       // Utils.getApp().getApplicationContext().startActivity(new Intent(Utils.getApp().getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }


    private long firstTime;// 记录点击返回时第一次的时间毫秒值
    public void BackPressed() {
        if(System.currentTimeMillis() - firstTime >= 2000){
            ToastUtils.showShort("再按一次退出应用");
            firstTime = System.currentTimeMillis();
        }else {
            AppExit();
        }
    }
}
