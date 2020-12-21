package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/18 11:02
 */
public class HomeClumnInfo implements Serializable {


    /**
     * status : true
     * code : 200
     * message : 查询成功
     * data : {"app_Sections":[{"id":1,"sectionId":18,"type":0,"sectionName":"闻喜周边","logo":"Upload/Files/Global_Sections/4b7dd895c03b47199c2a3af5317d8483/small/头像2.jpg","createID":3,"creator":"测试用户2","createDate":"2020-02-02 00:00:00"},{"id":2,"sectionId":1,"type":0,"sectionName":"今日闻喜","logo":"Upload/Files/My_ImageRepository/4eff8d1117f146398c146284968123b6/793bfb20-a733-4029-bcab-142ffb6b5895.jpg","createID":3,"creator":"测试用户2","createDate":"2020-02-02 00:00:00"},{"id":3,"sectionId":2,"type":1,"sectionName":"推荐","logo":"Upload/Files/My_ImageRepository/4eff8d1117f146398c146284968123b6/793bfb20-a733-4029-bcab-142ffb6b5895.jpg","createID":3,"creator":"测试用户2","createDate":"2020-02-02 00:00:00"}],"app_SectionsSpecial":[{"id":3,"sectionId":2,"type":1,"sectionName":"推荐","logo":"Upload/Files/My_ImageRepository/4eff8d1117f146398c146284968123b6/793bfb20-a733-4029-bcab-142ffb6b5895.jpg","createID":3,"creator":"测试用户2","createDate":"2020-02-02 00:00:00"}]}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBean data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private List<AppSectionsBean> app_Sections;
        private List<AppSectionsSpecialBean> app_SectionsSpecial;

        public List<AppSectionsBean> getApp_Sections() {
            return app_Sections;
        }

        public void setApp_Sections(List<AppSectionsBean> app_Sections) {
            this.app_Sections = app_Sections;
        }

        public List<AppSectionsSpecialBean> getApp_SectionsSpecial() {
            return app_SectionsSpecial;
        }

        public void setApp_SectionsSpecial(List<AppSectionsSpecialBean> app_SectionsSpecial) {
            this.app_SectionsSpecial = app_SectionsSpecial;
        }

        public static class AppSectionsBean implements Serializable {
            /**
             * id : 1
             * sectionId : 18
             * type : 0
             * sectionName : 闻喜周边
             * logo : Upload/Files/Global_Sections/4b7dd895c03b47199c2a3af5317d8483/small/头像2.jpg
             * createID : 3
             * creator : 测试用户2
             * createDate : 2020-02-02 00:00:00
             */

            private int id;
            private int sectionId;
            private int type;
            private String sectionName;
            private String logo;
            private int createID;
            private String creator;
            private String createDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSectionId() {
                return sectionId;
            }

            public void setSectionId(int sectionId) {
                this.sectionId = sectionId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSectionName() {
                return sectionName;
            }

            public void setSectionName(String sectionName) {
                this.sectionName = sectionName;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getCreateID() {
                return createID;
            }

            public void setCreateID(int createID) {
                this.createID = createID;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }

        public static class AppSectionsSpecialBean implements Serializable{
            /**
             * id : 3
             * sectionId : 2
             * type : 1
             * sectionName : 推荐
             * logo : Upload/Files/My_ImageRepository/4eff8d1117f146398c146284968123b6/793bfb20-a733-4029-bcab-142ffb6b5895.jpg
             * createID : 3
             * creator : 测试用户2
             * createDate : 2020-02-02 00:00:00
             */

            private int id;
            private int sectionId;
            private int type;
            private String sectionName;
            private String logo;
            private int createID;
            private String creator;
            private String createDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSectionId() {
                return sectionId;
            }

            public void setSectionId(int sectionId) {
                this.sectionId = sectionId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSectionName() {
                return sectionName;
            }

            public void setSectionName(String sectionName) {
                this.sectionName = sectionName;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getCreateID() {
                return createID;
            }

            public void setCreateID(int createID) {
                this.createID = createID;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }
}
