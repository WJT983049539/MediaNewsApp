package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.BaseBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetForGet;
import com.rcdz.medianewsapp.persenter.interfaces.GetPhoneCode;
import com.rcdz.medianewsapp.tools.GlobalToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:忘记密码 *
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/30 13:46
 */
public class RetrievePsdActivity extends BaseActivity implements GetPhoneCode , GetForGet {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bindTel)
    EditText bindTel;
    @BindView(R.id.shortmsg)
    EditText shortmsg;
    @BindView(R.id.getSmsCode)
    Button getSmsCode;
    @BindView(R.id.newPwd)
    EditText newPwd;
    @BindView(R.id.confirmPwd)
    EditText confirmPwd;
    @BindView(R.id.submit_btn)
    Button submitBtn;
    int vc_time = 0;
    Handler mHandler_vc;
    String phoneNumber = "";
    String newPwd2 = "";
    String Msg = "";
    String confirmPwd2 = "";
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.forgetpsd;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        toolbarTitle.setText("找回密码");
    }

    @Override
    public void inintData() {

    }

    @OnClick({R.id.img_back, R.id.getSmsCode, R.id.submit_btn})
    public void onViewClicked(View view) {
        phoneNumber = bindTel.getText().toString();
        Msg = shortmsg.getText().toString();
         newPwd2 = newPwd.getText().toString();
         confirmPwd2 = confirmPwd.getText().toString();
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.getSmsCode:
                if(phoneNumber.equals("")||phoneNumber==null){
                    GlobalToast.show("请填写手机号码",5000);
                    return;
                }else{
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
                    newNetWorkPersenter.GetPhoneCode(phoneNumber,RetrievePsdActivity.this);
                }
                break;
            case R.id.submit_btn:
                if(phoneNumber.equals("")){
                    Toast.makeText(getApplicationContext(),"手机号不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(Msg.equals("")){
                    Toast.makeText(getApplicationContext(),"短信验证码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }else if(newPwd.equals("")){
                    Toast.makeText(getApplicationContext(),"新密码不能为空",Toast.LENGTH_LONG).show();
                }else if(confirmPwd2.equals("")){
                    Toast.makeText(getApplicationContext(),"确认密码不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!newPwd2.equals(confirmPwd2)){
                    Toast.makeText(getApplicationContext(),"俩次输入密码不相同",Toast.LENGTH_LONG).show();
                    return;
                }
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
                newNetWorkPersenter.RequestGorgetPsd(phoneNumber,Msg,confirmPwd2,this);

                break;
        }
    }
    //得到验证码
    @Override
    public void getPhoneCode(BaseBean value) {
        if(value.getCode()==200){
            GlobalToast.show(value.getMessage(), 5000);
            changeSmsCodeStyle();
        }else{
            //发送失败
            GlobalToast.show(value.getMessage(), 5000);
        }
    }

    private void changeSmsCodeStyle() {
        //转换获取验证码按钮样式（60s不可重新获取）
        vc_time = 60;
        getSmsCode.setEnabled(false);
        getSmsCode.setText("重新获取(" + vc_time + "s)");

        mHandler_vc = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // 完成主界面更新,拿到数据
                        vc_time--;
                        getSmsCode.setText("重新获取(" + vc_time + "s)");
                        if (vc_time <= 0) {
                            // getSmsCode.setBackgroundColor(0xffff983d);//0xFF626262   0xFFfc3c17
                            getSmsCode.setBackgroundResource(R.drawable.com_button_bg);
                            getSmsCode.setText("获取验证码");
                            getSmsCode.setEnabled(true);
                        }
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        // 耗时操作，完成之后发送消息给Handler，完成UI更新；
                        mHandler_vc.sendEmptyMessage(0);
                        if (vc_time <= 0) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public void forget(BaseBean baseBean) {
            if(baseBean.getCode()==200){
                GlobalToast.show("修改成功",Toast.LENGTH_LONG);
                this.finish();
            }else{
                GlobalToast.show(baseBean.getMessage(),Toast.LENGTH_LONG);
            }
    }
}
