package com.haoyue.demo_list.checkview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.haoyue.demo_list.R;

public class CheckViewActivity extends AppCompatActivity implements View.OnClickListener{
    FrameLayout flShowView;
    Button btCheck, btUnCheck;
    CheckView checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_view);
        checkView = new CheckView(this);
        checkView.setAnimDuration(200);
//        checkView.setBackgroundColor(Color.GRAY);

        flShowView = (FrameLayout) findViewById(R.id.flShowView);
        flShowView.addView(checkView);

        btCheck = (Button) findViewById(R.id.btCheck);
        btUnCheck = (Button) findViewById(R.id.btUnCheck);
        btCheck.setOnClickListener(this);
        btUnCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCheck:
                checkView.check();
                break;
            case R.id.btUnCheck:
                checkView.unCheck();
                break;
            default:
                break;
        }
    }
}
