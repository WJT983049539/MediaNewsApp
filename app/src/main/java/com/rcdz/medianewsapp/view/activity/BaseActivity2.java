package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.tools.mLog;

/**
 * activity基类
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public abstract class BaseActivity2 extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示
        setNowActivityName();
        setContentView(setLayout());
        inintView();
        inintData();
        mLog.i("在"+setNowActivityName()+"oncreate");
    }

    /**
     * 标识本Activity
     * @return
     */
    public abstract String setNowActivityName();

    /**
     * 主布局
     */
    public abstract int setLayout();

    /**
     * 初始化控件
     */
    public abstract void inintView();

    /**
     * 初始化数据
     */
    public abstract void inintData();

    /**
     * 简化findViewById()
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T fvbi(int resId){
        return (T) findViewById(resId);
    }

    /**
     *  通过类名启动Activity
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }
    /**
     * 通过类启动Activity,然后销毁自己
     */

    protected void openActicityAndDestoryme(Class<?> hClass){
        openActivityAndDestoryme(hClass,null);
    }
    /**
     * //通过类名启动Activity并携带Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
    /**
     * //通过类名启动Activity并携带Bundle数据,这个是跳转后销毁的
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivityAndDestoryme(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        this.finish();
    }
    /**
     * 保存sp数据
     *
     * @param key
     * @param object
     */
    public void putSp(String key, Object object) {
        SharedPreferenceTools.putValuetoSP(BaseActivity2.this, key, object);
    }

    /**
     * 清除Sp数据
     */
    public void clearSp() {
        SharedPreferenceTools.clearAllSPvalue(BaseActivity2.this);
    }

    /**
     * 获取sp数据
     *
     * @param key
     * @param object
     * @return
     */
    public Object getSp(String key, Object object) {
        return SharedPreferenceTools.getValueofSP(BaseActivity2.this, key, object);
    }
}
