package com.library.basemodule.util.notchtools.phone;

import android.app.Activity;
import android.view.Window;

import com.library.basemodule.util.notchtools.core.AbsNotchScreenSupport;
import com.library.basemodule.util.notchtools.core.OnNotchCallBack;


/**
 * @author zhangzhun
 * @date 2018/11/5
 */

public class CommonScreen extends AbsNotchScreenSupport {

    @Override
    public boolean isNotchScreen(Window window) {
        return false;
    }

    @Override
    public int getNotchHeight(Window window) {
        return 0;
    }

    @Override
    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack notchCallBack) {
        super.fullScreenDontUseStatus(activity, notchCallBack);
    }

    @Override
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack notchCallBack) {
        super.fullScreenUseStatus(activity, notchCallBack);
    }

}
