package com.rcdz.medianewsapp.view.customview;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.rcdz.medianewsapp.MAppaction.ActivityManager.getContext;
import static com.rcdz.medianewsapp.MAppaction.ActivityManager.getManager;

/**
 * 作用: 分割线
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 17:00
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int dalte=dp2px(5);


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if(parent.getChildAdapterPosition(view)%2==0){ //第二行
            outRect.left=10;
            outRect.right=0;
        }else if(parent.getChildAdapterPosition(view)%2==1){ //第一行
            outRect.left=0;
            outRect.right=10;
        }
    }

    public int dp2px(int dp) {
        // px = dp * (dpi / 160)
        DisplayMetrics metrics = getManager().getTopActivity().getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;
        return (int) (dp * (dpi / 160f) + 0.5f);
    }
}
