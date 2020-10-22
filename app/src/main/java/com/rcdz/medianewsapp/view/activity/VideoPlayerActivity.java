package com.rcdz.medianewsapp.view.activity;

import com.rcdz.medianewsapp.R;

/**
 * 作用:频道播放
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 12:01
 */
public class VideoPlayerActivity extends BaseActivity{
    private String playurl;
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_live;
    }

    @Override
    public void inintView() {
        playurl= getIntent().getStringExtra("videoUrl");
    }

    @Override
    public void inintData() {

    }
}
