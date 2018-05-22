package com.haoyue.demo_list.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.haoyue.demo_list.R;

import java.io.File;

/**
 * Created by chen1 on 2017/8/1.
 */

public class DownLoadService extends Service {
    private DownLoadTask mDownLoadTask = null;
    private String downLoadUrl;

    DownLoadBinder mBinder = new DownLoadBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    DownLoadListener mListener = new DownLoadListener(){
        @Override
        public void onProcess(int process) {
            getNotificationManager().notify(1, getNotification("DownLoading......", process));
        }

        @Override
        public void onSuccess() {
            mDownLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("DownLoad Success", -1));
            Toast.makeText(DownLoadService.this, "DownLoad Success", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed() {
            mDownLoadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("DownLoad Failed", -1));
            Toast.makeText(DownLoadService.this, "DownLoad Failed", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPause() {
            mDownLoadTask = null;
            Toast.makeText(DownLoadService.this, "DownLoad Pause", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            mDownLoadTask = null;
            stopForeground(true);
            Toast.makeText(DownLoadService.this, "DownLoad Cancel", Toast.LENGTH_LONG).show();
        }
    };

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        Intent intent = new Intent(this, DownLoadServiceActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this, 0 ,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pending);
        builder.setContentTitle(title);
        if(progress > 0){
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }


    class DownLoadBinder extends Binder {
        public void startDownload(String url){
            if(mDownLoadTask == null){
                downLoadUrl = url;
                mDownLoadTask = new DownLoadTask(mListener);
                mDownLoadTask.execute(downLoadUrl);
                startForeground(1, getNotification("DownLoading......", 0));
                Toast.makeText(DownLoadService.this, "DownLoading......", Toast.LENGTH_LONG).show();
            }
        }

        public void pauseDownload(){
            if(mDownLoadTask != null) {
                mDownLoadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(mDownLoadTask != null) {
                mDownLoadTask.cancelDownload();
            } else {
                if(downLoadUrl != null) {
                    String fileName = downLoadUrl.substring(downLoadUrl.lastIndexOf("/"));
                    //将文件下载到Environment.DIRECTORY_DOWNLOADS目录，也就是SD卡的download目录；
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownLoadService.this, "DownLoading Cancel", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
