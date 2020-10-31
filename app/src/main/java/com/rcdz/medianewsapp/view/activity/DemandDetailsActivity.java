package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYTextureView;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.CommentListAdapter;
import com.rcdz.medianewsapp.model.adapter.JishuAdapter;
import com.rcdz.medianewsapp.model.bean.CommentInfoBean;
import com.rcdz.medianewsapp.model.bean.DemandEpisodeBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetComment;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandJiNumDetails;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用: 点播详情页面
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 17:49
 */
public class DemandDetailsActivity extends BaseActivity implements GetDemandJiNumDetails, GetComment {


    String demandId;
    String title = "";
    String litletitle = "";
    String content = "";
    String VideoUrl = "";
    @BindView(R.id.ksy_textureview)
    KSYTextureView mVideoView;
    @BindView(R.id.demand_details_title)
    TextView demandDetailsTitle;
    @BindView(R.id.demand_details_litlt_title)
    TextView demandDetailsLitltTitle;
    @BindView(R.id.demand_details_content)
    TextView demandDetailsContent;
    @BindView(R.id.demand_details_num)
    TextView demand_details_num;
    @BindView(R.id.rc_xuanji)
    RecyclerView rcXuanji;
    @BindView(R.id.contentView)
    LinearLayout contentView;
    JishuAdapter jishuAdapter;
    List<DemandEpisodeBean.DemandEpisodeInfo> list = new ArrayList<DemandEpisodeBean.DemandEpisodeInfo>();
    @BindView(R.id.backBtn)
    ImageButton backBtn;
    @BindView(R.id.topPanel)
    RelativeLayout topPanel;
    @BindView(R.id.openStartTime)
    TextView openStartTime;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.openSunTime)
    TextView openSunTime;
    @BindView(R.id.fullBtn)
    ImageButton fullBtn;
    @BindView(R.id.mPlayerPanel)
    LinearLayout mPlayerPanel;
    @BindView(R.id.outside)
    RelativeLayout outside;
    @BindView(R.id.pause)
    ImageView thum;
    @BindView(R.id.thum)
    ImageView pause; //暂停按钮
    @BindView(R.id.commentlist)
    RecyclerView commentlist; //暂停按钮
    boolean is_play = true;//是否播放/暂停
    private int mVideoWidth = 0;
    private int mVideoHeight = 0;
    public static final int UPDATE_SEEKBAR = 0;
    public static final int HIDDEN_SEEKBAR = 1;
    public static final int UPDATE_CURPROGRAM = 2;
    private boolean mTouching;//是否touch
    private float lastMoveX = -1;
    private float lastMoveY = -1;
    private float movedDeltaX;
    private float movedDeltaY;
    private boolean mPlayerPanelShow = false;//是否展示外部的组件（停止、播放、全屏等）

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_SEEKBAR:
                    setVideoProgress(0);//更新进度条上的时间
                    break;
                case HIDDEN_SEEKBAR://隐藏
//                    mPlayerPanelShow = false;
//                    mPlayerPanel.setVisibility(View.GONE);//进度bar
//                    topPanel.setVisibility(View.GONE);//top bar
//                    screenBtn.setVisibility(View.GONE);//截图按钮
//                    if (isFullScreen) {
//                        backBtn.setVisibility(View.GONE);//返回按钮
//                    }
                    break;
                case UPDATE_CURPROGRAM:
                    //定时更新直播节目选中状态
                    break;
            }
        }
    };
    ;

    String liveUrl = "";

    @Override
    public String setNowActivityName() {
        return "点播详情页面";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_demand_details;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        demandId = getIntent().getStringExtra("demandId");
        title = getIntent().getStringExtra("title");
        litletitle = getIntent().getStringExtra("litletitle");
        content = getIntent().getStringExtra("content");
        list.clear();
        initVideoView();//初始化播放器


    }

    private void initVideoView() {
        mPlayerPanel.setVisibility(View.GONE);
        fullBtn.setVisibility(View.GONE);
        //设置监听器
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);//网络流媒体的缓冲变化时回调
        mVideoView.setOnCompletionListener(mOnCompletionListener);//网络流媒体播放结束时回调
        mVideoView.setOnPreparedListener(mOnPreparedListener);//当装载流媒体完毕的时候回调。
        mVideoView.setOnInfoListener(mOnInfoListener);//信息监听
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        mVideoView.setOnErrorListener(mOnErrorListener);// 发生错误时回调
        mVideoView.setOnSeekCompleteListener(mOnSeekCompletedListener);
        mVideoView.setOnTouchListener(mTouchListener);
        mVideoView.setKeepScreenOn(true);
        seekBar.setOnSeekBarChangeListener(mSeekBarListener);

        //设置播放参数
        mVideoView.setBufferTimeMax(2.0f);
        mVideoView.setTimeout(5, 30);

        //设置播放地址并准备
