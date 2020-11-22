package com.rcdz.medianewsapp.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作用: 公共常用工具类
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 9:24
 */
public class Comment {
    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public  static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为手机号
     * @return
     */

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
            }
            return isMatch;
        }
    }

    /**
     * 用户名验证
     *  @param name
     *  @return
     */
    public static boolean checkName(String name) {
        String regExp = "^[^0-9][\\w_]{5,9}$";
        if(name.matches(regExp)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 密码验证
     *  @param pwd
     *  @return
     */
    public static boolean checkPwd(String pwd) {
        String regExp = "^[\\w_]{6,20}$";
        if(pwd.matches(regExp)) {
            return true;
        }
        return false;
    }
}
