package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.view.activity.BaseActivity;
import com.rcdz.medianewsapp.view.activity.ShowXieYiBookActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/13 11:32
 */
public class GYActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.xieyitext)
    TextView xieyitext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.gy;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        toolbarTitle.setText("关于");
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.img_back, R.id.xieyitext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.xieyitext:
                startActivity(new Intent(this, ShowXieYiBookActivity.class));
                break;
        }
    }
}
