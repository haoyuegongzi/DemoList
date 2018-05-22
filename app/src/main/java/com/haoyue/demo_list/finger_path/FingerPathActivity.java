package com.haoyue.demo_list.finger_path;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FingerPathActivity extends AppCompatActivity {

    private FingerPath fingerPath;
    private boolean lineOrQuad = true;

    @BindView(R.id.flFingerPath)
    FrameLayout mFlFingerPath;
    @BindView(R.id.tvFingerPath)
    TextView mTvFingerPath;
    @BindView(R.id.tvFingerLineTo)
    TextView mTvFingerLineTo;
    @BindView(R.id.tvFingerQuadTo)
    TextView mTvFingerQuadTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_path);
        ButterKnife.bind(this);
        fingerPath = new FingerPath(this);
        mFlFingerPath.addView(fingerPath);
    }

    @OnClick({R.id.tvFingerPath, R.id.tvFingerLineTo, R.id.tvFingerQuadTo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvFingerPath:
                fingerPath.reset();
                break;
            case R.id.tvFingerLineTo:
                fingerPath.reset();
                lineOrQuad = true;
                fingerPath.setQuadOrLine(lineOrQuad);
                mTvFingerLineTo.setTextColor(Color.RED);
                mTvFingerQuadTo.setTextColor(Color.BLACK);
                break;
            case R.id.tvFingerQuadTo:
                fingerPath.reset();
                lineOrQuad = false;
                fingerPath.setQuadOrLine(lineOrQuad);
                mTvFingerQuadTo.setTextColor(Color.RED);
                mTvFingerLineTo.setTextColor(Color.BLACK);
                break;
        }
    }
}
