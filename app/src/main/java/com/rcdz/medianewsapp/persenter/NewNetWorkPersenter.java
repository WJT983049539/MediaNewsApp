package com.rcdz.medianewsapp.persenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.call.CustomStringCallback;
import com.rcdz.medianewsapp.call.JsonCallback;
import com.rcdz.medianewsapp.model.bean.AppVersionBean;
import com.rcdz.medianewsapp.model.bean.BannerInfoBean;
import com.rcdz.medianewsapp.model.bean.BaseBean;
import com.rcdz.medianewsapp.model.bean.CannalSationBean;
import com.rcdz.medianewsapp.model.bean.CannelProgeressDateListBean;
import com.rcdz.medianewsapp.model.bean.CollectListInfoBean;
import com.rcdz.medianewsapp.model.bean.CommentInfoBean;
import com.rcdz.medianewsapp.model.bean.DemandEpisodeBean;
import com.rcdz.medianewsapp.model.bean.DemandListBean;
import com.rcdz.medianewsapp.model.bean.DepartmnetInfoBean;
import com.rcdz.medianewsapp.model.bean.FeedbackBean;
import com.rcdz.medianewsapp.model.bean.HistoryListInfoBean;
import com.rcdz.medianewsapp.model.bean.JiFenLogBean;
import com.rcdz.medianewsapp.model.bean.JifenType;
import com.rcdz.medianewsapp.model.bean.LeaveMegBean;
import com.rcdz.medianewsapp.model.bean.LiveBean;
import com.rcdz.medianewsapp.model.bean.LiveCoverInfo;
import com.rcdz.medianewsapp.model.bean.LoginBean;
import com.rcdz.medianewsapp.model.bean.MessageDetalInfoBean;
import com.rcdz.medianewsapp.model.bean.MuhuNewBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.NoSetionsBean;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.model.bean.TopNewsInfo;
import com.rcdz.medianewsapp.model.bean.TopVideoNewBean;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.model.bean.YuYueInfoBean;
import com.rcdz.medianewsapp.model.bean.YuYueProgresListInfoBean;
import com.rcdz.medianewsapp.model.bean.wherebean;
import com.rcdz.medianewsapp.model.bean.wherebean2;
import com.rcdz.medianewsapp.persenter.interfaces.AddCollect;
import com.rcdz.medianewsapp.persenter.interfaces.AddReserve;
import com.rcdz.medianewsapp.persenter.interfaces.CheckAppVersion;
import com.rcdz.medianewsapp.persenter.interfaces.Commentimpl;
import com.rcdz.medianewsapp.persenter.interfaces.DeleteYuyue;
import com.rcdz.medianewsapp.persenter.interfaces.DisCollect;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.persenter.interfaces.GetBanner;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelDataInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelSetion;
import com.rcdz.medianewsapp.persenter.interfaces.GetCollectList;
import com.rcdz.medianewsapp.persenter.interfaces.GetComment;
import com.rcdz.medianewsapp.persenter.interfaces.GetCoverInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandJiNumDetails;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandList;
import com.rcdz.medianewsapp.persenter.interfaces.GetDepartmentInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetDetailMessage;
import com.rcdz.medianewsapp.persenter.interfaces.GetForGet;
import com.rcdz.medianewsapp.persenter.interfaces.GetHistory;
import com.rcdz.medianewsapp.persenter.interfaces.GetJifenList;
import com.rcdz.medianewsapp.persenter.interfaces.GetLiveListInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetLivingMInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetMoHuNewTitle;
import com.rcdz.medianewsapp.persenter.interfaces.GetNoSationList;
import com.rcdz.medianewsapp.persenter.interfaces.GetPhoneCode;
import com.rcdz.medianewsapp.persenter.interfaces.GetPliveLeaveMsgInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetProgerssListInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetSignStatus;
import com.rcdz.medianewsapp.persenter.interfaces.GetTopNews;
import com.rcdz.medianewsapp.persenter.interfaces.GetTopVideoNews;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserSetion;
import com.rcdz.medianewsapp.persenter.interfaces.GetYuyue;
import com.rcdz.medianewsapp.persenter.interfaces.IshowLogin;
import com.rcdz.medianewsapp.persenter.interfaces.IshowSearchOrganization;
import com.rcdz.medianewsapp.persenter.interfaces.ShowRegister;
import com.rcdz.medianewsapp.persenter.interfaces.UpFile;
import com.rcdz.medianewsapp.persenter.interfaces.YJRequestSuccess;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.GsonUtil;

