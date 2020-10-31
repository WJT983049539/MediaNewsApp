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
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.MainApi;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelInfo;
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
 * time 2020/10/22 17:39
 */
public class SexEditActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sex_men)
    TextView sexMen;
    @BindView(R.id.sex_women)
    TextView sexWomen;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.edit_sex;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.back, R.id.sex_men, R.id.sex_women})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.sex_men:
                Map<String,String> areaMap = new HashMap<String,String>();
                areaMap.put("Gender","0");
                CommApi.post("api/Sys_User/UpdateUserGender",areaMap).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            Log.i("test","修改性别成功-->");
                            Intent intent=new Intent();
                            intent.putExtra("sex","男");
                            setResult(10,intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.i("test","直播列表列表失败-->"+response.message());
                        GlobalToast.show("修改失败,请稍后重试！", Toast.LENGTH_LONG);
                    }
                });
                break;
            case R.id.sex_women:
                Map<String,String> areaMap1 = new HashMap<String,String>();
                areaMap1.put("Gender","1");
                CommApi.post("api/Sys_User/UpdateUserGender",areaMap1).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            Log.i("test","修改性别成功-->");
                            Intent intent=new Intent();
                            intent.putExtra("sex","女");
                            setResult(10,intent);
                            finish();

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.i("test","直播列表列表失败-->"+response.message());
                    }
                });
                break;
        }
    }
}
