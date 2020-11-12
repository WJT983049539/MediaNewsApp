package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.CommonAdapter;
import com.rcdz.medianewsapp.model.bean.FeedbackBean;
import com.rcdz.medianewsapp.model.bean.LeaveMegBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.Commentimpl;
import com.rcdz.medianewsapp.persenter.interfaces.IshowSearchOrganization;
import com.rcdz.medianewsapp.persenter.interfaces.UpFile;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.GridImageUtil;
import com.rcdz.medianewsapp.tools.ImageUtils;
import com.rcdz.medianewsapp.view.customview.ListDialog;
import com.rcdz.medianewsapp.view.customview.MyGridView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:区留言
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 9:47
 */
public class WriteMessageActivity extends BaseActivity implements IshowSearchOrganization, AdapterView.OnItemClickListener, Commentimpl, UpFile {
    //提交按钮
    @BindView(R.id.submitBtn)
    Button submitBtn;
    //主题
    @BindView(R.id.messageTheme)
    EditText message_theme;
    //反馈内容
    @BindView(R.id.feedContent)
    EditText feed_content;
    //手机
    @BindView(R.id.contactmode)
    EditText contact_mode;
    @BindView(R.id.select_type)
    LinearLayout select_type;
    @BindView(R.id.feedbackType)
    TextView feedbacktype;
    @BindView(R.id.select_unit)
    LinearLayout select_unit;
    @BindView(R.id.feedbackUnit)
    TextView feedbackunit;
    @BindView(R.id.file)
    MyGridView gridView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    CommonAdapter commonAdapter;
    String FeedbackOrganization="";//反馈单位
    int FeedbackOrganizationId=0;//反馈单位ID
    String FeedbackType="";//反馈类型
    String theme="";
    String feedcontent="";
    String phone="";
    Uri photoUri;
    private  List<FeedbackBean.FeedbackInfo>  Feedbacklist=new ArrayList<>();
    List<File>files=new ArrayList<>();
    private List<String> FeedbackTypeList=new ArrayList<String>();
    @Override
    public String setNowActivityName() {
        return "区留言";
    }
    public static ArrayList<String> photopath = new ArrayList<>();//图片地址
    public static ArrayList<String> photoData = new ArrayList<String>() {{
        add("1@拍照");
        add("2@从相册中选择");
    }};
    @Override
    public int setLayout() {
        return R.layout.activity_write_message;
    }

