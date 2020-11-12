package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.tools.GlobalToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 8:49
 */
public class PhoneSendCodeActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.editcode)
    EditText editcode;
    @BindView(R.id.sendcode)
    TextView sendcode;
    String phone="";

    int vc_time = 60;
    Handler mHandler_vc;
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.phone;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        phone=getIntent().getStringExtra("phone");

    }

    @Override
    public void inintData() {

    }




    @OnClick({R.id.back, R.id.save, R.id.sendcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.save:
                if(editcode.getText()!=null){
                    String code=editcode.getText().toString();
                    CommApi.postNoParams("api/Sys_User/ValidateMsgCode/"+phone+"/"+code).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            //{"status":true,"code":200,"message":"验证码正确!","data":null}
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code=jsonObject.getInt("code");
                                String message=jsonObject.getString("message");
                                if(code==200){
                                    Log.i("test", "验证成功-->");
                                    Intent intent=new Intent(PhoneSendCodeActivity.this,PhoneEditActivity.class);
                                    PhoneSendCodeActivity.this.startActivityForResult(intent,50);
                                }else{
                                    GlobalToast.show(message,Toast.LENGTH_LONG);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (response.body() != null) {

                            }
                        }

                        @Override
                        public void onError(Response response) {
                            super.onError(response);
                            Log.i("test", "验证失败-->" + response.message());
                            GlobalToast.show("修改失败,请稍后重试！", Toast.LENGTH_LONG);
                        }
                    });
                }


                break;
            case R.id.sendcode:
                    Map<String, String> areaMap = new HashMap<String, String>();
                    areaMap.put("PhoneNo", phone);
                    CommApi.post("api/Sys_User/SendPhoneMsg", areaMap).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response.body() != null) {
                                Log.i("test", "发送验证码成功-->");
                                try {
                                    JSONObject jsonObject=new JSONObject(response.body());
                                    int code=jsonObject.getInt("code");
                                    String message=jsonObject.getString("message");
                                    if(code==200){
                                        changeSmsCodeStyle();
                                    }else{
                                    }
                                    GlobalToast.show(message,Toast.LENGTH_LONG);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }

                        @Override
                        public void onError(Response response) {
                            super.onError(response);
                            Log.i("test", "修改地址成功失败-->" + response.message());
                            GlobalToast.show("修改失败,请稍后重试！", Toast.LENGTH_LONG);
                        }
                    });

                break;
        }
    }

    private void changeSmsCodeStyle(){
        //转换获取验证码按钮样式（60s不可重新获取）
        mHandler_vc = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // 完成主界面更新,拿到数据
                        vc_time--;
                        sendcode.setText("重新获取(" + vc_time + "s)");
                        if (vc_time <= 0) {
                            // getSmsCode.setBackgroundColor(0xffff983d);//0xFF626262   0xFFfc3c17
                            sendcode.setText("获取验证码");
                            sendcode.setEnabled(true);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==56){
            String phone=data.getStringExtra("phone");
            Intent intent = new Intent();
            intent.putExtra("phone",phone);
            PhoneSendCodeActivity.this.setResult(86, intent);
            finish();
        }
    }
}
