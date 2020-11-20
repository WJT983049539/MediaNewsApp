package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.AppVersionBean;
import com.rcdz.medianewsapp.view.activity.ShowXieYiBookActivity;

/**
 * 弹出dialog
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class XieYiDialog extends Dialog {
    private Confirm confirm;
    private Context mContext;

    public void  setOnDialogListen(Confirm confirm){
        this.confirm=confirm;
    }
    public XieYiDialog(@NonNull Context context) {
        super(context,R.style.dd);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.dialog_xieyi);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width =width;
        window.setAttributes(layoutParams);
        window.setGravity(Gravity.CENTER);

        setCanceledOnTouchOutside(false);
        TextView xieyi=findViewById(R.id.xieyi);//协议
        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ShowXieYiBookActivity.class));
            }
        });
        TextView update_canal=findViewById(R.id.noshiyong);
        update_canal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    confirm.cannal();
                }
            }
        });
        TextView update_ok=findViewById(R.id.tongyi);

        //自动获取焦点并弹出软键盘
        update_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    confirm.ok();
                }
            }
        });
    }

    public interface Confirm{
        void ok();
        void cannal();
    }

}