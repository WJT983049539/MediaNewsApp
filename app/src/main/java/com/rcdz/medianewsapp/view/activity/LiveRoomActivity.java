package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
import com.rcdz.medianewsapp.model.bean.LiveWoShouReciviceBean;
import com.rcdz.medianewsapp.model.bean.LiveWoshouBean;
import com.rcdz.medianewsapp.model.bean.LivingMasterBean;
import com.rcdz.medianewsapp.model.bean.NormorReciviceBean;
import com.rcdz.medianewsapp.model.bean.ReceiveMessage;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.model.bean.litlemessagebean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetLivingMInfo;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GsonUtil;
import com.rcdz.medianewsapp.tools.SensitiveWordUtils;
import com.rcdz.medianewsapp.tools.SensitivewordFilter;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.customview.CommentDialog;
import com.rckj.rcsocket.JfSocketEvent;
import com.rckj.rcsocket.JfSocketSdk;
import com.shehuan.niv.NiceImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作用:直播间详情
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 17:08
 *
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
    @BindView(R.id.lin_over)
    RelativeLayout lin_over; //覆盖副局
    @BindView(R.id.rela_content)
    RelativeLayout rela_content; //内容布局
    @BindView(R.id.hh)
    ImageView hh; //主播头像
    @BindView(R.id.aaa)
    TextView aaa; //主播名称
    @BindView(R.id.backk)
    TextView backk; //
    JfSocketSdk jfSockSDK; //直播间服务器
    private RequestOptions options = new RequestOptions().error(R.mipmap.peop).centerCrop();
    List<BarrageInfobean> list = new LinkedList<BarrageInfobean>();
    private LinearLayoutManager linearLayoutManager;
    private LiveBarrageAdapter liveBarrageAdapter;
    private String videoUrl;
    private String name;
    private int type;
    private int liveState;//直播状态
    private String roomId;
    private int CreateID;
    String picture;
    private UserInfoBean userInfoBean;
    private Boolean loginstatu=false;
    List<String> worldlist=new ArrayList<>();
    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
            Log.i("live",msg.obj.toString());
            if(msg.what==0){ //普通消息
                ReceiveMessage SS= (ReceiveMessage) msg.obj;
                    String UserCount= String.valueOf(SS.getUserCount());
//                     tv_num2.setText(UserCount);//人数
                         String meg=SS.getMessage();
                        JSONObject jj = new JSONObject(meg);
                        String UserName = jj.getString("UserName");
                        String SendDate = jj.getString("SendDate");
                        String Message = jj.getString("Message");
                        int  Type = jj.getInt("Type");
                        String UserId = jj.getString("UserId");
                        Log.i("test","Type=="+Type+"UserId=="+UserId+"CreateID=="+CreateID);
                        if(Type==2&& UserId.equals(String.valueOf(CreateID))){  //直播间关闭了（全部都停止） todo
                            lin_over.setVisibility(View.VISIBLE);
                            rela_content.setVisibility(View.GONE);
                            mLoginSemphore.release();
                            jfSockSDK.stop();
                            myhandler.removeCallbacksAndMessages(null);
                        }else{

                        BarrageInfobean barrageInfobean = new BarrageInfobean();
                        barrageInfobean.setInfo(UserName + ":" + Message);
                        barrageInfobean.setHeadimage("");//没有头像了 //弹幕消息
                        list.add(barrageInfobean);
                        Log.i("live", "共有" + list.size() + "条弹幕");
                        if (liveBarrageAdapter != null) {
                            liveBarrageAdapter.notifyDataSetChanged();
                            this.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rcBarrage.scrollToPosition(liveBarrageAdapter.getItemCount() - 1);
                                }
                            }, 50);
                        }
                        }
                }else if(msg.what==1){ //头像消息

                LiveWoShouReciviceBean liveWoShouReciviceBean= (LiveWoShouReciviceBean) msg.obj;
                int count=liveWoShouReciviceBean.getUserCount();
                String UserCount= String.valueOf(count);
                tv_num2.setText(UserCount);//人数
                JSONArray jsonArray=new JSONArray(liveWoShouReciviceBean.getMessage());

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
            }
            } catch (JSONException e) {
                e.printStackTrace();
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
        loginstatu = (boolean) SharedPreferenceTools.getValueofSP(LiveRoomActivity.this,"loginStru",false);

        ButterKnife.bind(this);
            ACache aCache=ACache.get(this);
            userInfoBean= (UserInfoBean) aCache.getAsObject("userinfo");
        videoUrl=getIntent().getStringExtra("videoUrl");
        name=getIntent().getStringExtra("name");
        roomId=getIntent().getStringExtra("roomId");
        type= getIntent().getIntExtra("type",0);
        liveState= getIntent().getIntExtra("liveState",-1);
        CreateID=getIntent().getIntExtra("CreateID",0);
        livingName.setText(name);
        getPremission2();
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetLivingMasterInfo(String.valueOf(CreateID),this);
        if(liveState!=2){ //除了直以外的其他状态
            lin_over.setVisibility(View.VISIBLE);
            rela_content.setVisibility(View.GONE);

        }else{
            lin_over.setVisibility(View.GONE);
            rela_content.setVisibility(View.VISIBLE);
            initVideoView();

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


    }

    private void initVideoView() {
        ACache aCache=ACache.get(LiveRoomActivity.this);
        JSONArray jsonArray1=aCache.getAsJSONArray("word");
        for(int i=0;i<jsonArray1.length();i++){
            try {
                String ww=jsonArray1.getString(i);
                worldlist.add(ww);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.i("test",list.size()+"");

        Player.setOnErrorListener(mOnErrorListener);// 发生错误时回调
        Player.setOnPreparedListener(mOnPreparedListener);//当装载流媒体完毕的时候回调。

        //设置播放地址并准备
        try {
            Log.i("live","直播地址为"+videoUrl);
            //1.setDataSource(path)  path是一个网络地址,去掉回车 空格
            String playurl = videoUrl.replaceAll("(\\\r\\\n|\\\r|\\\n|\\\n\\\r)", "").trim();
            Player.setDataSource(playurl);
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
    @OnClick({R.id.edit_livecomment, R.id.living_close,R.id.backk})
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

                            if(worldlist.contains(six)){
                                List<String> list = new ArrayList<String>();
                                list.add("");
                                //从文件中读取词库中的内容，将内容添加到list集合中
                                //初始化词库,读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
                                SensitiveWordUtils.init(worldlist);
                                //输入的字符串
                                //List<String> rep = SensitivewordFilter.getSensitiveWord(datas,str,1);
                                six = SensitivewordFilter.replaceSensitiveWord(worldlist,six,1,"*");
                            }
                            //发送信息
                            if (jfSockSDK != null) {
                                LiveMessage.AuthBean authBean = new LiveMessage.AuthBean();
                                authBean.setPassword("qwer1234.0");
                                LiveMessage liveMessage = new LiveMessage();
                                liveMessage.setAuth(authBean);
                                LiveMessage.SendMessageBean sendMessageBean=new   LiveMessage.SendMessageBean();
                                if(loginstatu){
                                    sendMessageBean.setRoomId(Integer.parseInt(roomId));
                                    sendMessageBean.setUserId(CreateID);
                                    sendMessageBean.setMessageType(0); //todo 暂时没有真实人数据
                                    String img= AppConfig.BASE_PICTURE_URL+userInfoBean.getData().getHeadImageUrl();
                                    sendMessageBean.setImg(img);
                                    litlemessagebean SS=new litlemessagebean();
                                    String username=userInfoBean.getData().getUserTrueName();
                                    SS.setUserName(username);
                                    SS.setMessage(six);
                                    String time=simpleDateFormat.format(new Date());
                                    SS.setSendDate(time);
                                    String message = GsonUtil.BeanToJson(SS);
                                    sendMessageBean.setMessage(message);

                                }else{
                                    sendMessageBean.setRoomId(Integer.parseInt(roomId));
                                    sendMessageBean.setUserId(-1);
                                    sendMessageBean.setMessageType(0); //todo 暂时没有真实人数据
                                    String img= AppConfig.BASE_PICTURE_URL+"test.jpg";
                                    sendMessageBean.setImg(img);
                                    litlemessagebean SS=new litlemessagebean();
                                    String username="游客";
                                    SS.setUserName(username);
                                    SS.setMessage(six);
                                    String time=simpleDateFormat.format(new Date());
                                    SS.setSendDate(time);
                                    String message = GsonUtil.BeanToJson(SS);
                                    sendMessageBean.setMessage(message);
                                }

                                liveMessage.setData(sendMessageBean);

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
            case  R.id.backk:
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
        LiveWoshouBean liveWoshouBean=new LiveWoshouBean();
        LiveWoshouBean.AuthBean authBean=new    LiveWoshouBean.AuthBean();
        authBean.setPassword("qwer1234.0");
        liveWoshouBean.setAuth(authBean);
        LiveWoshouBean.DataBean dataBean=new   LiveWoshouBean.DataBean();
        if(loginstatu){
            String pic=AppConfig.BASE_PICTURE_URL+userInfoBean.getData().getHeadImageUrl();
            dataBean.setImg(pic);
            dataBean.setMessage(null);
            dataBean.setMessageType(1);
            dataBean.setRoomId(Integer.parseInt(roomId));
            dataBean.setUserId(CreateID);
        }else{
            String pic=AppConfig.BASE_PICTURE_URL+"test.jpg";
            dataBean.setImg(pic);
            dataBean.setMessage(null);
            dataBean.setMessageType(1);
            dataBean.setRoomId(Integer.parseInt(roomId));
            dataBean.setUserId(-1);
        }





        liveWoshouBean.setData(dataBean);
        String message2 = GsonUtil.BeanToJson(liveWoshouBean);
        String mm = new String(message2.getBytes(), StandardCharsets.UTF_8);
        Log.i("live", "发送消息"+mm);
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
        //{"Message":"{\"UserName\":\"张三\",\"SendDate\":\"2020/11/1 12:02:58\",\"Message\":\"你好，你是谁？234234543123441234\"}","UserCount":5,"MessageType":0,"RoomId":1015,"Img":"http://www.a.com/users/123123.png"}
        //{"Message":"","UserCount":2,"MessageType":0,"RoomId":1010}
        String receiveJson = new String(buf, StandardCharsets.UTF_8);
        Log.i("test", "收到的消息" + receiveJson);
        try {
            Message message = new Message();
            JSONObject jsonObject=new JSONObject(receiveJson);
            int MessageType=jsonObject.getInt("MessageType");
            if(MessageType==0){ //普通消息
                ReceiveMessage receiveMessage = GsonUtil.GsonToBean(receiveJson, ReceiveMessage.class);
                message.obj = receiveMessage;
                message.what = 0;
                myhandler.sendMessage(message);
            }else{
                LiveWoShouReciviceBean liveWoshouBean = GsonUtil.GsonToBean(receiveJson, LiveWoShouReciviceBean.class);
                message.obj = liveWoshouBean;
                message.what = 1;
                myhandler.sendMessage(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        //发送主播头像
        LiveWoshouBean liveWoshouBean=new LiveWoshouBean();
        LiveWoshouBean.AuthBean authBean=new    LiveWoshouBean.AuthBean();
        authBean.setPassword("qwer1234.0");
        String mm="";
        if(loginstatu){
            LiveWoshouBean.DataBean dataBean=new   LiveWoshouBean.DataBean();
            String pic=AppConfig.BASE_PICTURE_URL+userInfoBean.getData().getHeadImageUrl();
            dataBean.setImg(pic);
            dataBean.setMessage(null);
            dataBean.setMessageType(2);
            dataBean.setRoomId(Integer.parseInt(roomId));
            dataBean.setUserId(CreateID);
            liveWoshouBean.setAuth(authBean);
            liveWoshouBean.setData(dataBean);
            String message2 = GsonUtil.BeanToJson(liveWoshouBean);
            mm= new String(message2.getBytes(), StandardCharsets.UTF_8);
        }else{
            LiveWoshouBean.DataBean dataBean=new   LiveWoshouBean.DataBean();
            String pic=AppConfig.BASE_PICTURE_URL+"test.jpg";
            dataBean.setImg(pic);
            dataBean.setMessage(null);
            dataBean.setMessageType(2);
            dataBean.setRoomId(Integer.parseInt(roomId));
            dataBean.setUserId(CreateID);
            liveWoshouBean.setAuth(authBean);
            liveWoshouBean.setData(dataBean);
            String message2 = GsonUtil.BeanToJson(liveWoshouBean);
            mm= new String(message2.getBytes(), StandardCharsets.UTF_8);
        }

        Log.i("live", mm);
        if(jfSockSDK!=null){
            jfSockSDK.send(mm.getBytes());
            mLoginSemphore.release();
            jfSockSDK.stop();

        }
        myhandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void getinfo(String userName, String headImg) {
                aaa.setText(userName);
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.peop).centerCrop();
                Glide.with(this).load(AppConfig.BASE_PICTURE_URL+headImg.toString()).apply(options).into(userPic);
                Glide.with(this).load(AppConfig.BASE_PICTURE_URL+headImg.toString()).apply(options).into(hh);
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
                if(type==0){ //公共
                    Player.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
                }else if(type==1){ //私有
                    Player.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_NOSCALE_TO_FIT);
                }
                Player.start();
            }
        }
    };


}
