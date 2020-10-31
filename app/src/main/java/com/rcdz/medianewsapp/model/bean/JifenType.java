package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/22 16:10
 */
public class JifenType implements Serializable {

    /**
     * mainData : {"Type":"1"}
     */

    private MainDataBean mainData;

    public MainDataBean getMainData() {
        return mainData;
    }

    public void setMainData(MainDataBean mainData) {
        this.mainData = mainData;
    }

    public static class MainDataBean implements Serializable{
        /**
         * Type : 1
         */

        private String Type;

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }
}
