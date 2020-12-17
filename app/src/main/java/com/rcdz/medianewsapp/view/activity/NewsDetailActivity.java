package com.rcdz.medianewsapp.view.activity;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetPermiss;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:新闻详情页
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/4 13:50
 */
public class NewsDetailActivity extends BaseActivity implements GetPermiss {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_menu)
    ImageView toolbar_menu;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.news_web)
    WebView webView;
    private String plateId;
    private String platName;
    private String id;
    private int ActivityType;
    private int Type;
    private String imageUrl;
    private String title;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_newdetails;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
//        toolbarTitle.setText("新闻详情");
        plateId = getIntent().getStringExtra("plateId");
        platName = getIntent().getStringExtra("platName");
        imageUrl = getIntent().getStringExtra("imageUrl");
        title = getIntent().getStringExtra("title");
        ActivityType = getIntent().getIntExtra("ActivityType",-1);
        id = String.valueOf(getIntent().getIntExtra("id",-1));
        Type=getIntent().getIntExtra("Type",-1);
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetPermissBySationId(this,plateId);
        initWebView();
    }

    private void initWebView() {
        toolbar_menu.setVisibility(View.GONE);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //localStorage  允许存储
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//存储的最大容量
        String appCachePath = this.getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);  //设置参数
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultZoom(com.tencent.smtt.sdk.WebSettings.ZoomDensity.CLOSE);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    public void inintData() {

            if(Type==1){ //文章

                if (ActivityType==0) { //投票
                    String mylink = AppConfig.NEWSDETAIL+"static/html/vote.html";
                    webView.loadUrl(mylink);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            // 加载页面
                        }

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageFinished(WebView webView, String s) {
                            super.onPageFinished(webView, s);
                            //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                            webView.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                    webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                                }
                            });

                        }
                    });

                }else if(ActivityType==1){ //答题
                    String mylink = AppConfig.NEWSDETAIL+"static/html/answer.html";
                    webView.loadUrl(mylink);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            // 加载页面
                        }

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageFinished(WebView webView, String s) {
                            super.onPageFinished(webView, s);
                            //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                            webView.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                    webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                                }
                            });

                        }
                    });

                }else if(ActivityType==2){ //活动
                    String mylink = AppConfig.NEWSDETAIL+"static/html/activity.html";
                    webView.loadUrl(mylink);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            // 加载页面
                        }

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageFinished(WebView webView, String s) {
                            super.onPageFinished(webView, s);
                            //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                            webView.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                    webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                                }
                            });
                        }
                    });
                }else{ //没有活动
                    String mylink = AppConfig.NEWSDETAIL+"static/html/activity.html";
                    webView.loadUrl(mylink);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            // 加载页面
                        }
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageFinished(WebView webView, String s) {
                            super.onPageFinished(webView, s);
                            //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                            webView.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                    webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                                }
                            });

                        }
                    });
                }


            }else if(Type==2){ //视频

                String mylink =AppConfig.NEWSDETAIL+"static/html/video.html";
                webView.loadUrl(mylink);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        // 加载页面
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView webView, String s) {
                        super.onPageFinished(webView, s);
                        //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                            }
                        });

                    }
                });

            }else if(Type==3){ //图集
                String mylink = AppConfig.NEWSDETAIL+"static/html/imgpreview.html";
                webView.loadUrl(mylink);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        // 加载页面
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView webView, String s) {
                        super.onPageFinished(webView, s);
                        //安卓调用js方法。注意需要在 onPageFinished 回调里调用
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("test","javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')");
                                webView.evaluateJavascript("javascript:getContent('"+ AppConfig.BASE_URL +"','"+ Constant.token+"','"+id+"','"+plateId+"','"+ platName+"')",null);
                            }
                        });

                    }
                });
            }else {
                GlobalToast.show("不知道的新闻类型", Toast.LENGTH_LONG);
                this.finish();
            }




    }

    @OnClick({R.id.img_back,R.id.toolbar_menu})
    public void onViewClicked(View view) {
        if(view.getId()==R.id.img_back){
            NewsDetailActivity.this.finish();
        }else if(view.getId()==R.id.toolbar_menu){ //分享

            shareInfo("http://192.168.1.59:8080/static/html/activity.html?id=2182&sId=2&sName=%E6%8E%A8%E8%8D%90",title,this,imageUrl);

        }
    }


    private void shareInfo(String url, String title, Activity context,String imageUrl){
        UMImage thumb =  new UMImage(context, R.mipmap.icon);
        UMImage th = new UMImage(context,imageUrl); //切记切记 这里分享的链接必须是http开头
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(th);  //缩略图
        web.setDescription("测试");//描述

        new ShareAction(NewsDetailActivity.this).withMedia(web).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_FAVORITE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {


                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        GlobalToast.show4("分享成功",Toast.LENGTH_LONG);

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                        Toast.makeText(LocalShopActivity.this,"分享失败",Toast.LENGTH_LONG).show();
                        GlobalToast.show4(throwable.getMessage(),Toast.LENGTH_LONG);
                    }


                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        GlobalToast.show4("分享取消",Toast.LENGTH_LONG);
                    }
                }).open();

    }

    /**
     * 判断是否有权限 //0  代表物分享权限，1 有分享权限
     */
    @Override
    public void Permiss(int permiss ) {

        if(permiss==1){ //有分享权限
            toolbar_menu.setVisibility(View.VISIBLE);
        }else{
            toolbar_menu.setVisibility(View.GONE);
        }
    }
}
