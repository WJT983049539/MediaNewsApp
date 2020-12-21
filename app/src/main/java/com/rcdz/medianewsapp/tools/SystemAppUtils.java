package com.rcdz.medianewsapp.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * PublicUtil
 *
 * @author yandaocheng <br/>
 * 获取屏幕，虚拟键等高度
 * 2018-08-14
 * 修改者，修改日期，修改内容
 */
public class SystemAppUtils {

    /**
     * 标题栏高度
     *
     * @return
     */
    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }


    /**
     * 在Activity中获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixel = outMetrics.widthPixels;
        int heightPixel = outMetrics.heightPixels;
        mLog.w( "widthPixel = " + widthPixel + ",heightPixel = " + heightPixel);
        return heightPixel;

    }
    /**
     * 在Activity中获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixel = outMetrics.widthPixels;
        int heightPixel = outMetrics.heightPixels;
        mLog.w( "widthPixel = " + widthPixel + ",heightPixel = " + heightPixel);
        return widthPixel;
    }

    public static String getAPPVersionName(Context context){
        String versionName="";
        try {
            String pkName = context.getPackageName();
            versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
    public static int getAPPVersionCode(Context context){
        int versionCode =0;
        try {
            String pkName = context.getPackageName();
            versionCode= context.getPackageManager().getPackageInfo(pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;

    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
