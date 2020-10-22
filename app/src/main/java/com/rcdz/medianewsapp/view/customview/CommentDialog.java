package com.rcdz.medianewsapp.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.rcdz.medianewsapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 弹出dialog
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class CommentDialog extends Dialog {
    Confirm confirm;


    public void  setOnDialogListen(Confirm confirm){
        this.confirm=confirm;
    }
    public CommentDialog(@NonNull Context context) {
        super(context, R.style.MyDialogTheme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.dialog_comment);
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0f;
        window.setAttributes(layoutParams);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        EditText editcomment=(EditText)findViewById(R.id.editcomment);
        TextView tvSendComment=(TextView)findViewById(R.id.tvSendComment);
        //        //自动获取焦点并弹出软键盘
        showSoftInput(editcomment);

        tvSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info= String.valueOf(editcomment.getText());
                if(info!=null){
                    confirm.ok(info);
                    cancel();
                }

            }
        });
    }

    public interface Confirm{
        void ok(String six);
    }

    /**
     * 自动获取焦点并弹出软键盘
     * @param etIpAddress
     */
    private void showSoftInput(EditText etIpAddress) {
        //自动获取焦点
        etIpAddress.setFocusable(true);
        etIpAddress.setFocusableInTouchMode(true);
        etIpAddress.requestFocus();
        //200毫秒后弹出软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) etIpAddress.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(etIpAddress, 0);
            }
        }, 200);
    }
}