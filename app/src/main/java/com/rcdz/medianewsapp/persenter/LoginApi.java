package com.rcdz.medianewsapp.persenter;
import com.rcdz.medianewsapp.tools.AppConfig;

/**
 * 用户登录API
 * Author：panhongyu
 * date:2019.3.23
 */
public class LoginApi {

    /**
     * 发送验证码接口
     * */
    public static String getCheckCodeUrl(){
        return String.format("api/User/SendPhoneMsg");
    }
    /**
     * APP短信验证码登录
     * */
    public static String getLoginForMes(){
        return String.format("api/User/AppLoginByMessage");
    }

    /**
     * app端登录
     * @return
     */
    public static String AppLogin(){
        return String.format("api/User/Applogin");
    }
    /**
     * app端注册
     * @return
     */
    public static String AppRegister(){
        return String.format("api/User/AppRegister");
    }
    /**
     * 更换token
     * @return
     */
    public static String ReplaceToken(){
        return String.format("api/User/replaceToken");
    }
    /**
     * 忘记密码
     * @return
     */
    public static String ForgetPsd(){
        return String.format("api/Sys_User/ForgetPwd");
    }

}
