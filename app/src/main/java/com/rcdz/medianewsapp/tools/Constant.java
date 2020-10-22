package com.rcdz.medianewsapp.tools;

/**
 * 公共静态变量
 */

public class Constant {
    /**
     * 欢迎页停留时间
     */
    public final static int TIME_DELAY_WELCOME = 2000;


    /**
     * SharedPreferences 时使用
     */
    public static final String IS_FIRSTSTART = "isFirstStart";						//是否首次启动
    /**
     * 设备屏幕宽度高度
     */
    public static int displayWidth = 0;				//屏幕宽度
    public static int displayHeight = 0;			//屏幕高度
    public static String token="";//保存现有的token
    public static boolean logonstau=false;//登录状态

}
