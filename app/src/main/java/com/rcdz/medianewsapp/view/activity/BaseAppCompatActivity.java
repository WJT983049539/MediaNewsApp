package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/22 14:32
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private static final String TAG = BaseAppCompatActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //隐藏底部键盘，一直不会弹出
        layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        setContentView(getLayoutId());
    }

    //设置layout布局,在子类重写该方法.
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
