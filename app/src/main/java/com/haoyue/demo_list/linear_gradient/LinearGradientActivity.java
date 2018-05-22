package com.haoyue.demo_list.linear_gradient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearGradientActivity extends AppCompatActivity {

    @BindView(R.id.flLinerGradient)
    FrameLayout mFlLinerGradient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_gradient);
        ButterKnife.bind(this);
        mFlLinerGradient.addView(new LinearGradientText(this));
    }
}
