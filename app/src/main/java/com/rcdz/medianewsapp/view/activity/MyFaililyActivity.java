package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.adapter.CommonAdapter;
import com.rcdz.medianewsapp.model.adapter.CommonRecyclerAdapter;
import com.rcdz.medianewsapp.model.adapter.CommonViewHolder;
import com.rcdz.medianewsapp.model.adapter.Familydapter;
import com.rcdz.medianewsapp.model.bean.FaimilyListInfiBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.ImageUtils;
import com.rcdz.medianewsapp.view.customview.AddFamilyPeopleDialog;
import com.rcdz.medianewsapp.view.customview.MyGridView;
import com.rcdz.medianewsapp.view.customview.yaoqingmaDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:家庭
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/22 19:29
 */
public class MyFaililyActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.yaoaing)
    TextView yaoaing;
    @BindView(R.id.mygrid)
    RecyclerView mygrid;
    @BindView(R.id.peoplenum)
    TextView pooplenums;
    Familydapter familydapter;
    public static ArrayList<String> photopath = new ArrayList<>();//图片地址
    private String jj="";
    private String userId="";
    private int FamilyId;
    private List<FaimilyListInfiBean.FaimilyInfo> list=new ArrayList<FaimilyListInfiBean.FaimilyInfo>();
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.faimily;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        ACache aCache=ACache.get(this);
        UserInfoBean userInfoBean= (UserInfoBean) aCache.getAsObject("userinfo");

        if(userInfoBean!=null){
            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
            newNetWorkPersenter.GetUserInfo("",null);
        }else{
            return;
        }


        userId= String.valueOf(userInfoBean.getData().getUser_Id());
        familydapter=new Familydapter(MyFaililyActivity.this,list);
        mygrid.setLayoutManager(new GridLayoutManager(MyFaililyActivity.this,6));
        mygrid.setAdapter(familydapter);
        familydapter.setOnItemClick(new Familydapter.OnItemClick() {
            @Override
            public void onitemclik(int position, boolean flag) {
                Log.i("test",position+"");
                if(!flag){ //不是真数据
                    //表示点击到了加号
                    AddFamilyPeopleDialog addFamilyPeopleDialog=new AddFamilyPeopleDialog(MyFaililyActivity.this);
                    addFamilyPeopleDialog.setOnDialogListen(new AddFamilyPeopleDialog.Confirm() {
                        @Override
                        public void ok(String info) {

                            if(info.equals(userId)){
                                GlobalToast.show("不能添加本人家庭", Toast.LENGTH_LONG);
                                return;
                            }

                            try {
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("Code",info);
                            jsonObject1.put("Family",FamilyId);
                            jj= jsonObject1.toString();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //{"status":false,"code":400,"message":"邀请码不存在!","data":null}
                            CommApi.postAddJson("api/Sys_UserFamily_Users/AddUserToFamily",jj).execute(new CustomStringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    if(response.body()!=null){
                                        try {
                                            JSONObject jsonObject=new JSONObject(response.body());
                                           int code= jsonObject.getInt("code");
                                            String message=jsonObject.getString("message");
                                            if(code!=200){
                                                GlobalToast.show(message,Toast.LENGTH_LONG);
                                            }else{
                                                getFamilyList();//刷新列表
                                            }
                                            addFamilyPeopleDialog.cancel();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        Log.i("test","添加家庭成员-->"+response.body().toString());
                                    }
                                }

                                @Override
                                public void onError(Response response) {
                                    super.onError(response);
                                    Log.i("test","家庭成员失败-->"+response.message());
                                }
                            });





                            Log.i("test",info);
                        }
                    });
                    addFamilyPeopleDialog.show();
                }else{
                    //查看人员信息
                    Intent intent=new Intent(MyFaililyActivity.this,PersonInfoActivity.class);
                    intent.putExtra("userId",list.get(position).getUserId());
                    startActivity(intent);


                }

            }
        });

        getFamilyList();
    }

    private void getFamilyList() {
        Map<String,String> areaMap = new HashMap<String,String>();
        //{"status":true,"code":200,"message":"查询成功!","data":[{"createDate":"2020-10-23 09:00:38","familyId":8,"joinType":0,"userId":16,"headImageUrl":null}]}
        CommApi.postNoParams("api/Sys_UserFamily_Users/GetMyFamilyUsers").execute(new JsonCallback<FaimilyListInfiBean>() {
            @Override
            public void onSuccess(Response<FaimilyListInfiBean> response) {
                if(response.body()!=null){
                    list.clear();
                    list.addAll(response.body().getData());
                    pooplenums.setText(list.size()+"");
                    FaimilyListInfiBean.FaimilyInfo faimilyInfo=new FaimilyListInfiBean.FaimilyInfo();
                    FamilyId= list.get(0).getFamilyId();
                    faimilyInfo.setRealse(false);
                    list.add(faimilyInfo);
                    familydapter.notifyDataSetChanged();

                    Log.i("test","全部家庭成员-->"+response.body().toString());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i("test","家庭成员失败-->"+response.message());
            }
        });
    }

    @Override
    public void inintData() {


    }


    @OnClick({R.id.back, R.id.yaoaing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.yaoaing: //邀请码

                Map<String,String> areaMap = new HashMap<String,String>();
                //{"status":true,"code":200,"message":"查询成功!","data":[{"createDate":"2020-10-23 09:00:38","familyId":8,"joinType":0,"userId":16,"headImageUrl":null}]}
                CommApi.postNoParams("api/Sys_UserCode/Sys_UserCode").execute(new CustomStringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            //得到邀请码
                            Log.i("test","邀请码"+response.body());
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code=jsonObject.getInt("code");
                                if(code==200){
                                   JSONObject jsonObject1= jsonObject.getJSONObject("data");
                                    String yqcode= String.valueOf(jsonObject1.getInt("codeId"));
                                    yaoqingmaDialog yaoqingmaDialog=new yaoqingmaDialog(MyFaililyActivity.this, yqcode, new yaoqingmaDialog.DialogConfirmClick() {
                                        @Override
                                        public void ConfirmClick() { }
                                    });
                                    yaoqingmaDialog.show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.i("test","家庭成员失败-->"+response.message());
                    }
                });




                break;
        }
    }




}
