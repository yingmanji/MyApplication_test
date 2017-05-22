package com.example.a0_0.myapplication_test;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.a0_0.myapplication_test.service.DownloadFileService;

import java.io.File;

public class DownloadServiceActivity extends Activity {
    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_service);
    }
    public void onClick(View v) {
        //System.out.println(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS));
        // new CheckedUpdate().execute();
        String url = "http://gdown.baidu.com/data/wisegame/0852f6d39ee2e213/QQ_676.apk";
        Intent downloadIntent = new Intent(this, DownloadFileService.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        downloadIntent.putExtras(bundle);
        startService(downloadIntent);
        // 设置广播接收器，当新版本的apk下载完成后自动弹出安装界面
        IntentFilter intentFilter = new IntentFilter("com.test.downloadComplete");
        receiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                Intent install = new Intent(Intent.ACTION_VIEW);
                String pathString = intent.getStringExtra("downloadFile");
                install.setDataAndType(Uri.fromFile(new File(pathString)), "application/vnd.android.package-archive");
                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(install);
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    protected void onDestroy() {
        // 移除广播接收器
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
