package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.tools.SystemAppUtils;
import com.umeng.commonsdk.debug.I;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class WelcomeActivity extends BaseActivity {
   private String CorrelationUrl="";
    private  int Duration=0;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.welcome_bg)
    ConstraintLayout welcomeBg;
    Handler welcomeHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==2){
                Duration=Duration-1;
                if(Duration<=0){
                    getPremission2();
                }else{
                    timeTv.setText("跳过 "+Duration+" s");
                    this.sendEmptyMessageDelayed(2,1000);
                }
            }
        }
    };

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
        ButterKnife.bind(this);
        //把屏幕高度存入缓存，这是包含了虚拟按键
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Constant.displayWidth = SystemAppUtils.getScreenWidth(this);
        Constant.displayHeight = SystemAppUtils.getScreenHeight(this);
    }

    @Override
    public void inintData() {
        //http://192.168.1.59:9992/Upload/DataSource/AppStartPage/index.json
        String url= AppConfig.BASE_URL+"/Upload/DataSource/AppStartPage/index.json?code="+System.currentTimeMillis() ;
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //{"ImageUrl":"http://xxx.com/xx.jpg","Duration":2}
                Log.i("test",response.body());
                try {
                    JSONObject jsonObject=new JSONObject(response.body());
                   String ImageUrl= jsonObject.getString("ImageUrl");
                    CorrelationUrl= jsonObject.getString("CorrelationUrl");
                    Duration=jsonObject.getInt("Duration"); //持续时间
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bb=getBitmap(ImageUrl);
                            Message message=new Message();
                            WelcomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(bb!=null){
                                        welcomeBg.setBackground( new BitmapDrawable(getResources(),bb));
                                    }else{
                                        welcomeBg.setBackgroundResource(R.mipmap.welcome);
                                    }
                                    timeTv.setText("跳过 "+Duration+" s");
                                    Countdown(Duration);
                                }
                            });

                        }
                    }).start();




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);

            }
        });



    }

    public void getPremission2() {
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
                        Log.i("test", "权限获取成功");
                        ACache aCache = ACache.get(WelcomeActivity.this);
                        //判断是否已经登录 ，有时效性，时效为24小时,退出 或者重新登录都重置
                        String loginstau = aCache.getAsString("loginStru");
                        //判断是否为第一次启动

                        boolean isFirstStart = (boolean) SharedPreferenceTools.getValueofSP(WelcomeActivity.this, "isFirstStart", true);
                        String token = (String) getSp("token", "");
                        boolean loginStru = (boolean) SharedPreferenceTools.getValueofSP(WelcomeActivity.this, "loginStru", false);
                        Constant.token = token;

                        if (!isFirstStart) {   //不是第一次登录

//                            if(loginStru){ //已经登录过了，直接进入主页
//                                openActivity(MainActivity.class);
//                                WelcomeActivity.this.finish();
//                            }else{
//                                openActivity(LoginActivity.class);
//                                WelcomeActivity.this.finish();
//                            }

                            //不管登录不登录直接进入主页
                            openActivity(MainActivity.class);
                            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                            WelcomeActivity.this.startActivity(intent,
                                    ActivityOptions.makeSceneTransitionAnimation
                                            (WelcomeActivity.this,welcomeBg,"welcome").toBundle());
                            WelcomeActivity.this.finish();

                        } else {    //第一次登录
                            openActivity(GuideActivity.class); //todo 欢迎页面完善
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
                                        boolean ff = PanduanIsProhibitedPermissionDenied(refusedPermissions);
                                        if (!ff) {
                                            SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
                                                @Override
                                                public void onBackFromAppDetail(Intent data) {
                                                    Log.i("test", "这里是在设置也手动获取到权限以后返回，回调");
                                                }
                                            });
                                        } else {
                                            getPremission2();
                                        }
                                    }
                                }).create().show();
                    }
                });
    }

    private Boolean PanduanIsProhibitedPermissionDenied(Permission[] refusedPermissions) {
        boolean flag = true;
        for (int i = 0; i < refusedPermissions.length; i++) {
            if (!refusedPermissions[i].shouldRationale()) {
                flag = false;
                return flag;
            }
        }
        return flag;

    }


    @OnClick({R.id.time_tv,R.id.welcome_bg})
    public void onViewClicked(View view) {
        if(view.getId()==R.id.time_tv){
            getPremission2();
        }else if(view.getId()==R.id.welcome_bg){
            if(!CorrelationUrl.equals("")){
                Bundle bundle=new Bundle();
                bundle.putString("url",CorrelationUrl);
                openActivityAndDestoryme(ShowWelcomeActivity.class,bundle);
            }

        }
    }


    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过第二个参数(文件名)来判断，是否本地有此图片
            drawable = Drawable.createFromStream(new URL(imageUrl).openStream(), null);
        } catch (IOException e) {
            Log.d("skythinking", e.getMessage());
        }
        if (drawable == null) {
            Log.d("skythinking", "null drawable");
        } else {
            Log.d("skythinking", "not null drawable");
        }

        return drawable;
    }


    private void Countdown(int duration) {
        if(duration!=0){
            welcomeHandler.sendEmptyMessageDelayed(2,1000);
        }else{
            //获取权限
            getPremission2();
        }
    }

    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        welcomeHandler.removeCallbacksAndMessages(null);
    }
}
