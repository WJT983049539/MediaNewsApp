package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rcdz.medianewsapp.R;

/**
 * @author:create by wtj
 * 邮箱 983049539@qq.com
 */
public class testActivity extends AppCompatActivity {
    private ImageView mSignIn;
    private ImageView redDot;
    private TextView signSuccess;
    private AnimationSet set;
//    private String isSign;
    private TextView textView;



    private Handler mHandler = new Handler() {
        private int i=100;
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:// 签到
                    i = i+100;
//                    mSignIn.setImageResource(R.drawable.icon_signed);//已签到
//                    redDot.setVisibility(View.GONE);//圆点隐藏
                    // start平移和渐变动画
                    signSuccess.startAnimation(set);
                    signSuccess.setVisibility(View.GONE);
                    textView.setText("当前积分:"+i);
                    //   mSignIn.setClickable(false);
                    break;

                default:
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mSignIn = (ImageView) findViewById(R.id.iv_sign);//签到
//        redDot = (ImageView) findViewById(R.id.iv_redpoint);//显示未签到的红圆点


        //签到添加积分动画文本
        signSuccess = (TextView) findViewById(R.id.iv_sign_success);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signSuccess.startAnimation(set);
                signSuccess.setVisibility(View.GONE);
//                textView.setText("当前积分:"+100);
            }
        });
        // 　获取签到成功图片的位置
        int left = signSuccess.getLeft();
        int top = signSuccess.getTop();

        // 创建平移和渐变的动画集合
        // 定义一个平移动画对象
        TranslateAnimation translate = new TranslateAnimation(left, left, top, top - 100);
        translate.setDuration(2000);
        //translate.setRepeatCount(1);

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);

        // 创建动画集合，将平移动画和渐变动画添加到集合中，一起start
        set = new AnimationSet(false);
        set.addAnimation(translate);
        set.addAnimation(alpha);


    }


    /**
     * 签到
     * @param v
     */
    public void signIn(View v) {

        //   if (!TextUtils.isEmpty(isSign)) {
        // if ("0".equals(isSign)) {// 0代表未签到
        signSuccess.setVisibility(View.VISIBLE);
        //  mHandler.sendEmptyMessage(1);
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);
        //  }
        //    }
    }


}
