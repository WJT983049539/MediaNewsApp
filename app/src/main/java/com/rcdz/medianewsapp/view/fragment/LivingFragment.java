package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.LiveingAdapter;
import com.rcdz.medianewsapp.model.bean.LiveBean;
import com.rcdz.medianewsapp.model.bean.LiveCoverInfo;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetCoverInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetLiveListInfo;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.LiveRoomActivity;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用:直播间
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 14:13
 */
public class LivingFragment extends Fragment implements GetLiveListInfo , GetCoverInfo {
    private View mRootView;
    private NRecyclerView live_ListView;
    private LiveingAdapter mAdapter;
    private int mPage = 1;
    private List<LiveBean.LiveInfo>livelists=new ArrayList<LiveBean.LiveInfo>();
    public LivingFragment(){}

    private LiveBean liveBean;
    public static LivingFragment newInstance(String param1, String param2) {
        LivingFragment fragment = new LivingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.tv_list, container, false);
            live_ListView=mRootView.findViewById(R.id.live_list);
            //先清空
            livelists.clear();
            initView();
            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
            newNetWorkPersenter.GetLivingList(String.valueOf(mPage),this);
        }
        return mRootView;
    }
    private void initView() {
        live_ListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new LiveingAdapter(livelists,getActivity());
        live_ListView.setAdapter(mAdapter);
        live_ListView.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        livelists.clear();
                        NewNetWorkPersenter newsNetWorkPersenter = new NewNetWorkPersenter(getContext());
                        newsNetWorkPersenter.GetLivingList( "1",LivingFragment.this);
                    }
                }, 1000);
            }
            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                NewNetWorkPersenter newsNetWorkPersenter = new NewNetWorkPersenter(getContext());
                newsNetWorkPersenter.GetLivingList(String.valueOf(mPage),LivingFragment.this);
            }
        });

        mAdapter.setOnItemClick(new LiveingAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                //获取直播间详情
                int liveState=livelists.get(position).getLiveState(); //2是直播其他都微直播
                String HlsUrl= livelists.get(position).getHLSUrl();
                int type=livelists.get(position).getType();
                String videourl=livelists.get(position).getRtmpUrl().toString()+"?id="+livelists.get(position).getId()+"&token="+ Constant.token;//得到直播地址开始直播
                String roomId= String.valueOf(livelists.get(position).getId());
                int CreateID= livelists.get(position).getCreateID();
                if(videourl==null||videourl.equals("")){
                    GlobalToast.show("地址为空,直播间状态有误！",5000);
                }else{
                    String name=livelists.get(position).getName();
                    Intent intent=new Intent(getActivity(), LiveRoomActivity.class);
                    intent.putExtra("videoUrl",videourl);
                    intent.putExtra("liveState",liveState);
                    intent.putExtra("name",name);
                    intent.putExtra("roomId",roomId);
                    intent.putExtra("type",type);
                    intent.putExtra("CreateID",CreateID);
                    getActivity().startActivity(intent);
                }
            }
        });
    }
    //得到直播间列表
    @Override
    public void getLiveInfo(LiveBean liveBean2) {
          liveBean=liveBean2;
        String param = "";
        List<LiveBean.LiveInfo> liveInfos=  liveBean.getRows();
        for(int i=0;i<liveInfos.size();i++){
            int  status=liveInfos.get(i).getLiveState();
            if(status==2){ //2正在直播 ，其他状态都不管
                param+=liveInfos.get(i).getId()+",";
            }
        }
        if(param.equals("")){ //没有正在直播的

        }else{
            param =param.substring(0,param.length()-1);

            NewNetWorkPersenter newsNetWorkPersenter=new NewNetWorkPersenter(getContext());
            newsNetWorkPersenter.GetLiveCoverInfo(param,this); //得到直播间列表预览图
        }
        livelists.addAll(liveBean.getRows());
        mAdapter.notifyDataSetChanged();
        live_ListView.refreshComplete();//刷新成功
        live_ListView.loadMoreComplete();//加载成功
        if(livelists.size() == Integer.valueOf(liveBean.getTotal()) ){
            live_ListView.setNoMore(true);//没有更多了
        }else{
            live_ListView.loadMoreComplete();//加载成功
        }
    }
    //得到直播间略缩图
    @Override
    public void getCoverInfo(LiveCoverInfo liveCoverInfo) {
        List<LiveBean.LiveInfo> liveInfos= liveBean.getRows();
        if(liveInfos!=null||liveInfos.size()!=0){
            List<LiveCoverInfo.CoverInfo> livePictures= liveCoverInfo.getData();
            for(int i=0;i<liveInfos.size();i++){
                int id=liveInfos.get(i).getId();
                for(int j=0;j<livePictures.size();j++){
                    if(livePictures.get(j).getId()==id){//id 相同
                        String postUrl= livePictures.get(j).getUrl()+"?aa"+Math.random();
                        liveInfos.get(i).setUrl(postUrl);
                    }
                }
            }
            livelists.clear();
            livelists.addAll(liveInfos);
            mAdapter.notifyDataSetChanged();
        }
    }
}
