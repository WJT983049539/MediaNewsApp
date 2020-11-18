package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.JifenAdapter;
import com.rcdz.medianewsapp.model.bean.JiFenLogBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetJifenList;
import com.rcdz.medianewsapp.view.fragment.LivingFragment;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/22 14:31
 */
public class MyJifenActivity extends BaseAppCompatActivity implements GetJifenList {
    @BindView(R.id.jifen_list)
    NRecyclerView jifenList;
    @BindView(R.id.back)
    ImageView back;

    private String AllJiFen;
    String User_id;
    private int mPage=1;
    JifenAdapter jifenAdapter;
    List<JiFenLogBean.JiFenLog>  logList=new ArrayList<JiFenLogBean.JiFenLog>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jifen;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        logList.clear();
        NewNetWorkPersenter newsNetWorkPersenter=new NewNetWorkPersenter(this);
        newsNetWorkPersenter.jifenList(String.valueOf(mPage),this);
        jifenAdapter=new JifenAdapter(logList);
        //设置LayoutManager为LinearLayoutManager
        jifenList.setLayoutManager(new LinearLayoutManager(this));
        jifenList.setAdapter(jifenAdapter);
        jifenList.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        logList.clear();
                        NewNetWorkPersenter newsNetWorkPersenter = new NewNetWorkPersenter(MyJifenActivity.this);
                        newsNetWorkPersenter.jifenList("1",MyJifenActivity.this);
                    }
                }, 1000);
            }
            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                NewNetWorkPersenter newsNetWorkPersenter = new NewNetWorkPersenter(MyJifenActivity.this);
                newsNetWorkPersenter.jifenList(String.valueOf(mPage),MyJifenActivity.this);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyJifenActivity.this.finish();
            }
        });
    }
    /**
     * 得到积分
     * @param List
     */
    @Override
    public void getJifenNum(JiFenLogBean List) {
        logList.addAll(List.getRows());
        jifenAdapter.notifyDataSetChanged();
        jifenList.refreshComplete();//刷新成功
        if (logList.size() >= Integer.valueOf(List.getTotal())) {
            jifenList.setNoMore(true);//没有更多了
        } else {
            jifenList.loadMoreComplete();//加载成功
        }
    }
}