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

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.tools.Comment;
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
public class PhoneEditActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.editphone)
    EditText editphone;
    @BindView(R.id.ValidateCode)
    TextView ValidateCode;
    @BindView(R.id.sendcode)
    TextView sendcode;
    private String phone;
    int vc_time = 60;
    Handler mHandler_vc;
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.phone2;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);

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
                if (!phone.equals("")) {
                    String phone = editphone.getText().toString();
                    Map<String, String> areaMap = new HashMap<String, String>();
                    areaMap.put("PhoneNo", phone);
                    CommApi.post("api/Sys_User/UpdateUserPhoneNo", areaMap).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response.body() != null) {
                                Log.i("test", "修改手机成功-->");

                                try {
                                    JSONObject jsonObject=new JSONObject(response.body());
                                    int code=jsonObject.getInt("code");
                                    String message=jsonObject.getString("message");
                                    if(code==200){

                                        Intent intent = new Intent();
                                        intent.putExtra("phone",phone);
                                        PhoneEditActivity.this.setResult(56, intent);
                                        PhoneEditActivity.this.finish();
                                    }else{
                                        GlobalToast.show(message,Toast.LENGTH_LONG);
                                    }
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

                }else{
                    GlobalToast.show("请输入要修改的手机号",Toast.LENGTH_LONG);
                }
                break;
            case R.id.sendcode:
                if(editphone.getText()!=null){
                    phone= editphone.getText().toString();
                }else{
                    GlobalToast.show("请填写手机号",Toast.LENGTH_LONG);
                    return;
                }

               if( !Comment.isPhone(phone)){
                   GlobalToast.show("请填写正确的手机号",Toast.LENGTH_LONG);
                   return;
               }

                //判断手机是否绑定过
                CommApi.postNoParams("api/Sys_User/AppGetByPhone/"+phone).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //{"status":true,"code":200,"message":"发送成功","data":"067452"}
                        if (response.body() != null) {
                            Log.i("test", "绑定过成功-->");
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
                        Log.i("test", "绑定过失败-->" + response.message());
                    }
                });
                break;
        }
    }


    private void changeSmsCodeStyle(){
        vc_time=60;
        //转换获取验证码按钮样式（60s不可重新获取）
        mHandler_vc = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // 完成主界面更新,拿到数据
                        vc_time--;
                        sendcode.setEnabled(false);
                        sendcode.setText("重新获取(" + vc_time + "s)");
                        if (vc_time <= 0) {
                            sendcode.setEnabled(true);
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
}
