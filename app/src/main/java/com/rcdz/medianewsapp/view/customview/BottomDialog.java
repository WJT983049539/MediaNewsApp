package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;


import com.rcdz.medianewsapp.R;

import java.util.SortedMap;
import java.util.TreeMap;

public class BottomDialog {
    private String content="";
    public interface Firm{
        void commit(String info);
        void cannel();

    }
    private  Firm firm=null;
    public void setFirm(Firm firm){
        this.firm=firm;
    }
    private View view;
    private EditText commetContent;
    public void BottomDialog(Context context) {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        //2、设置布局
        view = View.inflate(context, R.layout.dailog_bottom_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        commetContent = dialog.findViewById(R.id.commetContent);

        dialog.findViewById(R.id.relBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firm!=null){
                    if(commetContent.getText()!=null){
                        content=commetContent.getText().toString();
                    }

                    firm.commit( content);
                    dialog.cancel();
                }
            }
        });

        dialog.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firm!=null){
                    firm.cannel();
                    dialog.cancel();
                }
            }
        });

    }
}
