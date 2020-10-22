package com.rcdz.medianewsapp.persenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.DeleteRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PatchRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GsonUtil;

import java.util.Map;

/**
 * 对okgo框架进行封装
 * create by panhongyu
 * on 2019.3.19
 */
public class CommApi {
    private static final String TAG = "CommApi";

    /**
     * 有入参、需要user_token的get方法
     * @param uri
     * @param map
     * @return
     */
    public static GetRequest get(String uri, Map map) {
        String userToken = Constant.token;
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return  OkGo.get(AppConfig.BASE_URL+uri).headers("user-token",userToken).params(httpParams);
    }



    /**
     * 有入参、需要user_token的post方法
     * @param uri
     * @param map
     * @return
     */
    public static PostRequest post(String uri, Map map) {
        String userToken = Constant.token;
        String sb="";
        if (map!=null && !map.isEmpty()){
             sb = GsonUtil.BeanToJson(map);
        }
        return  OkGo.post(AppConfig.BASE_URL+uri).headers("Authorization", "Bearer " + Constant.token).upJson(sb);
    }
    /**
     * 有入参、需要user_token的put方法
     * @param uri
     * @param map
     * @return
     */
    public static PutRequest put(String uri, Map map) {
       String userToken = Constant.token;
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return  OkGo.put(AppConfig.BASE_URL+uri).headers("user-token",userToken).params(httpParams);
    }
    /**
     * w无入参、需要user_token的put方法
     * @param uri
     * @return
     */
    public static PutRequest putNoParams(String uri) {
        return  OkGo.put(AppConfig.BASE_URL+uri).headers("Authorization", "Bearer " + Constant.token);
    }

    /**
     * 不需要入参、需要user_token的get方法
     * @param uri
     * @return
     */
    public static GetRequest getNoParams(String uri) {
       String userToken = Constant.token;
        return  OkGo.get(AppConfig.BASE_URL+uri).headers("user-token",userToken);
    }

    /**
     * 有入参、不需要user_token的get方法
     * @param uri
     * @param map
     * @return
     */
    public static GetRequest getNoToken(String uri, Map map) {
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return  OkGo.get(AppConfig.BASE_URL+uri).params(httpParams);
    }

    /**
     * 不需要入参、需要user_token的post方法
     * @param uri
     * @return
     */
    public static PostRequest postNoParams(String uri) {
        return  OkGo.post(AppConfig.BASE_URL+uri).headers("Authorization", "Bearer " + Constant.token);
    }

    /**
     * 有入参、不需要user_token的post方法
     * @param uri
     * @param map
     * @return
     */
    public static PostRequest postNoToken(String uri, Map map) {
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return  OkGo.post(AppConfig.BASE_URL+uri).params(httpParams);
    }
    /**
     * 无参数 需要user_token 并且指定 强制使用 multipart/form-data 表单上传
     */
    public static PostRequest postNoParamUpToken(String uri){
       String userToken = Constant.token;
        return OkGo.post(AppConfig.BASE_URL+uri).headers("user-token",userToken).isMultipart(true);
    }
    /**
     * 有参数 不需要user_token 并且指定 强制使用 multipart/form-data 表单上传
     */
    public static PostRequest postUpNoToken(String uri,Map map){
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return OkGo.post(AppConfig.BASE_URL+uri).isMultipart(true).params(httpParams);
    }
    /*
     * 有参数 需要user_token 并且指定 强制使用 multipart/form-data 表单上传
     */
    public static PostRequest postParamUpToken(String uri,Map map){
       String userToken = Constant.token;
        return OkGo.post(AppConfig.BASE_URL+uri+"?id="+map.get("id")+"&tag="+map.get("tag")).headers("user-token",userToken).isMultipart(true);
    }
    /**
     * 有入参、需要user_token的delete方法
     * @param uri
     * @param map
     * @return
     */
    public static DeleteRequest delete(String uri, Map map) {
       String userToken = Constant.token;
        HttpParams httpParams = new HttpParams();
        httpParams.put(map);
        return  OkGo.delete(AppConfig.BASE_URL+uri).headers("user-token",userToken).params(httpParams);
    }

    /**
     * 无入参、需要user_token的delete方法
     * @param uri
     * @return
     */
    public static DeleteRequest deleteNoParam(String uri) {
       String userToken = Constant.token;
        return  OkGo.delete(AppConfig.BASE_URL+uri).headers("user-token",userToken);
    }
    /**
     * 无入参 需要user_token的put方法
     */
    public static PutRequest put(String uri){
       String userToken = Constant.token;
        return  OkGo.put(AppConfig.BASE_URL+uri).headers("user-token",userToken);
    }

    /**
     * 无入参 需要user_token的PatCh方法
     */
    public static PatchRequest patchNoParam(String uri) {
       String userToken = Constant.token;
        return  OkGo.patch(AppConfig.BASE_URL+uri).headers("user-token",userToken);
    }

}
