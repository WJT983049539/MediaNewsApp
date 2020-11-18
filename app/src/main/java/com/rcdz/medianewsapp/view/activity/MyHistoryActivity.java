package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.adapter.CommonAdapter;
import com.rcdz.medianewsapp.model.adapter.HistoryAdapter;
import com.rcdz.medianewsapp.model.bean.HistoryListInfoBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetHistory;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用: 历史记录
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:19
 */
public class MyHistoryActivity extends BaseActivity implements GetHistory {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.history_list)
    NRecyclerView historyList;
    HistoryAdapter historyAdapter;
    int mPage=1;
    private ArrayList<HistoryListInfoBean.HistoryInfo> list=new ArrayList<>();


    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        historyAdapter= new HistoryAdapter(MyHistoryActivity.this,list,R.layout.item_history);
        historyList.setLayoutManager(new LinearLayoutManager(this));
        historyList.setAdapter(historyAdapter);
        historyList.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        mPage = 1;
                        list.clear();
                        initNewsList(mPage);
                    }
                }, 1000);
            }

            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                initNewsList(mPage);
            }
        });

        initNewsList(mPage);
    }

    private void initNewsList(int mPage) {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetHistoryList(this,String.valueOf(mPage));
    }

    @Override
    public void inintData() {

    }

    @OnClick({R.id.back, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.clear: //清除全部
                //{"status":true,"code":200,"message":"清除成功!","data":87}
                CommApi.postNoParams("api/Sys_UserFootMark/DelAllFootMark").execute(new CustomStringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            Log.i("test","清除历史记录-->"+response.message());
                            JSONObject jsonObject= null;
                            try {
                                jsonObject = new JSONObject(response.body());
                                int code=jsonObject.getInt("code");
                                if(code==200){
                                    mPage=1;
                                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(MyHistoryActivity.this);
                                    newNetWorkPersenter.GetHistoryList(MyHistoryActivity.this,String.valueOf(mPage));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.i("test","清除历史记录失败-->"+response.message());
                    }
                });
                break;
        }
    }

    @Override
    public void getHistory(HistoryListInfoBean historyListInfoBean) {
        list.addAll(historyListInfoBean.getRows());
        historyList.refreshComplete();//刷新成功
        historyAdapter.notifyDataSetChanged();
        if (list.size() >= Integer.valueOf(historyListInfoBean.getTotal())) {
            historyList.setNoMore(true);//没有更多了
        } else {
            historyList.loadMoreComplete();//加载成功
        }
    }
}
