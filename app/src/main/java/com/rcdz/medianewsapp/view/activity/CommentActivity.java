package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.CommentAdapter;
import com.rcdz.medianewsapp.model.adapter.HistoryAdapter;
import com.rcdz.medianewsapp.model.bean.CommentInfoBean;
import com.rcdz.medianewsapp.model.bean.HistoryListInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetComment;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 20:21
 */
public class CommentActivity extends BaseActivity implements GetComment {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.comment_list)
    NRecyclerView commentList;

    CommentAdapter commentAdapter;
    int mPage=1;
    private ArrayList<CommentInfoBean.CommentInfo> list=new ArrayList<>();


    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.comment;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);

    }

    @Override
    public void inintData() {
        commentAdapter= new CommentAdapter(CommentActivity.this,list,R.layout.item_comment);
        commentList.setLayoutManager(new LinearLayoutManager(this));
        commentList.setAdapter(commentAdapter);
        commentList.setLoadingListener(new LoadingListener() {
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
        newNetWorkPersenter.GetCommentList(this,String.valueOf(mPage));
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
    }

    @Override
    public void getcomment(CommentInfoBean commentInfoBean) {
        list.addAll(commentInfoBean.getRows());
        commentList.refreshComplete();//刷新成功
        commentAdapter.notifyDataSetChanged();
        if (list.size() >= Integer.valueOf(commentInfoBean.getTotal())) {
            commentList.setNoMore(true);//没有更多了
        } else {
            commentList.loadMoreComplete();//加载成功
        }
    }
}
