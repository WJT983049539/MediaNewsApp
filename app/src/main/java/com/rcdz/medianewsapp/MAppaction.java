package com.rcdz.medianewsapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.github.moduth.blockcanary.BlockCanary;
import com.liulishuo.filedownloader.FileDownloader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.qw.soul.permission.SoulPermission;
import com.rcdz.medianewsapp.tools.AppBlockCanaryContext;
import com.rcdz.medianewsapp.tools.CrashHandler;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.Https;
import com.rcdz.medianewsapp.tools.OkHttpUtils;
import com.rcdz.medianewsapp.tools.SetList;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 应用的appaciton 用来存放长周期的对象或者初始化
 */

@SuppressLint("NewApi")
public class MAppaction extends Application implements Application.ActivityLifecycleCallbacks {
    private static Context context;
    private static SetList<Activity> activityList = new SetList<>();
    private static MAppaction mInstance = null;
    public static MAppaction getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("Application is not created.");
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        activityList.clear();
        context=getApplicationContext();
        this.registerActivityLifecycleCallbacks(this);//注册
        SoulPermission.init(this);//权限框架初始化
        GlobalToast.init(this);//全局Toast初始化
        FileDownloader.init(getApplicationContext());
        BlockCanary.install(this, new AppBlockCanaryContext());//崩溃日志
        OkGo.getInstance().init(this);//初始化网络框架
        File cache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getBaseContext()));
        Https.SSLParams sslParams = Https.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//连接超时(单位:秒)
                .writeTimeout(20, TimeUnit.SECONDS)//写入超时(单位:秒)
                .readTimeout(20, TimeUnit.SECONDS)//读取超时(单位:秒)
                .pingInterval(20, TimeUnit.SECONDS) //websocket轮训间隔(单位:秒)
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize))//设置缓存
                .cookieJar(cookieJar)//Cookies持久化
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)//https配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        Glide.get(context).getRegistry().replace(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory(okHttpClient));

        initLog();
    }
    /**
     * 获取自身App安装包信息
     *
     * @return
     */
    public PackageInfo getLocalPackageInfo() {
        return getPackageInfo(getPackageName());
    }
    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo(String packageName) {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }
    private void initLog() {
        //禁用日志，正式版的时候设置
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
        //配置日志
        LogConfiguration config = new LogConfiguration.Builder()
                .tag("NEWS")//制定TAG
                .t() // 允许打印线程信息，默认禁止
                .st(2) // 允许打印深度为2的调用栈信息，默认禁止
                .b() // 允许打印日志边框，默认禁止
                .build();
        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
        Printer consolePrinter = new ConsolePrinter();             // 通过 System.out 打印日志到控制台的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder("/sdcard/xlog/")                         // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())
                .build();    // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")

        XLog.init(                                                 // 初始化 XLog
                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
                consolePrinter,
                filePrinter);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        ActivityManager.getManager().setCurrentActivity(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        addActivity(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        finishActivity(activity);
    }
    //activity管理类
    public static class ActivityManager{

        private static ActivityManager manager = new ActivityManager();
        private WeakReference<Activity> current;
        private ActivityManager(){
        }
        public static ActivityManager getManager(){
            return manager;
        }

        public Activity getTopActivity(){
            if (current!=null)
                return current.get();
            return null;
        }
        public void setCurrentActivity(Activity obj){
            current = new WeakReference<Activity>(obj);
        }

        public static Context getContext() {
            return context;
        }
    }


    /**
     * 添加Activity到activityList，在onCreate()中调用
     */
    public static void addActivity(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            if (!activityList.contains(activity)) {
                activityList.add(activity);
            }
        } else {
            activityList.add(activity);
        }
    }

    /**
     *结束Activity到activityList，在onDestroy()中调用
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityList != null && activityList.size() > 0) {
            activityList.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        }
        activityList.clear();
    }

    //关闭每一个list内的activity
    public static void exit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

}
