package com.rcdz.medianewsapp.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.CommonAdapter;
import com.rcdz.medianewsapp.view.customview.ListDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 类似9宫格图片拍照、从相册选择、删除、放大等功能
 */
public class GridImageUtil {

    //图片选项：拍照/从相册选择
    public static ArrayList<String> photoData = new ArrayList<String>() {{
        add("1@拍照");
        add("2@从相册中选择");
    }};
    public static Bitmap bmp; // 导入临时图片
    public static ArrayList<HashMap<String, Object>> imageItem;
    public static CommonAdapter commonAdapter;
    public static ArrayList<String> photopath = new ArrayList<>();//图片地址

    /**
     * 默认添加“+”图片
     *
     * @param activity
     */
    public static void initData(Activity activity) {

        bmp = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.insert); // 加号
        imageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        commonAdapter = new CommonAdapter<HashMap<String, Object>>(R.layout.image, imageItem) {
            @Override
            public void bindView(ViewHolder holder, HashMap obj) {
                if (holder.getItemPosition() == 0) {
                    holder.setVisibile(R.id.delete_image, View.INVISIBLE);
                    holder.setImage(R.id.upload_image, (Bitmap) obj.get("itemImage"));
                } else {
                    holder.setImage(R.id.upload_image, (Bitmap) obj.get("itemImage"));
                }
            }
        };
    }

    /**
     * 删除图片
     *
     * @param position
     * @param activity
     */
    public static void dialog(final int position, Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                photopath.remove(position - 1);
                imageItem.remove(position);
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
     * @param activity
     */
    public static void init(ImageView view, Activity activity) {

        Dialog dialog = new Dialog(activity, R.style.FullActivity);
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(attributes);

        ImageView image = new ImageView(activity);
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
     * Grid中每个item图片的操作处理
     *
     * @param view
     * @param position
     * @param activity
     */
    public static void itemClick(View view, int position, Activity activity, int num) {
        ImageView imageview = view.findViewById(R.id.upload_image);
        ImageView deleteButton = view.findViewById(R.id.delete_image);
        if (imageItem.size() == num + 1) { // 第一张为默认图片
            if (position != 0) {
                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        init(imageview, activity);
                    }
                });
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog(position, activity);
                    }
                });
            } else {
                Toast.makeText(activity, "图片数" + num + "张已满", Toast.LENGTH_SHORT).show();
            }
        } else if (position == 0) { // 点击图片位置为+ 0对应0张图片
            // 选择图片
            showSelectDialogs(2, activity);
        } else {
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    init(imageview, activity);
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog(position, activity);
                }
            });
        }
    }

    private static Uri createImageUri(Activity activity) {
        String status = Environment.getExternalStorageState();
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return activity.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    public Uri getImageUri(Activity activity) {
        Uri imageUri = null;
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        String path = file.getPath();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(file);
        } else {
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, path);
            imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
        return imageUri;
    }

    static Uri photoUri;

    /**
     * 选择拍照或是从相册选取照片
     */
    public static void showSelectDialogs(final int code, Activity activity) {
        ListDialog dialog = new ListDialog(activity, new ListDialog.DialogItemClickListener() {
            @Override
            public void onItemClik(int position, String id, String text, int chooseIndex) {//1:拍照   2：相册
                if (id.equals("1")) {
                    File outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");    //内部存储设备/Android/data/com.example.choosepictest/cache/output_image.jpg
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
                        photoUri = FileProvider.getUriForFile(activity,
                                "com.privatee.wjtbaseapp.fileprovider", outputImage);
                    } else {
                        photoUri = Uri.fromFile(outputImage);
                    }
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    activity.startActivityForResult(intent, 2);
                } else if (id.equals("2")) {
                    ImageUtils.openLocalImage(activity, 3);
                }
            }
        }, photoData, 0);
        dialog.show();
    }

    /**
     * Grid中图片结果处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void imageResult(int requestCode, int resultCode, Intent data, Activity activity) {
        //当上传资料拍照时，将图片加到GridView中显示
        String path;
        Bitmap bitmap2 = null;
        Uri uri = null;
        try {
            if (requestCode == 2) {  //申请资料拍照
                File outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");
                String prent = Environment.getExternalStorageDirectory() + "/temp";
                if (!new File(prent).exists()) {
                    new File(prent).mkdirs();
                }
                path = Environment.getExternalStorageDirectory() + "/temp/" + Math.abs(Math.random()) + ".jpg";
                copyFile(outputImage.getAbsolutePath(), path);
                bitmap2 = BitmapFactory.decodeFile(outputImage.getAbsolutePath());
                if (bitmap2 != null) {
                    bitmap2 = get_bmp(bitmap2, 200, 200);//对图片进行压缩
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("itemImage", bitmap2);
                    imageItem.add(map);
                    photopath.add(path);
                }
            } else if (requestCode == 3) {  //申请资料从相册中选择
                uri = data.getData();
                bitmap2 = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
                if (bitmap2 != null) {
                    bitmap2 = get_bmp(bitmap2, 200, 200);//对图片进行压缩
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("itemImage", bitmap2);
                    imageItem.add(map);
                     path= ImageUtils.saveFile(bitmap2);
                    photopath.add(path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        commonAdapter = new CommonAdapter<HashMap<String, Object>>(R.layout.image, imageItem) {
            @Override
            public void bindView(ViewHolder holder, HashMap obj) {
                if (holder.getItemPosition() == 0) {
                    holder.setVisibile(R.id.delete_image, View.INVISIBLE);
                    holder.setImage(R.id.upload_image, (Bitmap) obj.get("itemImage"));
                } else {
                    holder.setImage(R.id.upload_image, (Bitmap) obj.get("itemImage"));
                }
            }
        };
        commonAdapter.notifyDataSetChanged();


    }


    /**
     * 复制文件到另一个路径下
     *
     * @param readfile
     * @param writefile
     * @return
     * @throws Exception
     */
    public static boolean copyFile(String readfile, String writefile) throws Exception {
        if (!new File(writefile).exists()) {
            new File(writefile).createNewFile();
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        // 定义两个直连的文件管道
        FileChannel in = null, out = null;
        fis = new FileInputStream(readfile);
        fos = new FileOutputStream(writefile);
        in = fis.getChannel();
        out = fos.getChannel();
        // 直接让流流向要拷贝的文件
        out.transferFrom(in, 0, in.size());
        in.close();
        out.close();
        fis.close();
        fos.close();
        return true;
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
}
