package com.rcdz.medianewsapp.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.EncodingHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/3 9:32
 */
public class ShareActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.qrCodeView)
    ImageView qrCodeView;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_share;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        toolbarTitle.setText("推荐好友");
        ImageView mImageView = (ImageView) findViewById(R.id.qrCodeView);
        try {
            Bitmap mBitmap = EncodingHandler.createQRCode("https://www.pgyer.com/HRBQ", 400);
            mImageView.setImageBitmap(mBitmap);
        } catch (Exception e){

        }
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
        }
    }
}
