package com.haoyue.demo_list.operatecheckres;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoyue.demo_list.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class OperateCheckresActivity extends AppCompatActivity {

    @BindView(R.id.llOperateCheckres)
    LinearLayout llOperateCheckres;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.tvUnCheck)
    TextView tvUnCheck;
    @BindView(R.id.tvCancel)
    TextView tvCancel;

    CanvasCheckres canvasCheckres;
    int checker = 1;
    int unChecker = 13;
    String checkOrder = "checkOrder";
    String unCheckOrder = "checkReverse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_operate_checkres);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        canvasCheckres = new CanvasCheckres(OperateCheckresActivity.this);
        llOperateCheckres.addView(canvasCheckres);
    }

    @OnClick({R.id.tvCheck, R.id.tvUnCheck, R.id.tvCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCheck:
                ScheduledExecutorService ScheduledCheck = Executors.newScheduledThreadPool(2);
                ScheduledCheck.scheduleAtFixedRate(new MyScheduledExecutor(checkOrder), 100, 40, TimeUnit.MILLISECONDS);
                ScheduledCheck = null;
                break;
            case R.id.tvUnCheck:
                ScheduledExecutorService SchedulUnedCheck = Executors.newScheduledThreadPool(2);
                SchedulUnedCheck.scheduleAtFixedRate(new MyScheduledExecutor(unCheckOrder), 100, 40, TimeUnit.MILLISECONDS);
                SchedulUnedCheck = null;
                break;
            case R.id.tvCancel:
                break;
            default:
                break;
        }
    }

    public void onEventMainThread(EventCheckerMsg eventMsg) {
        if (eventMsg.getOrder().equals(checkOrder)) {
            if (checker > 13) {
                return;
            }
            canvasCheckres.setPosition(checker);
            checker++;
        }

        if (eventMsg.getOrder().equals(unCheckOrder)) {
            if (unChecker < 0) {
                return;
            }
            canvasCheckres.setPosition(unChecker);
            unChecker--;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
