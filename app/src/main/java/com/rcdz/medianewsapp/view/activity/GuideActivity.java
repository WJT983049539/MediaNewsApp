package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.GuideViewPagerAdapter;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.customview.XieYiDialog;

import java.util.ArrayList;


/**
 *  WelcomeActivity
 *  @author yandaocheng <br/>
 *	引导页
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private LinearLayout mLayout_point;
    private ImageView mBtnStar;
    private boolean loginStru=false;

    // 适配器
    private GuideViewPagerAdapter adapter;
    // 数据源
    private ArrayList<Integer> imagelist = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
         loginStru= (boolean) SharedPreferenceTools.getValueofSP(GuideActivity.this,"loginStru",false);
        initData();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mLayout_point = (LinearLayout) findViewById(R.id.guide_layout);
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        adapter = new GuideViewPagerAdapter(getSupportFragmentManager(),imagelist);
        adapter.showPoint(this, mLayout_point);
        mViewPager.addOnPageChangeListener(new GuidePageListener());
        mViewPager.setAdapter(adapter);
        mBtnStar =  findViewById(R.id.guide_star);
        mBtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(loginStru){ //已经登录
//                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
//                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
//                    finish();
//                }else{
//                    startActivity(new Intent(GuideActivity.this,LoginActivity.class));
//                    finish();
//                }


                XieYiDialog xieYiDialog=new XieYiDialog(GuideActivity.this);
                xieYiDialog.show();
                xieYiDialog.setOnDialogListen(new XieYiDialog.Confirm() {
                    @Override
                    public void ok() {
                        SharedPreferenceTools.putValuetoSP(GuideActivity.this,"isFirstStart",false);
                        startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void cannal() { //不同意
                        SharedPreferenceTools.putValuetoSP(GuideActivity.this,"isFirstStart",true);
                        xieYiDialog.cancel();
                        GuideActivity.this.finish();
                    }
                });



            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        imagelist.add(R.mipmap.welcom1);
        imagelist.add(R.mipmap.welcom2);
        imagelist.add(R.mipmap.welcom3);
    }

    /**
     * viewpager的滑动监听
     */
    class GuidePageListener implements ViewPager.OnPageChangeListener {

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < imagelist.size(); i++) {
                mLayout_point.getChildAt(i).setBackgroundResource(
                        R.drawable.xml_shape_point_gray);
                if (i == position) {
                    mLayout_point.getChildAt(i).setBackgroundResource(
                            R.drawable.xml_shape_point_red);
                }
            }
            //是否是最后一个页面
            if (position == imagelist.size() - 1) {
                mBtnStar.setVisibility(View.VISIBLE);
            } else {
                mBtnStar.setVisibility(View.GONE);
            }
        }

        // 滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

    }
}
