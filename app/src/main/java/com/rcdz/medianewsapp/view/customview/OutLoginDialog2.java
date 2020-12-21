package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rcdz.medianewsapp.R;


/**
 * 退出登录
 *
 * @author yandaocheng <br/>
 * 弹窗温馨提示
 * 2018-06-21
 * 修改者，修改日期，修改内容
 */
public class OutLoginDialog2 extends Dialog {
    private DialogConfirmClick mLisetner;
    private Context mContext;
    private boolean cancelShow = true;//是否显示取消按钮
    private TextView tv_sure;
    private TextView tv_no;
    private String code = "";


    public OutLoginDialog2(Context context, DialogConfirmClick lisetner) {
        super(context,R.style.DialogTheme2);
        this.mContext = context;
        this.mLisetner = lisetner;
        this.code=code;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_outgin2);
        initView();
    }

    private void initView() {
        tv_sure=findViewById(R.id.sure);
        tv_no=findViewById(R.id.no);
        tv_sure.setOnClickListener(new View.OnClickListener() {   //确定
            @Override
            public void onClick(View v) {
                if(mLisetner!=null){
                    mLisetner.ConfirmClick();
                    cancel();
                }
            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {   //确定
            @Override
            public void onClick(View v) {
                if(mLisetner!=null){
                    mLisetner.noClick();
                   cancel();
                }
            }
        });


    }

    public interface DialogConfirmClick {
        void ConfirmClick();
        void noClick();
    }
}
