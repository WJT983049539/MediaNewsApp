package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.FeedbackBean;
import com.rcdz.medianewsapp.model.bean.LeaveMegBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.IshowSearchOrganization;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.customview.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:区留言
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 9:47
 */
public class WriteMessageActivity extends BaseActivity implements IshowSearchOrganization {
    //提交按钮
    @BindView(R.id.submitBtn)
    Button submitBtn;
    //主题
    @BindView(R.id.messageTheme)
    EditText message_theme;
    //反馈内容
    @BindView(R.id.feedContent)
    EditText feed_content;
    //手机
    @BindView(R.id.contactmode)
    EditText contact_mode;
    @BindView(R.id.select_type)
    LinearLayout select_type;
    @BindView(R.id.feedbackType)
    TextView feedbacktype;
    @BindView(R.id.select_unit)
    LinearLayout select_unit;
    @BindView(R.id.feedbackUnit)
    TextView feedbackunit;
    @BindView(R.id.file)
    MyGridView gridView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    String FeedbackOrganization="";//反馈单位
    int FeedbackOrganizationId=0;//反馈单位ID
    String FeedbackType="";//反馈类型
    String theme="";
    String feedcontent="";
    String phone="";
    private  List<FeedbackBean.FeedbackInfo>  Feedbacklist=new ArrayList<>();
    private List<String> FeedbackTypeList=new ArrayList<String>();
    @Override
    public String setNowActivityName() {
        return "区留言";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_write_message;
    }

    @Override
    public void inintView() {
        Feedbacklist.clear();
        FeedbackTypeList.clear();
        FeedbackTypeList.add("投诉");
        FeedbackTypeList.add("建议");
        FeedbackTypeList.add("咨询");
        ButterKnife.bind(this);
        SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new CheckRequestPermissionListener() {
            @Override
            public void onPermissionOk(Permission permission) {
                Log.i("WriteMessageActivity", "请求权限成功");
            }

            @Override
            public void onPermissionDenied(Permission permission) {
                Log.i("WriteMessageActivity", "请求权限失败");
            }
        });
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.img_back, R.id.select_type, R.id.select_unit, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back://返回
                finish();
                break;
            case R.id.select_type://反馈类型 投诉、建议、咨询
                addPickerFeedbackType();
                break;
            case R.id.select_unit://反馈单位
                addPickerDanweiView();
                break;
            case R.id.submitBtn:

                if(FeedbackType.equals("")){
                    GlobalToast.show("反馈类型为空", Toast.LENGTH_LONG);
                    return;
                }
                if(FeedbackOrganization.equals("")){
                    GlobalToast.show("反馈单位为空", Toast.LENGTH_LONG);
                    return;
                }

                if(message_theme.getText()==null||message_theme.getText().equals("")){
                    GlobalToast.show("请填写主题", Toast.LENGTH_LONG);
                    return;

                }else{
                    theme= message_theme.getText().toString();
                }

                if(feed_content.getText()==null||feed_content.getText().equals("")){
                    GlobalToast.show("请填写反馈内容", Toast.LENGTH_LONG);
                    return;

                }else{
                    feedcontent= feed_content.getText().toString();
                }
                if(contact_mode.getText()!=null){
                   phone= contact_mode.getText().toString();
                }

                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
                LeaveMegBean leaveMegBean=new LeaveMegBean();
                leaveMegBean.setSubject(theme);
                leaveMegBean.setContents(feedcontent);
                leaveMegBean.setImages("");
                leaveMegBean.setIsBlackList(0);
                leaveMegBean.setIsReply(0);
                leaveMegBean.setOrganizationId(FeedbackOrganizationId);
                leaveMegBean.setOrganizationName(FeedbackOrganization);
                leaveMegBean.setPhoneNo(phone);
                leaveMegBean.setType(FeedbackType);
                leaveMegBean.setState("0");
                newNetWorkPersenter.AddLeaveMessage(leaveMegBean);
                break;
        }
    }

    private void addPickerFeedbackType() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                FeedbackType=FeedbackTypeList.get(options1);
                feedbacktype.setText(FeedbackType);//选中的值放入editText中

            }
        }).setTitleText("反馈类型选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(FeedbackTypeList);
        pvOptions.show();
    }

    //查看反馈单位
    private void addPickerDanweiView() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.Search_Organization(this);
    }
    //得到反馈单位
    @Override
    public void ishowSearchOrganization(FeedbackBean feedbackBean) {
        Feedbacklist.clear();
        Feedbacklist=feedbackBean.getRows();
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                FeedbackOrganization=Feedbacklist.get(options1).getName();
                FeedbackOrganizationId= Feedbacklist.get(options1).getId();
                feedbackunit.setText(FeedbackOrganization);//选中的值放入editText中   //todo 值未能正常显示

            }
        }).setTitleText("反馈单位选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(Feedbacklist);
        pvOptions.show();
    }
}
