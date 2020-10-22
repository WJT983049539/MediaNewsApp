package com.rcdz.medianewsapp.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.OrganizationAdapter;
import com.rcdz.medianewsapp.model.bean.DepartmnetInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetDepartmentInfo;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.customview.ClearEditText;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用: 部门机构
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 16:28
 */
public class OrganizationListFragment extends Fragment implements GetDepartmentInfo {
    View mRootView;
    @BindView(R.id.search_Org)
    ClearEditText searchOrg;
    @BindView(R.id.searchBtn)
    TextView searchBtn;
    @BindView(R.id.organizeListView)
    NRecyclerView mRecyclerView;
    private OrganizationAdapter organizationAdapter;
    private Activity mContext;
    public List<DepartmnetInfoBean.DepartmnetInfo> dataList = new ArrayList<DepartmnetInfoBean.DepartmnetInfo>();
    private int mPage = 1;
    private String organizationName="";

    public OrganizationListFragment() {
    }

    public static OrganizationListFragment newInstance() {
        OrganizationListFragment fragment = new OrganizationListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_department, container, false);
            ButterKnife.bind(this, mRootView);
            mContext = getActivity();
            //先清空
            dataList.clear();
            initView();
        }
        return mRootView;
    }

    private void initView() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetDepartmentInfo(this);
        organizationAdapter = new OrganizationAdapter(dataList, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(organizationAdapter);
    }

    private void initListData(int mPage) {
        mRecyclerView.refreshComplete();//刷新成功
        organizationAdapter.notifyDataSetChanged();


    }


    @Override
    public void getDepartmentinfo(DepartmnetInfoBean departmnetInfoBean) {


        if (departmnetInfoBean.getCode() == 200) {
            if (departmnetInfoBean.getData().size() != 0) {
                List<DepartmnetInfoBean.DepartmnetInfo> ll = departmnetInfoBean.getData();
                dataList.addAll(ll);
            }
            organizationAdapter.notifyDataSetChanged();
        } else {
            //获取失败
            GlobalToast.show("获取部门机构失败", Toast.LENGTH_LONG);
        }
    }

    @OnClick({ R.id.searchBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchBtn://搜索按钮
                organizationName = searchBtn.getText().toString();
                dataList.clear();
                mPage = 1;
                initListData(mPage);

                break;
        }
    }
}