import org.json.JSONArray;
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
     * 忘记密码
     * @param phone1
     */
    public void RequestGorgetPsd(String phone1, String ValidateCode,String NewPwd,GetForGet getForGet) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("PhoneNo",phone1);
        areaMap.put("ValidateCode",ValidateCode);
        areaMap.put("NewPwd",NewPwd);
            CommApi.postNoToken(LoginApi.ForgetPsd(),areaMap).execute(new JsonCallback<BaseBean>() {
                //{"status":false,"code":403,"message":"修改密码失败!","data":null}
                @Override
                public void onSuccess(Response<BaseBean> response) {
                    BaseBean baseBean = response.body();
                    Log.i(TAG,"忘记密码-->"+baseBean.toString());
                    getForGet.forget(response.body());
                }

                @Override
                public void onError(Response response) {
                    super.onError(response);
                    Log.i(TAG,"忘记密码失败-->");
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
        CommApi.postNoToken(LoginApi.AppLogin(),areaMap).execute(new JsonCallback<LoginBean>() {
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
        CommApi.postNoToken(LoginApi.getLoginForMes(),areaMap).execute(new JsonCallback<LoginBean>() {
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
        CommApi.postNoToken(LoginApi.AppRegister(),areaMap).execute(new JsonCallback<BaseBean>() {
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
     * 获取全部新闻列表
     */
    public void GetNewsList(GetAllNewsList getAllNewsList) {


        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","30");
        areaMap.put("sort","PublishDate");
        areaMap.put("order","desc");
        areaMap.put("wheres","[]");
        wherebean wherebean=new wherebean();
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
     * 获取轮播图
     */
    public void GetNewsBanner(GetBanner getBanner) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","10");
        areaMap.put("sort","Orders");
        areaMap.put("order","desc");
        areaMap.put("wheres","[]");
        CommApi.post("api/RepeatImages/GetPageList",areaMap).execute(new JsonCallback<BannerInfoBean>() {
            @Override
            public void onSuccess(Response<BannerInfoBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"获取轮播图-->"+response.body().toString());
                    if(getBanner!=null){
                        getBanner.getbanner(response.body());
                    }
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取轮播图失败-->"+response.message());
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
        List<wherebean2> list=new ArrayList<wherebean2>();
        wherebean2 w=new wherebean2();
        w.setName("SectionId");
        w.setValue(plate);
        list.add(w);
        String Where=GsonUtil.BeanToJson(list);
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
     * 获取置顶新闻
     */
    public void GetTopNews(GetTopNews getTopNews) {

        CommApi.postNoParams("api/NewsView/GetTopNews/3").execute(new JsonCallback<TopNewsInfo>() {
            @Override
            public void onSuccess(Response<TopNewsInfo> response) {
                Log.i(TAG,"获取置顶新闻-->"+response.toString());
                if(response.body()!=null){
                    getTopNews.getTopNews(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取置顶新闻失败-->"+response.message());
            }
        });
    }
    /**
     * 获取评论
     */
    public void GeCommentList(String TargetId,GetComment getComment) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","100");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        List<wherebean>list=new ArrayList<>();
        wherebean w=new wherebean();
        w.setName("Type");
        w.setValue("5");
        w.setDisplayType("text");
        wherebean w2=new wherebean();
        w2.setName("Mode");
        w2.setValue("1");
        w2.setDisplayType("text");
        wherebean w3=new wherebean();
        w3.setName("TargetId");
        w3.setValue(TargetId);
        w3.setDisplayType("text");
        list.add(w);
        list.add(w2);
        list.add(w3);
        String Where=GsonUtil.BeanToJson(list);
        areaMap.put("wheres",Where);
        CommApi.post("api/Sys_UserCommentsView/GetPageList",areaMap).execute(new JsonCallback<CommentInfoBean>() {
            @Override
            public void onSuccess(Response<CommentInfoBean> response) {
                Log.i(TAG,"获取获取评论-->"+response.body().getRows());
                if(response.body()!=null){
                    getComment.getcomment(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取评论失败-->"+response.message());
            }
        });
    }



//    /**
//     * 添加收藏
//     * 收藏表中的字段：SourceType:来源类别，如精品点播等(1 文章 2 视频 3 图集 4 频道 5剧集点播),是哪里的收藏就传几即可
//     */
//    public void AddCollect(String type,String TargetId,String Title,String Url,String SourceType,String ActivityTyppe,String GlobalSectionId,String SetionName,AddCollect addCollect) {
//        Map<String,String> areaMap = new HashMap<String,String>();
//        areaMap.put("Type",type);
//        areaMap.put("TargetId",TargetId);
//        areaMap.put("Title",Title);
//        areaMap.put("Url",Url);
//        areaMap.put("SourceType",SourceType);
//        areaMap.put("ActivityTyppe",ActivityTyppe);
//        areaMap.put("GlobalSectionId",GlobalSectionId);
//        areaMap.put("SetionName",SetionName);
//
//        CommApi.post("api/Sys_UserStores/add",areaMap).execute(new StringCallback() {
//            @Override
//            public void onSuccess(Response<String> response) {
//                Log.i(TAG,"添加收藏-->"+response.body());
//                if(response.body()!=null){
//                    JSONObject jsonObject= null;
//                    try {
//                        jsonObject = new JSONObject(response.body());
//                        int code=jsonObject.getInt("code");
//                        String message=jsonObject.getString("message");
//                        if(code==200){
//                            addCollect.addcollect();
//                        }else{
//                            GlobalToast.show(message,Toast.LENGTH_LONG);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//
//                }
//            }
//
//            @Override
//            public void onError(Response response) {
//                super.onError(response);
//                Log.i(TAG,"添加收藏失败-->"+response.message());
//            }
//        });
//    }
    /**
     * 添加收藏
     * 收藏表中的字段：SourceType:来源类别，如精品点播等(1 文章 2 视频 3 图集 4 频道 5剧集点播),是哪里的收藏就传几即可
     */
    public void AddCollect(String Type,String TargetId,String Title,String Url,String SourceType,String ActivityType,String imageurl,AddCollect addCollect) {
        String parmes="";
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Type",Type);
            jsonObject.put("TargetId",TargetId);
            jsonObject.put("Title",Title);
            jsonObject.put("Url",Url);
            jsonObject.put("SourceType",SourceType);
            jsonObject.put("ActivityType",ActivityType);
            jsonObject.put("ImageUrl",imageurl);
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("mainData",jsonObject);
            parmes=jsonObject1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //{"status":true,"code":311,"message":"保存成功","data":null}
        CommApi.postAddJson("api/Sys_UserStores/Add",parmes).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i(TAG,"添加收藏-->"+response.body());
                if(response.body()!=null){
                    JSONObject jsonObject= null;
                    try {
                        jsonObject = new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        String message=jsonObject.getString("message");
                        if(code==311){
                            addCollect.addcollect();
                        }else{
                            GlobalToast.show(message,Toast.LENGTH_LONG);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加收藏失败-->"+response.message());
            }
        });
    }
    /**
     * 取消收藏收藏
     */
    public void DeleteCollect(String id, DisCollect disCollect) {
        Map<String,String> areaMap = new HashMap<String,String>();
        String json="";
        JsonArray jsonElements=new JsonArray();
        jsonElements.add(id);
        json=jsonElements.toString();

        CommApi.postAddJson("api/Sys_UserStores/Del",json).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i(TAG,"取消收藏收藏-->"+response.toString());
                if(response.body()!=null){
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        if(jsonObject.getInt("code")==317)
                            disCollect.disCollect();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"取消收藏收藏失败-->"+response.message());
            }
        });
    }
    /**
     * 获取置顶新闻
     */
    public void GetTopVideoNews(GetTopVideoNews getTopVideoNews) {
        CommApi.postNoParams("api/NewsView/GetTopVideo/1").execute(new JsonCallback<TopVideoNewBean>() {
            @Override
            public void onSuccess(Response<TopVideoNewBean> response) {
                Log.i(TAG,"获取置顶新闻-->"+response.toString());
                if(response.body()!=null){
                    getTopVideoNews.getTopNews(response.body());
                }
            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"获取置顶新闻失败-->"+response.message());
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
            wherebean w=new wherebean();
            w.setName("Title");
            w.setValue(text);
            w.setDisplayType("like");
            list.add(w);
            if(!IsAll){
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
    //{"status":true,"code":200,"message":"查询成功!","data":[{"id":1,"name":"今日闻喜"},{"id":2,"name":"推荐"},{"id":3,"name":"智慧党建"},{"id":4,"name":"桐乡文化"},{"id":8,"name":"大事记"},{"id":9,"name":"特色"},{"id":10,"name":"旅游"},{"id":13,"name":"新时代好青年"},{"id":14,"name":"人物"},{"id":15,"name":"读中国"},{"id":17,"name":"要闻资讯"},{"id":20,"name":"视频新闻"}]}
    public void GetUserSetion(GetUserSetion getUserSetion) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","100");
        areaMap.put("sort","OrderNum");
        areaMap.put("order","asc");
        CommApi.post(MainApi.getUserSection(),areaMap).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"获取用户版块-->"+response.toString());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        String message=jsonObject.getString("message");
                        if(code==200){
                            List<SetionBean.DataBean> list=new ArrayList<>();
                            JSONArray jsonElements= jsonObject.getJSONArray("data");
                            for(int i=0;i<jsonElements.length();i++){
                                JSONObject jj=jsonElements.getJSONObject(i);
                                String name=jj.getString("name");
                                int id=jj.getInt("id");
                                SetionBean.DataBean dataBean=new SetionBean.DataBean();
                                dataBean.setName(name);
                                dataBean.setId(id);
                                list.add(dataBean);
                            }
                            getUserSetion.getUserSetion(list);


                        }else{
                            GlobalToast.show(message,Toast.LENGTH_LONG);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
     * 获取用户版块未登录状态
     */
    public void GetUserSetionasNoLogin(GetUserSetion getUserSetion) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","100");
        areaMap.put("sort","OrderNum");
        areaMap.put("order","asc");
        CommApi.post(MainApi.getNoLoginUserSection(),areaMap).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"获取用户版块-->"+response.toString());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        String message=jsonObject.getString("message");
                        if(code==200){
                            List<SetionBean.DataBean> list=new ArrayList<>();
                            JSONArray jsonElements= jsonObject.getJSONArray("rows");
                            for(int i=0;i<jsonElements.length();i++){
                                JSONObject jj=jsonElements.getJSONObject(i);
                                String name=jj.getString("Name");
                                int id=jj.getInt("Id");
                                SetionBean.DataBean dataBean=new SetionBean.DataBean();
                                dataBean.setName(name);
                                dataBean.setId(id);
                                list.add(dataBean);
                            }
                            getUserSetion.getUserSetion(list);


                        }else{
                            GlobalToast.show(message,Toast.LENGTH_LONG);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
     * 1 模糊查询 0查全部
     */
    public void GetDepartmentInfo(GetDepartmentInfo getDepartmentInfo,String type,String content) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("name",content);
        CommApi.post(MainApi.Department()+type,areaMap).execute(new JsonCallback<DepartmnetInfoBean>() {
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
    public void GetDepartmentInfoForid(String id , GetDetailMessage getDetailMessage) {
        CommApi.postNoParams(MainApi.LeaveMegDetailedInfo()+"/"+id).execute(new JsonCallback<MessageDetalInfoBean>() {
            //{"status":true,"code":200,"message":"查询成功!","data":{"id":37,"subject":"64646+","contents":"ceshi ","images":"Upload/Files/Livelihood_Feedback/2bf663251fdd42528b535fbc949a794d/small/0.41669806662035924.jpg","phoneNo":"6546","organizationId":45,"organizationName":"职能机构","type":"投诉","userTrueName":null,"auditUserId":null,"auditDate":null,"description":null,"isReply":0,"replyUserId":null,"replyContents":null,"replyDate":null,"time":null,"createID":16,"creator":"eer","createDate":"2020-11-03 19:59:26","isBlackList":0,"state":"0"}}
            @Override
            public void onSuccess(Response<MessageDetalInfoBean> response) {
                if(response.body()!=null){
                    if(getDetailMessage!=null){
                        getDetailMessage.getDetailMessage(response.body());
                    }
                    Log.i(TAG,"留言详情-->"+response.message());
                }

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
    public void GetUserInfo(String userId, GetUserInfo getUserInfo) {
        String uri=MainApi.GetInfo();
        if(!userId.equals("")){
            uri=uri+"/"+userId;
        }
        //{"status":true,"code":200,"message":null,"data":{"status":true,"code":200,"message":null,"data":{"user_Id":16,"userName":"ccaaa","userTrueName":"eer","address":"address","phoneNo":"15935938255","email":null,"remark":"remake","gender":0,"roleName":null,"headImageUrl":"Upload/Files/a.jpg","createDate":"2020-10-13 08:48:43","isBlackList":1}}}
        CommApi.postNoParams(uri).execute(new JsonCallback<UserInfoBean>() {
            @Override
            public void onSuccess(Response<UserInfoBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"当前用户信息-->"+response.message());
                    ACache aCache=ACache.get(context);
                    aCache.put("userinfo",response.body());
                    if(getUserInfo!=null){
                        getUserInfo.getUserInfo(response.body());
                    }
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
     * 添加评论
     * @param Creator
     * @param Title
     * @param LongTitle
     * @param Contents
     * @param TargetId
     * @param GlobalSectionId
     * @param type 1文章 2 视频 3图集 4直播间 5点播 6频道
     */
    public void AddComment(String Creator,String Title,String LongTitle,String Contents,String TargetId,String GlobalSectionId,String type){
        String parmes="";
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Creator",Creator);
            jsonObject.put("Title",Title);
            jsonObject.put("LongTitle",LongTitle);
            jsonObject.put("Contents",Contents);
            jsonObject.put("TargetId",TargetId);
            jsonObject.put("GlobalSectionId",GlobalSectionId);
            jsonObject.put("Type",type);
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("mainData",jsonObject);
            parmes=jsonObject1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //{"status":true,"code":200,"message":null,"data":{"status":true,"code":200,"message":null,"data":{"user_Id":16,"userName":"ccaaa","userTrueName":"eer","address":"address","phoneNo":"15935938255","email":null,"remark":"remake","gender":0,"roleName":null,"headImageUrl":"Upload/Files/a.jpg","createDate":"2020-10-13 08:48:43","isBlackList":1}}}
        CommApi.postAddJson(MainApi.AddComment(),parmes).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //{"status":true,"code":311,"message":"保存成功","data":null}
                if(response.body()!=null){
                    Log.i(TAG,"添加评论-->"+response.message());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        String message=jsonObject.getString("message");
                        if(code==311){
                            Log.i("test","添加评论成功！");
                            GlobalToast.show(message,Toast.LENGTH_LONG);

                        }else{
                            GlobalToast.show(message,Toast.LENGTH_LONG);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加评论失败-->"+response.message());
            }
        });
    }
    /**
     * 意见反馈
     * ,品开
     */
    public void upYiJianBack(String Contents, String PhoneNo, String CoverUrl, YJRequestSuccess yjRequestSuccess) {
            String parmes="";
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Contents",Contents);
            jsonObject.put("PhoneNo",PhoneNo);
            jsonObject.put("CoverUrl",CoverUrl);
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("mainData",jsonObject);
            parmes=jsonObject1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //{"status":true,"code":200,"message":null,"data":{"status":true,"code":200,"message":null,"data":{"user_Id":16,"userName":"ccaaa","userTrueName":"eer","address":"address","phoneNo":"15935938255","email":null,"remark":"remake","gender":0,"roleName":null,"headImageUrl":"Upload/Files/a.jpg","createDate":"2020-10-13 08:48:43","isBlackList":1}}}
        CommApi.postAddJson(MainApi.YiJianYiBack(),parmes).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                //{"status":true,"code":311,"message":"保存成功","data":null}
                if(response.body()!=null){
                    Log.i(TAG,"意见反馈-->"+response.message());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        if(code==311){
                            if(yjRequestSuccess!=null){
                                yjRequestSuccess.yjquest();
                            }

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"意见反馈失败-->"+response.message());
            }
        });
    }
    /**
     * 历史足迹
     * ,品开
     */
    public void AddHistoryforNews(String Type, String TargetId, String SectionId,String ActivityType) {
        //{"status":true,"code":200,"message":null,"data":{"status":true,"code":200,"message":null,"data":{"user_Id":16,"userName":"ccaaa","userTrueName":"eer","address":"address","phoneNo":"15935938255","email":null,"remark":"remake","gender":0,"roleName":null,"headImageUrl":"Upload/Files/a.jpg","createDate":"2020-10-13 08:48:43","isBlackList":1}}}
        CommApi.postNoParams(MainApi.AddHistoryforNews()+Type+"/"+TargetId+"/"+SectionId+"/"+ActivityType).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
//                {"status":true,"code":200,"message":"添加成功!","data":1}
                if(response.body()!=null){
                    Log.i(TAG,"添加历史成功-->"+response.message());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加历史失败-->"+response.message());
            }
        });
    }
    /**
     * 获取主播信息
     */

    //todo
    public void GetLivingMasterInfo( String id,GetLivingMInfo getLivingMInfo ) {
        CommApi.postNoParams(MainApi.GetLivingMasterInfo()+"/"+id).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i(TAG,"当前主播信息-->"+response.message());
                if(response.body()!=null){
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        if(code==200){
                            JSONObject jsonObject1=jsonObject.getJSONObject("data");
                            String userTrueName= jsonObject1.getString("userTrueName");
                            String headImageUrl= jsonObject1.getString("headImageUrl");
                            getLivingMInfo.getinfo(userTrueName,headImageUrl);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


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
     * 查看收藏列表
     * title 搜索关键字
     */
    public void GetCollectList(GetCollectList getCollectList,String title,String page) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","50");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        if(title.equals("")){
            areaMap.put("where","[]");
        }else{
            List<wherebean> list=new ArrayList<>();
            wherebean w=new wherebean();
            w.setName("Title");
            w.setValue(title);
            w.setDisplayType("like");
            list.add(w);
            String as=GsonUtil.BeanToJson(list);
            areaMap.put("wheres",as);
        }
        CommApi.post("api/Sys_UserStores/getPageData",areaMap).execute(new JsonCallback<CollectListInfoBean>() {
            @Override
            public void onSuccess(Response<CollectListInfoBean> response) {
                Log.i(TAG,"收藏列表-->"+response.message());
                if(response.body()!=null){
                    getCollectList.getCollect(response.body());
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
     * 查看历史记录
     */
    public void GetHistoryList(GetHistory getHistory, String page) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        areaMap.put("where","[]");
        CommApi.post("api/Sys_UserFootMark/GetPageData",areaMap).execute(new JsonCallback<HistoryListInfoBean>() {
            @Override
            public void onSuccess(Response<HistoryListInfoBean> response) {
                Log.i(TAG,"历史记录-->"+response.message());
                if(response.body()!=null){
                    getHistory.getHistory(response.body());
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"历史记录失败-->"+response.message());
            }
        });
    }
    /**
     * 查看评论
     */
    public void GetCommentList(GetComment getComment, String page) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("rows","30");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        areaMap.put("where","[]");

        CommApi.post("api/Sys_UserCommentsView/GetPageData",areaMap).execute(new JsonCallback<CommentInfoBean>() {
            @Override
            public void onSuccess(Response<CommentInfoBean> response) {
                Log.i(TAG,"评论-->"+response.message());
                if(response.body()!=null){
                    getComment.getcomment(response.body());
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"评论失败-->"+response.message());
            }
        });
    }
    /**
     * 查看预约记录
     */
    public void GetYuyueList(GetYuyue getYuyue) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page","1");
        areaMap.put("rows","60");
        areaMap.put("sort","Id");
        areaMap.put("order","desc");
        areaMap.put("where","[]");

        CommApi.post("api/Sys_UserReserve/GetMyReserve",areaMap).execute(new JsonCallback<YuYueInfoBean>() {
            @Override
            public void onSuccess(Response<YuYueInfoBean> response) {
                Log.i(TAG,"预约记录-->"+response.message());
                if(response.body()!=null){
                    getYuyue.getyuyue(response.body());
                }

            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"预约记录失败-->"+response.message());
            }
        });
    }
    /**
     *  添加留言
     */
    public void AddLeaveMessage(LeaveMegBean leaveMegBean , Commentimpl commentimpl) {
        String gson=GsonUtil.BeanToJson(leaveMegBean);
        OkGo.<String>post(AppConfig.BASE_URL+MainApi.AddLeaveMessag()).headers("Authorization", "Bearer " + Constant.token).upJson(gson).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"留言成功-->"+response.message());
                    commentimpl.getInfo(response.body());
                }

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i(TAG,"留言失败-->"+response.message());
            }
        });

    }
    /**
     *  积分列表
     */
    public void jifenList(  String page,GetJifenList getJifenList ) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("page",page);
        areaMap.put("sort","Id");
        areaMap.put("rows","30");
        areaMap.put("order","desc");
        List<wherebean> list=new ArrayList<>();
        wherebean w=new wherebean();
        w.setName("Mode");
        w.setValue("1");
        list.add(w);
        String as=GsonUtil.BeanToJson(list);
        areaMap.put("wheres",as);
        CommApi.post("api/Sys_UserScore/getPageData",areaMap).execute(new JsonCallback<JiFenLogBean>() {
            @Override
            public void onSuccess(Response<JiFenLogBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"积分列表-->"+response.message());
                    getJifenList.getJifenNum(response.body());
                }

            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"积分列表失败-->"+response.message());
            }
        });
    }
    /**
     *  添加积分
     */
    public void AddJifen( String Type ) {
        JifenType jifenType=new JifenType();
        JifenType.MainDataBean mainDataBean=new   JifenType.MainDataBean();
        mainDataBean.setType(Type);
        jifenType.setMainData(mainDataBean);
       String sshabiwanyi= GsonUtil.BeanToJson(jifenType);
        CommApi.postAddJson("api/Sys_UserScore/Add",sshabiwanyi).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"添加积分-->"+response.message());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        if(code==200){
                            GlobalToast.show("添加积分成功",5000);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加积分失败-->"+response.message());
            }
        });
    }


    /**
     *  添加积分
     */
    public void AddSignJifen(String Type, GetSignStatus getSignStatus) {

        JifenType jifenType=new JifenType();
        JifenType.MainDataBean mainDataBean=new   JifenType.MainDataBean();
        mainDataBean.setType(Type);
        jifenType.setMainData(mainDataBean);
        String sshabiwanyi= GsonUtil.BeanToJson(jifenType);
        CommApi.postAddJson("api/Sys_UserScore/Add",sshabiwanyi).execute(new CustomStringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                if(response.body()!=null){
                    Log.i(TAG,"添加积分-->"+response.message());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        String message=jsonObject.getString("message");
                        if(code==200){
                            getSignStatus.ShowSignStatus(true);
                        }else{
                            GlobalToast.show(message,Toast.LENGTH_LONG);
                            getSignStatus.ShowSignStatus(false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加积分失败-->"+response.message());
                getSignStatus.ShowSignStatus(false);
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
                    Log.i(TAG,"频道栏目列表-->"+response.body().toString());
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
                    Log.i(TAG,"直播间列表-->"+response.body().getTotal());
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
        w.setValue(ChannelSectionId);
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
     * 获取点播列表集数
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
                    Log.i(TAG,"精品集数-->"+response.body().getData().size());
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
    /**
     * 根据频道编号查询节目日期
     */
    public void GetCannelDateInfo(String Id, GetCannelDataInfo getCannelDataInfo) {

        CommApi.postNoParams(MainApi.GetCannelDate()+Id).execute(new JsonCallback<CannelProgeressDateListBean>() {
            @Override
            public void onSuccess(Response<CannelProgeressDateListBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"请求频道日期成功-->");
                    getCannelDataInfo.getCannelData(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"请求频道日期失败-->"+response.message());
            }
        });
    }
    /**
     * 根据日期和频道查询节目列表和我的预约信息
     */
    public void GetProgressListForDate(String Id, String date,GetProgerssListInfo GetProgerssListInfo) {

        CommApi.postNoParams(MainApi.GetCannelYuyueProgerssList()+date+"/"+Id).execute(new JsonCallback<YuYueProgresListInfoBean>() {
            @Override
            public void onSuccess(Response<YuYueProgresListInfoBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"请求频道节目列表成功-->");
                    GetProgerssListInfo.getProgressList(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"请求频道节目列表成功失败-->"+response.message());
            }
        });
    }
    /**
     * 预约
     */
    public void AddYuYue(String ProgramId,String ChannelId, String ProgramTime,String StartTime,String Name,String  LiveUrl, String registration_id,AddReserve addReserve) {
        Map<String,String> areaMap = new HashMap<String,String>();
        areaMap.put("ProgramId",ProgramId);
        areaMap.put("ChannelId",ChannelId);
        areaMap.put("ProgramTime",ProgramTime);
        areaMap.put("StartTime",StartTime);
        areaMap.put("Name",Name);
        areaMap.put("LiveUrl",LiveUrl);
        areaMap.put("registration_id",registration_id);
        areaMap.put("Platform","android");

        CommApi.post(MainApi.ADDYuyue(),areaMap).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"添加预约成功-->");
                    addReserve.addrever();
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"添加预约失败-->"+response.message());
            }
        });
    }
    /**
     * 删除预约
     */
    public void DeleteYuYue(String ProgramId,String ScheduleId, DeleteYuyue deleteYuyue) {

        CommApi.postNoParams(MainApi.DeleteYuyue()+"/"+ProgramId+"/"+ScheduleId).execute(new StringCallback() {
            //"{\"status\":true,\"code\":317,\"message\":\"删除成功\",\"data\":null}"
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i(TAG,"删除预约成功-->");
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        if(jsonObject.getInt("code")==317)
                            deleteYuyue.DeleteYuyue();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"删除预约失败-->"+response.message());
            }
        });
    }

    /**
     * 查询APP主版本
     */
    public void CheckAppVersion(CheckAppVersion CheckAppVersion) {

        CommApi.postNoParams(MainApi.GetAppVersionInfo()+"/"+0).execute(new JsonCallback<AppVersionBean>() {
            @Override
            public void onSuccess(Response<AppVersionBean> response) {
                if(response.body()!=null){
                    Log.i(TAG,"查询APP主版本成功-->");
                    CheckAppVersion.GetAppVersion(response.body());
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i(TAG,"查询APP主版本失败-->"+response.message());
            }
        });
    }

    //上传留言图片
    public void UppictureMessage(List<File> files, UpFile upFile) {
        for(File file:files){
            if (!file.exists()) {
                GlobalToast.show("文件不存在！", Toast.LENGTH_LONG);
                return;
            }
        }
        String web_path = AppConfig.BASE_URL+MainApi.UpLeavseMsg();
        OkGo.<String>post(web_path).headers("Authorization", "Bearer " + Constant.token).addFileParams("fileInput",files)
                .isMultipart(true).execute(new StringCallback() {
        //{"status":true,"code":200,"message":"文件上传成功","data":"Upload/Files/Livelihood_Feedback/3523cb85fcdc4d728b9194f4748fdb23/small"}
            @Override
            public void onSuccess(Response<String> response) {
                Log.i(TAG,"留言上传图片成功" + response.body());
                try {
                    JSONObject jsonObject=new JSONObject(response.body());
                    int code=jsonObject.getInt("code");
                    String data=jsonObject.getString("data");
                    if(code==200){
                        upFile.upfileSuccess(data);
                    }else{
                        upFile.upfileFail();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.i(TAG,"上传失败");
                upFile.upfileFail();
            }
        });

    }


}
