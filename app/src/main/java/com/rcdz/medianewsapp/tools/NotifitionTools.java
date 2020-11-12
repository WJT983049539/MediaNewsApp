package com.rcdz.medianewsapp.tools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.rcdz.medianewsapp.R;


/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/5 18:49
 */
public class NotifitionTools {

        private static NotificationManager manager;

        private static NotificationManager getManager(Context context) {
            if (manager == null) {
                manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            }
            return manager;
        }

        private static NotificationCompat.Builder getNotificationBuilder(Context mContext, String title
                , String content, String channelId) {
            //大于8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //id随便指定
                NotificationChannel channel = new NotificationChannel(channelId
                        , mContext.getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
                channel.canBypassDnd();//可否绕过，请勿打扰模式
                channel.enableLights(true);//闪光
                channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);//锁屏显示通知
                channel.setLightColor(Color.RED);//指定闪光是的灯光颜色
                channel.canShowBadge();//桌面laucher消息角标
                channel.enableVibration(true);//是否允许震动
                channel.setSound(null, null);
                //channel.getAudioAttributes();//获取系统通知响铃声音配置
                channel.getGroup();//获取通知渠道组
                channel.setBypassDnd(true);//设置可以绕过，请勿打扰模式
                channel.setVibrationPattern(new long[]{100, 100, 200});//震动的模式，震3次，第一次100，第二次100，第三次200毫秒
                channel.shouldShowLights();//是否会闪光
                //通知管理者创建的渠道
                getManager(mContext).createNotificationChannel(channel);

            }

            return new NotificationCompat.Builder(mContext, channelId).setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(content).setSmallIcon(R.mipmap.ic_launcher);

        }

        /**
         * @param title
         * @param content
         * @param manageId
         * @param channelId
         * @param progress
         * @param maxProgress
         */
        public static void showNotificationProgress(Context mContext, String title
                , String content, int manageId, String channelId
                , int progress, int maxProgress) {
            final NotificationCompat.Builder builder = getNotificationBuilder(mContext, title, content, channelId);
       /* Intent intent = new Intent(this, SecondeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);*/
            builder.setOnlyAlertOnce(true);
            builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
            builder.setProgress(maxProgress, progress, false);
            builder.setWhen(System.currentTimeMillis());
            getManager(mContext).notify(manageId, builder.build());
        }


        public static void showNotificationProgressApkDown(Context mContext
                , int progress,String content) {
            final NotificationCompat.Builder builder = getNotificationBuilder(mContext, "content", "闻喜新闻", "yunjia");
            builder.setOnlyAlertOnce(true);
            builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
            Log.i("test","进度"+progress);
            builder.setProgress(100, progress, false);
            builder.setWhen(System.currentTimeMillis());
            getManager(mContext).notify(R.mipmap.icon, builder.build());
        }

        public static void cancleNotification(Context mContext, int manageId) {
            getManager(mContext).cancel(manageId);
        }
}
