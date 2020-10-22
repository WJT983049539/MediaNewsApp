package com.rcdz.medianewsapp.persenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.bean.BaseBean;
import com.rcdz.medianewsapp.model.bean.CannalSationBean;
import com.rcdz.medianewsapp.model.bean.DemandEpisodeBean;
import com.rcdz.medianewsapp.model.bean.DemandListBean;
import com.rcdz.medianewsapp.model.bean.DepartmnetInfoBean;
import com.rcdz.medianewsapp.model.bean.FeedbackBean;
import com.rcdz.medianewsapp.model.bean.LeaveMegBean;
import com.rcdz.medianewsapp.model.bean.LiveBean;
import com.rcdz.medianewsapp.model.bean.LiveCoverInfo;
import com.rcdz.medianewsapp.model.bean.LivingMasterBean;
import com.rcdz.medianewsapp.model.bean.LoginBean;
import com.rcdz.medianewsapp.model.bean.MuhuNewBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.NoSetionsBean;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.model.bean.wherebean;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelSetion;
import com.rcdz.medianewsapp.persenter.interfaces.GetCoverInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandJiNumDetails;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandList;
import com.rcdz.medianewsapp.persenter.interfaces.GetDepartmentInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetLiveListInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetLivingMInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetMoHuNewTitle;
import com.rcdz.medianewsapp.persenter.interfaces.GetNoSationList;
import com.rcdz.medianewsapp.persenter.interfaces.GetPhoneCode;
import com.rcdz.medianewsapp.persenter.interfaces.GetPliveLeaveMsgInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserSetion;
import com.rcdz.medianewsapp.persenter.interfaces.IshowLogin;
import com.rcdz.medianewsapp.persenter.interfaces.IshowSearchOrganization;
import com.rcdz.medianewsapp.persenter.interfaces.ShowRegister;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.GsonUtil;
import com.rcdz.medianewsapp.view.fragment.OrganizationListFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/12 17:04
 */
public class NewNetWorkPersenter {
    public final static String TAG="NewNetWorkPersenter";
    public NewNetWorkPersenter(Context context) {
        this.context = context;
    }
    private Context context;

