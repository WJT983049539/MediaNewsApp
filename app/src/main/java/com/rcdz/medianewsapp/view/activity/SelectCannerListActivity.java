package com.rcdz.medianewsapp.view.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.AllPlateAdapter;
import com.rcdz.medianewsapp.model.adapter.MyPlateAdapter;
import com.rcdz.medianewsapp.model.bean.MessageEvent3;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.NoSetionsBean;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetNoSationList;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserSetion;
import com.rcdz.medianewsapp.persenter.interfaces.ISowUserPlate;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.SetList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:栏目编辑
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/16 9:19
 */
 public class SelectCannerListActivity extends BaseActivity implements  GetUserSetion, GetNoSationList {
    @BindView(R.id.tv_edit_mycannel)
    TextView tvEditMycannel;
    @BindView(R.id.mycannel)
    RecyclerView mycannel;
    @BindView(R.id.allcannel)
    RecyclerView allcannel;
//    public static Boolean SelectPlate = false;
    NewNetWorkPersenter newsNetWorkPersenter = null;
    SetionBean setionBean;//全部版块信息
    SetList<SetionBean.DataBean> UserList = new SetList<>(); //用户版块集合
    SetList<SetionBean.DataBean> OrderUserList = new SetList<>(); //除了用户版块以外的集合
    private SetionBean.DataBean TJplate = null;//推荐
    MyPlateAdapter myPlateAdapter;
    AllPlateAdapter allPlateAdapter;
    @Override
    public String setNowActivityName() {
        return "版块修改";
    }
    @Override
    public int setLayout() {
        return R.layout.activity_selectcanner;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
//        ACache aCache = ACache.get(this);
//        setionBean = (SetionBean) aCache.getAsObject("setionBean");//这是所有的title
        newsNetWorkPersenter = new NewNetWorkPersenter(this);
    }

    @Override
    public void inintData() {
        newsNetWorkPersenter.GetUserSetion(this);//获取用户自己的版块
        newsNetWorkPersenter.getNoUserSetions("1",this);//获取用户取消的版块
    }


    /**
     * 得到用户版块信息
     */
    @Override
    public void getUserSetion(SetionBean setionBean) {
        UserList.clear();
        OrderUserList.clear();
        if (setionBean.getData().size() != 0&&setionBean.getData()!=null) {
            if(TJplate!=null){
                UserList.addFirst(TJplate);
            }
            for (SetionBean.DataBean plateinfo : setionBean.getData()) {
                if(!UserList.contains(plateinfo)){
                    UserList.add(plateinfo);
                }

            }
        } else {
            if(TJplate!=null){
                UserList.addFirst(TJplate);//默认永远有推荐
            }

        }

        mycannel.setLayoutManager(new GridLayoutManager(this, 4));
        myPlateAdapter= new MyPlateAdapter(UserList);
        mycannel.setAdapter(myPlateAdapter);
        myPlateAdapter.setOnItemClick(new MyPlateAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                //删除版块事件 推荐版块不允许删除
                OrderUserList.add(UserList.get(position));
                UserList.remove(position);
                if(allPlateAdapter!=null){
                    allPlateAdapter.notifyDataSetChanged();
                }
                if(myPlateAdapter!=null){
                    myPlateAdapter.notifyDataSetChanged();
                }


            }
        });


    }



    @OnClick({R.id.tv_edit_mycannel, R.id.mycannel, R.id.allcannel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_mycannel: //编辑按钮
                tvEditMycannel.setText("完成");
                String tempPlateId="";
                if(OrderUserList!=null&&OrderUserList.size()!=0){
                    MessageEvent3 messageEvent3=new MessageEvent3();
                    messageEvent3.setUserList(OrderUserList);
                    EventBus.getDefault().post(messageEvent3);
                    SelectCannerListActivity.this.finish();
                }
//                if(tvEditMycannel.getText().equals("编辑")){
//                    SelectPlate=true;
//                    if(myPlateAdapter!=null){
//                        myPlateAdapter.notifyDataSetChanged();
//                    }
//                    ;
//                }else if(tvEditMycannel.getText().equals("完成")){
//                    SelectPlate=false;
//                    tvEditMycannel.setText("编辑");
//                    if(myPlateAdapter!=null){
//                        myPlateAdapter.notifyDataSetChanged();
//                    }
//
//                }
                break;
        }
    }
    //获取用户取消的版块
    @Override
    public void getNoSationList(NoSetionsBean noSetionsBean) {
        OrderUserList.clear();
        List<NoSetionsBean.RowsBean>ll= noSetionsBean.getRows();

        if(noSetionsBean.getRows().size()!=0){
            for(int  i=0;i<noSetionsBean.getRows().size();i++){
                SetionBean.DataBean dataBean=new SetionBean.DataBean();
                dataBean.setId(noSetionsBean.getRows().get(i).getSectionId());
                dataBean.setName(noSetionsBean.getRows().get(i).getSectionName());
                OrderUserList.add(dataBean);
            }
        }

        //显示出来用户选择的版块以外的版块
        allcannel.setLayoutManager(new GridLayoutManager(this,4));
        allPlateAdapter=new AllPlateAdapter(OrderUserList);
        allcannel.setAdapter(allPlateAdapter);
        allPlateAdapter.setOnItemClick(new AllPlateAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                    UserList.add(OrderUserList.get(position));
                    OrderUserList.remove(position);
                    if(allPlateAdapter!=null){
                        allPlateAdapter.notifyDataSetChanged();
                    }
                    if(myPlateAdapter!=null){
                        myPlateAdapter.notifyDataSetChanged();
                    }
            }
        });
    }
}