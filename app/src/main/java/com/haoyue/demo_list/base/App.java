package com.haoyue.demo_list.base;

import android.app.Application;

import com.haoyue.demo_list.CrashHandler.CrashHandler;

/**
 * 作者：chen1 on 2018/3/2 14
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