    /**
     * 获取验证码
     * @param phone1
     */
    public void GetPhoneCode(String phone1, GetPhoneCode getPhoneCode) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("phoneNo",phone1);
            CommApi.post(LoginApi.getCheckCodeUrl(),areaMap).execute(new JsonCallback<BaseBean>() {
                @Override
                public void onSuccess(Response<BaseBean> response) {
                    BaseBean baseBean = response.body();
                    Log.i(TAG,"获取验证码-->"+baseBean.toString());
                    getPhoneCode.getPhoneCode(baseBean);
                }

                @Override
                public void onError(Response response) {
                    super.onError(response);
                    Log.i(TAG,"获取验证码失败-->");
                }
            });
    }
    /**
     * 密码登录
     */
    public void AppLogin(String userName,String passWord, IshowLogin login) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("userName",userName);
        areaMap.put("passWord",passWord);
        areaMap.put("verificationCode","string");
        areaMap.put("uuid","string");
        CommApi.post(LoginApi.AppLogin(),areaMap).execute(new JsonCallback<LoginBean>() {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                Log.i(TAG,"密码登录-->"+response.toString());
                login.ishowLogin(response.body());
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"密码登录-->"+response.message());
            }
        });
    }
    /**
     * app验证码登录
     */
    public void AppLoginForMes(String phone, String validateCode, IshowLogin login) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("phone",phone);
        areaMap.put("validateCode",validateCode);
        areaMap.put("isApp","1");
        CommApi.post(LoginApi.getLoginForMes(),areaMap).execute(new JsonCallback<LoginBean>() {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                Log.i(TAG,"验证码登录"+response.body().toString());
                login.ishowLogin(response.body());

            }

            @Override
            public void onError(Response<LoginBean> response) {
                Log.i(TAG,"验证码登录失败");
                super.onError(response);
            }
        });
    }

    /**
     * app注册
     * @param userName
     * @param phoneNo
     * @param validateCode
     * @param userPwd
     */
    public void AppRegister(String userName, String phoneNo, String validateCode, String userPwd, ShowRegister showRegister) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("userName",userName);
        areaMap.put("phoneNo",phoneNo);
        areaMap.put("validateCode",validateCode);
        areaMap.put("userPwd",userPwd);
        areaMap.put("appType","1");
        CommApi.post(LoginApi.AppRegister(),areaMap).execute(new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
            //{"status":true,"code":200,"message":"注册成功！","data":null}
                    Log.i("注册",response.body().toString());
                showRegister.showRegister(response.body());
            }
            @Override
            public void onError(Response<BaseBean> response)
            {
                super.onError(response);
                Log.i("注册","注册请求失败"+response.body());
            }
        });
    }
    /**
     * 更换token
     */
    public void ReplaceToken() {
        Map<String,String> areaMap = new HashMap<String,String>();
        CommApi.postNoParams(LoginApi.ReplaceToken()).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

    /**
     * 获取新闻列表
     */
    public void GetNewsList(GetAllNewsList getAllNewsList) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","30");
        areaMap.put("sort","PublishDate");
        areaMap.put("order","desc");
        areaMap.put("wheres","[]");
        CommApi.post(MainApi.getNewsListUrl(),areaMap).execute(new JsonCallback<NewsListBean>() {
            @Override
            public void onSuccess(Response<NewsListBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"新闻列表-->"+response.body().toString());
                    getAllNewsList.getAllNewsList(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"新闻列表-->"+response.message());
            }
        });
    }
    /**
     * 获取新闻列表
     * 条件 "[{\"name\":\"SectionId\",\"value\":2,\"displayType\":\"text\"}]"
     */
    public void GetNewsList(GetAllNewsList getAllNewsList,String plate,String page) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("sort","PublishDate");
        areaMap.put("order","desc");
        wherebean w=new wherebean();
        w.setName("SectionId");
        w.setValue(plate);
        w.setDisplayType("text");
        String Where=GsonUtil.BeanToJson(w);
        areaMap.put("wheres",Where);
        CommApi.post(MainApi.getNewsListUrl(),areaMap).execute(new JsonCallback<NewsListBean>() {
            @Override
            public void onSuccess(Response<NewsListBean> response) {
                Log.i(TAG,"新闻列表-->"+response.toString());
                if(response.body()!=null){
                    getAllNewsList.getAllNewsList(response.body());
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"新闻列表-->"+response.message());
            }
        });
    }

    /**
     * 搜索新闻列表
     * IsAll true 全部  false 视频
     */
        public void NewSearch(String text,String page,Boolean IsAll,GetAllNewsList getAllNewsList) {

            Map<String,String> areaMap = new HashMap<String,String>();
            areaMap.put("page",page);
            areaMap.put("rows","30");
            areaMap.put("sort","PublishDate");
            areaMap.put("order","desc");
            List<wherebean> list=new ArrayList<>();
            if(IsAll){

                wherebean w=new wherebean();
                w.setName("Title");
                w.setValue(text);
                w.setDisplayType("like");
                list.add(w);
            }else{
                wherebean w2=new wherebean();
                w2.setName("Type");
                w2.setValue("2");
                w2.setDisplayType("text");
                list.add(w2);
            }


            String result=GsonUtil.BeanToJson(list);
            areaMap.put("wheres",result);
          CommApi.post(MainApi.getNewsListUrl(),areaMap).execute(new JsonCallback<NewsListBean>() {
            @Override
            public void onSuccess(Response<NewsListBean> response) {
                if(response.body()!=null){
                    getAllNewsList.getAllNewsList(response.body());
                    Log.i(TAG,"搜索新闻->"+response.toString());

                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"搜索新闻失败-->"+response.message());
            }
        });
    }
    /**
     * 模糊搜索
     * 只显示标题
     */
        public void MohuNewSearch(String text, GetMoHuNewTitle getMoHuNewTitle) {

            Map<String,String> areaMap = new HashMap<String,String>();
            areaMap.put("page","1");
            areaMap.put("rows","30");
            areaMap.put("sort","PublishDate");
            areaMap.put("order","desc");
            List<wherebean> list=new ArrayList<>();
            wherebean w=new wherebean();
            w.setName("Title");
            w.setValue(text);
            w.setDisplayType("like");
            list.add(w);
            String result=GsonUtil.BeanToJson(list);
            areaMap.put("wheres",result);
          CommApi.post(MainApi.newsSearch(),areaMap).execute(new JsonCallback<MuhuNewBean>() {
            @Override
            public void onSuccess(Response<MuhuNewBean> response) {
                if(response.body()!=null){
                    MuhuNewBean muhuNewBean= response.body();
                    getMoHuNewTitle.getMohuNewTitle(muhuNewBean.getRows());
                    Log.i(TAG,"模糊搜索新闻->"+response.toString());


                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"模糊搜索新闻失败-->"+response.message());
            }
        });
    }

    /**
     * 获取用户版块
     */
    public void GetUserSetion(GetUserSetion getUserSetion) {
        CommApi.postNoParams(MainApi.getUserSection()).execute(new JsonCallback<SetionBean>() {
            @Override
            public void onSuccess(Response<SetionBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"获取用户版块-->"+response.toString());
                    getUserSetion.getUserSetion(response.body());
                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取用户版块失败-->"+response.message());
            }
        });
    }
    /**
     * 获取用户自己取消的版块
     */
        public void getNoUserSetions( String page, GetNoSationList getNoSationList) {

            Map<String,String> areaMap = new HashMap<String,String>();
            areaMap.put("page",page);
            areaMap.put("rows","30");
            areaMap.put("sort","Id");
            areaMap.put("order","desc");
            areaMap.put("wheres","[]");
          CommApi.post(MainApi.getNoUserSection(),areaMap).execute(new JsonCallback<NoSetionsBean>() {
            //{"code":200,"message":null,"status":0,"msg":null,"total":1,"rows":[{"Id":204,"SectionId":8,"SectionName":"大事记","CreateID":16,"Creator":"测试7","CreateDate":"2020-10-16 16:19:12"}],"summary":null,"extra":null}
            @Override
            public void onSuccess(Response<NoSetionsBean> response) {
                if(response.body()!=null){
                    getNoSationList.getNoSationList(response.body());
                    Log.i(TAG,"获取用户自己取消的版块->"+response.toString());

                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取用户自己取消的版块失败-->"+response.message());
            }
        });
    }
    /**
     *
     * 用户修改个人版块
     */
    public void UpdateUserSections(String SectionName) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("SectionName",SectionName);
        CommApi.post(MainApi.UpdateUserSections(),areaMap).execute(new CustomStringCallback() {
            //{"status":true,"code":200,"message":"修改用户个人版块成功!","data":1}
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){

                    Log.i(TAG,"修改个人版块->"+response.toString());

                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"修改个人版块失败-->"+response.message());
            }
        });
    }
    /**
     *
     * 民生投诉留言
     */
    public void GetPLiveLeaveInfo(String page, String Type, GetPliveLeaveMsgInfo getPliveLeaveMsgInfo) {

        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        List<wherebean> list=new ArrayList<>();
        wherebean w=new wherebean();
        w.setName("Type");
        w.setValue(Type); //Type=0：热点诉求、Type=1：我的留言
        w.setDisplayType("text");
        list.add(w);
        String result=GsonUtil.BeanToJson(list);
        areaMap.put("wheres",result);
        CommApi.post(MainApi.PliveData(),areaMap).execute(new JsonCallback<PliveLeaveInfo>() {
            //{"status":true,"code":200,"message":"修改用户个人版块成功!","data":1}
            @Override
            public void onSuccess(Response<PliveLeaveInfo> response) {
                if(response.body()!=null){
                    getPliveLeaveMsgInfo.getPliveLeaveMsgInfo(response.body());
                    Log.i(TAG,"民生投诉留言有->"+response.body().getRows().size()+"条");
                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"民生投诉留言失败-->"+response.message());
            }
        });
    }

    /**
     * 民生机构
     */
    public void GetDepartmentInfo(GetDepartmentInfo getDepartmentInfo ) {
        CommApi.postNoParams(MainApi.Department()).execute(new JsonCallback<DepartmnetInfoBean>() {
            @Override
            public void onSuccess(Response<DepartmnetInfoBean> response) {
                Log.i(TAG,"民生机构-->"+response.message());
                if(getDepartmentInfo!=null&&response.body()!=null){
                    getDepartmentInfo.getDepartmentinfo(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"民生机构失败-->"+response.message());
            }
        });
    }
    /**
     * 根据id查询留言详情
     */
    public void GetDepartmentInfoForid(String id ) {
        CommApi.postNoParams(MainApi.LeaveMegDetailedInfo()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                if(response.body()!=null){

                }
                Log.i(TAG,"留言详情-->"+response.message());
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"留言详情失败-->"+response.message());
            }
        });
    }
    /**
     * 查新当前用户信息
     */
    public void GetUserInfo( ) {
        CommApi.postNoParams(MainApi.GetInfo()).execute(new JsonCallback<UserInfoBean>() {
            @Override
            public void onSuccess(Response<UserInfoBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"当前用户信息-->"+response.message());
                    ACache aCache=ACache.get(context);
                    UserInfoBean userInfoBean=response.body();
                    aCache.put("userinfo",userInfoBean);
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"当前用户信息失败-->"+response.message());
            }
        });
    }
    /**
     * 获取主播信息
     */
    public void GetLivingMasterInfo(String id,GetLivingMInfo getLivingMInfo ) {
        CommApi.postNoParams(MainApi.GetLivingMasterInfo()+"/"+id).execute(new JsonCallback<LivingMasterBean>() {
            @Override
            public void onSuccess(Response<LivingMasterBean> response) {
                Log.i(TAG,"当前主播信息-->"+response.message());
                if(response.body()!=null){
                    getLivingMInfo.getinfo(response.body());
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"主播信息失败-->"+response.message());
            }
        });
    }
    /**
     * 得到直播间列表预览图
     */
    public void GetLiveCoverInfo(String RoomIds , GetCoverInfo getCoverInfo) {
        SortedMap<String, String> param = new TreeMap<String, String>();
        param.put("RoomIds", RoomIds);
        CommApi.post(MainApi.GetLivingCover(),param).execute(new JsonCallback<LiveCoverInfo>() {
            @Override
            public void onSuccess(Response<LiveCoverInfo> response) {

                if(response.body()!=null){
                    getCoverInfo.getCoverInfo(response.body());
                }

                Log.i(TAG,"直播间列表预览图-->"+response.message());

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播间列表预览图失败-->"+response.message());
            }
        });
    }
    /**
     * 得到直播间人数
     */
    public void GetLiveNum( String RoomIds ) {
        SortedMap<String, String> param = new TreeMap<String, String>();
        param.put("roomId", RoomIds);
        CommApi.post(MainApi.GetLivingPeople(),param).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                if(response.body()!=null){
                    Log.i(TAG,"直播间人数-->"+response.message());
                }


            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播间人数失败-->"+response.message());
            }
        });
    }
    /**
     * 留言弹框  查询反馈单位
     */
    public void Search_Organization(IshowSearchOrganization ishowSearchOrganization) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","30");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        areaMap.put("where","[]");
        CommApi.post(MainApi.GetOrganization(),areaMap).execute(new JsonCallback<FeedbackBean>() {
            @Override
            public void onSuccess(Response<FeedbackBean> response) {
                Log.i(TAG,"反馈单位-->"+response.message());
                if(response.body()!=null){
                    ishowSearchOrganization.ishowSearchOrganization(response.body());
                }


            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"反馈单位失败-->"+response.message());
            }
        });
    }
    /**
     *  添加留言
     */
    public void AddLeaveMessage(  LeaveMegBean leaveMegBean ) {
        String gson=GsonUtil.BeanToJson(leaveMegBean);
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("MainData",gson);
        CommApi.post(MainApi.AddLeaveMessag(),areaMap).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i(TAG,"添加留言-->"+response.message());
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加留言失败-->"+response.message());
            }
        });
    }

    /**
     * 获取频道栏目列表
     */
    public void GetChannelSations(GetCannelSetion getCannelSetion) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","50");
        areaMap.put("order","asc");
        areaMap.put("sort","Id");
        CommApi.post(MainApi.GetChannels_Setion(),areaMap).execute(new JsonCallback<CannalSationBean>() {
            @Override
            public void onSuccess(Response<CannalSationBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"频道栏目列表-->"+response.body().toString());
                    getCannelSetion.getCannel(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"频道栏目列表失败-->"+response.message());
            }
        });
    }
    /**
     * 获取Tv直播列表
     */
    public void GetTvLiveList(String page, GetCannelInfo getCannelInfo) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("order","desc");
        CommApi.post(MainApi.GetTv_LIVE(),areaMap).execute(new JsonCallback<TvCannelBean>() {
            @Override
            public void onSuccess(Response<TvCannelBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"频道栏目列表-->");
                    getCannelInfo.getCannelInfo(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播列表列表失败-->"+response.message());
            }
        });
    }
    /**
     * 获取直播间列表
     */
    public void GetLivingList(String page, GetLiveListInfo getLiveListInfo) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("order","desc");
        areaMap.put("sort","LiveState");
        CommApi.post(MainApi.GetLiving(),areaMap).execute(new JsonCallback<LiveBean>() {
            @Override
            public void onSuccess(Response<LiveBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"直播间列表-->");
                    getLiveListInfo.getLiveInfo(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播间列表失败-->"+response.message());
            }
        });
    }
    /**
     * 获取点播列表
     */
    public void GetDemandList(String page, String ChannelSectionId, GetDemandList getDemandList) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("order","desc");
        areaMap.put("sort","PublishDate");

        List<wherebean> list=new ArrayList<>();
        wherebean w=new wherebean();
        w.setName("ChannelSectionId");
