package com.rcdz.medianewsapp.model.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.tools.AppConfig;

import java.util.ArrayList;

/**
 * 作用:新闻列表适配器
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 16:02
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //布局类型
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;
    private static final int VIEW_TYPE_THREE = 3;
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
    private ArrayList<NewsListBean.NewsInfo> newsItemList;
    private Activity activity;
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }
    public NewsAdapter(ArrayList<NewsListBean.NewsInfo> newsItemList, Activity activity) {
        this.newsItemList=newsItemList;
        this.activity=activity;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater= LayoutInflater.from(activity);
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType==1){ //1图+文字
            viewHolder=new ViewHolder(inflater.inflate(R.layout.item_news1,parent,false));
        }else if(viewType==2){ //2图加图加图
            viewHolder=new ViewHolder.ViewHolder2(inflater.inflate(R.layout.item_news2,parent,false));
        }else if(viewType==3){ //3大图
            viewHolder=new ViewHolder.ViewHolder3(inflater.inflate(R.layout.item_news3,parent,false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType=getItemViewType(position);
        if(viewType==1){ //1图+文字
            ((ViewHolder)holder).news1_title.setText(newsItemList.get(position).getLongTitle());
            String usrl=newsItemList.get(position).getCoverUrl();
            usrl=usrl.split(",")[0];
//            String asda=usrl.replace("/small","");
            Glide.with(activity).load(AppConfig.BASE_PICTURE_URL +usrl).apply(options).into(((ViewHolder)holder).news1_thumb);
            ((ViewHolder)holder).news1_createedit.setText(newsItemList.get(position).getCreator());
            ((ViewHolder)holder).news1_commentcount.setText(newsItemList.get(position).getCommentCount()+"评");
            ((ViewHolder)holder).news1_item_time.setText(newsItemList.get(position).getPublishDateString());
        }else if(viewType==2){ //2图加图加图
            ((ViewHolder.ViewHolder2)holder).news2_title.setText(newsItemList.get(position).getLongTitle());
            ((ViewHolder.ViewHolder2)holder).news2_createedit.setText(newsItemList.get(position).getCreator());
            ((ViewHolder.ViewHolder2)holder).news2_commentcount.setText(newsItemList.get(position).getCommentCount()+"评");
            ((ViewHolder.ViewHolder2)holder).news2_item_time.setText(newsItemList.get(position).getPublishDateString());
            Object thumb=newsItemList.get(position).getCoverUrl();
            if(thumb!=null) {
                String thumb1=thumb.toString();
                String[] thumbs = thumb1.split(",");
                if(thumbs.length==1){

                    String usrl=thumbs[0];
//                    String asda=usrl.replace("/small","");

                    Glide.with(activity).load(AppConfig.BASE_PICTURE_URL + usrl).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb);
                } else if (thumbs.length == 2) {
                    String usrl=thumbs[0];
//                    String asda=usrl.replace("/small","");

                    String usrl1=thumbs[1];
//                    String asda1=usrl1.replace("/small","");


                    Glide.with(activity).load(AppConfig.BASE_PICTURE_URL +  usrl).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb);
                    Glide.with(activity).load( AppConfig.BASE_PICTURE_URL +  usrl1).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_hubm2);
                } else if (thumbs.length == 3) {

                    String usrl=thumbs[0];
//                    String asda=usrl.replace("/small","");

                    String usrl1=thumbs[1];
//                    String asda1=usrl1.replace("/small","");

                    String usrl2=thumbs[2];
//                    String asda2=usrl2.replace("/small","");

                    Glide.with(activity).load(AppConfig.BASE_PICTURE_URL +  usrl).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb);
                    Glide.with(activity).load( AppConfig.BASE_PICTURE_URL +  usrl1).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_hubm2);
                    Glide.with(activity).load(AppConfig.BASE_PICTURE_URL +  usrl2).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb3);
                }

            }else{
                Glide.with(activity).load(R.mipmap.default_image).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb);
                Glide.with(activity).load( R.mipmap.default_image).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_hubm2);
                Glide.with(activity).load(R.mipmap.default_image).apply(options).into(((ViewHolder.ViewHolder2) holder).news2_thumb3);
            }
        }else if(viewType==3){ //3大图
                ((ViewHolder.ViewHolder3)holder).news3_title.setText(newsItemList.get(position).getLongTitle());
            ((ViewHolder.ViewHolder3)holder).news3_createedit.setText(newsItemList.get(position).getCreator());
            ((ViewHolder.ViewHolder3)holder).news3_commentcount.setText(newsItemList.get(position).getCommentCount()+"评");
            ((ViewHolder.ViewHolder3)holder).news3_item_time.setText(newsItemList.get(position).getPublishDateString());

            String usrl=newsItemList.get(position).getCoverUrl();
            usrl=usrl.split(",")[0];

            String asda=usrl.replace("/small","");

            Glide.with(activity).load(AppConfig.BASE_PICTURE_URL +asda).apply(options).into(((ViewHolder.ViewHolder3)holder).news3_hubm);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick(position);
                }
            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        //1图+文字  2图加图加图  3大图
        int styleType=1;
        if(newsItemList.get(position).getStyleType()==null){
            styleType=1;
        }else if(newsItemList.get(position).getStyleType().equals("06")){
            styleType=1;
        }else if(newsItemList.get(position).getStyleType().equals("04")){
            styleType=2;
        }else if(newsItemList.get(position).getStyleType().equals("02")){
            styleType=3;
        }
        return styleType;
    }



    @Override
    public int getItemCount() {
        return newsItemList.size();
    }
    //图+文字布局
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView news1_title;
        private ImageView news1_thumb;
        private TextView news1_createedit;
        private TextView news1_commentcount;
        private TextView news1_item_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news1_title=itemView.findViewById(R.id.news1_title);
            news1_thumb=itemView.findViewById(R.id.news1_thumb);
            news1_createedit=itemView.findViewById(R.id.news1_createedit);
            news1_commentcount=itemView.findViewById(R.id.news1_commentcount);
            news1_item_time=itemView.findViewById(R.id.news1_item_time);
        }
        //图加图加图
    public static class ViewHolder2 extends RecyclerView.ViewHolder {
            private TextView news2_title;
            private ImageView news2_thumb;
            private ImageView news2_hubm2;
            private ImageView news2_thumb3;
            private TextView news2_createedit;
            private TextView news2_commentcount;
            private TextView news2_item_time;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            news2_title=itemView.findViewById(R.id.news2_title);
            news2_thumb=itemView.findViewById(R.id.news2_hubm);
            news2_hubm2=itemView.findViewById(R.id.news2_hubm2);
            news2_thumb3=itemView.findViewById(R.id.news2_hubm3);
            news2_createedit=itemView.findViewById(R.id.news2_createedit);
            news2_commentcount=itemView.findViewById(R.id.news2_commentcount);
            news2_item_time=itemView.findViewById(R.id.news2_item_time);
        }
    }
    //大图
    public static class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView news3_title;
        private ImageView news3_hubm;
        private TextView news3_createedit;
        private TextView news3_commentcount;
        private TextView news3_item_time;
        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            news3_title=itemView.findViewById(R.id.news3_title);
            news3_hubm=itemView.findViewById(R.id.news3_hubm);
            news3_createedit=itemView.findViewById(R.id.news3_createedit);
            news3_commentcount=itemView.findViewById(R.id.news3_commentcount);
            news3_item_time=itemView.findViewById(R.id.news3_item_time);
        }
    }
    }
}
