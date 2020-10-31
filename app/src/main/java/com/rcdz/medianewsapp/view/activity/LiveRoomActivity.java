package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonArray;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYTextureView;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.LiveBarrageAdapter;
import com.rcdz.medianewsapp.model.bean.BarrageInfobean;
import com.rcdz.medianewsapp.model.bean.LiveMessage;
import com.rcdz.medianewsapp.model.bean.LiveReciveMessage;
import com.rcdz.medianewsapp.model.bean.LivingMasterBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.model.bean.litlemessagebean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetLivingMInfo;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GsonUtil;
import com.rcdz.medianewsapp.view.customview.CommentDialog;
import com.rckj.rcsocket.JfSocketEvent;
import com.rckj.rcsocket.JfSocketSdk;
import com.shehuan.niv.NiceImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:直播间详情
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 17:08
 */
public class LiveRoomActivity extends BaseActivity implements JfSocketEvent, GetLivingMInfo {
    @BindView(R.id.ksy_textureview)
    KSYTextureView Player;
    @BindView(R.id.edit_livecomment)
    TextView editLivecomment;
    @BindView(R.id.rc_barrage)
    RecyclerView rcBarrage;
    @BindView(R.id.user_pic)
    NiceImageView userPic;
    @BindView(R.id.living_name)
    TextView livingName;
    @BindView(R.id.living_close)
    ImageView livingClose;
    @BindView(R.id.head_1)
    ImageView head_1;
    @BindView(R.id.head_2)
    ImageView head_2;
    @BindView(R.id.head_3)
    ImageView head_3;
    @BindView(R.id.tv_num)
    TextView tv_num2;
    JfSocketSdk jfSockSDK; //直播间服务器
    private RequestOptions options = new RequestOptions().error(R.mipmap.peop).centerCrop();
    List<BarrageInfobean> list = new LinkedList<BarrageInfobean>();
    private LinearLayoutManager linearLayoutManager;
    private LiveBarrageAdapter liveBarrageAdapter;
    private String videoUrl;
    private String name;
    private String roomId;
    private int CreateID;
    String picture;
    private UserInfoBean userInfoBean;

    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Log.i("live",msg.obj.toString());
                LiveReciveMessage liveReciveMessage= (LiveReciveMessage) msg.obj;
                tv_num2.setText(liveReciveMessage.getUserCount()+"");//人数
                if(liveReciveMessage.getMessageType()!=0){ //头像
                    try {
                        JSONArray jsonArray=new JSONArray(liveReciveMessage.getMessage());

                            if(jsonArray.length()==0){
                                head_1.setVisibility(View.GONE);
                                head_2.setVisibility(View.GONE);
                                head_3.setVisibility(View.GONE);
                            }else if(jsonArray.length()==1){
                              String iimag=jsonArray.getJSONObject(0).getString("Img");

                                head_1.setVisibility(View.VISIBLE);
                                head_2.setVisibility(View.GONE);
                                head_3.setVisibility(View.GONE);
                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag).apply(options).into(head_1);
                            }else if(jsonArray.length()==2){
                                String iimag=jsonArray.getJSONObject(0).getString("Img");
                                String iimag2=jsonArray.getJSONObject(1).getString("Img");

                                head_1.setVisibility(View.VISIBLE);
                                head_2.setVisibility(View.VISIBLE);
                                head_3.setVisibility(View.GONE);

                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag).apply(options).into(head_1);
                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag2).apply(options).into(head_2);
                            }else if(jsonArray.length()==3){
                                String iimag=jsonArray.getJSONObject(0).getString("Img");
                                String iimag2=jsonArray.getJSONObject(1).getString("Img");
                                String iimag3=jsonArray.getJSONObject(2).getString("Img");
                                head_1.setVisibility(View.VISIBLE);
                                head_2.setVisibility(View.VISIBLE);
                                head_3.setVisibility(View.VISIBLE);
                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag).apply(options).into(head_1);
                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag2).apply(options).into(head_2);
                                Glide.with(LiveRoomActivity.this).load(AppConfig.BASE_PICTURE_URL+iimag3).apply(options).into(head_3);
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{

                    litlemessagebean SS=GsonUtil.GsonToBean(liveReciveMessage.getMessage(),litlemessagebean.class);
                    String username=SS.getUserName();
                    String type=SS.getType();
                    String info=SS.getMessage();
                    if(type.equals("1")){ //弹幕消息
                        BarrageInfobean barrageInfobean=new BarrageInfobean();
                        barrageInfobean.setInfo(username+":"+info);
                        barrageInfobean.setHeadimage("");//没有头像了
                        list.add(barrageInfobean);
                        Log.i("live","共有"+list.size()+"条弹幕");
                        if(liveBarrageAdapter!=null){
                            liveBarrageAdapter.notifyDataSetChanged();
                            this.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rcBarrage.scrollToPosition(liveBarrageAdapter.getItemCount()-1);
                                }
                            },50);
                        }
                    }
                }




            }
        }
    };
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_live;
    }
    final Semaphore mLoginSemphore = new Semaphore(0);
    boolean bIsStart = false;
    @Override
    public void inintView() {
        ButterKnife.bind(this);
        ACache aCache=ACache.get(this);
        userInfoBean= (UserInfoBean) aCache.getAsObject("userinfo");
        videoUrl=getIntent().getStringExtra("videoUrl");
        name=getIntent().getStringExtra("name");
        roomId=getIntent().getStringExtra("roomId");
        CreateID=getIntent().getIntExtra("CreateID",0);
        livingName.setText(name);
        getPremission2();
        initVideoView();

        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetLivingMasterInfo(String.valueOf(CreateID),this);
        liveBarrageAdapter = new LiveBarrageAdapter(list,this);
        linearLayoutManager=new LinearLayoutManager(this);
        rcBarrage.setLayoutManager(new LinearLayoutManager(this));
        rcBarrage.setAdapter(liveBarrageAdapter);

        jfSockSDK = new JfSocketSdk(this); //初始化聊天服务器
        if (jfSockSDK.start(AppConfig.LiveService, 9899, true)) {
            Log.i("live", "开始连接聊天室服务器");
            jfSockSDK.SetMaxMessageSize(50000);
            jfSockSDK.SetDetectInterval(5000);
            jfSockSDK.SetDetectAttempts(2);
            jfSockSDK.SetNoDelay(true);
            try {
                mLoginSemphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initVideoView() {

        Player.setOnErrorListener(mOnErrorListener);// 发生错误时回调
        Player.setOnPreparedListener(mOnPreparedListener);//当装载流媒体完毕的时候回调。
        Player.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        //设置播放地址并准备
        try {
            Log.i("live","直播地址为"+videoUrl);
            //1.setDataSource(path)  path是一个网络地址
            Player.setDataSource(videoUrl);
            Player.setBufferTimeMax(10);
            Player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Override
    public void inintData() {

    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @OnClick({R.id.edit_livecomment, R.id.living_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_livecomment: //填写评论
                //发送信息
                CommentDialog commentDialog = new CommentDialog(this);
                commentDialog.setOnDialogListen(new CommentDialog.Confirm() {
                    @Override
                    public void ok(String six) {
                        if (six.equals("") || six.equals("")) {
                        } else {
                            //发送信息
                            if (jfSockSDK != null) {

                                LiveMessage.AuthBean authBean = new LiveMessage.AuthBean();
                                authBean.setPassword("qwer1234.0");
                                LiveMessage liveMessage = new LiveMessage();
                                liveMessage.setAuth(authBean);
                                LiveMessage.DataBean dataBean = new LiveMessage.DataBean();
                                dataBean.setRoomId(Integer.parseInt(roomId));
                                dataBean.setUserId(CreateID);
                                dataBean.setMessageType(0); //todo 暂时没有真实人数据
                                String img= AppConfig.BASE_PICTURE_URL+userInfoBean.getData().getHeadImageUrl();
                                dataBean.setImg(img);
                                litlemessagebean SS=new litlemessagebean();
                                SS.setType("1");//聊天信息
                                String username=userInfoBean.getData().getUserTrueName();
                                SS.setUserName(username);
                                SS.setMessage(six);
                                String message = GsonUtil.BeanToJson(SS);
                                dataBean.setMessage(message);
                                liveMessage.setData(dataBean);
                                String message2 = GsonUtil.BeanToJson(liveMessage);
                                String mm = new String(message2.getBytes(), StandardCharsets.UTF_8);
                                Log.i("live", mm);
                                jfSockSDK.send(mm.getBytes());
                            }
                        }
                    }
                });
                commentDialog.show();
                break;
            case R.id.living_close: //关闭
                LiveRoomActivity.this.finish();
                break;
        }
    }
    //连接
    @Override
    public boolean OnConnect(long dwConnID) {
        return true;
    }

    @Override
    public boolean OnHandShake(long dwConnID) {
        Log.i("live", "OnHandShake");
        Message msg = new Message();
        msg.what = 0;
        msg.obj = "  握手：" + dwConnID;//可以是基本类型，可以是对象，可以是List、map等；
        LiveMessage.AuthBean authBean = new LiveMessage.AuthBean();
        authBean.setPassword("qwer1234.0");
        LiveMessage liveMessage = new LiveMessage();
        liveMessage.setAuth(authBean);
        //发送主播头像
        LiveMessage.DataBean dataBean = new LiveMessage.DataBean();
        dataBean.setRoomId(Integer.parseInt(roomId));
        dataBean.setMessageType(1);
        dataBean.setUserId(CreateID);
        String pic=AppConfig.BASE_PICTURE_URL+userInfoBean.getData().getHeadImageUrl();
        dataBean.setImg(pic);
//        litlemessagebean SS=new litlemessagebean();
//        SS.setType("2");//1聊天信息 2图片  0测试内容
//        userInfoBean.getData().getHeadImageUrl();
//        String username=userInfoBean.getData().getUserTrueName();
//        SS.setUserName(username);
//        SS.setMessage(AppConfig.BASE_PICTURE_URL);
//        String message = GsonUtil.BeanToJson(SS);
        liveMessage.setData(dataBean);
        String message2 = GsonUtil.BeanToJson(liveMessage);
        String mm = new String(message2.getBytes(), StandardCharsets.UTF_8);
        Log.i("live", mm);
        jfSockSDK.send(mm.getBytes());
        mLoginSemphore.release();
        return true;
    }
    //连接前事件
    @Override
    public boolean OnPrepareConnect(long dwConnID) {
        return true;
    }

    @Override
    public boolean OnReceive(long dwConnID, byte[] buf, int len) {

        //{"Message":"","UserCount":2,"MessageType":0,"RoomId":1010}
        String receiveJson = new String(buf, StandardCharsets.UTF_8);
        LiveReciveMessage liveReciveMessage = GsonUtil.GsonToBean(receiveJson, LiveReciveMessage.class);
        Log.i("test", "收到的消息" + liveReciveMessage.getMessage());
        Message message = new Message();
        message.obj = liveReciveMessage;
        message.what = 0;
        myhandler.sendMessage(message);
        return true;
    }

    @Override
    public boolean OnSend(long dwConnID, byte[] buf, int len) {
        return false;
    }

    @Override
    public boolean OnClose(long dwConnID, int enOperation, int iErrorCode) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LiveMessage.AuthBean authBean = new LiveMessage.AuthBean();
        authBean.setPassword("qwer1234.0");
        LiveMessage liveMessage = new LiveMessage();
        liveMessage.setAuth(authBean);
        //发送主播头像
        LiveMessage.DataBean dataBean = new LiveMessage.DataBean();
        dataBean.setRoomId(Integer.parseInt(roomId));
        dataBean.setMessageType(2);
        dataBean.setUserId(CreateID);
        liveMessage.setData(dataBean);
        String message2 = GsonUtil.BeanToJson(liveMessage);
        String mm = new String(message2.getBytes(), StandardCharsets.UTF_8);
        Log.i("live", mm);
        jfSockSDK.send(mm.getBytes());
        mLoginSemphore.release();



        jfSockSDK.stop();
    }

    @Override
    public void getinfo(LivingMasterBean livingMasterBean) {
        if(livingMasterBean.getData()!=null){
            Object o=livingMasterBean.getData().getHeadImageUrl();
            if(o!=null){
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
                Glide.with(this).load(AppConfig.BASE_PICTURE_URL+o.toString()).apply(options).into(userPic);
            }
        }
    }



    //页面恢复 播放器的处理
    @Override
    protected void onResume() {
        super.onResume();
        if (Player != null) {
            Player.runInForeground();
            Player.start();
        }
    }
    //页面暂停 播放器的处理
    @Override
    protected void onPause() {
        super.onPause();

        if (Player != null) {
            Player.runInBackground(true);
            Player.pause();
        }
    }
    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            if (what == KSYMediaPlayer.MEDIA_ERROR_SERVER_DIED) {
                //媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                Log.i("live", "媒体服务器挂掉了");
            } else if (what == KSYMediaPlayer.MEDIA_ERROR_UNKNOWN) {
                if (extra == KSYMediaPlayer.MEDIA_ERROR_IO) {
                    //文件不存在或错误，或网络不可访问错误

                    Log.i("live", "文件不存在或错误，或网络不可访问错误");
                } else if (extra == KSYMediaPlayer.MEDIA_ERROR_TIMED_OUT) {
                    //超时
                    Log.i("live", "超时");
                }
            }

            switch (what) {
                default:
                    Log.i("play", "OnErrorListener, Error:" + what + ",extra:" + extra);
            }

//            videoPlayEnd();
            return false;
        }
    };

    public void getPremission2(){
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE
                ),
                //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        Log.i("test","权限获取成功");
                    }
                    @Override
                    public void onPermissionDenied(final Permission[] refusedPermissions) {
                        Toast.makeText(LiveRoomActivity.this, refusedPermissions[0].toString() +
                                " 权限获取失败", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(LiveRoomActivity.this).setTitle("提示").setMessage("如果你拒绝了权限,应用中的一些功能将不糊能正常使用")
                                .setPositiveButton("授予权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //用户点击以后
                                        boolean ff=PanduanIsProhibitedPermissionDenied(refusedPermissions);
                                        if(!ff){
                                            SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
                                                @Override
                                                public void onBackFromAppDetail(Intent data) {
                                                    Log.i("test","这里是在设置也手动获取到权限以后返回，回调");
                                                }
                                            });
                                        }else{
                                            getPremission2();
                                        }
                                    }
                                }).create().show();
                    }
                });
    }

    private Boolean PanduanIsProhibitedPermissionDenied(Permission[] refusedPermissions) {
        boolean flag=true;
        for(int i=0;i<refusedPermissions.length;i++){
            if(!refusedPermissions[i].shouldRationale()){
                flag=false;
                return flag;
            }
        }
        return flag;
    }

    //为MediaPlayer调用prepare()方法时触法该监听器。
    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if (Player != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                //mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
//                ksyTextureview.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_NOSCALE_TO_FIT);
                // 开始播放视频
                Player.start();

            }
        }
    };
}
