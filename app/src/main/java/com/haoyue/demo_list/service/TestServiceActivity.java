package com.haoyue.demo_list.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haoyue.demo_list.R;
import com.haoyue.demo_list.thread.EventThreadMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 服务的练习：启动，结束，绑定，解绑；
 *
 * @author chen1
 */
public class TestServiceActivity extends AppCompatActivity {

    @BindView(R.id.btStartService)
    Button mBtStartService;
    @BindView(R.id.btStopService)
    Button mBtStopService;
    @BindView(R.id.tvService)
    TextView mTvService;
    @BindView(R.id.btBindService)
    Button mBtBindService;
    @BindView(R.id.btUnBindService)
    Button mBtUnBindService;
    @BindView(R.id.btIntentService)
    Button mBtIntentService;
    @BindView(R.id.btTestBindService)
    Button btTestBindService;

    private DownloadBinder down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btStartService, R.id.btStopService, R.id.btBindService, R.id.btUnBindService,
              R.id.btIntentService, R.id.btTestBindService})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btStartService:
                /***********作为四大组件之一的服务，也是运行在主线程（MainThread）中的************/
                //第一次点击：会创建服务，调用onCreate方法并启动服务并启动服务，调用onStartCommand方法
                //之后无论点击多少次按钮，该服务都不会再被创建，只会启动服务。
                Intent start = new Intent(TestServiceActivity.this, MyService.class);
                //启动服务
                startService(start);
                break;
            case R.id.btStopService:
                Intent stop = new Intent(TestServiceActivity.this, MyService.class);
                //结束服务，调用onDestroy方法
                stopService(stop);
                break;
            //绑定服务
            case R.id.btBindService:
                Intent bindService = new Intent(TestServiceActivity.this, MyService.class);
                //启动服务
                startService(bindService);
                bindService(bindService, mConnection, BIND_AUTO_CREATE);
                break;
            //解除绑定
            case R.id.btUnBindService:
                unbindService(mConnection);
                break;
            case R.id.btIntentService:
                Intent intentService = new Intent(TestServiceActivity.this, MyIntentService.class);
                startService(intentService);
                Thread mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG", "Activity的btIntentService事件执行的线程 === " + Thread.currentThread() + "\n" +
                                Thread.currentThread().getName() + "\n" +
                                Thread.currentThread().getId());
                    }
                });
                mThread.start();
                //Thread[Thread-548,5,main]
                Log.i("TAG", "Activity的btIntentService事件***子线程***执行的线程 === " +
                        mThread.currentThread() + "\n" +
                        mThread.currentThread().getName() + "\n" +
                        mThread.currentThread().getId());
                break;
            case R.id.btTestBindService:

                break;
            default:
                break;
        }
    }

    public void onEventMainThread(EventThreadMsg msg) {
        mTvService.setText("服务的执行情况：" + msg.string);
    }

    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            down = (DownloadBinder) iBinder;
            down.startDownload();
            down.getProcess();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
