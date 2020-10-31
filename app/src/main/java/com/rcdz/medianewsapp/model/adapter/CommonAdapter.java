package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * 公共的适配器
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private ArrayList<T> dataList;
    private int mLayoutRes; //布局id

    //    public CommonAdapter(int mLayoutRes, ArrayList<T> dataList) {
//        this.dataList = dataList;
//        this.mLayoutRes = mLayoutRes;
//    }
    public CommonAdapter(int mLayoutRes, @NonNull ArrayList<T> dataList) {
        this.dataList = dataList;
        this.mLayoutRes = mLayoutRes;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.bind(viewGroup.getContext(), view, viewGroup, mLayoutRes, position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindView(ViewHolder holder, T obj);

    //添加一个元素
    public void add(T data) {
        if (dataList == null) {
            dataList = new ArrayList<T>();
        }
        dataList.add(data);
        notifyDataSetChanged();

    }

    //往特定位置添加一个元素
    public void add(int position, T data) {
        if (dataList == null) {
            dataList = new ArrayList<T>();
        }
        dataList.add(position, data);
        notifyDataSetChanged();
    }

    //移除一个元素
    public void remove(T data) {
        if (dataList != null) {
            dataList.remove(data);
        }
        notifyDataSetChanged();
    }

    //移除特定位置的元素
    public void remove(int position) {
        if (dataList != null) {
            dataList.remove(position);
        }
        notifyDataSetChanged();
    }

    //清空
    public void clear() {
        if (dataList != null) {
            dataList.clear();
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder {

        private SparseArray<View> commonViews;   //存储ListView 的 item中的View
        private View item;                  //存放convertView
        private int position;               //游标
        private Context context;            //Context上下文

        private ViewHolder(Context context, ViewGroup viewGroup, int layoutRes) {
            commonViews = new SparseArray<View>();
            this.context = context;
            View view = LayoutInflater.from(context).inflate(layoutRes, viewGroup, false);
            view.setTag(this);
            item = view;
        }


        //绑定ViewHolder与item
        public static ViewHolder bind(Context context, View view, ViewGroup viewGroup, int layoutRes, int position) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder(context, viewGroup, layoutRes);
            } else {
                holder = (ViewHolder) view.getTag();
                holder.item = view;
            }
            holder.position = position;

            return holder;
        }

        //根据id获取集合中的控件
        public <T extends View> T getView(int i) {
            T t = (T) commonViews.get(i);
            if (t == null) {
                t = (T) item.findViewById(i);
                commonViews.put(i, t);
            }
            return t;
        }

        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            TextView view = getView(id);
            view.setText(text);
            return this;
        }

        //设置点击监听事件
        public ViewHolder setOnclickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        //设置可见
        public ViewHolder setVisibile(int id, int visibile) {
            getView(id).setVisibility(visibile);
            return this;
        }

        //设置可见
        public ViewHolder setEnabled(int id, boolean enabled) {
            getView(id).setEnabled(enabled);
            return this;
        }

        //设置标签
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }

        //设置图片
        public ViewHolder setImageResource(int id, int drawableRes) {
            ImageView view = getView(id);
            view.setImageResource(drawableRes);
            return this;
        }

        public ViewHolder setImage(int id, Bitmap bitmap) {
            ImageView view = getView(id);
            view.setImageBitmap(bitmap);
            return this;
        }

    }

}
