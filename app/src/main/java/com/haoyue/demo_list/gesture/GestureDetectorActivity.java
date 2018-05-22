package com.haoyue.demo_list.gesture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GestureDetectorActivity extends AppCompatActivity {

    @BindView(R.id.rlGesture)
    RelativeLayout mRlGesture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        // 1.创建一个监听回调
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(GestureDetectorActivity.this, "双击666", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        };

        // 2.创建一个检测器
        final GestureDetector detector = new GestureDetector(this, listener);

        // 3.给监听器设置数据源
        mRlGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("TAG", "onTouch: boolean ===" + detector.onTouchEvent(event));
                return detector.onTouchEvent(event);
            }
        });
    }
}
