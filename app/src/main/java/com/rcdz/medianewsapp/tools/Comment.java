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
}
