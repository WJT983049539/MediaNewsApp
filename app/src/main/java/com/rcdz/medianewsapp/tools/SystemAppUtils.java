package com.rcdz.medianewsapp.tools;

import android.app.Activity;
import android.content.Context;
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
     * 在Activity中获取屏幕的宽度
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
     * 在Activity中获取屏幕的高度和宽度
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


}
