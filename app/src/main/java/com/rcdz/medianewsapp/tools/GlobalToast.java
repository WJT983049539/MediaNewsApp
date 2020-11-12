package com.rcdz.medianewsapp.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rcdz.medianewsapp.MAppaction;
import com.rcdz.medianewsapp.R;
/**
 * 全局吐司
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class GlobalToast {
    private static Toast toast = null;
    private static Toast toast2 = null;
    private static Toast toast3 = null;
    private static Toast toast4 = null;
    private static Application sContext;

    public static void init(MAppaction application) {
        sContext = application;
    }

    @SuppressLint("ShowToast")
    public static void show(CharSequence sequence, int toastDuration) {
        if (toast == null) {
            toast = Toast.makeText(sContext, sequence, toastDuration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.cancel();
            toast.setText(sequence);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast = Toast.makeText(sContext, sequence, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();

    }
    @SuppressLint("ShowToast")
    public static void show4(CharSequence sequence, int toastDuration) {
        if (toast4 == null) {
            toast4 = Toast.makeText(sContext, sequence, toastDuration);
            toast4.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast4.cancel();
            toast4 = Toast.makeText(sContext, sequence, toastDuration);
            toast4.setGravity(Gravity.CENTER, 0, 0);
        }
        toast4.show();
    }

    /**
     * 带图片的
     *
     * @param toastDuration
     */
    public static void showPicture(int picure, int toastDuration) {
        if (toast2 == null) {
            toast2=new Toast(sContext);
            toast2.setGravity(Gravity.CENTER, 0, 0);
            //填充物来自的xml文件,在这个改成一个view
            //实现xml到view的转变哦
            LayoutInflater inflater = LayoutInflater.from(sContext);
           View view = inflater.inflate(R.layout.toast, null);
            //不一定需要，找到xml里面的组件，设置组件里面的具体内容
            ImageView imageView1 = view.findViewById(R.id.iv_toast);
            imageView1.setImageResource(picure);
            toast2.setView(view);
            toast2.setDuration(toastDuration);
        }
        toast2.show();
    }
    /**
     * 带图片的
     *
     * @param content
     * @param toastDuration
     */
    @SuppressLint("ShowToast")
    public static void showToastforCenter(String content, int toastDuration) {
        if (toast3 == null) {
            toast3=new Toast(sContext);
            toast3.setGravity(Gravity.CENTER, 0, 0);
            //填充物来自的xml文件,在这个改成一个view
            //实现xml到view的转变哦
            LayoutInflater inflater = LayoutInflater.from(sContext);
            View view = inflater.inflate(R.layout.toast2, null);
            //不一定需要，找到xml里面的组件，设置组件里面的具体内容
            ImageView imageView1 = view.findViewById(R.id.iv_toast);
            TextView textView1 = view.findViewById(R.id.tv_toast);
            imageView1.setImageResource(R.mipmap.ic_nowifi);
            textView1.setText(content);
            toast3.setView(view);
            toast3.setDuration(toastDuration);
        }else{
            toast3.cancel();
            toast3=new Toast(sContext);
            toast3.setGravity(Gravity.CENTER, 0, 0);
            //填充物来自的xml文件,在这个改成一个view
            //实现xml到view的转变哦
            LayoutInflater inflater = LayoutInflater.from(sContext);
            View view = inflater.inflate(R.layout.toast2, null);
            //不一定需要，找到xml里面的组件，设置组件里面的具体内容
            ImageView imageView1 = view.findViewById(R.id.iv_toast);
            TextView textView1 = view.findViewById(R.id.tv_toast);
            imageView1.setImageResource(R.mipmap.ic_nowifi);
            textView1.setText(content);
            toast3.setView(view);
            toast3.setDuration(toastDuration);
        }
        toast3.show();

    }
}
