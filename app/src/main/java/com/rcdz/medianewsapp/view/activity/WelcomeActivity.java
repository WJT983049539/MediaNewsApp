package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.tools.SystemAppUtils;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class WelcomeActivity extends BaseActivity{


    @Override
    public String setNowActivityName() {
        return "WelcomeActivity";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void inintView() {
        //把屏幕高度存入缓存，这是包含了虚拟按键
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Constant.displayWidth =SystemAppUtils.getScreenWidth(this);
        Constant.displayHeight = SystemAppUtils.getScreenHeight(this);
    }

    @Override
    public void inintData() {
        //获取权限
        getPremission2();
    }

    public void getPremission2(){
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ),
                //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        Log.i("test","权限获取成功");
                        ACache aCache=ACache.get(WelcomeActivity.this);
                        //判断是否已经登录 ，有时效性，时效为24小时,退出 或者重新登录都重置
                        String loginstau= aCache.getAsString("loginStru");
                        //判断是否为第一次启动

                        boolean  isFirstStart= (boolean) SharedPreferenceTools.getValueofSP(WelcomeActivity.this,"isFirstStart",true);
                        String token = (String) getSp("token", "");
                        boolean loginStru= (boolean) SharedPreferenceTools.getValueofSP(WelcomeActivity.this,"loginStru",false);
                        Constant.token = token;

                        if(!isFirstStart){   //不是第一次登录

                            if(loginStru){ //已经登录过了，直接进入主页
                                openActivity(MainActivity.class);
                                WelcomeActivity.this.finish();
                            }else{
                                openActivity(LoginActivity.class);
                                WelcomeActivity.this.finish();
                            }


                        }else{    //第一次登录
                            openActivity(LoginActivity.class);
                            WelcomeActivity.this.finish();
                        }
                    }
                    @Override
                    public void onPermissionDenied(final Permission[] refusedPermissions) {
                        Toast.makeText(WelcomeActivity.this, refusedPermissions[0].toString() +
                                " 权限获取失败", Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(WelcomeActivity.this).setTitle("提示").setMessage("如果你拒绝了权限,应用中的一些功能将不糊能正常使用")
                                .setPositiveButton("授予权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //用户点击以后
                                        boolean ff=PanduanIsProhibitedPermissionDenied(refusedPermissions);
                                        if(!ff){
                                            SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
                                                @Override
                                                public void onBackFromAppDetail(Intent data) {
                                                    Log.i("test","这里是在设置也手动获取到权限以后返回，回调");
                                                }
                                            });
                                        }else{
                                            getPremission2();
                                        }
                                    }
                                }).create().show();
                    }
                });
    }

    private Boolean PanduanIsProhibitedPermissionDenied(Permission[] refusedPermissions) {
        boolean flag=true;
        for(int i=0;i<refusedPermissions.length;i++){
            if(!refusedPermissions[i].shouldRationale()){
                flag=false;
                return flag;
            }
        }
        return flag;

    }
}
