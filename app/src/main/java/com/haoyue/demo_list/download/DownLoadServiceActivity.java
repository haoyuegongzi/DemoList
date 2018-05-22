package com.haoyue.demo_list.download;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownLoadServiceActivity extends AppCompatActivity {

    @BindView(R.id.btStartDown)
    Button mBtStartDown;
    @BindView(R.id.btPauseDown)
    Button mBtPauseDown;
    @BindView(R.id.btCancelDown)
    Button mBtCancelDown;

    DownLoadService.DownLoadBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_service);
        ButterKnife.bind(this);
        bindService();
    }

    private void bindService(){
        Intent intent = new Intent(this, DownLoadService.class);
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(DownLoadServiceActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownLoadServiceActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (DownLoadService.DownLoadBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @OnClick({R.id.btStartDown, R.id.btPauseDown, R.id.btCancelDown})
    public void onClick(View view) {
        if (mBinder == null){
            return;
        }
        switch (view.getId()) {
            case R.id.btStartDown:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                mBinder.startDownload(url);
                break;
            case R.id.btPauseDown:
                mBinder.pauseDownload();
                break;
            case R.id.btCancelDown:
                mBinder.cancelDownload();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(DownLoadServiceActivity.this, "拒绝该权限后将无法下载该应用", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
