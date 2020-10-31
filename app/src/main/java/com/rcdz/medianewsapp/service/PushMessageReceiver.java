package com.rcdz.medianewsapp.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class PushMessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "PushMessageReceiver";
    private String cLiveUrl;
    private String channelid;
    private String picUrl;
    private String channelName;
    //{"cLiveUrl":"http:\/\/ivi.bupt.edu.cn\/hls\/cctv1hd.m3u8","channelName":"CCTV-1","channelid":"20200415000002","picUrl":"Upload\/Tables\/Channelmanager\/202008011857537381\/３１.png"}
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG,"[onMessage] "+customMessage);
        processCustomMessage(context,customMessage);
    }

    //NotificationMessage{notificationId=533922698, msgId='67554069808431977', appkey='3c93e1e733d34e41a90b1a95', notificationContent='通知消息', notificationAlertType=-1, notificationTitle='你预约的节目第一动画乐园-2020-282马上就要开始了，请及时查看', notificationSmallIcon='', notificationLargeIcon='', notificationExtras='{}', notificationStyle=0, notificationBuilderId=0, notificationBigText='', notificationBigPicPath='', notificationInbox='', notificationPriority=0, notificationCategory='', developerArg0='', platform=0, notificationChannelId='', displayForeground='', notificationType=0', inAppIntent=', inAppMsgContent=', inAppMsgType=1', inAppMsgShowType=2', inAppMsgShowPos=0', inAppMsgShowAniAction=0', inAppMsgDismissAniAction=0', inAppMsgShowBackground=0', inAppMsgAllowIntercept= 1', inAppMsgFilterMsg=0', inAppMsgPicPath=', inAppMsgCountLmt=5', inAppMsgGap=1800', inAppMsgToUser=0', inAppMsgDelayDisplayTime=10', inAppMsgBackgroundColor=#FFFFFFFF', inAppMsgTitleColor=#FF000000', inAppMsgContentColor=#FF000000', inAppMsgTitleSize=14', inAppMsgContentSize=14', inAppMsgCircularSize=9', inAppMsgShowTime=5', inAppMsgShowElapseTime=0.5', inAppMsgDismissElapseTime=0.5, inAppMsgTitle=, inAppMsgContentBody=}
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        Log.e("test","通知栏收到预约信息"+message);
        String info=message.notificationExtras;
        try{

            try {
                if(!info.equals("")){
                    JSONObject jsonObject=new JSONObject(info);
                    cLiveUrl=jsonObject.getString("cLiveUrl");
                    channelName=jsonObject.getString("channelName");
                    channelid=jsonObject.getString("channelid");
                    picUrl=jsonObject.getString("picUrl");
                    //转到视频列表
//                    Intent intent = new Intent(context, VideoPlayerActivity.class);
//                    intent.putExtra("channelid", channelid);
//                    intent.putExtra("cLiveUrl", cLiveUrl);
//                    intent.putExtra("picUrl", picUrl);
//                    intent.putExtra("channeName",channelName);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            //点击了通知栏
            //打开自定义的Activity
//            Intent i = new Intent(context, TestActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE,message.notificationTitle);
//            bundle.putString(JPushInterface.EXTRA_ALERT,message.notificationContent);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);
        }catch (Throwable throwable){

        }
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if(nActionExtra==null){
            Log.d(TAG,"ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        //{"cLiveUrl":"http:\/\/ivi.bupt.edu.cn\/hls\/cctv1hd.m3u8","channelName":"CCTV-1","channelid":"20200415000002","picUrl":"Upload\/Tables\/Channelmanager\/202004240931415949\/2012052017214581.jpg"}
        Log.e(TAG,"[onNotifyMessageArrived] "+message);

    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        //移除了通知栏
        Log.e(TAG,"[onNotifyMessageDismiss] "+message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG,"[onRegister] "+registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG,"[onConnected] "+isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG,"[onCommandResult] "+cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage){
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {

            String message = customMessage.message;
            String extras = customMessage.extra;
        //{"cLiveUrl":"http:\/\/ivi.bupt.edu.cn\/hls\/cctv1hd.m3u8","channelName":"CCTV-1","channelid":"20200415000002","picUrl":"Upload\/Tables\/Channelmanager\/202008011857537381\/３１.png"}




    }

    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        Log.e(TAG,"[onNotificationSettingsCheck] isOn:"+isOn+",source:"+source);
    }
    
}
