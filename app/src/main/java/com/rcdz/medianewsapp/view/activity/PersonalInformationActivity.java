package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.os.BuildCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserInfo;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.FileUtils;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.ImageUtils;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.customview.ListDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 个人中心页面
 *
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class PersonalInformationActivity extends BaseActivity implements GetUserInfo {

    @BindView(R.id.p_back)
    ImageView pBack;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.p_head)
    ImageView pHead;
    @BindView(R.id.view11)
    View view11;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.p_nick)
    TextView pNick;
    @BindView(R.id.view12)
    View view12;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.p_remake)
    TextView pRemake;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.view15)
    View view15;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.p_sex)
    TextView pSex;
    @BindView(R.id.view16)
    View view16;
    @BindView(R.id.sex_women)
    TextView textView16;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.p_phone)
    TextView pPhone;
    @BindView(R.id.view18)
    View view18;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.p_address)
    TextView pAddress;
    Boolean SignStatus = false;
    @BindView(R.id.lin_nick)
    LinearLayout linNick;
    @BindView(R.id.lin_remake)
    LinearLayout linRemake;
    @BindView(R.id.lin_sex)
    LinearLayout linSex;
    @BindView(R.id.lin_phone)
    LinearLayout linPhone;
    @BindView(R.id.lin_address)
    LinearLayout linAddress;
    private UserInfoBean userInfo;
    boolean loginStru = false;
    private RequestOptions options = new RequestOptions().error(R.mipmap.peop).circleCrop();
    ACache aCache;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.person2;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        loginStru = (boolean) SharedPreferenceTools.getValueofSP(PersonalInformationActivity.this, "loginStru", false);
        if (loginStru) {
            aCache = ACache.get(PersonalInformationActivity.this);
            userInfo = (UserInfoBean) aCache.getAsObject("userinfo");

            if (userInfo == null) {
                NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(this);
                newNetWorkPersenter.GetUserInfo("", this);
            } else {
                if (userInfo != null) {
                    String headimg = (String) userInfo.getData().getHeadImageUrl();
                    Glide.with(this).load(AppConfig.BASE_PICTURE_URL + headimg).apply(options).into(pHead);//头像
                    if (userInfo.getData().getUserName() != null) {
                        pNick.setText(userInfo.getData().getUserName());
                    }
                    if (userInfo.getData().getAddress() != null) {
                        pAddress.setText(userInfo.getData().getAddress().toString());
                    }
                    if (userInfo.getData().getRemark() != null) {
                        pRemake.setText(userInfo.getData().getRemark().toString());
                    }
                    if (userInfo.getData().getPhoneNo() != null) {
                        pPhone.setText(userInfo.getData().getPhoneNo());
                    }
                        int ad = (int) userInfo.getData().getGender();
                        if (ad == 0) {
                            pSex.setText("男");
                        }
                        if (ad == 1) {
                            pSex.setText("女");
                        }

                }
            }
        } else {
            pNick.setText("-");
            pAddress.setText("-");
            pRemake.setText("-");
            pPhone.setText("-");
            pPhone.setText("-");

        }

    }

    @Override
    public void inintData() {
    }


    @OnClick({R.id.p_back, R.id.p_head,  R.id.lin_nick, R.id.lin_remake, R.id.lin_sex, R.id.lin_phone, R.id.lin_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.lin_nick:
                if (loginStru) {
                    // 修改昵称
                    String nick = (String) pNick.getText();
                    Intent intent3 = new Intent(this, NiieEditActivity.class);
                    intent3.putExtra("nick", nick);
                    startActivityForResult(intent3, 12);
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }
                break;
            case R.id.lin_remake:
                if (loginStru) {
                    // 调用系统的相冊
                    Intent intent4 = new Intent(this, RemakeEditActivity.class);
                    startActivityForResult(intent4, 14);
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }

                break;
            case R.id.lin_sex:
                if (loginStru) {
                    // 调用系统的相冊
                    Intent intent = new Intent(this, SexEditActivity.class);
                    startActivityForResult(intent, 10);  //todo 上传头像未测验
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }
                break;
            case R.id.lin_phone:
                if (loginStru) {
                    // 调用系统的相冊
                    String phone = userInfo.getData().getPhoneNo();
                    Intent intent5 = new Intent(this, PhoneSendCodeActivity.class);
                    intent5.putExtra("phone", phone);
                    startActivityForResult(intent5, 15);
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }
                break;
            case R.id.lin_address:

                if (loginStru) {
                    // 调用系统的相冊
                    Intent intent2 = new Intent(this, AddressActivity.class);
                    startActivityForResult(intent2, 11);
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }
                break;
            case R.id.p_back:
                this.finish();
                break;
            case R.id.p_head:

                if (loginStru) {
                    // 调用系统的相冊
                    getPremission2();
                } else {
                    GlobalToast.show("未登录", Toast.LENGTH_LONG);
                }

                break;



        }
    }

    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private String mCameraImagePath;
    //用于保存拍照图片的uri
    private Uri mCameraUri;

    public void showSelectDialogs(final int code, Activity activity) {
        ListDialog dialog = new ListDialog(activity, new ListDialog.DialogItemClickListener() {
            @Override
            public void onItemClik(int position, String id, String text, int chooseIndex) { //1:拍照   2：相册
                if (id.equals("1")) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P || BuildCompat.isAtLeastQ()) {
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断是否有相机
                        if (captureIntent.resolveActivity(getPackageManager()) != null) {
                            File photoFile = null;
                            Uri photoUri = null;
                            // 适配android 10
                            photoUri = createImageUri();
                            mCameraUri = photoUri;
                            if (photoUri != null) {
                                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                PersonalInformationActivity.this.startActivityForResult(captureIntent, 6);
                            }
                        }
                    } else {
                        ImageUtils.openCameraImage(activity, 2);
                        // 是否是Android 10以上手机
                    }
                } else if (id.equals("2")) {
                    ImageUtils.openLocalImage(activity, 3);
                }
            }
        }, photoData, 0);
        dialog.show();
    }

    public static ArrayList<String> photoData = new ArrayList<String>() {{
        add("1@拍照");
        add("2@从相册中选择");
    }};

    public void getPremission2() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ),
                //if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        Log.i("test", "权限获取成功");
                        showSelectDialogs(2, PersonalInformationActivity.this);
                    }

                    @Override
                    public void onPermissionDenied(final Permission[] refusedPermissions) {
                        Toast.makeText(PersonalInformationActivity.this, refusedPermissions[0].toString() +
                                " 权限获取失败", Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(PersonalInformationActivity.this).setTitle("提示").setMessage("如果你拒绝了权限,应用中的一些功能将不糊能正常使用")
                                .setPositiveButton("授予权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //用户点击以后
                                        boolean ff = PanduanIsProhibitedPermissionDenied(refusedPermissions);
                                        if (!ff) {
                                            SoulPermission.getInstance().goApplicationSettings(new GoAppDetailCallBack() {
                                                @Override
                                                public void onBackFromAppDetail(Intent data) {
                                                    Log.i("test", "这里是在设置也手动获取到权限以后返回，回调");
                                                    showSelectDialogs(2, PersonalInformationActivity.this);
                                                }
                                            });
                                        } else {
                                            getPremission2();
                                        }
                                    }
                                }).create().show();
                    }
                });
    }

    private Boolean PanduanIsProhibitedPermissionDenied(Permission[] refusedPermissions) {
        boolean flag = true;
        for (int i = 0; i < refusedPermissions.length; i++) {
            if (!refusedPermissions[i].shouldRationale()) {
                flag = false;
                return flag;
            }
        }
        return flag;

    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private Uri createImageUri() {
        String status = Environment.getExternalStorageState();
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    List<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("photo", "requestCode==" + requestCode + "resultCode==" + resultCode);
        if (resultCode == -1) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap2 = null;
                        Uri uri = null;
                        String path = null;
                        if (requestCode == 2) {   //申请资料拍照
                            uri = ImageUtils.imageUriFromCamera;
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                                bitmap2 = get_bmp(bitmap, 200, 200);//对图片进行压缩
                                path = FileUtils.saveBitmap(PersonalInformationActivity.this, bitmap2);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }


                        } else if (requestCode == 3) {  //申请资料从相册中选择
                            if (data != null && data.getData() != null) {
                                uri = data.getData();
                                Bitmap bitmap = null;
                                try {
                                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                                    Thread.sleep(1000);
                                    bitmap2 = get_bmp(bitmap, 200, 200);//对图片进行压缩
                                    path = FileUtils.saveBitmap(PersonalInformationActivity.this, bitmap2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                return;
                            }
                        } else if (requestCode == 6) {
                            Log.i("photo", resultCode + "");
                            if (resultCode == RESULT_OK) {
//                      // Android 10 使用图片uri加载
                                //将图片内容解析成字节数组
                                ContentResolver resolver = getContentResolver();
                                try {
                                    byte[] mContent = readStream(resolver.openInputStream(Uri.parse(mCameraUri.toString())));
                                    if (mContent != null) {
                                        //将字节数组转换为ImageView可调用的Bitmap对象
                                        Bitmap myBitmap2 = getPicFromBytes(mContent, null);
                                        Bitmap myBitmap = get_bmp(myBitmap2, 200, 200);//对图片进行压缩
                                        path = FileUtils.saveBitmap(PersonalInformationActivity.this, myBitmap);
                                        Log.i("test", path);
                                    } else {
                                        return;
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        File file = new File(path);
                        if (file != null || file.exists()) {
                            String web_path = AppConfig.BASE_URL + "api/Sys_User/Upload";
                            OkGo.<String>post(web_path).headers("Authorization", "Bearer " + Constant.token).params("fileInput", file)
                                    .isMultipart(true).execute(new StringCallback() {
                                //{"status":true,"code":200,"message":"文件上传成功","data":"Upload/Files/Livelihood_Feedback/3523cb85fcdc4d728b9194f4748fdb23/small"}
                                @Override
                                public void onSuccess(Response<String> response) {
                                    try {
                                        Log.i("test", "头像上传图片成功" + response.body());
                                        JSONObject jsonObject = new JSONObject(response.body());
                                        int code = jsonObject.getInt("code");
                                        String data = jsonObject.getString("data");
                                        String iamg = data + "/" + file.getName();

                                        Map<String, String> areaMap = new HashMap<String, String>();
                                        areaMap.put("NewHeadImageUrl",iamg);
                                        if(userInfo==null||userInfo.getData()==null||userInfo.getData().getHeadImageUrl()==null){
                                            areaMap.put("OldHeadImageUrl", "");
                                        }else{
                                            areaMap.put("OldHeadImageUrl", userInfo.getData().getHeadImageUrl().toString());
                                        }
                                        CommApi.post("api/Sys_User/UpdateUserUserHeadImageUrl", areaMap).execute(new StringCallback() {
                                            @Override
                                            public void onSuccess(Response<String> response) {
                                                //{"status":true,"code":200,"message":"头像修改提交成功，请等待审核","data":"Upload/Files/Sys_User/af044437c03149e8905a0af669b8df00/small/temp.jpg"}
                                                if (response.body() != null) {
                                                    Log.i("test", "修改头像成功-->");

                                                    try {
                                                        JSONObject jsonObject1 = new JSONObject(response.body());
                                                        int code = jsonObject1.getInt("code");
                                                        String data = jsonObject1.getString("data");
                                                        String message = jsonObject1.getString("message");
                                                        GlobalToast.show(message, Toast.LENGTH_LONG);
                                                        RequestOptions options = new RequestOptions()
                                                                .placeholder(R.mipmap.default_image)
                                                                .error(R.mipmap.default_image)
                                                                .centerCrop()
                                                                .skipMemoryCache(true)
                                                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                                                .circleCrop();//加载成圆形
                                                        Glide.with(PersonalInformationActivity.this).load(AppConfig.BASE_PICTURE_URL + data + "?rom=" + Math.random())
                                                                .apply(options)
                                                                .into(pHead);

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

//
                                                }
                                            }

                                            @Override
                                            public void onError(Response response) {
                                                super.onError(response);
                                                Log.i("test", "修改头像失败-->");
                                            }
                                        });


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    Log.i("test", "上传失败");
                                }
                            });

                        } else {
                            GlobalToast.show("文件不存在,上传失败", 5000);
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == 10) {
            String sex = data.getStringExtra("sex");
            UserInfoBean.UserInfo uu = userInfo.getData();
            if (sex.equals("男")) {
                uu.setGender(0);
            } else {
                uu.setGender(1);
            }
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pSex.setText(sex);

        } else if (resultCode == 11) {

            String address = data.getStringExtra("address");
            UserInfoBean.UserInfo uu = userInfo.getData();
            uu.setAddress(address);
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pAddress.setText(address);
        } else if (resultCode == 12) {
            String nickname = data.getStringExtra("nickname");

            UserInfoBean.UserInfo uu = userInfo.getData();
            uu.setUserName(nickname);
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pNick.setText(nickname);
        } else if (resultCode == 13) {
            String remake = data.getStringExtra("remake");
            UserInfoBean.UserInfo uu = userInfo.getData();
            uu.setRemark(remake);
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pRemake.setText(remake);
        } else if (resultCode == 15) {
            String phone = data.getStringExtra("phone");
            UserInfoBean.UserInfo uu = userInfo.getData();
            uu.setPhoneNo(phone);
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pPhone.setText(phone);
        } else if (resultCode == 86) {
            String phone = data.getStringExtra("phone");
            UserInfoBean.UserInfo uu = userInfo.getData();
            uu.setPhoneNo(phone);
            userInfo.setData(uu);
            aCache.put("userinfo", userInfo);
            pPhone.setText(phone);
        }
    }

    /**
     * 图片等比压缩
     */
    public static Bitmap get_bmp(Bitmap bmp, int reqWidth, int reqHeight) {
        if (bmp.getWidth() > reqWidth || bmp.getHeight() > reqHeight) {
            float f_Ratio = 0;//比例值
            f_Ratio = (float) reqWidth / bmp.getWidth();//计算宽的缩放比例
            if (bmp.getHeight() * f_Ratio < reqHeight) {//内容可以在控件里显示全
            } else {//高超出控件,使用高计算缩放比例
                f_Ratio = (float) reqHeight / bmp.getHeight();//计算高的缩放比例
            }
            //缩放图片
            if (f_Ratio > 0) bmp = bmp_sf(bmp, f_Ratio, f_Ratio);
        }
        return bmp;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    /**
     * 缩放图片
     *
     * @param bmp
     * @param sf_w 缩放宽比例
     * @param sf_h 缩放高比例
     * @return 缩放后图片
     */
    public static Bitmap bmp_sf(Bitmap bmp, float sf_w, float sf_h) {
        if (sf_w == 1 && sf_h == 1) {//缩放比例为1时不进行缩放
            return bmp;
        }
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(sf_w, sf_h);
        //得到新的图片
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        ACache aCache = ACache.get(this);
        aCache.put("userinfo", userInfoBean);
        this.userInfo = userInfoBean;

    }



}