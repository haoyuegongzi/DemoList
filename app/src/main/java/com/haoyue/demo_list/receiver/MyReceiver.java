package com.haoyue.demo_list.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chen1 on 2017/8/1.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "MyReceiver————onReceive: acyion ===" + action);
//        abortBroadcast();//阶段广播的继续传递
    }
}
