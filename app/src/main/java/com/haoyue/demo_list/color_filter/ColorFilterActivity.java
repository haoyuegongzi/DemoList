package com.haoyue.demo_list.color_filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorFilterActivity extends AppCompatActivity {
    MyColorFilter mColorFilter;

    @BindView(R.id.flColorFilter)
    FrameLayout mFlColorFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filter);
        ButterKnife.bind(this);
        mColorFilter = new MyColorFilter(this);
        mFlColorFilter.addView(mColorFilter);
    }
}
