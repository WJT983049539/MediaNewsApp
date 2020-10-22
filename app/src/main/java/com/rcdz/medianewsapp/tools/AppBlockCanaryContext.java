package com.rcdz.medianewsapp.tools;

import com.github.moduth.blockcanary.BuildConfig;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class AppBlockCanaryContext extends com.github.moduth.blockcanary.BlockCanaryContext {
    //设置卡顿判断的阙值
    public int getConfigBlockThreshold() {
        return 500;
    }

    //是否需要显示卡顿的信息
    public boolean isNeedDisplay() {
        return BuildConfig.DEBUG;
    }

    //设置log保存在sd卡的目录位置
    public String getLogPath() {
        return "/blockcanary/MediaNewsApp";
    }
}
