package com.rcdz.medianewsapp.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/09/29.
 * 存放下载图片和保存xml的方法
 */

public class FileUtils {

    //标识，看下载图片是否下载完毕   //0未执行，1，未更新图片，2更新完毕
//    static int flag = 0;

    public static String firstxml ="<NewDataSet>\n"+
            "  <xs:schema id=\"NewDataSet\" xmlns=\"\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:msdata=\"urn:schemas-microsoft-com:xml-msdata\">\n"+
            "    <xs:element name=\"NewDataSet\" msdata:IsDataSet=\"true\" msdata:MainDataTable=\"Table\" msdata:UseCurrentLocale=\"true\">\n"+
            "      <xs:complexType>\n"+
            "        <xs:choice minOccurs=\"0\" maxOccurs=\"unbounded\">\n"+
            "          <xs:element name=\"Table\">\n"+
            "            <xs:complexType>\n"+
            "              <xs:sequence>\n"+
            "                <xs:element name=\"id\" type=\"xs:int\" minOccurs=\"0\" />\n"+
            "                <xs:element name=\"code\" type=\"xs:string\" minOccurs=\"0\" />\n"+
            "                <xs:element name=\"name\" type=\"xs:string\" minOccurs=\"0\" />\n"+
            "                <xs:element name=\"path\" type=\"xs:string\" minOccurs=\"0\" />\n"+
            "                <xs:element name=\"ver\" type=\"xs:int\" minOccurs=\"0\" />\n"+
            "                <xs:element name=\"parentcode\" type=\"xs:string\" minOccurs=\"0\" />\n"+
            "              </xs:sequence>\n"+
            "            </xs:complexType>\n"+
            "          </xs:element>\n"+
            "        </xs:choice>\n"+
            "      </xs:complexType>\n"+
            "    </xs:element>\n"+
            "  </xs:schema>\n"+
            "  <Table>\n"+
            "    <id></id>\n"+
            "    <code></code>\n"+
            "    <name></name>\n"+
            "    <path></path>\n"+
            "    <ver></ver>\n"+
            "    <parentcode>app</parentcode>\n"+
            "  </Table>\n"+
            "</NewDataSet>";
    //得到xml的路径
    public static String getXmlPath(Context context){
        String path = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory() + File.separator + "rcdownload/";
        }else {
            path = context.getFilesDir() + File.separator +"rcdownload/";
        }

        return path;

    }
    public static String saveBitmap(Context context, Bitmap mBitmap, String filename) {
        String SD_PATH = "/sdcard/dskqxt/pic/";
       String IN_PATH = "/dskqxt/pic/";

       String imagepath= Environment.getExternalStorageDirectory()+"/"+"tempimage/";
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;

        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(imagepath +filename+".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String SD_PATH = "/sdcard/dskqxt/pic/";
        String IN_PATH = "/dskqxt/pic/";

        String imagepath= Environment.getExternalStorageDirectory()+"/"+"tempimage/";
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SD_PATH;

        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath()
                    + IN_PATH;
        }
        try {
            filePic = new File(imagepath +"temp.jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }

    //拼接path 长度+内容的拼接
    public static byte[] byteIntContent(String path){
        byte[] tempcontent;
        byte[] templength;
        byte[] tempall;
        byte[] bitmapall = null;
        tempcontent = path.getBytes();
        templength =  intToBytes(tempcontent.length);
        tempall = byteMerger(templength,tempcontent);
        if (bitmapall == null){
            bitmapall = tempall;
        }else {
            bitmapall = byteMerger(bitmapall, tempall);
        }
        return bitmapall;
    }

    //拼接path 长度+内容的拼接
    public static byte[] byteIntContentList(ArrayList<String> path){
        byte[] tempcontent;
        byte[] templength;
        byte[] tempall;
        byte[] bitmapall = null;
        for (int i = 0; i < path.size(); i++) {
            tempcontent = path.get(i).getBytes();
            templength =  intToBytes(tempcontent.length);
            tempall = byteMerger(templength,tempcontent);
            if (bitmapall == null){
                bitmapall = tempall;
            }else {
                bitmapall = byteMerger(bitmapall, tempall);
            }
        }

        return bitmapall;
    }

    //int转byte[]
    public static byte[] intToBytes(int value) {
        byte[] byte_src = new byte[4];
        byte_src[3] = (byte) ((value & 0xFF000000) >> 24);
        byte_src[2] = (byte) ((value & 0x00FF0000) >> 16);
        byte_src[1] = (byte) ((value & 0x0000FF00) >> 8);
        byte_src[0] = (byte) ((value & 0x000000FF));
        return byte_src;
    }

    //拼接数组
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }


    //使用byte[]返回图片
    public static Bitmap getBitmapFromByte(byte[] temp){
        if(temp != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        }else{
            return null;
        }
    }



    //返回图片结果类
    class downloadimage{
        String id;
        String code;
        String name;
        String path;
        String ver;
        String parentcode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getVer() {
            return ver;
        }

        public void setVer(String ver) {
            this.ver = ver;
        }

        public String getParentcode() {
            return parentcode;
        }

        public void setParentcode(String parentcode) {
            this.parentcode = parentcode;
        }
    }

}
