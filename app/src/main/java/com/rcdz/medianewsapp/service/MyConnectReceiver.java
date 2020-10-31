package com.rcdz.medianewsapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class MyConnectReceiver extends BroadcastReceiver {
    public int flag = 0;
    private int flag1 = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast toast = Toast.makeText(context, "网络状态发生改变", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

//        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
//        if (activeNetworkInfo == null) {
//            Toast.makeText(context, "当前无网络，请检查移动设备的网络连接", Toast.LENGTH_SHORT).show();
//            flag = 1;
//            flag1 = 1;
//        }
//        // activeNetworkInfo.getTypeName(); 以何种方式连线
//        // :cmwap/cmnet/wifi/uniwap/uninet
//        // activeNetworkInfo.isAvailable(); 当前网络是否可用(true)
//        // activeNetworkInfo.isFailover();网络有问题
//        else {
//            if (!activeNetworkInfo.isAvailable() || activeNetworkInfo.isFailover()) {
//                Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show();
//                flag = 1;
//                flag1 = 2;
//            }
//            if (flag == 1) {
//                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                    Toast.makeText(context, "已连接上移动数据", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "已连接上WIFI数据", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
    }
}
