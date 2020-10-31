package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.PictureAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 9:27
 */
public class SuggestActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.uppicture)
    RecyclerView uppicture;
    @BindView(R.id.suggestcontent)
    EditText suggestcontent;
    @BindView(R.id.suggest_text)
    TextView suggest_text;
    @BindView(R.id.sendsuggest)
    TextView sendsuggest;
    PictureAdapter pictureAdapter;
    public List<String> picture= Collections.synchronizedList(new ArrayList<>());

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.suggest;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        picture.clear();
        uppicture.setLayoutManager(new LinearLayoutManager(this));
        pictureAdapter=new PictureAdapter(this,picture,1);
//        uppicture.setAdapter();


        suggestcontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                   String info= editable.toString();
                    suggest_text.setText(info.length()+"/300");
                }
                suggest_text.setText("0/300");

            }
        });

    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.back,R.id.sendsuggest})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back:
                break;
            case R.id.sendsuggest:
                break;
        }
    }
}
