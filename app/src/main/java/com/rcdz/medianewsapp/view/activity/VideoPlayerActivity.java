package com.rcdz.medianewsapp.view.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYTextureView;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.model.adapter.CannelDateAdapter;
import com.rcdz.medianewsapp.model.adapter.CommentAdapter;
import com.rcdz.medianewsapp.model.adapter.ProgressAdapter;
import com.rcdz.medianewsapp.model.bean.CannelProgeressDateListBean;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.model.bean.YuYueProgresListInfoBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.AddCollect;
import com.rcdz.medianewsapp.persenter.interfaces.AddReserve;
import com.rcdz.medianewsapp.persenter.interfaces.DeleteYuyue;
import com.rcdz.medianewsapp.persenter.interfaces.DisCollect;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelDataInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetProgerssListInfo;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.tools.Strings;
import com.rcdz.medianewsapp.tools.SystemAppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 作用:频道播放
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 12:01
 */
public class VideoPlayerActivity extends BaseActivity implements GetCannelDataInfo , GetProgerssListInfo , AddReserve, DeleteYuyue, AddCollect, DisCollect {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.video_view)
    RelativeLayout video_view;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.backBtn)
    ImageButton backBtn;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.openfitmix)
    ImageView openfitmix;
    @BindView(R.id.cannel_player)
    KSYTextureView mVideoView;
    @BindView(R.id.cannel_title)
    TextView cannelTitle;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.cancel_share)
    ImageView cancelShare;
    @BindView(R.id.cannel_date)
    RecyclerView cannelDate;
    @BindView(R.id.prigresslist)
    RecyclerView prigresslist;
    @BindView(R.id.seekBarView)
    LinearLayout seekBarView;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    //播放时间
    @BindView(R.id.openStartTime)
    TextView openStartTime;
    //总的时间
    @BindView(R.id.openSunTime)
    TextView openSunTime;
    private String playurl;
    private String id;
    private String title;
    private String image;
    private Boolean isCollect=false;
//    TvCannelBean.TvCanneInfo tvCanneInfo;
    CannelDateAdapter cannelDateAdapter;
    ProgressAdapter progressAdapter;
    public  static List<String> SelectedDatelist=new ArrayList<>();
    private List<CannelProgeressDateListBean.CannelDataInfo> datelist=new ArrayList<>();
    private List<YuYueProgresListInfoBean.YuYueProgresInfo> progressList=new ArrayList<>();
    private String rid;
    public static final int UPDATE_SEEKBAR = 0; //更新进度条
    public static final int HIDDEN_SEEKBAR = 1;
    public static final int UPDATE_CURPROGRAM = 2;
    private Timer timer=null;
    private boolean loginStru=false;
    private  int dataId=-1;
//    private String PlantName;
//    private String PlantId;
    //8.处理消息
    private Handler  mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_SEEKBAR:
                    setVideoProgress(0);//更新进度条上的时间
                    break;

                case 2:
                    //刷新任务
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(VideoPlayerActivity.this); //获取频道信息
                    newNetWorkPersenter.GetCannelDateInfo(id,VideoPlayerActivity.this);
                    break;
            }
        }
    };
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_cannel;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);

        if(Constant.isQuanping){
            toolbar.setVisibility(View.GONE);
            backBtn.setVisibility(View.VISIBLE);

//            content.setVisibility(View.VISIBLE);//内容区隐藏
        }else{
            toolbar.setVisibility(View.VISIBLE);
            backBtn.setVisibility(View.GONE);

//            content.setVisibility(View.GONE);//内容区隐藏
        }

        rid = JPushInterface.getRegistrationID(getApplicationContext());
