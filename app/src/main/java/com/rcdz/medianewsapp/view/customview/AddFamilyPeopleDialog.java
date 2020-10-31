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
public class AddFamilyPeopleDialog extends Dialog {
    Confirm confirm;
    private Context mContext;
    EditText familay_edit;
    public void  setOnDialogListen(Confirm confirm){
        this.confirm=confirm;
    }
    public AddFamilyPeopleDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_addpople);
        familay_edit=findViewById(R.id.familay_edit);
        TextView fa_sure=findViewById(R.id.fa_sure);
        fa_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm!=null){
                    if(familay_edit.getText()!=null){
                        String info=familay_edit.getText().toString();
                        confirm.ok(info);
                    }else{
                        confirm.ok("");
                    }

                }
            }
        });

    }

    public interface Confirm{
        void ok(String info);
    }

}