package com.rcdz.medianewsapp.view.activity;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;

/**
 * 作用:留言详情
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 9:24
 */
public class MessageDetailActivity extends BaseActivity{
    String id;//留言id
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void inintView() {
        //查询id
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetDepartmentInfoForid(id);
    }

    @Override
    public void inintData() {

    }
}
