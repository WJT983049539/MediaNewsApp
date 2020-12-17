package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rcdz.medianewsapp.R;


/**
 * 弹出dialog
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class DeleteFamilyPeopleDialog extends Dialog {
    Confirm confirm;
    private Context mContext;
    private String content;
    TextView message_deletepeop;
    private int type;
    public void  setOnDialogListen(Confirm confirm){
        this.confirm=confirm;
    }
    public DeleteFamilyPeopleDialog(@NonNull Context context,String content,int type) {
        super(context);
        this.mContext = context;
        this.content=content;
        this.type=type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delepople);
        message_deletepeop=findViewById(R.id.message_deletepeop);
        if(type==1){
            message_deletepeop.setText("确定要解散本家庭吗？");
        }else {
            message_deletepeop.setText("确定要移出"+content+"吗？");
        }

        TextView delete_sure=findViewById(R.id.delete_sure);
        delete_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    confirm.ok();
                }
            }
        });
        TextView nativebutton=findViewById(R.id.nativebutton);
        nativebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    confirm.no();
                }
            }
        });

    }

    public interface Confirm{
        void ok();
        void no();
    }

}