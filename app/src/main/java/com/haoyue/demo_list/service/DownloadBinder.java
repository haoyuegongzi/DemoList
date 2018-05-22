package com.haoyue.demo_list.service;

import android.os.Binder;
import android.util.Log;

/**
 *
 * @author chen1
 * @date 2017/7/31
 */

public class DownloadBinder extends Binder {
    public void startDownload(){
        Log.i("TAG", "startDownload: " + getClass().getSimpleName());
    }

    public int getProcess(){
        Log.i("TAG", "getProcess: " + getClass().getSimpleName());
        return 0;
    }
}
