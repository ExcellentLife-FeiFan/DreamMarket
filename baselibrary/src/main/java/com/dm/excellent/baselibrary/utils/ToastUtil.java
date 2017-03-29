package com.dm.excellent.baselibrary.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Excellent on 2016/4/12.
 */
public class ToastUtil {

    public static void showToastShort(Context context, String txt) {
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String txt) {
        Toast.makeText(context, txt, Toast.LENGTH_LONG).show();
    }

    public static void showDialog2Btn(Context context, String title, DialogInterface.OnClickListener oklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", oklistener);
        builder.setCancelable(true);
        builder.show();
    }

    public static void showDialog2Btn(Context context, String title, DialogInterface.OnClickListener oklistener, DialogInterface.OnClickListener cancellistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setNegativeButton("取消", cancellistener);
        builder.setPositiveButton("确定", oklistener);
        builder.setCancelable(true);
        builder.show();
    }
    public static void showDialog2Btn(Context context, String title, String btnTxt1, String btnTxt2, DialogInterface.OnClickListener oklistener, DialogInterface.OnClickListener cancellistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setNegativeButton(btnTxt2, cancellistener);
        builder.setPositiveButton(btnTxt1, oklistener);
        builder.setCancelable(true);
        builder.show();
    }

    public static void showDialog1Btn(Context context, String title, DialogInterface.OnClickListener oklistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setPositiveButton("确定", oklistener);
        builder.setCancelable(true);
        builder.show();
    }

/*    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("正在缓存在线音乐....");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    public static void showProgressDialog(Context context, String txt) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(txt);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }

    }*/

}

