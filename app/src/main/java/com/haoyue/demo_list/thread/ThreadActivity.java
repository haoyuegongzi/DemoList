package com.haoyue.demo_list.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haoyue.demo_list.R;
import com.haoyue.demo_list.operatecheckres.EventCheckerMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import static com.haoyue.demo_list.R.id.tvTextThread;

public class ThreadActivity extends AppCompatActivity {
    @BindView(tvTextThread)
    TextView mTvTextThread;
    @BindView(R.id.btChildThreadEventBus)
    Button mBtChildThreadEventBus;
    @BindView(R.id.btChildThread_RunOnUiThread_Inter)
    Button mBtChildThreadRunOnUiThreadInter;
    @BindView(R.id.btRunOnUiThread_Inter)
    Button mBtRunOnUiThreadInter;
    @BindView(R.id.btRunOnUiThread_EventBus)
    Button mBtRunOnUiThreadEventBus;
    @BindView(R.id.btUpdateUI_Handller)
    Button mBtUpdateUIHandller;
    @BindView(R.id.btUpdateUI_EventBus)
    Button mBtUpdateUIEventBus;
    @BindView(R.id.btRunOnUiThread)
    Button mBtRunOnUiThread;
    @BindView(R.id.btInterface)
    Button mBtInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    InterfaceThread mThread = new InterfaceThread() {
        @Override
        public void update(String string) {
            mTvTextThread.setText(string);
            Log.i("TAG", "子线程回调接口后接口运行的线程：==" + Thread.currentThread() + "\n string ==" + string);
        }
    };

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mTvTextThread.setText("子线程通过Handler更新UI的测试成功");
                    break;
                default:
                    break;
            }
        }
    };

    public void onEventMainThread(EventThreadMsg event) {
        String msg = event.string + "\n 灰色";
        mTvTextThread.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btChildThreadEventBus, R.id.btChildThread_RunOnUiThread_Inter,
            R.id.btRunOnUiThread_Inter, R.id.btRunOnUiThread_EventBus,
            R.id.btUpdateUI_Handller, R.id.btUpdateUI_EventBus, R.id.btRunOnUiThread, R.id.btInterface})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btChildThreadEventBus:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventCheckerMsg("子线程new Thread + EventBus更新UI的测试成功"));
                    }
                }).start();
                break;
            case R.id.btChildThread_RunOnUiThread_Inter:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ThreadActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mThread.update("子线程 ---→ runOnUiThread ---→Interface接口更新UI的测试成功");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.btRunOnUiThread_Inter:
                ThreadActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mThread.update("runOnUiThread线程 + Interface接口更新UI");
                    }
                });
                break;
            case R.id.btRunOnUiThread_EventBus:
                ThreadActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventCheckerMsg("runOnUiThread + EventBus更新UI的测试成功"));
                    }
                });
                break;
            case R.id.btUpdateUI_Handller:
                Message msg = new Message();
                msg.what = 1;
                mHandler.sendMessage(msg);
                break;
            case R.id.btUpdateUI_EventBus:
                EventBus.getDefault().post(new EventCheckerMsg("通过EventBus更新UI的测试成功"));
                break;
            case R.id.btRunOnUiThread:
                mTvTextThread.setText("通过RunOnUiThread更新UI测试成功");
                break;
            case R.id.btInterface:
                mThread.update("通过Interface接口回调更新UI的测试成功");
                break;
            default:
                mThread.update("通过Interface接口回调更新UI的测试成功");
                break;
        }
    }
}
