package com.haoyue.demo_list.Water_ripple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaterRippleActivity extends AppCompatActivity {

    @BindView(R.id.flWaterRipple)
    FrameLayout mFlWaterRipple;
    @BindView(R.id.flGradientView)
    FrameLayout mFlGradientView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_ripple);
        ButterKnife.bind(this);

        mFlWaterRipple.addView(new WaterRipple(this));
        mFlGradientView.addView(new RippleView(this));
    }

    @OnClick(R.id.flWaterRipple)
    public void onClick() {

    }
}
