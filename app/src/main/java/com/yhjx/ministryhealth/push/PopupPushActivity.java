package com.yhjx.ministryhealth.push;

import android.content.Intent;

import com.alibaba.sdk.android.push.AndroidPopupActivity;
import com.library.basemodule.util.LogUtils;
import com.yhjx.ministryhealth.SplashActivity;

import java.util.Map;

/**
 * @author
 * @create
 * @Describe 辅助推送通道指定打开的弹窗activity,目前包括:小米弹窗、华为弹窗
 */
public  class PopupPushActivity extends AndroidPopupActivity {
    private static final String TAG = "PopupPushActivity";
    @Override
    protected void onSysNoticeOpened(String title, String content, Map<String, String> extraMap) {
        LogUtils.dTag(TAG, "Receive ThirdPush notification, title: " + title + ", content: " + content + ", extraMap: " + extraMap);
        //打开主activity
        Intent intent=new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
