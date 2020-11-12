package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.MessageDetalInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetDetailMessage;
import com.rcdz.medianewsapp.tools.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:留言详情
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 9:24
 */
public class MessageDetailActivity extends BaseActivity implements GetDetailMessage {
    String id;//留言id
    @BindView(R.id.feedbackType)
    TextView feedbackType;
    @BindView(R.id.feedbackUnit)
    TextView feedbackUnit;
    @BindView(R.id.messageTheme)
    TextView messageTheme;
    @BindView(R.id.contactmode)
    TextView contactmode;
    @BindView(R.id.feedContent)
    TextView feedContent;
    @BindView(R.id.otherPic1)
    ImageView otherPic1;
    @BindView(R.id.otherPic2)
    ImageView otherPic2;
    @BindView(R.id.otherPic3)
    ImageView otherPic3;
    @BindView(R.id.message_detail)
    LinearLayout messageDetail;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        toolbarTitle.setText("详情");
        id = String.valueOf(getIntent().getIntExtra("id", 0));
        //查询id
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetDepartmentInfoForid(id, MessageDetailActivity.this);
    }

    @Override
    public void inintData() {

    }

    @Override
    public void getDetailMessage(MessageDetalInfoBean messageDetalInfoBean) {
        RequestOptions options = new RequestOptions().error(R.mipmap.default_image).centerCrop();
        if (messageDetalInfoBean.getCode() == 200) {
            feedbackType.setText( messageDetalInfoBean.getData().getType());
            feedbackUnit.setText(messageDetalInfoBean.getData().getOrganizationName());
            messageTheme.setText(messageDetalInfoBean.getData().getSubject());
            if(messageDetalInfoBean.getData().getPhoneNo()!=null){
                contactmode.setText((messageDetalInfoBean.getData().getPhoneNo()));
            }
            feedContent.setText(messageDetalInfoBean.getData().getContents());

            if(messageDetalInfoBean.getData().getImages()!=null){
                String imags=messageDetalInfoBean.getData().getImages();
                String[] strings=imags.split(",");
                if(strings.length==1){
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[0]).apply(options).into(otherPic1);
                    otherPic2.setVisibility(View.GONE);
                    otherPic3.setVisibility(View.GONE);
                }else if(strings.length==2){
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[0]).apply(options).into(otherPic1);
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[1]).apply(options).into(otherPic2);
                    otherPic3.setVisibility(View.GONE);
                }else if(strings.length==3){
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[0]).apply(options).into(otherPic1);
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[1]).apply(options).into(otherPic2);
                    Glide.with(MessageDetailActivity.this).load(AppConfig.BASE_PICTURE_URL+strings[2]).apply(options).into(otherPic3);
                }


            }
        }
    }


    @OnClick({R.id.img_back, R.id.feedbackType, R.id.feedbackUnit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.feedbackType:
                break;
            case R.id.feedbackUnit:
                break;
        }
    }
}