//        w.setValue(ChannelSectionId);
        w.setValue("1");
        w.setDisplayType("text");
        list.add(w);
        String as=GsonUtil.BeanToJson(list);
        areaMap.put("wheres",as);

        CommApi.post(MainApi.GetDemandList(),areaMap).execute(new JsonCallback<DemandListBean>() {
            @Override
            public void onSuccess(Response<DemandListBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"精品列表-->"+response.body().getTotal());
                    getDemandList.getDemandList(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播间列表失败-->"+response.message());
            }
        });
    }
    /**
     * 获取点播列表详情
     */
    public void GetDemandDetails(String Id, GetDemandJiNumDetails getDemandJiNumDetails) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1"); //集数
        areaMap.put("rows","100");
        areaMap.put("sort","number");

        CommApi.post(MainApi.GetDemandDetailsList()+Id,areaMap).execute(new JsonCallback<DemandEpisodeBean>() {
            @Override
            public void onSuccess(Response<DemandEpisodeBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"精品列表-->"+response.body().getData().size());
//                    getDemandList.getDemandList(response.body());
                    getDemandJiNumDetails.getDemandJiNumDetails(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"直播间列表失败-->"+response.message());
            }
        });
    }

    //上传留言图片
//    public void UppictureMessage(String path, GetPhonePath getPhonePath, boolean isfinish) {
//        File file = new File(path);
//        if (!file.exists()) {
//            return;
//        }
//        String web_path = MainActivity.MG.IP_second_cloud + "api/Feedback/Uploads";
//        OkGo.<String>post(web_path).headers("Authorization", "Bearer " + Constant.token).params("files", file)
//                .isMultipart(true).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                MyLog.i("留言上传图片成功" + response.body());
//
//                try {
//                    JSONObject jsonObject1 = new JSONObject(response.body());
//                    int code = jsonObject1.getInt("code");
//                    if (code == 200) {
//                        String data = jsonObject1.getString("data");
//                        getPhonePath.getPhotoPath(data, isfinish);
//                    } else {
//                        MyLog.i("留言图片上传失败！");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Response<String> response) {
//                super.onError(response);
//                MyLog.i("上传失败");
//            }
//        });
//
//    }

}
