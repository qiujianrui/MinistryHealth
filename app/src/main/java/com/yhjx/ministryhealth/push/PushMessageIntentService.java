package com.yhjx.ministryhealth.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.push.AliyunMessageIntentService;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.yhjx.ministryhealth.ui.consult.ConsultMainActivity;
import com.yhjx.ministryhealth.ui.home.NotificationMessageActivity;
import com.yhjx.ministryhealth.util.BadgeUtils;


import java.util.Map;


/**
 * Created by liyazhou on 17/8/22.
 * 为避免推送广播被系统拦截的小概率事件,我们推荐用户通过IntentService处理消息互调,接入步骤:
 * 1. 创建IntentService并继承AliyunMessageIntentService
 * 2. 覆写相关方法,并在Manifest的注册该Service
 * 3. 调用接口CloudPushService.setPushIntentService
 * 详细用户可参考:https://help.aliyun.com/document_detail/30066.html#h2-2-messagereceiver-aliyunmessageintentservice
 */

public class PushMessageIntentService extends AliyunMessageIntentService {
    private static final String TAG = "MyMessageIntentService";

    /**
     * 推送通知的回调方法
     * @param context
     * @param summary
     * @param title
     * @param extraMap
     */
    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        Log.i(TAG,"收到一条推送通知 ： " + title + ", summary:" + summary+"通知id："+extraMap.get("_ALIYUN_NOTIFICATION_ID_")+"-extraMap==" +extraMap);
      //  Log.d(TAG, "onNotification: "+extraMap.get("pushType"));
        if(!extraMap.get("pushType").isEmpty()) {

        }
//        if(!extraMap.get("count").isEmpty()) {
//            BadgeUtils.setCount(Integer.parseInt(extraMap.get("count")), context);
//
//        }
    }

    /**
     * 推送消息的回调方法
     * @param context
     * @param cPushMessage
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        Log.i(TAG,"收到一条推送消息 ： " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());

    }

    /**
     * 从通知栏打开通知的扩展处理
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.i(TAG,"onNotificationOpened ： " + " : " + title + " : " + summary + " : " + extraMap);
        JSONObject jsonObject= JSONObject.parseObject(extraMap);
        switch (jsonObject.getString("pushType")){
            case "1":
                startActivity(new Intent(context, NotificationMessageActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case "2":
                startActivity(new Intent(context, ConsultMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }

    /**
     * 无动作通知点击回调。当在后台或阿里云控制台指定的通知动作为无逻辑跳转时,通知点击回调为onNotificationClickedWithNoAction而不是onNotificationOpened
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.i(TAG,"onNotificationClickedWithNoAction ： " + " : " + title + " : " + summary + " : " + extraMap);

    }

    /**
     * 通知删除回调
     * @param context
     * @param messageId
     */
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.i(TAG, "onNotificationRemoved ： " + messageId);

    }

    /**
     * 应用处于前台时通知到达回调。注意:该方法仅对自定义样式通知有效,相关详情请参考https://help.aliyun.com/document_detail/30066.html#h3-3-4-basiccustompushnotification-api
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     * @param openType
     * @param openActivity
     * @param openUrl
     */
    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.i(TAG,"onNotificationReceivedInApp ： " + " : " + title + " : " + summary + "  " + extraMap + " : " + openType + " : " + openActivity + " : " + openUrl);

    }
}