    @Override
    public void inintView() {
        photopath.clear();
        Feedbacklist.clear();
        FeedbackTypeList.clear();
        FeedbackTypeList.add("投诉");
        FeedbackTypeList.add("建议");
        FeedbackTypeList.add("咨询");
        ButterKnife.bind(this);
        SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new CheckRequestPermissionListener() {
            @Override
            public void onPermissionOk(Permission permission) {
                Log.i("WriteMessageActivity", "请求权限成功");
                gridView.setOnItemClickListener(WriteMessageActivity.this);
            }

            @Override
            public void onPermissionDenied(Permission permission) {
                Log.i("WriteMessageActivity", "请求权限失败");
            }
        });

    }

    @Override
    public void inintData() {
        //添加默认加号图片
       Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.insert); // 加号
        try {
            String path=ImageUtils.saveFile(bmp);
            photopath.add(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        commonAdapter = new CommonAdapter<String>(R.layout.image, photopath) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                if (holder.getItemPosition() == 0) {
                    holder.setVisibile(R.id.delete_image, View.INVISIBLE);
                    holder.setImageResource(R.id.upload_image,R.mipmap.insert);
                } else {
                    Bitmap bitmap2 = BitmapFactory.decodeFile(obj);
                    holder.setImage(R.id.upload_image, bitmap2);
                }
            }
        };
        gridView.setAdapter(commonAdapter);
    }


    @OnClick({R.id.img_back, R.id.select_type, R.id.select_unit, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back://返回
                finish();
                break;
            case R.id.select_type://反馈类型 投诉、建议、咨询
                addPickerFeedbackType();
                break;
            case R.id.select_unit://反馈单位
                addPickerDanweiView();
                break;
            case R.id.submitBtn://提交
                files.clear();
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);

                for(int i=0;i<photopath.size();i++){
                    File file=new File(photopath.get(i));
                    files.add(file);
                }

                newNetWorkPersenter.UppictureMessage(files,this);

                break;
        }
    }

    private void addPickerFeedbackType() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                FeedbackType=FeedbackTypeList.get(options1);
                feedbacktype.setText(FeedbackType);//选中的值放入editText中

            }
        }).setTitleText("反馈类型选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(FeedbackTypeList);
        pvOptions.show();
    }

    //查看反馈单位
    private void addPickerDanweiView() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.Search_Organization(this);
    }
    //得到反馈单位
    @Override
    public void ishowSearchOrganization(FeedbackBean feedbackBean) {
        Feedbacklist.clear();
        Feedbacklist.addAll(feedbackBean.getRows());
        List<String> nameList=new ArrayList<>();
        for(int i=0;i<Feedbacklist.size();i++){
            nameList.add(Feedbacklist.get(i).getName());
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                FeedbackOrganization=Feedbacklist.get(options1).getName();
                FeedbackOrganizationId= Feedbacklist.get(options1).getId();
                feedbackunit.setText(FeedbackOrganization);//选中的值放入editText中   //todo 值未能正常显示
            }
        }).setTitleText("反馈单位选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(nameList);
        pvOptions.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemClick(view, i,  3);
    }
    /**
     * Grid中每个item图片的操作处理
     *
     * @param view
     * @param position
     */
    public  void itemClick(View view, int position, int num) {
        ImageView imageview = view.findViewById(R.id.upload_image);
        ImageView deleteButton = view.findViewById(R.id.delete_image);
        if (photopath.size() == num + 1) { // 第一张为默认图片
            if (position != 0) {
                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        init(imageview);
                    }
                });
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog(position);
                    }
                });
            } else {
                GlobalToast.show( "图片数" + num + "张已满",Toast.LENGTH_SHORT);
            }
        } else if (position == 0) {   // 点击图片位置为+ 0对应0张图片
            // 选择图片
            showSelectDialogs(2);
        } else {
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    init(imageview);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog(position);
                }
            });
        }
    }
    /**
     * 删除图片
     *
     * @param position
     */
    public  void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WriteMessageActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                photopath.remove(position);
                commonAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    /**
     * 点击显示大图,点击大图返回显示小图
     *
     * @param view
     */
    public  void init(ImageView view) {
        Dialog dialog = new Dialog(WriteMessageActivity.this, R.style.FullActivity);
        WindowManager.LayoutParams attributes = WriteMessageActivity.this.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);

        ImageView image = new ImageView(WriteMessageActivity.this);
        //宽高
        image.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //imageView设置图片
        Bitmap bm = ((BitmapDrawable) view.getDrawable()).getBitmap();
        Drawable drawable = new BitmapDrawable(bm);
        image.setImageDrawable(drawable);
        dialog.setContentView(image);
        //大图的点击事件（点击让他消失）
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 选择拍照或是从相册选取照片
     */
    public  void showSelectDialogs(final int code) {
        ListDialog dialog = new ListDialog(WriteMessageActivity.this, new ListDialog.DialogItemClickListener() {
            @Override
            public void onItemClik(int position, String id, String text, int chooseIndex) {//1:拍照   2：相册
                if (id.equals("1")) {
                    File outputImage = new File(WriteMessageActivity.this.getExternalCacheDir(), "output_image.jpg");    //内部存储设备/Android/data/com.example.choosepictest/cache/output_image.jpg
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //Android 7.0  FileProvider传入Uri对象
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        photoUri = FileProvider.getUriForFile(WriteMessageActivity.this,
                                "com.rcdz.medianewsapp.fileprovider", outputImage);
                    } else {
                        photoUri = Uri.fromFile(outputImage);
                    }
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    WriteMessageActivity.this.startActivityForResult(intent, 2);
                } else if (id.equals("2")) {
                    ImageUtils.openLocalImage(WriteMessageActivity.this, 3);
                }
            }
        }, photoData, 0);
        dialog.show();
    }


    /**
     * 处理拍照或选择照片后的结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) return;
        if (requestCode == 2 || requestCode == 3 || requestCode == 6) {
            //当上传资料拍照时，将图片加到GridView中显示
            imageResult(requestCode, resultCode, data);
        }
    }

    /**
     * Grid中图片结果处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public  void imageResult(int requestCode, int resultCode, Intent data) {
        Log.i("photo", "WriteMessageActivity的imageResult了");
        //当上传资料拍照时，将图片加到GridView中显示
        String path;
        Bitmap bitmap2 = null;
        Uri uri = null;
        try {
            if (requestCode == 2) {  //申请资料拍照
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                String prent = Environment.getExternalStorageDirectory() + "/temp";
                if (!new File(prent).exists()) {
                    new File(prent).mkdirs();
                }
                path = Environment.getExternalStorageDirectory() + "/temp/" + Math.abs(Math.random()) + ".jpg";
                GridImageUtil.copyFile(outputImage.getAbsolutePath(), path);
                bitmap2 = BitmapFactory.decodeFile(outputImage.getAbsolutePath());
                if (bitmap2 != null) {
                    bitmap2 = GridImageUtil.get_bmp(bitmap2, 200, 200);//对图片进行压缩
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("itemImage", bitmap2);
//                    photopath.add(map);
                    photopath.add(path);
                }
            } else if (requestCode == 3) {  //申请资料从相册中选择
                uri = data.getData();
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                if (bitmap2 != null) {
                    bitmap2 = GridImageUtil.get_bmp(bitmap2, 200, 200);//对图片进行压缩
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("itemImage", bitmap2);
//                    photopath.add(map);
                    path= ImageUtils.saveFile(bitmap2);
                    photopath.add(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        commonAdapter = new CommonAdapter<String>(R.layout.image, photopath) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                if (holder.getItemPosition() == 0) {
                    holder.setVisibile(R.id.delete_image, View.INVISIBLE);
                    holder.setImageResource(R.id.upload_image,R.mipmap.insert);
                } else {
                    Bitmap bitmap2 = BitmapFactory.decodeFile(obj);
                    holder.setImage(R.id.upload_image, bitmap2);
                }
            }
        };
        gridView.setAdapter(commonAdapter);
    }

    @Override
    public void getInfo(String info) {
        WriteMessageActivity.this.finish();
    }

    @Override
    public void upfileSuccess(String data) {
        String fileurl="";
        for(int i=0;i<files.size();i++){
            String url= data+"/"+files.get(i).getName();
            fileurl=url+",";
        }
        fileurl= fileurl.substring(0,fileurl.length()-1);

        if(FeedbackType.equals("")){
            GlobalToast.show("反馈类型为空", Toast.LENGTH_LONG);
            return;
        }
        if(FeedbackOrganization.equals("")){
            GlobalToast.show("反馈单位为空", Toast.LENGTH_LONG);
            return;
        }

        if(message_theme.getText()==null||message_theme.getText().equals("")){
            GlobalToast.show("请填写主题", Toast.LENGTH_LONG);
            return;

        }else{
            theme= message_theme.getText().toString();
        }

        if(feed_content.getText()==null||feed_content.getText().equals("")){
            GlobalToast.show("请填写反馈内容", Toast.LENGTH_LONG);
            return;

        }else{
            feedcontent= feed_content.getText().toString();
        }
        if(contact_mode.getText()!=null){
            phone= contact_mode.getText().toString();
        }

        NewNetWorkPersenter newNetWorkPersenter2=new NewNetWorkPersenter(this);
        LeaveMegBean leaveMegBean=new LeaveMegBean();
        LeaveMegBean.MainData mainData=new   LeaveMegBean.MainData();
        mainData.setSubject(theme);
        mainData.setContents(feedcontent);
        mainData.setImages(fileurl);
        mainData.setIsBlackList(0);
        mainData.setIsReply(0);
        mainData.setOrganizationId(FeedbackOrganizationId);
        mainData.setOrganizationName(FeedbackOrganization);
        mainData.setPhoneNo(phone);
        mainData.setType(FeedbackType);
        mainData.setState("0");
        leaveMegBean.setMainData(mainData);
        newNetWorkPersenter2.AddLeaveMessage(leaveMegBean,this);
    }

    @Override
    public void upfileFail() {

    }
}
