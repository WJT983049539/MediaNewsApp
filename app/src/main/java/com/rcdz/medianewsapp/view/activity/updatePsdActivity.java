package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 * 作用:修改密码 *
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 16:54
 */
public class updatePsdActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.edit_jiupwd)
    EditText editJiupwd;
    @BindView(R.id.edit_newpwd)
    EditText editNewpwd;
    @BindView(R.id.edit_surepwd)
    EditText editSurepwd;
    @BindView(R.id.surepsd)
    Button surepsd;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_updatepwd;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);


    }

    @Override
    public void inintData() {

    }



    @OnClick({R.id.img_back, R.id.surepsd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.surepsd:

                if(editJiupwd.getText()==null||editJiupwd.getText().equals("")){
                    GlobalToast.show("请输入旧密码", Toast.LENGTH_LONG);
                    return;
                }
                if(editNewpwd.getText()==null||editNewpwd.getText().equals("")){
                    GlobalToast.show("请输入新密码", Toast.LENGTH_LONG);
                    return;
                }
                if(editSurepwd.getText()==null||editSurepwd.getText().equals("")){
                    GlobalToast.show("请输入确认密码", Toast.LENGTH_LONG);
                    return;
                }
                String jiupwd=editJiupwd.getText().toString();
                String newpwd=editNewpwd.getText().toString();
                String surepwd=editSurepwd.getText().toString();
                if(!newpwd.equals(surepwd)){
                    GlobalToast.show("俩次密码不一致", Toast.LENGTH_LONG);
                    return;
                }

                Map<String,String> areaMap = new HashMap<String,String>();
                areaMap.put("oldPwd",jiupwd);
                areaMap.put("newPwd",surepwd);
                CommApi.post("api/Sys_User/modifyPwd",areaMap).execute(new StringCallback() {
                    //{"status":true,"code":200,"message":"密码修改成功","data":null}
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            Log.i("test","签到状态-->"+response.body());
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code=jsonObject.getInt("code");
                                if(code==200){
                                    String message = jsonObject.getString("message");
                                    GlobalToast.show("message",Toast.LENGTH_LONG);
                                    updatePsdActivity.this.finish();

                                }else{
                                    GlobalToast.show("修改密码失败！",Toast.LENGTH_LONG);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.i("test","签到状态失败-->"+response.message());
                        GlobalToast.show("获取签到状态失败！",Toast.LENGTH_LONG);
                    }
                });
                break;
        }
    }
}
