package com.haoyue.demo_list.beziers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.haoyue.demo_list.R.id.llBezier3Order;

public class BezierActivity extends AppCompatActivity {

    @BindView(R.id.llBezier)
    LinearLayout mLlBezier;
    @BindView(llBezier3Order)
    LinearLayout mLlBezier3Order;
    @BindView(R.id.btButtonOne)
    Button mBtButtonOne;
    @BindView(R.id.btButtonTwo)
    Button mBtButtonTwo;
    @BindView(R.id.clConstraint)
    RelativeLayout mClConstraint;
    private boolean conFla = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);

        LinearLayout llBezier = (LinearLayout) findViewById(R.id.llBezier);
        Bezier mBezier = new Bezier(this);
        llBezier.addView(mBezier);

        LinearLayout llBezier3Order = (LinearLayout) findViewById(R.id.llBezier3Order);
        BezierThree bezierThree = new BezierThree(this);
        llBezier3Order.addView(bezierThree);
    }
}
