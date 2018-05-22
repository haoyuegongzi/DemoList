package com.haoyue.demo_list.pathmeasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.haoyue.demo_list.R;

public class PathMeasureActivity extends AppCompatActivity {
    RelativeLayout rlPathMeasure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_path_measure);

        rlPathMeasure = (RelativeLayout) findViewById(R.id.rlPathMeasure);
        rlPathMeasure.addView(new NewPathMeasure(this));
    }
}
