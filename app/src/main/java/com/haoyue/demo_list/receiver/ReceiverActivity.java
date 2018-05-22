package com.haoyue.demo_list.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceiverActivity extends AppCompatActivity {
    @BindView(R.id.btCustomReceive)
    Button mBtCustomReceive;
    @BindView(R.id.btCustomOrderReceive)
    Button mBtCustomOrderReceive;

    NetworkChangeReceiver networkChange;
    LocalBroadcastManager mLocalBroadcastManager;
    LocalReceiver mLocalReceiver;

    @BindView(R.id.btLocalReceive)
    Button mBtLocalReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        ButterKnife.bind(this);

//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChange = new NetworkChangeReceiver();
//        registerReceiver(networkChange, filter)//注册

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        //广播注册
        IntentFilter filter =  new IntentFilter();
        filter.addAction("LocalBroadcast.LocalReceiver.LOCAL_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, filter);

        doSomething();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(networkChange);//解注
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    @OnClick({R.id.btCustomReceive, R.id.btCustomOrderReceive, R.id.btLocalReceive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btCustomReceive:
                Intent intent = new Intent("wujinjianzhuangbiwang");
                sendBroadcast(intent);
                break;
            case R.id.btCustomOrderReceive:
                Intent intentOrder = new Intent("wujinjianzhuangbiwang");
                sendOrderedBroadcast(intentOrder, null);
                break;
            case R.id.btLocalReceive:
                Intent localIntent = new Intent("LocalBroadcast.LocalReceiver.LOCAL_BROADCAST");
                mLocalBroadcastManager.sendBroadcast(localIntent);//无效
                break;
        }
    }

    private int doSomething(){
        Log.i("TAG", "doSomething: ");
        return  0;
    }
}