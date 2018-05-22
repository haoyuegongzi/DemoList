package com.haoyue.demo_list.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by chen1 on 2017/8/2.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivity.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isAvailable()){
            Toast.makeText(context, "network is isAvailable", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "network is unAvailable", Toast.LENGTH_SHORT).show();
        }
    }
}
