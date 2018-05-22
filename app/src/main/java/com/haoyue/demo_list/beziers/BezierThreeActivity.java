package com.haoyue.demo_list.beziers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BezierThreeActivity extends AppCompatActivity {
    BezierThree bezierThree;

    @BindView(R.id.llBezier3Order)
    LinearLayout mLlBezier3Order;
    @BindView(R.id.btButtonOne)
    Button mBtButtonOne;
    @BindView(R.id.btButtonTwo)
    Button mBtButtonTwo;
    private boolean conFla = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_three);
        ButterKnife.bind(this);

        bezierThree = new BezierThree(this);
        mLlBezier3Order.addView(bezierThree);
    }

    @OnClick({R.id.btButtonOne, R.id.btButtonTwo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btButtonOne:
                conFla = true;
                bezierThree.setConFlag(conFla);
                break;
            case R.id.btButtonTwo:
                conFla = false;
                bezierThree.setConFlag(conFla);
                break;
        }
    }
}
