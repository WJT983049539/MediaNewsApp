package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.rcdz.medianewsapp.tools.GlobalToast;

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

                if (editphone.getText() != null) {
                    String phone = editphone.getText().toString();
                    Map<String, String> areaMap = new HashMap<String, String>();
                    areaMap.put("PhoneNo", phone);
                    CommApi.post("api/Sys_User/UpdateUserPhoneNo", areaMap).execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (response.body() != null) {
                                Log.i("test", "修改手机成功-->");
                                Intent intent = new Intent();
                                intent.putExtra("phone", phone);
                                setResult(15, intent);
                                finish();
                            }
                        }

                        @Override
                        public void onError(Response response) {
                            super.onError(response);
                            Log.i("test", "修改地址成功失败-->" + response.message());
                            GlobalToast.show("修改失败,请稍后重试！",Toast.LENGTH_LONG);
                        }
                    });

                } else {
                    GlobalToast.show("家庭地址为空", Toast.LENGTH_LONG);
                }
                break;
        }
    }
}
