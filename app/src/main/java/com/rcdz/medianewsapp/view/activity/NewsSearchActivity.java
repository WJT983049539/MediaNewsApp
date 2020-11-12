package com.rcdz.medianewsapp.view.activity;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.MuhuNewBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.persenter.interfaces.GetMoHuNewTitle;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.SetList;
import com.rcdz.medianewsapp.view.fragment.SearchAllFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:搜索新闻
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/15 9:59
 */
public class NewsSearchActivity extends BaseActivity2 implements GetMoHuNewTitle , GetAllNewsList {
    @BindView(R.id.backBtn)
    ImageButton backBtn;
    @BindView(R.id.search_Org)
    EditText searchOrg;
    @BindView(R.id.searchBtn)
    TextView searchBtn;
    @BindView(R.id.sousuoshowfragment)
    FrameLayout sousuoshowfragment;
    @BindView(R.id.zonghe)
    TextView zonghe;
    @BindView(R.id.video)
    TextView video;
    @BindView(R.id.tab_sousuo)
    LinearLayout tabSousuo;
    PopupWindow popupBigPhoto;
    PupwindowsAdapter pupwindowsAdapter;
    View view2;
    private String sousuoContent="";//搜索内容
    private SetList<String> list = new SetList<String>();
    private List<MuhuNewBean.RowsBean> NewTitlelist = new ArrayList<MuhuNewBean.RowsBean>();
    private HashMap<Integer,MuhuNewBean.RowsBean> NewTitlelist2 = new HashMap<>();

    @Override
    public String setNowActivityName() {
        return "搜索新闻";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_news_search;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        list.add("综合");
        list.add("视频");
        searchOrg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    // 此处为得到焦点时的处理内容
//                    NewTitlelist.clear();

                }else{
                    Log.i("test","as");
                }
            }
        });
        searchOrg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String sousuowhere=editable.toString().trim();
                if(sousuowhere.equals("")||sousuowhere==null){
                    NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(NewsSearchActivity.this);
                    newNetWorkPersenter.MohuNewSearch(sousuowhere,NewsSearchActivity.this);//模糊搜索
                }else{
                    NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(NewsSearchActivity.this);
                    newNetWorkPersenter.MohuNewSearch(sousuowhere,NewsSearchActivity.this);//模糊搜索
                }

            }
        });
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(NewsSearchActivity.this);
        newNetWorkPersenter.MohuNewSearch("",NewsSearchActivity.this);//模糊搜索
    }

    @Override
    public void inintData() {
//        zonghe.setTextColor(Color.parseColor("#000000"));
//        video.setTextColor(Color.parseColor("#555555"));
//        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.sousuoshowfragment,new SearchAllFragment(sousuoContent));
//        fragmentTransaction.commit();
    }


    @OnClick({R.id.backBtn, R.id.search_Org,R.id.zonghe, R.id.video,R.id.searchBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                this.finish();
                break;
            case R.id.searchBtn://点击搜索
                if (searchOrg.getText() == null || searchOrg.getText().equals("")) {
                    GlobalToast.show("搜索内容为空", 5000);
                } else {
                    sousuoContent=searchOrg.getText().toString();
                    zonghe.setTextColor(Color.parseColor("#000000"));
                    video.setTextColor(Color.parseColor("#555555"));
                    if(!sousuoContent.equals("")){
                        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.sousuoshowfragment,new SearchAllFragment(sousuoContent));
                        fragmentTransaction.commit();
                    }
                }

                break;
            case R.id.zonghe://全部新闻
                zonghe.setTextColor(Color.parseColor("#000000"));
                video.setTextColor(Color.parseColor("#555555"));
                if(!sousuoContent.equals("")){
                    FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.sousuoshowfragment,new SearchAllFragment(sousuoContent));
                    fragmentTransaction.commit();
                }

                break;
            case R.id.video: //视频新闻
                zonghe.setTextColor(Color.parseColor("#555555"));
                video.setTextColor(Color.parseColor("#000000"));
                if(!sousuoContent.equals("")){
                    FragmentTransaction fragmentTransaction2= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.sousuoshowfragment,new SearchVideoFragment(sousuoContent));
                    fragmentTransaction2.commit();
                }

                break;
        }
    }
    //新闻列表，搜索标题
    @Override
    public void getMohuNewTitle(List<MuhuNewBean.RowsBean> list2) {
        NewTitlelist.clear();
        NewTitlelist2.clear();
        for(int i=0;i<list2.size();i++){
            NewTitlelist2.put(list2.get(i).getTargetId(),list2.get(i));
        }
        List<MuhuNewBean.RowsBean> valuesList = new ArrayList<MuhuNewBean.RowsBean>(NewTitlelist2.values()); //去掉重复新闻
        NewTitlelist.addAll(valuesList);

        if(view2==null){
            view2= getLayoutInflater().inflate(R.layout.pup_new_seach, null);
            RecyclerView recyclerView= view2.findViewById(R.id.search_new_pup_rc);
            recyclerView.setLayoutManager(new LinearLayoutManager(NewsSearchActivity.this));
            pupwindowsAdapter=new PupwindowsAdapter(NewTitlelist);
            pupwindowsAdapter.setOnItemClickListener(new PupwindowsAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    String title=NewTitlelist.get(position).getTitle();
                    sousuoContent=title;
                    zonghe.setTextColor(Color.parseColor("#000000"));
                    video.setTextColor(Color.parseColor("#555555"));
                    FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.sousuoshowfragment,new SearchAllFragment(sousuoContent));
                    fragmentTransaction.commit();
                    if(popupBigPhoto!=null){
                        popupBigPhoto.dismiss();
                        popupBigPhoto=null;
                        view2=null;
                    }
                }
            });
            recyclerView.setAdapter(pupwindowsAdapter);
        }

        if (popupBigPhoto == null) {
            popupBigPhoto = new PopupWindow(view2,  LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, false);
            popupBigPhoto.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
            popupBigPhoto.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popupBigPhoto.setOutsideTouchable(false);
            popupBigPhoto.showAsDropDown(searchOrg,0,20);
        }

       if(popupBigPhoto!=null&&popupBigPhoto.isShowing()){
           pupwindowsAdapter.notifyDataSetChanged();
       }

    }

    @Override
    public void getAllNewsList(NewsListBean news) {

    }


    private static class PupwindowsAdapter extends RecyclerView.Adapter<PupwindowsAdapter.ViewHolder>{
        private List<MuhuNewBean.RowsBean> newTitlelist;
        private ItemClickListener mItemClickListener ;


        public interface ItemClickListener{
            public void onItemClick(int position) ;
        }
        public void setOnItemClickListener(ItemClickListener itemClickListener){
            this.mItemClickListener = itemClickListener ;
        }
        public PupwindowsAdapter(List<MuhuNewBean.RowsBean> newTitlelist) {
            this.newTitlelist=newTitlelist;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pup_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.pup_item_text.setText(newTitlelist.get(position).getTitle());
            holder.pup_item_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemClickListener!=null){
                        mItemClickListener.onItemClick(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return newTitlelist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView pup_item_text;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                pup_item_text=itemView.findViewById(R.id.pup_item_text);
            }
        }
    }
}
