package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.JsonArray;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.adapter.CollectAdapter;
import com.rcdz.medianewsapp.model.bean.CollectListInfoBean;
import com.rcdz.medianewsapp.model.bean.FaimilyListInfiBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetCollectList;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.customview.ClearEditText;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 14:52
 */
public class MyCollectActivity extends BaseActivity implements GetCollectList {
    public static Map<Integer, Boolean> selectCollectMap = new HashMap<Integer, Boolean>();//键id.值状态
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.collect_edit)
    TextView collectEdit;
    @BindView(R.id.collect_sousuo)
    ClearEditText collectSousuo;
    @BindView(R.id.collect_recyclerview)
    NRecyclerView collectRecyclerview;
    @BindView(R.id.delete_cllect)
    LinearLayout deleteCllect;
    public static Boolean isSelected=false;

    private List<CollectListInfoBean.CollectInfo> list=new ArrayList<>();

    CollectAdapter collectAdapter;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.mycollect;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        list.clear();
        selectCollectMap.clear();
        collectRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        collectAdapter=new CollectAdapter(this,list,R.layout.collect_item);
        collectRecyclerview.setAdapter(collectAdapter);
    }

    @Override
    public void inintData() {
        //回车搜索
        collectSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //此处做逻辑处理
                    if(collectSousuo.getText()!=null){
                        String ssContent=collectSousuo.getText().toString();
                        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(MyCollectActivity.this);
                        newNetWorkPersenter.GetCollectList(MyCollectActivity.this,ssContent); //查询全部

                    }

                    return true;
                }
                return false;
            }
        });

        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetCollectList(this,""); //查询全部
    }



    @OnClick({R.id.back, R.id.collect_edit, R.id.delete_cllect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.collect_edit:
                   if(collectEdit.getText().equals("管理")){
                       collectEdit.setText("完成");
                       isSelected=true;

                       deleteCllect.setVisibility(View.VISIBLE);
                       collectAdapter.notifyDataSetChanged();

                   }else if(collectEdit.getText().equals("完成")){
                       collectEdit.setText("管理");
                       isSelected=false;
                       deleteCllect.setVisibility(View.GONE);
                       collectAdapter.notifyDataSetChanged();
                   }
                break;
            case R.id.delete_cllect:
                Set<Integer> set= selectCollectMap.keySet();
                JsonArray jsonArray=new JsonArray();
                for (int str : set) {
                    if(selectCollectMap.get(str)){
                        jsonArray.add(String.valueOf(str));
                    }
                }
                String ss=jsonArray.toString();
                //删除收藏
            //{"status":true,"code":317,"message":"删除成功","data":null}
                CommApi.postAddJson("api/Sys_UserStores/Del",ss).execute(new CustomStringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code= jsonObject.getInt("code");
                                String message=jsonObject.getString("message");
                                if(code!=200){
                                    GlobalToast.show(message, Toast.LENGTH_LONG);
                                }else{
                                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(MyCollectActivity.this);
                                    newNetWorkPersenter.GetCollectList(MyCollectActivity.this,"");
                                    isSelected=false;
                                    //刷新列表
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

    @Override
    public void getCollect(CollectListInfoBean collectListInfoBean) { //todo 暂时不添加分页
        list.clear();
        list.addAll(collectListInfoBean.getRows());
        selectCollectMap.clear();
        for(int i=0;i<list.size();i++){
            selectCollectMap.put(list.get(i).getId(),false); //初始化
        }
        collectAdapter.notifyDataSetChanged();
    }
}