//        tvCanneInfo= (TvCannelBean.TvCanneInfo) getIntent().getSerializableExtra("tvinfo");
//        PlantName=getIntent().getStringExtra("PlantName");
//        PlantId=getIntent().getStringExtra("PlantId");
        id= getIntent().getStringExtra("id");
        title= getIntent().getStringExtra("name");
        playurl= getIntent().getStringExtra("url");
        image= getIntent().getStringExtra("image");

        datelist.clear();
        loginStru= (boolean) SharedPreferenceTools.getValueofSP(VideoPlayerActivity.this,"loginStru",false);
        if(loginStru){
            CheckCollectStatu();//检查收藏状态
        }
        prigresslist.setLayoutManager(new LinearLayoutManager(this));
        progressAdapter=new ProgressAdapter(this,progressList,R.layout.item_yuyue_progress);
        prigresslist.setAdapter(progressAdapter);
        progressAdapter.setLivingRefresh(new ProgressAdapter.LivingRefresh() {
            @Override
            public void goRefresh(Long jgtime) {
                if(timer!=null){
                    timer.cancel();
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //做刷新操作
                        mHandler.sendEmptyMessage(2);
                    }
                },jgtime);
            }
        });

        progressAdapter.setOnItemClick(new ProgressAdapter.OnItemClick() {
            @Override
            public void onitemclik(String type,YuYueProgresListInfoBean.YuYueProgresInfo item) {  //Type 1 回看 2预约 3取消预约

                if(type.equals("1")){
                    if(mVideoView!=null){
                        try {
                            mVideoView.setDataSource(playurl);
                            mVideoView.prepareAsync();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else if(type.equals("2")){ //添加预约
                    if(loginStru){
                        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(VideoPlayerActivity.this);
                        String progressId= String.valueOf(item.getId());
                        newNetWorkPersenter.AddYuYue(progressId,String.valueOf(item.getChannelId()),item.getProgramTime(),item.getStartTime(),item.getName(),item.getProgramUrl(),rid,VideoPlayerActivity.this);
                    }else{
                            startActivity(new Intent(VideoPlayerActivity.this,LoginActivity.class));
                    }
                }else if(type.equals("3")){
                    //取消预约

                    if(loginStru){
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(VideoPlayerActivity.this);
                    if( item.getFlag()!=null){
                        String yuyueId= (String) item.getFlag();
                        if( item.getScheduleId()!=null){
                            String ScheduleId=item.getScheduleId();
                            newNetWorkPersenter.DeleteYuYue(yuyueId,ScheduleId,VideoPlayerActivity.this);
                        }

                    }else{
                        GlobalToast.show("无法取消预约",Toast.LENGTH_LONG);
                    }
                    }else{
                        startActivity(new Intent(VideoPlayerActivity.this,LoginActivity.class));
                    }
                }
            }
        });


        LinearLayoutManager linearLayoutManager=  new LinearLayoutManager(VideoPlayerActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        cannelDate.setLayoutManager(linearLayoutManager);
        cannelDateAdapter=new CannelDateAdapter(datelist);
        cannelDate.setAdapter(cannelDateAdapter);
        cannelDateAdapter.setOnItemClick(new CannelDateAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                SelectedDatelist.clear();
                SelectedDatelist.add(datelist.get(position).getDate());
                cannelDateAdapter.notifyDataSetChanged();//并且让内容居中
                cannelDate.scrollToPosition(position);
                //日期点击事件
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(VideoPlayerActivity.this);
                newNetWorkPersenter.GetProgressListForDate(id,datelist.get(position).getDate(),VideoPlayerActivity.this);
            }
        });
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this); //获取频道信息
        newNetWorkPersenter.GetCannelDateInfo(id,this);
    }

    @Override
    public void inintData() {
        initVideoView();


    }

    private void initVideoView() {
        //设置监听器
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);//网络流媒体的缓冲变化时回调
        mVideoView.setOnCompletionListener(mOnCompletionListener);//网络流媒体播放结束时回调
        mVideoView.setOnPreparedListener(mOnPreparedListener);//当装载流媒体完毕的时候回调。


        //设置播放参数
        mVideoView.setBufferTimeMax(2.0f);
        mVideoView.setTimeout(5, 30);
        //设置播放地址并准备
        try {
            mVideoView.setDataSource(playurl);
            mVideoView.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ex) {

        }
    }


    @OnClick({R.id.img_back, R.id.cannel_title, R.id.collect, R.id.cancel_share,R.id.openfitmix,R.id.backBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                this.finish();
                break;
            case R.id.backBtn:
                this.finish();
                break;
            case R.id.cannel_title:
                break;
            case R.id.collect://收藏
                //{"status":false,"code":400,"message":"当前状态为未收藏","data":null}
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(VideoPlayerActivity.this);
                if(!isCollect){
                    newNetWorkPersenter.AddCollect("6",id,title,playurl,"6","-1",image,VideoPlayerActivity.this);
                }else{
                    newNetWorkPersenter.DeleteCollect(String.valueOf(dataId),VideoPlayerActivity.this);
                }
                break;
            case R.id.cancel_share:
                break;
            case R.id.openfitmix: //全屏
                if(Constant.isQuanping){ //旋转为竖屏
                    toolbar.setVisibility(View.VISIBLE);
                    backBtn.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);//内容区隐藏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Constant.isQuanping=false;

                    DisplayMetrics dm = new DisplayMetrics();
                    this.getWindowManager().getDefaultDisplay().getMetrics(dm);
                    int screenWidth = dm.widthPixels;
                    int screenHeight = dm.heightPixels;
                    //设置全屏
                    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) video_view.getLayoutParams();
                    int px=SystemAppUtils.dip2px(this,224);
                    params.height =px;
                    video_view.setLayoutParams(params);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                        mVideoView.setRotation(90);
                    openfitmix.setBackgroundResource(R.mipmap.quanping);

                }else{ //旋转为横屏
                    toolbar.setVisibility(View.GONE);
                    backBtn.setVisibility(View.VISIBLE);
                    content.setVisibility(View.GONE);//内容区隐藏
                    Constant.isQuanping=true;

                    //竖屏转横屏
                    DisplayMetrics dm = new DisplayMetrics();
                    this.getWindowManager().getDefaultDisplay().getMetrics(dm);
                    int screenWidth = dm.widthPixels;
                    int screenHeight = dm.heightPixels;
                        //设置全屏
                        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) video_view.getLayoutParams();
                        params.height =screenWidth;
                        video_view.setLayoutParams(params);
                         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                        mVideoView.setRotation(90);
                         openfitmix.setBackgroundResource(R.mipmap.disquanping);

                }
                break;
        }
    }

    //得到节目日期，有今天的显示今天的
    @Override
    public void getCannelData(CannelProgeressDateListBean cannelProgeressDateListBean) {
        if(cannelProgeressDateListBean!=null&&cannelProgeressDateListBean.getData()!=null&&cannelProgeressDateListBean.getData().size()!=0){
            datelist.addAll(cannelProgeressDateListBean.getData());
            cannelDateAdapter.notifyDataSetChanged();

            if(cannelProgeressDateListBean!=null&&cannelProgeressDateListBean.getData().size()!=0){
                if(!cannelProgeressDateListBean.getData().get(0).getDate().equals("")){
                    //有今天显示今天的
                    for(int i=0;i<cannelProgeressDateListBean.getData().size();i++){
                        if(cannelProgeressDateListBean.getData().get(i).getTitle().equals("今天")){
                            String datee=cannelProgeressDateListBean.getData().get(i).getDate();
                            SelectedDatelist.clear();
                            SelectedDatelist.add(datee);
                            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
                            newNetWorkPersenter.GetProgressListForDate(id,cannelProgeressDateListBean.getData().get(i).getDate(),VideoPlayerActivity.this);
                        }
                    }


                }else{
                    GlobalToast.show("数据有误,日期为空", Toast.LENGTH_LONG);
                }
            }
        }else{
            GlobalToast.show("无预约节目",Toast.LENGTH_LONG);
        }
    }
    //得到该天的节目详细列表
    @Override
    public void getProgressList(YuYueProgresListInfoBean yuYueProgresListInfoBean) {

        progressList.clear();
        if(yuYueProgresListInfoBean.getData().size()!=0){
            progressList.addAll(yuYueProgresListInfoBean.getData());
        }
        progressAdapter.notifyDataSetChanged();
    }

    //为MediaPlayer调用prepare()方法时触法该监听器。
    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if (mVideoView != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                //mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_NOSCALE_TO_FIT);
                // 开始播放视频
                mVideoView.start();
                setVideoProgress(0);

            }
        }
    };

    //设置播放进度条 //每秒回调自己
    public int setVideoProgress(int currentProgress) {
        if (mVideoView == null)
            return -1;
        long time = currentProgress > 0 ? currentProgress : mVideoView.getCurrentPosition();
        long length = mVideoView.getDuration();
        //更新
        seekBar.setMax((int) length);
        seekBar.setProgress((int) time);
        if (time >= 0) {
            String progress = Strings.millisToString(time);
            String sunTime = Strings.millisToString(length);
            openStartTime.setText(progress);
            openSunTime.setText(sunTime);
        }
        Message msg = new Message();
        msg.what = UPDATE_SEEKBAR;
        if (mHandler != null)
            mHandler.sendMessageDelayed(msg, 1000);
        return (int) time;
    }

    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            // todo 播放完了，重新刷新
            seekBarView.setVisibility(View.VISIBLE);
            mVideoView.reload(playurl, true);
            mVideoView.prepareAsync();
        }
    };
    //当从网络加载流媒体时流媒体缓冲的监听器
    private IMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            if (mVideoView != null) {
                long duration = mVideoView.getDuration();
//                long progress = duration * percent / 100;
//                seekBar.setSecondaryProgress((int) progress);
            }
        }
    };

    /**
     * 预约成功 ，去更新节目列表状态
     */
    @Override
    public void addrever() {
        if(SelectedDatelist.size()>0){
            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
            newNetWorkPersenter.GetProgressListForDate(id,SelectedDatelist.get(0),VideoPlayerActivity.this);
        }
    }
    //取消预约，去更新列表状态
    @Override
    public void DeleteYuyue() {
        if(SelectedDatelist.size()>0){
            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
            newNetWorkPersenter.GetProgressListForDate(id,SelectedDatelist.get(0),VideoPlayerActivity.this);
        }
    }
    public void CheckCollectStatu(){
    //{"status":true,"code":200,"message":"当前状态为已收藏","data":3}
    //{"status":false,"code":400,"message":"当前状态为未收藏","data":null}
        CommApi.postNoParams("api/Sys_UserStores/IsDo/6/"+id).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i("test","收藏状态-->"+response.message());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code= jsonObject.getInt("code");
                        if(code==400){
                            //未售出
                            Glide.with(VideoPlayerActivity.this).load(R.mipmap.collect_img).into(collect);
                            isCollect=false;
                        }else if(code==200){
                            dataId= jsonObject.getInt("data");
                            isCollect=true;
                            Glide.with(VideoPlayerActivity.this).load(R.mipmap.collected).into(collect);
                            //已经收藏
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i("test","收藏状态-失败-->"+response.message());
            }
        });
    }

    @Override
    public void addcollect() {
        CheckCollectStatu();
    }

    @Override
    public void disCollect() {
        CheckCollectStatu();
    }
}
