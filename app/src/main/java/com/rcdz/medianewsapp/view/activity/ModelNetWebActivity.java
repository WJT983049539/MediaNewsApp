package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcdz.medianewsapp.R;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/28 11:05
 */
public class ModelNetWebActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbartitle;
    @BindView(R.id.zhihuiquanweb)
    WebView webView;
    String type;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_newweb;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelNetWebActivity.this.finish();
            }
        });
    }

    @Override
    public void inintData() {
        type=getIntent().getStringExtra("type");
        inintView(webView);
        switch (type){
            case "1122": //听广播
                setToolBarTitle("听广播");
                webView.loadUrl("https://m.qingting.fm/categories/5/");
                break;
            case "1156": //看电视
                setToolBarTitle("看电视");
                webView.loadUrl("https://tv.cctv.com/live/");
                break;
            case "1136": //火车票
                setToolBarTitle("火车票");
                webView.loadUrl("https://www.12306.cn/index/");
                break;
            case "1134": //查违章
                setToolBarTitle("查询违章");
                webView.loadUrl("http://chaxun.weizhang8.cn/jiashizheng.php");
                http://www.365dhw.com/baozhi.html
                break;
            case "1123": //读报纸
                setToolBarTitle("读报纸");
//                webView.loadUrl("http://www.365dhw.com/baozhi.html");
                webView.loadUrl("http://www.pdfzj.com/");
                break;
            case "1197": //闻喜政务
                setToolBarTitle("闻喜政务");
                webView.loadUrl("http://yc.sxzwfw.gov.cn/");
                break;
            case "1124": //上网站
                setToolBarTitle("上网站");
//                webView.loadUrl("http://www.365dhw.com/baozhi.html");
                webView.loadUrl("http://guozhivip.com/");
                break;
            case "1141": //查社保
                setToolBarTitle("查社保");
                webView.loadUrl("http://yc.sxzwfw.gov.cn/icity/govservice/hotItemList?id=5700-g-0202947-140000&i=1");
                break;
            case "1142": //交电费
                setToolBarTitle("交电费");
                webView.loadUrl("http://www.95598.cn/person/index.shtml");
                break;
            case "1143": //交水费
                setToolBarTitle("交水费");
                webView.loadUrl("https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fjiaofei.alipay.com%2Ffare%2FebppChargeEntering.htm%3FchargeType%3Dwater%26province%3D%25C9%25BD%25CE%25F7%26city%3D%25D4%25CB%25B3%25C7");
                break;
            case "1151": //查快递
                setToolBarTitle("查快递");
                webView.loadUrl("https://m.kuaidi100.com/all/");
                break;
            case "1137": //查路况
                setToolBarTitle("查路况");
                webView.loadUrl("https://www.369.me/m");
                break;
            case "1162": //法网平台
                setToolBarTitle("法网平台");
                webView.loadUrl("http://www.12348.gov.cn/#/homepage");
                break;
            case "1199": //学科资源
                setToolBarTitle("学科资源");
                webView.loadUrl("https://zy.yceduyun.com");
                break;
            case "1161": //法务咨询
                setToolBarTitle("法务咨询");
                webView.loadUrl("https://www.66law.cn/question/");
                break;
            case "1163": //律师事务所
                setToolBarTitle("律师事务所");
                webView.loadUrl("https://map.baidu.com/search/%E8%BF%90%E5%9F%8E%E5%BE%8B%E5%B8%88%E4%BA%8B%E5%8A%A1%E6%89%80/@12329371.287709137,4169384.63,10.92z?querytype=s&c=328&wd=%E8%BF%90%E5%9F%8E%E5%BE%8B%E5%B8%88%E4%BA%8B%E5%8A%A1%E6%89%80&da_src=shareurl&on_gel=1&l=10&gr=1&b=(12199283.751029601,4105899.201943372;12459458.824388674,4232870.058056627)&pn=0&device_ratio=1");
                break;
            case "1172": //查路况
                setToolBarTitle("药店查询");
                webView.loadUrl(" https://map.baidu.com/search/%E8%8D%AF%E5%BA%97%E6%9F%A5%E8%AF%A2/@12360261.050985238,4149355.731437368,18.31z?querytype=s&c=328&wd=%E8%8D%AF%E5%BA%97%E6%9F%A5%E8%AF%A2&da_src=shareurl&on_gel=1&l=18&gr=1&b=(12359478.092497332,4149116.9513309198;12361024.677164799,4149871.7168691577)&pn=0&device_ratio=1");
                break;
            case "1171": //医院查询
                setToolBarTitle("医院查询");
                webView.loadUrl("https://map.baidu.com/search/%E5%8C%BB%E9%99%A2%E6%9F%A5%E8%AF%A2/@12360261.050985238,4149355.731437368,18.31z?querytype=s&da_src=shareurl&wd=%E5%8C%BB%E9%99%A2%E6%9F%A5%E8%AF%A2&c=328&src=0&pn=0&sug=0&l=18&b=(12359487.758651504,4148978.348668249;12361034.34331897,4149733.114206487)&from=webmap&biz_forward=%7B%22scaler%22:1,%22styles%22:%22pl%22%7D&device_ratio=1");
                break;
            case "1153": //房产
                setToolBarTitle("同城房产");
                webView.loadUrl("https://house.ltzxw.com/wap/?share=1&from=groupmessage");
                break;
            case "1154": //招聘
                setToolBarTitle("同城招聘");
                webView.loadUrl("https://job.ltzxw.com/seekwap/?source=app&from=groupmessage");
                break;
            case "1155": //家政服务
                setToolBarTitle("家政服务");
                webView.loadUrl("http://yuncheng.jaz581.com/");
                break;
            case "1173": //预约挂号
                setToolBarTitle("预约挂号");
                webView.loadUrl("https://www.yihu.com/hospital/");
                break;
            case "1178": //公安局
                setToolBarTitle("派出所查询");
                webView.loadUrl("http://yuncheng.bendibao.com/cyfw/wangdian/1950.shtm");
                break;
            case "1145": //交话费
                setToolBarTitle("交话费");
                webView.loadUrl("https://chongzhi.jd.com/");
                break;
            case "1146": //有线电费
                setToolBarTitle("有线电视费缴纳");
                webView.loadUrl("https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fjiaofei.alipay.com%2Ffare%2FebppChargeEntering.htm%3FchargeType%3Dcatv");
                break;
            case "1144": //燃气费
                setToolBarTitle("燃气费缴纳");
                webView.loadUrl("https://jiaofei.alipay.com/jiaofei.htm?_pdType=aecfbbfgeabbifdfdieh&_scType=bacfajegafddadijhagd");
                break;
            case "1135": //客运
                setToolBarTitle("客运站");
                webView.loadUrl("https://map.baidu.com/search/%E5%AE%A2%E8%BF%90/@12362219,4146196,12z?querytype=s&da_src=shareurl&wd=%E5%AE%A2%E8%BF%90&c=328&src=0&pn=0&sug=0&l=12&b=(12300779,4116212;12423659,4176180)&from=webmap&biz_forward=%7B%22scaler%22:1,%22styles%22:%22pl%22%7D&device_ratio=1");
                break;
            case "1132": //公共自行车
                setToolBarTitle("公共自行车租聘点");
                webView.loadUrl("https://map.baidu.com/search/%E5%85%AC%E5%85%B1%E8%87%AA%E8%A1%8C%E8%BD%A6/@12362219,4146196,12z?querytype=s&da_src=shareurl&wd=%E5%85%AC%E5%85%B1%E8%87%AA%E8%A1%8C%E8%BD%A6&c=328&src=0&pn=0&sug=0&l=12&b=(12300779,4116212;12423659,4176180)&from=webmap&biz_forward=%7B%22scaler%22:1,%22styles%22:%22pl%22%7D&device_ratio=1");
                break;
            case "1133": //停车位
                setToolBarTitle("周围停车位");
                webView.loadUrl("https://map.baidu.com/search/%E5%81%9C%E8%BD%A6%E5%9C%BA/@12362219,4146196,12z?querytype=s&da_src=shareurl&wd=%E5%81%9C%E8%BD%A6%E5%9C%BA&c=328&src=0&pn=0&sug=0&l=12&b=(12300779,4116212;12423659,4176180)&from=webmap&biz_forward=%7B%22scaler%22:1,%22styles%22:%22pl%22%7D&device_ratio=1");
                break;
            case "1131": //查公交
                setToolBarTitle("查公交");
                webView.loadUrl("http://yuncheng.gongjiao.com/");
                break;
            case "1140": //学校查询
                setToolBarTitle("学校查询");
                webView.loadUrl("https://www.yceduyun.com/xykj/xykj");
                break;
            case "1198": //智慧党建
                setToolBarTitle("智慧党建");
                webView.loadUrl("http://www.sxwxdj.gov.cn");
                break;


        }


    }

    private void setToolBarTitle(String title) {
        toolbartitle.setText(title);
    }

    private void inintView(WebView webView2) {

        WebSettings webSetting = webView2.getSettings();
        webSetting.setAllowFileAccess(true);
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

        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);//默认缩放模式
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        long time = System.currentTimeMillis();

        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView2.setInitialScale(25);
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

}
