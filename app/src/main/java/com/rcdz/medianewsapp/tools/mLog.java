package com.rcdz.medianewsapp.tools;

import android.util.Log;

import com.elvishew.xlog.Logger;
import com.elvishew.xlog.XLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class mLog {

    /**
     * Log i  显示
     * @param msg 要显示的信息
     */
    public static void i(String msg){
        XLog.i(msg);
    }

    public static void v (String msg){
        XLog.v(msg);
    }
    /**
     * Log w  显示
     * @param msg 要显示的信息
     */
    public static void w(String msg){
         XLog.w(msg);
    }
    /**
     * Log e  显示
     * @param msg 要显示的信息
     */
    public static void e(String msg){
          XLog.e(msg);
    }
    /**
     * Log d  显示
     * @param msg 要显示的信息
     */
    public static void d(String msg){
           XLog.d(msg);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date DateForString(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
