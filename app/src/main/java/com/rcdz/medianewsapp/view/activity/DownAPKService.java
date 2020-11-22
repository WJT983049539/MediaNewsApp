package com.rcdz.medianewsapp.view.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.NotifitionTools;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;

import java.io.File;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/5 11:13
 */
public class DownAPKService extends Service {
    private String url;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onCreate() {
        url= (String) SharedPreferenceTools.getValueofSP(this,"url","");
        super.onCreate();
        String downFile = url;
        String path= Environment.getExternalStorageDirectory()+"/"+"testDown/"+"test.apk";
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
        FileDownloader.getImpl().create(downFile).setWifiRequired(true).setPath(path).setListener(new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                Log.i("test","pending  soFarBytes="+soFarBytes);
                GlobalToast.showPicture(R.mipmap.startdown, Toast.LENGTH_LONG);

            }
            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                int percent=(int) ((double) soFarBytes / (double) totalBytes * 100);
                NotifitionTools.showNotificationProgressApkDown(DownAPKService.this,percent,"正在下载");
                Log.i("test","("+percent+"%"+")");

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                Log.i("test","completed");

                NotifitionTools.showNotificationProgressApkDown(DownAPKService.this,100,"下载完成");
                String filename =Environment.getExternalStorageDirectory()+"/"+"testDown/"+"test.apk";
                installApk(DownAPKService.this, filename);
                Log.i("test","下载完成");
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                Log.i("test","paused");
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                Log.i("test","error="+e.toString());
                NotifitionTools.showNotificationProgressApkDown(DownAPKService.this,100,"下载失败");
                Log.i("test","下载失败");
                GlobalToast.show("下载失败",5000);

            }
            @Override
            protected void warn(BaseDownloadTask task) {
                Log.i("test","下载成功");
                NotifitionTools.showNotificationProgressApkDown(DownAPKService.this,100,"下载完成");
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 安装APK文件
     */
    public void installApk(Context context, String fileName) {
        File apkfile = new File(fileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, "com.rcdz.medianewsapp.fileprovider", apkfile);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        this.stopSelf();
    }
}
