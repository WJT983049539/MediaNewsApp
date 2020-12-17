package com.rcdz.medianewsapp.view.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.bean.Permissions
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener
import com.rcdz.medianewsapp.R
import com.rcdz.medianewsapp.tools.ACache
import com.rcdz.medianewsapp.tools.Constant
import com.rcdz.medianewsapp.tools.SharedPreferenceTools
import com.tencent.smtt.sdk.CookieSyncManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.tencent.smtt.utils.TbsLog

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/17 16:03
 */
class ShowWelcomeActivity : BaseActivity() ,View.OnClickListener {
    lateinit var showinfowelcome:WebView
    lateinit var  bun: Bundle
    lateinit var UrlString:String
    override fun setNowActivityName(): String {
        return "ShowWelcomeActivity"
    }

    override fun setLayout(): Int {
        return R.layout.activity_showwelcomeinfo;
    }

    override fun inintView() {
        bun= intent.extras!!
        UrlString= bun.getString("url").toString()
        val img_back:ImageView = fvbi(R.id.img_back)
        img_back.setOnClickListener(this)
        val toolbar_title:TextView = fvbi(R.id.toolbar_title)
        toolbar_title.text=""
        showinfowelcome=fvbi(R.id.showinfowelcome)
        initWebView()
    }

    private fun initWebView() {
        val webSetting: WebSettings = showinfowelcome.getSettings()
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        webSetting.setAppCacheEnabled(true)
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.loadWithOverviewMode = true

        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        webSetting.setAppCachePath(getDir("appcache", 0).path)
        webSetting.databasePath = getDir("databases", 0).path
        showinfowelcome.getSettings().setBuiltInZoomControls(false)
        showinfowelcome.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE) //默认缩放模式

        showinfowelcome.getSettings().setSupportZoom(true)
        showinfowelcome.getSettings().setJavaScriptCanOpenWindowsAutomatically(true)
        webSetting.setGeolocationDatabasePath(getDir("geolocation", 0)
                .path)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        val time = System.currentTimeMillis()

        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time))
        CookieSyncManager.createInstance(this)
        CookieSyncManager.getInstance().sync()
        showinfowelcome.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }
        })
        showinfowelcome.setInitialScale(25)
        showinfowelcome.loadUrl(UrlString)
    }

    override fun inintData() {}
    override fun onClick(v: View?) {
    when (v?.id){
        R.id.img_back -> { //返回按钮
            getPremission2();
        }
    }
    }

    fun getPremission2() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ),  //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                object : CheckRequestPermissionsListener {
                    override fun onAllPermissionOk(allPermissions: Array<Permission>) {
                        Log.i("test", "权限获取成功")
                        val aCache: ACache = ACache.get(this@ShowWelcomeActivity)
                        //判断是否已经登录 ，有时效性，时效为24小时,退出 或者重新登录都重置
                        val loginstau = aCache.getAsString("loginStru")
                        //判断是否为第一次启动
                        val isFirstStart = SharedPreferenceTools.getValueofSP(this@ShowWelcomeActivity, "isFirstStart", true) as Boolean
                        val token = getSp("token", "") as String
                        val loginStru = SharedPreferenceTools.getValueofSP(this@ShowWelcomeActivity, "loginStru", false) as Boolean
                        Constant.token = token
                        if (!isFirstStart) {   //不是第一次登录

//                            if(loginStru){ //已经登录过了，直接进入主页
//                                openActivity(MainActivity.class);
//                                ShowWelcomeActivity.this.finish();
//                            }else{
//                                openActivity(LoginActivity.class);
//                                ShowWelcomeActivity.this.finish();
//                            }

                            //不管登录不登录直接进入主页
                            openActivity(MainActivity::class.java)
//                            val intent = Intent(this@ShowWelcomeActivity, MainActivity::class.java)
                            this@ShowWelcomeActivity.finish()
                        } else {    //第一次登录
                            openActivity(GuideActivity::class.java) //todo 欢迎页面完善
                            this@ShowWelcomeActivity.finish()
                        }
                    }

                    override fun onPermissionDenied(refusedPermissions: Array<Permission>) {
                        Toast.makeText(this@ShowWelcomeActivity, refusedPermissions[0].toString() +
                                " 权限获取失败", Toast.LENGTH_SHORT).show()
                        AlertDialog.Builder(this@ShowWelcomeActivity).setTitle("提示").setMessage("如果你拒绝了权限,应用中的一些功能将不糊能正常使用")
                                .setPositiveButton("授予权限") { dialog, which -> //用户点击以后
                                    val ff: Boolean = PanduanIsProhibitedPermissionDenied(refusedPermissions)!!
                                    if (!ff) {
                                        SoulPermission.getInstance().goApplicationSettings { Log.i("test", "这里是在设置也手动获取到权限以后返回，回调") }
                                    } else {
                                        getPremission2()
                                    }
                                }.create().show()
                    }
                })
    }

     fun PanduanIsProhibitedPermissionDenied(refusedPermissions: Array<Permission>): Boolean? {
        var flag = true
        for (i in refusedPermissions.indices) {
            if (!refusedPermissions[i].shouldRationale()) {
                flag = false
                return flag
            }
        }
        return flag
    }
}