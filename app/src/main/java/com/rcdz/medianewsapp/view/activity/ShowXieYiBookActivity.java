package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.rcdz.medianewsapp.R;


/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class ShowXieYiBookActivity extends BaseAppCompatActivity implements View.OnClickListener {
    WebView webView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_showxieyiactivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar_title=findViewById(R.id.toolbar_title);
        toolbar_title.setText("隐私与协议");
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowXieYiBookActivity.this.finish();
            }
        });
        initView();
    }

    private void initView() {
        webView=findViewById(R.id.xieyiweb);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //localStorage  允许存储
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024*1024*8);//存储的最大容量
        String appCachePath = this.getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);  //设置参数
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);//默认缩放模式
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        String mylink = "file:///android_asset/wen.html";
        webView.loadUrl(mylink);
    }

    @Override
    public void onClick(View v) {

    }
}
