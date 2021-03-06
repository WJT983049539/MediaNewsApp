package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rcdz.medianewsapp.R;


/**
 * 昵称修改dialog
 *
 * @author yandaocheng <br/>
 * 弹窗温馨提示
 * 2018-06-21
 * 修改者，修改日期，修改内容
 */
public class yaoqingmaDialog extends Dialog {
    private DialogConfirmClick mLisetner;
    private Context mContext;
    private boolean cancelShow = true;//是否显示取消按钮
    private TextView tv_yaoqingma;
    private String code = "";


    public yaoqingmaDialog(Context context, String code,DialogConfirmClick lisetner) {
        super(context);
        this.mContext = context;
        this.mLisetner = lisetner;
        this.code=code;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_yaoqingma);
        initView();
    }

    private void initView() {
        tv_yaoqingma=findViewById(R.id.familay_code);
        tv_yaoqingma.setText(code);
        findViewById(R.id.delete_sure).setOnClickListener(new View.OnClickListener() {//确定
            @Override
            public void onClick(View v) {
                if(mLisetner!=null){
                    mLisetner.ConfirmClick();
                    dismiss();
                }
            }
        });


    }

    public interface DialogConfirmClick {
        void ConfirmClick();
    }
}
