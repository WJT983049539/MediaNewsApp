package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 10:29
 */
public class FeedbackBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 4
     * rows : [{"Id":37,"Name":"D","CardNo":"2","Contacts":"B","ContactsPhone":null,"PId":1,"Logo":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-12 16:08:47"},{"Id":36,"Name":"c","CardNo":"3","Contacts":"c","ContactsPhone":null,"PId":4,"Logo":null,"CreateID":4,"Creator":"eee","CreateDate":"2020-02-02 00:00:00"},{"Id":5,"Name":"B","CardNo":"2","Contacts":"B","ContactsPhone":null,"PId":1,"Logo":null,"CreateID":2,"Creator":"qqq","CreateDate":"2020-05-05 00:00:00"},{"Id":1,"Name":"A","CardNo":"1","Contacts":"A","ContactsPhone":null,"PId":5,"Logo":null,"CreateID":1,"Creator":"aaaa","CreateDate":"2020-05-05 00:00:00"}]
     * summary : null
     * extra : null
     */

    private int code;
    private Object message;
    private int status;
    private Object msg;
    private int total;
    private Object summary;
    private Object extra;
    private List<FeedbackInfo> rows;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public List<FeedbackInfo> getRows() {
        return rows;
    }

    public void setRows(List<FeedbackInfo> rows) {
        this.rows = rows;
    }

    public static class FeedbackInfo implements Serializable{
        /**
         * Id : 37
         * Name : D
         * CardNo : 2
         * Contacts : B
         * ContactsPhone : null
         * PId : 1
         * Logo : null
         * CreateID : 3
         * Creator : 测试用户2
         * CreateDate : 2020-10-12 16:08:47
         */

        private int Id;
        private String Name;
        private String CardNo;
        private String Contacts;
        private Object ContactsPhone;
        private int PId;
        private Object Logo;
        private int CreateID;
        private String Creator;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public String getContacts() {
            return Contacts;
        }

        public void setContacts(String Contacts) {
            this.Contacts = Contacts;
        }

        public Object getContactsPhone() {
            return ContactsPhone;
        }

        public void setContactsPhone(Object ContactsPhone) {
            this.ContactsPhone = ContactsPhone;
        }

        public int getPId() {
            return PId;
        }

        public void setPId(int PId) {
            this.PId = PId;
        }

        public Object getLogo() {
            return Logo;
        }

        public void setLogo(Object Logo) {
            this.Logo = Logo;
        }

        public int getCreateID() {
            return CreateID;
        }

        public void setCreateID(int CreateID) {
            this.CreateID = CreateID;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