//        try {
////            mVideoView.setDataSource(liveUrl);
////            mVideoView.prepareAsync();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void inintData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcXuanji.setLayoutManager(linearLayoutManager);
        jishuAdapter = new JishuAdapter(list);
        rcXuanji.setAdapter(jishuAdapter);
        jishuAdapter.setOnItemClick(new JishuAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                GlobalToast.show("第" + position + 1 + "集", Toast.LENGTH_LONG);
            }
        });
        //获取集数
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetDemandDetails(demandId, this);
        newNetWorkPersenter.GeCommentList(demandId,this);

    }

    //拿到剧情集数
    @Override
    public void getDemandJiNumDetails(DemandEpisodeBean demandEpisodeBean) {
        if (demandEpisodeBean.getData().size() == 0) {
            GlobalToast.show("集数为空！", Toast.LENGTH_LONG);
        } else {
            list.addAll(demandEpisodeBean.getData());
            jishuAdapter.notifyDataSetChanged();

            liveUrl = AppConfig.BASE_VIDEO_URL+demandEpisodeBean.getData().get(0).getVideoUrl();
            demandDetailsTitle.setText(title);
            demandDetailsLitltTitle.setText(litletitle);
            demandDetailsContent.setText(content);
            demand_details_num.setText("共" + demandEpisodeBean.getData().size() + "集");
            try {
                mVideoView.setDataSource(liveUrl);
                mVideoView.setBufferTimeMax(10);
                mVideoView.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //    当从网络加载流媒体时流媒体缓冲的监听器
    private IMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            if (mVideoView != null) {
                long duration = mVideoView.getDuration();
                long progress = duration * percent / 100;
//                seekBar.setSecondaryProgress((int) progress);
            }
        }
    };
    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangeListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
            if (mVideoWidth > 0 && mVideoHeight > 0) {
                if (width != mVideoWidth || height != mVideoHeight) {
                    mVideoWidth = mp.getVideoWidth();
                    mVideoHeight = mp.getVideoHeight();
                    if (mVideoView != null)
                        mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                }
            }
        }
    };


    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {

            Log.i("play", "视频播放完毕-----------------------------------------------");
            mVideoView.start();
        }
    };

    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            Log.i("play", "视频播放完毕-----------------------------------------------");
        }
    };

    public IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            switch (i) {
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    Log.i("play", "Buffering Start.");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    Log.i("play", "Buffering End.");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    Log.i("play", "Audio Rendering Start");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START://
                    Log.i("play", "Video Rendering Start");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_RELOADED:
                    Log.i("play", "Succeed to reload video.");
                    return false;
            }
            return false;
        }
    };


    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            if (what == KSYMediaPlayer.MEDIA_ERROR_SERVER_DIED) {
                //媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                Log.i("play", "媒体服务器挂掉了");
            } else if (what == KSYMediaPlayer.MEDIA_ERROR_UNKNOWN) {
                if (extra == KSYMediaPlayer.MEDIA_ERROR_IO) {
                    //文件不存在或错误，或网络不可访问错误

                    Log.i("play", "文件不存在或错误，或网络不可访问错误");
                } else if (extra == KSYMediaPlayer.MEDIA_ERROR_TIMED_OUT) {
                    //超时
                    Log.i("play", "超时");
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
    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if (mVideoView != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                // 开始播放视频
                mVideoView.start();
                setVideoProgress(0);
                Log.i("play", "onPrepared");
            }
        }
    };


    //设置播放进度条
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

    private int mVideoProgress = 0;
    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mVideoProgress = progress;
                mHandler.removeMessages(HIDDEN_SEEKBAR);
                Message msg = new Message();
                msg.what = HIDDEN_SEEKBAR;
                mHandler.sendMessageDelayed(msg, 3000);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mVideoView.seekTo(mVideoProgress, true);
            setVideoProgress(mVideoProgress);
        }
    };

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                //当屏幕检测到第一个触点按下之后就会触发到这个事件。
                case MotionEvent.ACTION_DOWN:
                    mTouching = false;
                    break;
                //当屏幕上已经有触点处于按下的状态的时候，再有新的触点被按下时触发。
                case MotionEvent.ACTION_POINTER_DOWN:
                    mTouching = true;
                    break;
                //当触点在屏幕上移动时触发，触点在屏幕上停留也是会触发的，
                case MotionEvent.ACTION_MOVE:
                    if (event.getPointerCount() == 1) {
                        float posX = event.getX();
                        float posY = event.getY();
                        if (lastMoveX == -1 && lastMoveX == -1) {
                            lastMoveX = posX;
                            lastMoveY = posY;
                        }
                        movedDeltaX = posX - lastMoveX;
                        movedDeltaY = posY - lastMoveY;
                        if (Math.abs(movedDeltaX) > 5 || Math.abs(movedDeltaY) > 5) {
                            if (mVideoView != null) {
                                mVideoView.moveVideo(movedDeltaX, movedDeltaY);
                            }
                            mTouching = true;
                        }
                        lastMoveX = posX;
                        lastMoveY = posY;
                    } else if (event.getPointerCount() == 2) {
                    }
                    break;
                //当屏幕上有多个点被按住，松开其中一个点时触发
                case MotionEvent.ACTION_POINTER_UP:
                    if (event.getPointerCount() == 2) {
                        lastMoveX = -1;
                        lastMoveY = -1;
                    }
                    break;
                //当触点松开时被触发
                case MotionEvent.ACTION_UP:
                    lastMoveX = -1;
                    lastMoveY = -1;

                    if (!mTouching) {
                        dealTouchEvent(v, event);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    //touch事件来隐藏视频组件块
    private void dealTouchEvent(View view, MotionEvent event) {
        mPlayerPanelShow = !mPlayerPanelShow;
        //如果是显示状态 则三秒后再隐藏
        if (mPlayerPanelShow) {
            outside.setVisibility(View.VISIBLE);
            pause.setVisibility(View.VISIBLE);
//            if (isFullScreen) {
//                screenBtn.setVisibility(View.VISIBLE);
//                backBtn.setVisibility(View.VISIBLE);
//            }
            Message msg = new Message();
            msg.what = HIDDEN_SEEKBAR;
            mHandler.sendMessageDelayed(msg, 5000);
        } else {
            outside.setVisibility(View.GONE);
            pause.setVisibility(View.GONE);
//            if (isFullScreen) {
//                backBtn.setVisibility(View.GONE);
//            }
            mHandler.removeMessages(HIDDEN_SEEKBAR);
        }
    }


    @OnClick({R.id.pause, R.id.backBtn, R.id.fullBtn, R.id.rc_xuanji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pause:
                if (is_play) {
                    pause.setBackgroundResource(R.mipmap.play);
                    if (mVideoView != null) {
                        mVideoView.pause();
                    }
                } else {
                    pause.setBackgroundResource(R.mipmap.stop);
                    if (mVideoView != null) {
                        mVideoView.start();
                    }
                }
                is_play = !is_play;
                break;
            case R.id.backBtn:
                finish();
                break;
            case R.id.fullBtn:
                break;
            case R.id.rc_xuanji:
                break;
        }
    }

    //页面暂停 播放器的处理
    @Override
    protected void onPause() {
        super.onPause();

        if (mVideoView != null) {
            mVideoView.runInBackground(true);
            mVideoView.pause();
        }
    }

    //页面恢复 播放器的处理
    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.runInForeground();
            mVideoView.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mHandler = null;
        if(mVideoView != null){
            mVideoView.stop();
            mVideoView.release();
        }
    }
    //得到评论列表
    @Override
    public void getcomment(CommentInfoBean commentInfoBean) {
        commentlist.setLayoutManager(new LinearLayoutManager(this));
        CommentListAdapter commentAdapter=new CommentListAdapter(this,commentInfoBean.getRows(),R.layout.item_commentlist);
        commentlist.setAdapter(commentAdapter);
    }
}
