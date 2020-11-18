package com.rcdz.medianewsapp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.AppealHotAdapter;
import com.rcdz.medianewsapp.model.adapter.LeaveMessageAdapter;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetPliveLeaveMsgInfo;
import com.rcdz.medianewsapp.view.activity.MessageDetailActivity;
import com.rcdz.medianewsapp.view.activity.WriteMessageActivity;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:我的留言
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 8:44
 */
public class LeavingMessageFragment extends Fragment implements GetPliveLeaveMsgInfo {
    View mRootView;
    @BindView(R.id.leavingMessage)
    NRecyclerView RC_leavingMessage;
    @BindView(R.id.leaveBtn)
    LinearLayout leaveBtn;
    private AppealHotAdapter leaveMessageAdapter;
    private Activity mContext;
    public ArrayList<PliveLeaveInfo.LeaveMessageInfo> dataList = new ArrayList<PliveLeaveInfo.LeaveMessageInfo>();
    private int mPage = 1;

    public  LeavingMessageFragment() {
    }

    public static OrganizationListFragment newInstance() {
        OrganizationListFragment fragment = new OrganizationListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_leavemessage, container, false);
            ButterKnife.bind(this, mRootView);
            mContext = getActivity();
            //先清空
            dataList.clear();
            initView();
        }
        return mRootView;
    }

    private void initView() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetPLiveLeaveInfo(String.valueOf(mPage), "1", this);
        leaveMessageAdapter = new AppealHotAdapter(dataList,getActivity());
        RC_leavingMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        RC_leavingMessage.setAdapter(leaveMessageAdapter);
        leaveMessageAdapter.setOnItemClick(new AppealHotAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                //留言详情
                Intent intent = new Intent(mContext, MessageDetailActivity.class);
                intent.putExtra("id",dataList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        RC_leavingMessage.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mPage = 1;
                        dataList.clear();
                        initListData(mPage);
                    }
                }, 1000);
            }

            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                initListData(mPage);
            }
        });
    }

    private void initListData(int mPage) {

        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetPLiveLeaveInfo(String.valueOf(mPage), "1", this);
    }

    @Override
    public void getPliveLeaveMsgInfo(PliveLeaveInfo pliveLeaveInfo) {

        RC_leavingMessage.refreshComplete();//刷新成功
        int total = pliveLeaveInfo.getTotal();
        dataList.addAll(pliveLeaveInfo.getRows());

        if (dataList.size() == total) {
            RC_leavingMessage.setNoMore(true);//没有更多了
        } else {
            RC_leavingMessage.loadMoreComplete();//加载成功
        }
        leaveMessageAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.leaveBtn)
    public void onViewClicked(View view) {
        if(view.getId()==R.id.leaveBtn){
            Intent intent = new Intent(getActivity(), WriteMessageActivity.class);
            getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            getActivity().startActivity(intent);
        }
    }
}

