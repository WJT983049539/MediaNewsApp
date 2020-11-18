package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.customview.ClearEditText;

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
 * time 2020/10/22 18:33
 */
public class NiieEditActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.nickedit)
    ClearEditText nickedit;
    String OldNick="";
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.nike;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        OldNick=getIntent().getStringExtra("nick");
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.back, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.save:
                if(nickedit.getText()!=null){
                    String nickedit2= nickedit.getText().toString();
                    Map<String,String> areaMap = new HashMap<String,String>();
                    areaMap.put("OldNickName",OldNick);
                    areaMap.put("NewNickName",nickedit2);
                    CommApi.post("api/Sys_User/UpdateUserUserTrueName",areaMap).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
//                            {"status":true,"code":200,"message":"昵称修改提交成功，请等待审核","data":"我他发可"}
                            if(response.body()!=null){
                                Log.i("test","修改昵称成功-->");

                                try {
                                    JSONObject jsonObject=new JSONObject(response.body());
                                    int code=jsonObject.getInt("code");
                                    String data=jsonObject.getString("data");
                                    String message=jsonObject.getString("message");
                                    if(code==200){
                                        Intent intent=new Intent();
                                        intent.putExtra("nickname",data);
                                        GlobalToast.show(message,Toast.LENGTH_LONG);
                                        setResult(12,intent);
                                        finish();
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
                            Log.i("test","修改昵称失败-->"+response.message());
                            GlobalToast.show("修改失败,请稍后重试！",Toast.LENGTH_LONG);
                        }
                    });

                }else{
                    GlobalToast.show("家庭地址为空", Toast.LENGTH_LONG);
                }
                break;
        }
    }
}
