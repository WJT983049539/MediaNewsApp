package com.rcdz.medianewsapp.call;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.MAppaction;
import com.rcdz.medianewsapp.model.bean.BaseBean;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.activity.LoginActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;


public abstract class JsonCallback<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body= (ResponseBody) response.body();
        int code=response.code();
        T data=null;
        if(code==401||code==202){
            Log.i("test","token 过时"+code);
            SharedPreferenceTools.putValuetoSP(MAppaction.ActivityManager.getManager().getTopActivity(),"loginStru",false);
            Constant.token="";
            // token过时了
           if(MAppaction.ActivityManager.getManager().getTopActivity()instanceof LoginActivity){

            }else{
                MAppaction.ActivityManager.getManager().getTopActivity().startActivity(new Intent(MAppaction.ActivityManager.getManager().getTopActivity(), LoginActivity.class));
            }
//            MAppaction.ActivityManager.getManager().getTopActivity().startActivity(new Intent(MAppaction.ActivityManager.getManager().getTopActivity(), LoginActivity.class));
            MAppaction.ActivityManager.getManager().getTopActivity().finish();
                GlobalToast.show("未登录,请登录",Toast.LENGTH_LONG);
            return null;
        }else if(code==200){
            if(body==null) {
                return null;
            }
            Gson gson=new GsonBuilder().registerTypeAdapterFactory(new NullStringEmptyTypeAdapterFactory()).create();
            JsonReader jsonReader=new JsonReader(body.charStream());
            Type genType=getClass().getGenericSuperclass();//拿的是泛型的类型
            Type type=((ParameterizedType)genType).getActualTypeArguments()[0];
            data =gson.fromJson(jsonReader,type);
        }
        return data;
    }
    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if(exception!=null){
            exception.printStackTrace();
        }
        if(exception instanceof UnknownHostException ||exception instanceof ConnectException){
            GlobalToast.show("网络连接失败，请连接网络！", Toast.LENGTH_SHORT);
            GlobalToast.showToastforCenter("网络连接失败，请连接网络！",Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof SocketTimeoutException){
            GlobalToast.show("网络请求超时!", Toast.LENGTH_SHORT);
            GlobalToast.showToastforCenter("网络请求超时",Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof HttpException){
            GlobalToast.show("服务端异常了!", Toast.LENGTH_SHORT);
            GlobalToast.showToastforCenter("服务端异常了",Toast.LENGTH_SHORT);
//            if(HwApp.getApp().getCurActivity()instanceof DisconnectActivity){
//            }else {
//                OpenHelper.openDisconnectActivity(HwApp.getApp().getCurActivity());
//            }
        }else if(exception instanceof StorageException){
            GlobalToast.show("SD卡不存在或者没有权限",Toast.LENGTH_SHORT);
        }else if(exception instanceof IllegalStateException){
            GlobalToast.show(exception.getMessage(), Toast.LENGTH_SHORT);
            GlobalToast.showToastforCenter(exception.getMessage(),Toast.LENGTH_SHORT);
//            ToastUtil.showShort(HwApp.getApp(),exception.getMessage());
        }
    }

}
