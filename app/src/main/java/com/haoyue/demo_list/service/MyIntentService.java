package com.haoyue.demo_list.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 *
 * @author chen1
 * @date 2017/8/1
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
        Log.i("TAG", "IntentService的构造方法：IntentService");
        Log.i("TAG", "IntentService的构造方法 ===" + Thread.currentThread() + "\n" +
                Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("TAG", "IntentService的onHandleIntent方法：onHandleIntent");
        Log.i("TAG", "onHandleIntent执行的线程 ===" + Thread.currentThread() + "\n" +
                                                      Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "IntentService的onDestroy方法：onDestroy");
        Log.i("TAG", "onDestroy执行的线程 ===" + Thread.currentThread() + "\n" +
                Thread.currentThread().getName());
    }
}
