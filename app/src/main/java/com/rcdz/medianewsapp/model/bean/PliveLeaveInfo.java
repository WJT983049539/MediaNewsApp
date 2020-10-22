package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:民生留言
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 15:12
 */
public class PliveLeaveInfo implements Serializable {


    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 10
     * rows : [{"Id":25,"Subject":"阿松大阿松大啊","Contents":"asdasdasdasdasdasd","Images":"asdasdasdasd","PhoneNo":"10000000000","OrganizationId":5,"OrganizationName":"阿什顿","Type":"反馈","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-15 15:32:58","IsBlackList":0,"State":"0"},{"Id":24,"Subject":"阿松大阿松大啊","Contents":"asdasdasdasdasdasd","Images":"asdasdasdasd","PhoneNo":"10000000000","OrganizationId":5,"OrganizationName":"阿什顿","Type":"反馈","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":1,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-15 15:32:49","IsBlackList":0,"State":"0"},{"Id":23,"Subject":"阿松大阿松大啊","Contents":"asdasdasdasdasdasd","Images":"asdasdasdasd","PhoneNo":"10000000000","OrganizationId":5,"OrganizationName":"阿什顿","Type":"反馈","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-15 15:28:59","IsBlackList":0,"State":"0"},{"Id":21,"Subject":"阿松大阿松大啊","Contents":"asdasdasdasdasdasd","Images":"asdasdasdasd","PhoneNo":"10000000000","OrganizationId":36,"OrganizationName":"阿什顿","Type":"反馈","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-15 15:28:35","IsBlackList":0,"State":"0"},{"Id":20,"Subject":"阿松大阿松大啊","Contents":"asdasdasdasdasdasd","Images":"asdasdasdasd","PhoneNo":"10000000000","OrganizationId":36,"OrganizationName":"阿什顿","Type":"反馈","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-15 15:28:15","IsBlackList":0,"State":"0"},{"Id":19,"Subject":"阿松大","Contents":"昂首哈是否啊啊是否","Images":"0000000","PhoneNo":"12345678900","OrganizationId":36,"OrganizationName":"阿斯顿","Type":"建议","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-13 10:00:45","IsBlackList":0,"State":"no"},{"Id":18,"Subject":"阿松大","Contents":"昂首哈是否啊啊是否","Images":"0000000","PhoneNo":"12345678900","OrganizationId":36,"OrganizationName":"阿斯顿","Type":"建议","UserTrueName":null,"AuditUserId":null,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":null,"Time":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-13 10:00:11","IsBlackList":0,"State":"no"},{"Id":16,"Subject":"a","Contents":"BBB","Images":null,"PhoneNo":null,"OrganizationId":5,"OrganizationName":"a","Type":"a","UserTrueName":null,"AuditUserId":1,"AuditDate":null,"Description":null,"IsReply":0,"ReplyUserId":null,"ReplyContents":null,"ReplyDate":"2020-02-02 11:00:00","Time":"39600","CreateID":1,"Creator":"A","CreateDate":"2020-02-02 00:00:00","IsBlackList":0,"State":"1"},{"Id":15,"Subject":"b","Contents":"b","Images":null,"PhoneNo":null,"OrganizationId":1,"OrganizationName":"a","Type":"a","UserTrueName":"wwwww","AuditUserId":1,"AuditDate":"2020-09-27 11:49:33","Description":"qwer","IsReply":0,"ReplyUserId":5,"ReplyContents":"qwer","ReplyDate":"2020-02-02 00:00:00","Time":"50","CreateID":1,"Creator":"b","CreateDate":"2020-02-02 00:01:01","IsBlackList":1,"State":"sss"},{"Id":11,"Subject":"a","Contents":"BBB","Images":null,"PhoneNo":null,"OrganizationId":5,"OrganizationName":"a","Type":"a","UserTrueName":null,"AuditUserId":1,"AuditDate":null,"Description":null,"IsReply":1,"ReplyUserId":0,"ReplyContents":"string","ReplyDate":"2020-10-12 09:37:28","Time":"100","CreateID":1,"Creator":"A","CreateDate":"2020-02-02 00:00:00","IsBlackList":0,"State":"1"}]
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
    private List<LeaveMessageInfo> rows;

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

    public List<LeaveMessageInfo> getRows() {
        return rows;
    }

    public void setRows(List<LeaveMessageInfo> rows) {
        this.rows = rows;
    }

    public static class LeaveMessageInfo implements Serializable{
        @Override
        public String toString() {
            return "LeaveMessageInfo{" +
                    "Id=" + Id +
                    ", Subject='" + Subject + '\'' +
                    ", Contents='" + Contents + '\'' +
                    ", Images='" + Images + '\'' +
                    ", PhoneNo='" + PhoneNo + '\'' +
                    ", OrganizationId=" + OrganizationId +
                    ", OrganizationName='" + OrganizationName + '\'' +
                    ", Type='" + Type + '\'' +
                    ", UserTrueName=" + UserTrueName +
                    ", AuditUserId=" + AuditUserId +
                    ", AuditDate=" + AuditDate +
                    ", Description=" + Description +
                    ", IsReply=" + IsReply +
                    ", ReplyUserId=" + ReplyUserId +
                    ", ReplyContents=" + ReplyContents +
                    ", ReplyDate=" + ReplyDate +
                    ", Time=" + Time +
                    ", CreateID=" + CreateID +
                    ", Creator='" + Creator + '\'' +
                    ", CreateDate='" + CreateDate + '\'' +
                    ", IsBlackList=" + IsBlackList +
                    ", State='" + State + '\'' +
                    '}';
        }

        /**
         * Id : 25
         * Subject : 阿松大阿松大啊
         * Contents : asdasdasdasdasdasd
         * Images : asdasdasdasd
         * PhoneNo : 10000000000
         * OrganizationId : 5
         * OrganizationName : 阿什顿
         * Type : 反馈
         * UserTrueName : null
         * AuditUserId : null
         * AuditDate : null
         * Description : null
         * IsReply : 0
         * ReplyUserId : null
         * ReplyContents : null
         * ReplyDate : null
         * Time : null
         * CreateID : 3
         * Creator : 测试用户2
         * CreateDate : 2020-10-15 15:32:58
         * IsBlackList : 0
         * State : 0
         */

        private int Id;
        private String Subject;
        private String Contents;
        private String Images;
        private String PhoneNo;
        private int OrganizationId;
        private String OrganizationName;
        private String Type;
        private Object UserTrueName;
        private Object AuditUserId;
        private Object AuditDate;
        private Object Description;
        private int IsReply;
        private Object ReplyUserId;
        private Object ReplyContents;
        private Object ReplyDate;
        private Object Time;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private int IsBlackList;
        private String State;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String Subject) {
            this.Subject = Subject;
        }

        public String getContents() {
            return Contents;
        }

        public void setContents(String Contents) {
            this.Contents = Contents;
        }

        public String getImages() {
            return Images;
        }

        public void setImages(String Images) {
            this.Images = Images;
        }

        public String getPhoneNo() {
            return PhoneNo;
        }

        public void setPhoneNo(String PhoneNo) {
            this.PhoneNo = PhoneNo;
        }

        public int getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(int OrganizationId) {
            this.OrganizationId = OrganizationId;
        }

        public String getOrganizationName() {
            return OrganizationName;
        }

        public void setOrganizationName(String OrganizationName) {
            this.OrganizationName = OrganizationName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public Object getUserTrueName() {
            return UserTrueName;
        }

        public void setUserTrueName(Object UserTrueName) {
            this.UserTrueName = UserTrueName;
        }

        public Object getAuditUserId() {
            return AuditUserId;
        }

        public void setAuditUserId(Object AuditUserId) {
            this.AuditUserId = AuditUserId;
        }

        public Object getAuditDate() {
            return AuditDate;
        }

        public void setAuditDate(Object AuditDate) {
            this.AuditDate = AuditDate;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public int getIsReply() {
            return IsReply;
        }

        public void setIsReply(int IsReply) {
            this.IsReply = IsReply;
        }

        public Object getReplyUserId() {
            return ReplyUserId;
        }

        public void setReplyUserId(Object ReplyUserId) {
            this.ReplyUserId = ReplyUserId;
        }

        public Object getReplyContents() {
            return ReplyContents;
        }

        public void setReplyContents(Object ReplyContents) {
            this.ReplyContents = ReplyContents;
        }

        public Object getReplyDate() {
            return ReplyDate;
        }

        public void setReplyDate(Object ReplyDate) {
            this.ReplyDate = ReplyDate;
        }

        public Object getTime() {
            return Time;
        }

        public void setTime(Object Time) {
            this.Time = Time;
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

        public int getIsBlackList() {
            return IsBlackList;
        }

        public void setIsBlackList(int IsBlackList) {
            this.IsBlackList = IsBlackList;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
