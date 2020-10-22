package com.rcdz.medianewsapp.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.Toast;

import com.rcdz.medianewsapp.MAppaction;

/**
 * 全局吐司
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class GlobalToast {
    private static Toast toast = null;
    private static Application sContext;
    public static void init(MAppaction application) {
        sContext = application;
    }
    @SuppressLint("ShowToast")
    public static void show(CharSequence sequence, int toastDuration){
        if (toast == null) {
            toast = Toast.makeText(sContext, sequence, toastDuration);
        } else {
            toast.cancel();
            toast.setText(sequence);
            toast = Toast.makeText(sContext, sequence, Toast.LENGTH_LONG);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();

    }
}
