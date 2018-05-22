package com.haoyue.demo_list.motionevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MotionEventActivity extends AppCompatActivity {

    @BindView(R.id.llMotionEvent)
    LinearLayout mLlMotionEvent;
    @BindView(R.id.rlFailingBall)
    RelativeLayout mRlFailingBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        ButterKnife.bind(this);
        mRlFailingBall.addView(new RegionClickView(this));

        mLlMotionEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: mLlMotionEvent的点击Click事件");
            }
        });
        mLlMotionEvent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.i("TAG", "onLongClick: mLlMotionEvent的长按LongClick事件");
                return true;
            }
        });
        mLlMotionEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("TAG", "onTouch: mLlMotionEvent的OnTouch事件");
                return false;
            }
        });
    }
}
