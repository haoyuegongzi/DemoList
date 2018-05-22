package com.haoyue.demo_list.beziers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BezierHeartActivity extends AppCompatActivity {

    BezierHeart mHeart;
    @BindView(R.id.flBezierHeart)
    FrameLayout mFlBezierHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_heart);
        ButterKnife.bind(this);

        mHeart = new BezierHeart(this);
        mFlBezierHeart.addView(mHeart);
    }
}
