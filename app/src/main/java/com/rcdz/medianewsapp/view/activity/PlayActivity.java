package com.rcdz.medianewsapp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYTextureView;
import com.rcdz.medianewsapp.R;

import java.io.IOException;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class PlayActivity extends Activity {
    private KSYTextureView mVideoView;
    // 播放器的对象
    private KSYMediaPlayer ksyMediaPlayer;
    // 播放SDK提供的监听器
    // 播放器在准备完成，可以开播时会发出onPrepared回调
    // 播放完成时会发出onCompletion回调

    private SurfaceView mVideoSurfaceView;
    private SurfaceHolder mSurfaceHolder;


    //为MediaPlayer调用prepare()方法时触法该监听器。
    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if(mVideoView != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                //mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mVideoView.start();



            }
        }
    };

    //当从网络加载流媒体时流媒体缓冲的监听器
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

    };

    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
        }
    };

    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {

            //播放完毕
            }

        };
    };

    public IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            return false;
        }
    };

    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            if(what == KSYMediaPlayer.MEDIA_ERROR_SERVER_DIED){
                //媒体服务器挂掉了。此时，程序必须释放MediaPlayer 对象，并重新new 一个新的。
                Log.i("00", "媒体服务器挂掉了");
            }else if(what == KSYMediaPlayer.MEDIA_ERROR_UNKNOWN){
                if(extra == KSYMediaPlayer.MEDIA_ERROR_IO){
                    //文件不存在或错误，或网络不可访问错误
                    Log.i("00", "文件不存在或错误，或网络不可访问错误");
                }else if(extra == KSYMediaPlayer.MEDIA_ERROR_TIMED_OUT){
                    //超时
                    Log.i("00", "超时");
                }
            }
            switch (what) {
                default:
                    Log.i("im","OnErrorListener, Error:" + what + ",extra:" + extra);
            }
            //videoPlayEnd();
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video);
        //不需要再显示的创建mVideoView
        mVideoView= findViewById(R.id.ksy_textureview);
        //设置监听器
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnPreparedListener(mOnPreparedListener);
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        //设置播放参数
        mVideoView.setBufferTimeMax(2.0f);
        mVideoView.setTimeout(5, 30);
        //......
        //(其它参数设置)
        //......
        //设置播放地址并准备
        try {
            mVideoView.setDataSource("http://192.168.1.171:9992/Upload/Tables/VideoTranscods/19/测试啊/测试啊.mp4");
            mVideoView.prepareAsync();
            mVideoView.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}