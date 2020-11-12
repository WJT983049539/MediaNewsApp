package com.rcdz.medianewsapp.view.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;

import androidx.core.content.FileProvider;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.PhotoUtils;
import com.rcdz.medianewsapp.view.activity.BaseActivity;
import com.rcdz.medianewsapp.view.activity.BaseActivity2;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/6 10:07
 */
public class BannnerDetailActivity extends BaseActivity2 {

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int PHOTO_REQUEST = 100;
    private final static int VIDEO_REQUEST = 120;
    private boolean videoFlag = false;
    @BindView(R.id.newstime_web)
    WebView webView;
    private String url;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.znl;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        url=getIntent().getStringExtra("url");
        initWebView();
    }

    @Override
    public void inintData() {

    }

    private void initWebView() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDefaultTextEncodingName("UTF-8");
        webSetting.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        webSetting.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        webSetting.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        webSetting.setAllowUniversalAccessFromFileURLs(false);
        //开启JavaScript支持
        // 支持缩放
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);//默认缩放模式
        webView.getSettings().setSupportZoom(true);
        webSetting.setSupportMultipleWindows(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        long time = System.currentTimeMillis();

        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
        //辅助WebView设置处理关于页面跳转，页面请求等操作
        webView.setWebViewClient(new MyWebViewClient());
        //辅助WebView处理图片上传操作
        webView.setWebChromeClient(new MyChromeWebClient());
        webView.setInitialScale(25);
        webView.loadUrl(url);
    }
    /**
     * back键处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }
            else{
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private Uri imageUri;

    //自定义 WebViewClient 辅助WebView设置处理关于页面跳转，页面请求等操作【处理tel协议和视频通讯请求url的拦截转发】
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                videoFlag = url.contains("vedio");
            }
            if (url.trim().startsWith("tel")) {  //特殊情况tel，调用系统的拨号软件拨号【<a href="tel:1111111111">1111111111</a>】
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else {
                String port = url.substring(url.lastIndexOf(":") + 1, url.lastIndexOf("/"));//尝试要拦截的视频通讯url格式(808端口)：【http://xxxx:808/?roomName】
                if (port.equals("808")) {//特殊情况【若打开的链接是视频通讯地址格式则调用系统浏览器打开】
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {//其它非特殊情况全部放行
                    view.loadUrl(url);
                }
            }
            return true;
        }
    }

    //自定义 WebChromeClient 辅助WebView处理图片上传操作【<input type=file> 文件上传标签】
    public class MyChromeWebClient extends WebChromeClient {
        // For Android 3.0-
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            if (videoFlag) {
//                recordVideo();
            } else {
                takePhoto();
            }

        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            if (videoFlag) {
//                recordVideo();
            } else {
                takePhoto();
            }
        }

        //For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            if (videoFlag) {
//                recordVideo();
            } else {
                takePhoto();
            }
        }

        // For Android 5.0+
        public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = filePathCallback;
            if (videoFlag) {
//                recordVideo();
            } else {
                takePhoto();
            }
            return true;
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != PHOTO_REQUEST || mUploadCallbackAboveL == null) {
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.setWebViewClient(null);
            webView.setWebChromeClient(null);
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            webView.destroy();
            webView = null;
        }
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + SystemClock.currentThreadTimeMillis() + ".jpg");
        if(!fileUri.exists()){
            try {
                fileUri.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageUri = Uri.fromFile(fileUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(BannnerDetailActivity.this, getPackageName() + ".fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
        }
        PhotoUtils.takePicture(BannnerDetailActivity.this, imageUri, PHOTO_REQUEST);
 }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST) {
//            String dataString = data.getDataString();
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        } else if (requestCode == VIDEO_REQUEST) {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;

            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                if (resultCode == RESULT_OK) {
                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{result});
                    mUploadCallbackAboveL = null;
                } else {
                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{});
                    mUploadCallbackAboveL = null;
                }

            } else if (mUploadMessage != null) {
                if (resultCode == RESULT_OK) {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                } else {
                    mUploadMessage.onReceiveValue(Uri.EMPTY);
                    mUploadMessage = null;
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
