package com.yhjx.ministryhealth.push;

import android.content.Intent;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.push.AndroidPopupActivity;
import com.library.basemodule.util.LogUtils;
import com.yhjx.ministryhealth.SplashActivity;
import com.yhjx.ministryhealth.ui.consult.ConsultMainActivity;
import com.yhjx.ministryhealth.ui.home.NotificationMessageActivity;

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
        switch (extraMap.get("pushType")){
            case "1":
                startActivity(new Intent(this, NotificationMessageActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case "2":
                startActivity(new Intent(this, ConsultMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
        finish();
    }
}
