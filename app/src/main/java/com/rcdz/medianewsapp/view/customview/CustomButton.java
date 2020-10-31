package com.rcdz.medianewsapp.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 16:33
 */
public class CustomButton extends androidx.appcompat.widget.AppCompatButton {


    private Boolean flag=false;//标志按钮状态

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
