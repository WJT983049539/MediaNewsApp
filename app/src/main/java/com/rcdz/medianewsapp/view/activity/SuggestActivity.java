package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.PictureAdapter;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.UpFile;
import com.rcdz.medianewsapp.persenter.interfaces.YJRequestSuccess;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlobalToast;

import java.io.File;
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
public class SuggestActivity extends BaseActivity implements YJRequestSuccess, UpFile {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.contact)
    EditText contact;
    @BindView(R.id.uppicture)
    RecyclerView uppicture;
    @BindView(R.id.suggestcontent)
    EditText suggestcontent;
    @BindView(R.id.suggest_text)
    TextView suggest_text;
    @BindView(R.id.sendsuggest)
    TextView sendsuggest;
    PictureAdapter pictureAdapter;
    String content="";
    String contactcontent="";
    public List<String> picture= Collections.synchronizedList(new ArrayList<>());
    List<File> files=new ArrayList<>();
    public final static int REQUEST_CODE=0x001;
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
        picture.add("1");
        uppicture.setLayoutManager(new GridLayoutManager(this,4));
        pictureAdapter=new PictureAdapter(this,picture);
        uppicture.setAdapter(pictureAdapter);
        pictureAdapter.setOnItemClick(new PictureAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jia, null);
                SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.CAMERA, new CheckRequestPermissionListener() {
                    @Override
                    public void onPermissionOk(Permission permission) {
                        ImageSelector.builder()
                                .useCamera(true) // 设置是否使用拍照
                                .setSingle(false)  //设置是否单选
                                .setMaxSelectCount(4) // 图片的最大选择数量，小于等于0时，不限数量。
                                .canPreview(true) //是否可以预览图片，默认为true
                                .start(SuggestActivity.this, REQUEST_CODE); // 打开相册
                    }

                    @Override
                    public void onPermissionDenied(Permission permission) {
                    }
                });
            }
        });

        pictureAdapter.setppOnItemClick(new PictureAdapter.PictureOnItemClick() {
            @Override
            public void pponitemclik(int position) {

                new AlertDialog.Builder(SuggestActivity.this).setTitle("提示").setMessage("确定删除这张图片吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                picture.remove(position);
                                pictureAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });
        if(   suggestcontent.getText()!=null){
            int length=suggestcontent.getText().toString().length();
            suggest_text.setText(length+"/300");
        }


        suggestcontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("test",i+"");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("test",i+"");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){

                   String info= editable.toString();
                    suggest_text.setText(info.length()+"/300");
                }

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
                this.finish();
                break;
            case R.id.sendsuggest:
                //发送
                files.clear();
                if(picture.size()<4){
                    picture.remove(picture.size()-1);
                }
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
                for(int i=0;i<picture.size();i++){
                    File file=new File(picture.get(i));
                    files.add(file);
                }
                newNetWorkPersenter.UppictureMessage(files,this);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);
            picture.clear();
            picture.addAll(images);
            if(picture.size()<4){
                picture.add("1");
            }
            pictureAdapter.notifyDataSetChanged();

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

    @Override
    public void yjquest() {
        GlobalToast.show("提交成功",5000);
        this.finish();

    }

    @Override
    public void upfileSuccess(String data) { //图片上传成功
        String host= data+"/";
        String url="";
        for(int i=0;i<files.size();i++){
            String filename=host+files.get(i).getName();
            url=url+filename+",";
        }
        url=url.substring(0,url.length()-1);

        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);

        if(  suggestcontent.getText()!=null){
            content=suggestcontent.getText().toString();
        }
        if(  contact.getText()!=null){
            contactcontent=contact.getText().toString();
        }

        newNetWorkPersenter.upYiJianBack(content,contactcontent,url,this);
    }

    @Override
    public void upfileFail() {

    }
}
