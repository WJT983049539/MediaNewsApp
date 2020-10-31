package com.rcdz.medianewsapp.persenter;

/**
 * 用户登录API
 * Author：panhongyu
 * date:2019.3.23
 */
public class MainApi {

    /**
     * 获取新闻列表
     * */
    public static String getNewsListUrl(){
        return String.format("api/NewsView/getPageData");
    }
    /**
     * 获取用户版块
     * */
    public static String getUserSection(){
        return String.format("api/Sys_UserSections/GetUserSections");
    }
    /**
     * 获取用户自己取消的版块
     * */
    public static String getNoUserSection(){
        return String.format("api/Sys_UserSections/getPageData");
    }
    /**
     * 模糊搜索
     * */
    public static String newsSearch(){
        return String.format("api/NewsSearchView/getPageData");
    }
    /**
     *
     * 用户修改个人版块
     * */
    public static String UpdateUserSections(){
        return String.format("api/Sys_UserSections/UpdateUserSection");
    }
    /**
     *
     * 民生版块信息
     * */
    public static String PliveData(){
        return String.format("api/Livelihood_Feedback/GetPageData");
    }
    /**
     *
     * 获取用户积分
     * */
    public static String GetJifen(){
        return String.format("api/Sys_UserScore/getPageData");
    }
    /**
     *
     * 获得直播间列表
     * */
    public static String GetLiving(){
        return String.format("api/LiveRoomView/GetPageData");
    }
    /**
     *
     * 民生机构信息
     * */
    public static String Department(){
        return String.format("api/Livelihood_Organization/GetStatOrganization");
    }
    /**
     *
     * 民生详情
     * */
    public static String LeaveMegDetailedInfo(){
        return String.format("api/Livelihood_Feedback/GetFeedbackById");
    }
    /**
     *
     * 个人用户信息
     * */
    public static String GetInfo(){
        return String.format("api/User/getCurrentUserInfo");
    }
    /**
     *
     * 主播信息
     * */
    public static String GetLivingMasterInfo(){
        return String.format("api/User/getUserInfo");
    }
    /**
     *
     * 留言弹框  查询反馈单位
     * */
    public static String GetOrganization(){
        return String.format("api/Livelihood_Organization/GetPageData");
    }
    /**
     *
     * 留言弹框  查询反馈单位
     * */
    public static String AddLeaveMessag(){
        return String.format("api/Livelihood_Feedback/Add");
    }
    /**
     *
     * 留言弹框  查询反馈单位
     * */
    public static String GetChannels_Setion(){
        return String.format("api/Channels_Section/GetPageData");
    }
    /**
     *
     * 获取TV直播列表
     * */
    public static String GetTv_LIVE(){
        return String.format("api/Channels/GetPageData");
    }
//    /**
//     *
//     * 获取直播间列表
//     * */
//    public static String GetLiving(){
//        return String.format("api/LiveRoom/GetPageData");
//    }
    /**
     *
     * 获取 直播间获取实时封面
     * */
    public static String GetLivingCover(){
        return String.format("api/LiveRoom/GetLiveCoverInfo");
    }
    /**
     *
     * 获取 直播间人数
     * */
    public static String GetLivingPeople(){
        return String.format("api/Srs/getLiveRoomUserCount");
    }
    /**
     *
     * 获取点播列表
     * */
    public static String GetDemandList(){
        return String.format("api/VideoDemandListView/GetPageData");
    }
    /**
     *
     * 获取剧情详情列表
     * */
    public static String GetDemandDetailsList(){
        return String.format("api/VideoDemand_Source/GetAllVideoDemand/");
    }
    /**
     *
     * 获取该节目的预约日期
     * */
    public static String GetCannelDate(){
        return String.format("api/Program_List/GetProgramDate/");
    }
    /**
     *
     * 获取该节目的某天的节目单
     * */
    public static String GetCannelYuyueProgerssList(){
        return String.format("api/Program_List/GetProgramList/");
    }
    /**
     *
     * 添加预约
     * */
    public static String ADDYuyue(){
        return String.format("api/Sys_UserReserve/AddReserve");
    }
    /**
     *
     * 删除预约
     * */
    public static String DeleteYuyue(){
        return String.format("api/Sys_UserReserve/DeleteReserve");
    }
    /**
     *
     * 留言上传
     * */
    public static String UpLeavseMsg(){
        return String.format("api/Livelihood_Feedback/Upload");
    }

}
