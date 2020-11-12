package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
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

/**
 * 弹出dialog
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class UpdateApkDialog extends Dialog {
    Confirm confirm;
    private AppVersionBean.AppVersionInfo appVersionInfo;
    private Context mContext;
    private String code;

    public void  setOnDialogListen(Confirm confirm){
        this.confirm=confirm;
    }
    public UpdateApkDialog(@NonNull Context context, AppVersionBean.AppVersionInfo appVersionInfo, String code) {
        super(context,R.style.dd);
        this.appVersionInfo=appVersionInfo;
        this.mContext = context;
        this.code=code;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.dialog_update);
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

        ImageView update_canal=findViewById(R.id.close);
        update_canal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    confirm.cannal();
                }
            }
        });
        Button update_ok=findViewById(R.id.update_ok);

        TextView namecode=(TextView)findViewById(R.id.namecode); //名字
        TextView versionreleasetime=(TextView)findViewById(R.id.versionreleasetime); //时间
        TextView appversioncontent=(TextView)findViewById(R.id.appversioncontent); //描述

        namecode.setText("发现新版本"+code);
        versionreleasetime.setText("更新时间"+appVersionInfo.getCreateDate());
        appversioncontent.setText(appVersionInfo.getDescription());

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