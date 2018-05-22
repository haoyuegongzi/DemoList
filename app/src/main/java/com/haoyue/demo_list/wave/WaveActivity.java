package com.haoyue.demo_list.wave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaveActivity extends AppCompatActivity {
    WaveView mWaveView;

    @BindView(R.id.flWave)
    FrameLayout mFlWave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        ButterKnife.bind(this);
        mWaveView = new WaveView(this);
        mWaveView.startAnim();
        mFlWave.addView(mWaveView);
    }
}
