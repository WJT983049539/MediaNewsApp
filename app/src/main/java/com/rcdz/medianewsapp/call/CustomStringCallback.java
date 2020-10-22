package com.rcdz.medianewsapp.call;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.rcdz.medianewsapp.MAppaction;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.LoginActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;

/**
 * @auther wjt
 * @date 2019/5/16
 */
public abstract class CustomStringCallback extends AbsCallback<String> {

    private StringConvert convert;

    public CustomStringCallback() {
        convert = new StringConvert();
    }

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = convert.convertResponse(response);
        int code=response.code();
        if(code==401||code==202){
            Log.i("test","token 过时"+code);
            // token过时了
            MAppaction.ActivityManager.getManager().getTopActivity().startActivity(new Intent(MAppaction.ActivityManager.getManager().getTopActivity(), LoginActivity.class));
            return null;
        }
        response.close();
        return s;
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<String> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if(exception!=null){
            exception.printStackTrace();
        }
        if(exception instanceof UnknownHostException ||exception instanceof ConnectException){
            GlobalToast.show("网络连接失败，请连接网络！", Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof SocketTimeoutException){
            GlobalToast.show("网络请求超时!", Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof HttpException){
            GlobalToast.show("服务端异常了!", Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof StorageException){
//            ToastUtil.showShort(HwApp.getApp(),"SD卡不存在或者没有权限！");
            GlobalToast.show("SD卡不存在或者没有权限!", Toast.LENGTH_SHORT);
        }else if(exception instanceof IllegalStateException){
            GlobalToast.show(exception.getMessage(), Toast.LENGTH_SHORT);
//            ToastUtil.showShort(HwApp.getApp(),exception.getMessage());
        }
    }
}