package com.haoyue.demo_list.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.haoyue.demo_list.operatecheckres.EventCheckerMsg;

import de.greenrobot.event.EventBus;

/**
 *
 * @author chen1
 * @date 2017/7/14
 */

public class MyService extends Service {
    DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAG", "onBind——这里做点什么呢？");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", "onCreate——创建了服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //如果我们希望服务一旦启动就执行某个操作，那么具体的操作逻辑就可以写在这里
        Log.i("TAG", "onStartCommand——启动了服务");
        EventBus.getDefault().post(new EventCheckerMsg("onCreate——创建了服务" + "***之后又***" + "onStartCommand——启动了服务"));
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("TAG", "onUnbind——解绑了服务");
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "onDestroy——结束了服务");
        EventBus.getDefault().post(new EventCheckerMsg("onDestroy——结束了服务"));
    }
}
