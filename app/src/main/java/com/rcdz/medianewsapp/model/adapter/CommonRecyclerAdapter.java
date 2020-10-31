package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    //数据怎么办？
    protected List<T> mData;
    // 布局怎么办？
    private int mLayoutId;

    // 多布局支持
    private MultiTypeSupport mMultiTypeSupport;

    public CommonRecyclerAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    /**
     * 多布局支持
     */
    public CommonRecyclerAdapter(Context context, List<T> data, MultiTypeSupport<T> multiTypeSupport) {
        this(context, data, -1);
        this.mMultiTypeSupport = multiTypeSupport;
    }

    /**
     * 根据当前位置获取不同的viewType
     */
    @Override
    public int getItemViewType(int position) {
        // 多布局支持
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getLayoutId(mData.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 多布局支持
        if (mMultiTypeSupport != null) {
            mLayoutId = viewType;
        }
        // 先inflate数据
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        // 返回ViewHolder
        CommonViewHolder holder = new CommonViewHolder(itemView);


        return holder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        // 设置点击和长按事件
        if (onItemClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onitemclik(position);
                }
            });
        }
        if (onLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onLongClickListener.onLongClick(position);
                    return true; //消费掉
                }
            });
        }

        // 绑定怎么办？回传出去
        convert(holder,mContext, mData.get(position));
    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     *
     * @param item 当前的数据
     */
    public abstract void convert(CommonViewHolder holder,Context context, T item);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface MultiTypeSupport<T> {
        // 根据当前位置或者条目数据返回布局
        public int getLayoutId(T item, int position);
    }
    private OnItemClick onItemClick = null;
    private OnLongClickListener onLongClickListener = null;

    public void setOnItemClickListener(OnItemClick itemClickListener) {
                this.onItemClick = itemClickListener;
             }

          public void setOnLongClickListener(OnLongClickListener longClickListener) {
                 this.onLongClickListener = longClickListener;
            }
}