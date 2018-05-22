package com.haoyue.demo_list.matrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.haoyue.demo_list.R;

public class MatrixActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rlMatrix;
    SetPolyToPoly Poly;
    Button btZero, btOne, btTwo, btThree, btFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        initFindViewById();
//        rlMatrix.addView(new MatrixView(this));

//        rlMatrix.addView(new MatrixSetPolyToPoly(this));

//        Poly = new SetPolyToPoly(this);
//        Poly.setTestPoint(0);
//        rlMatrix.addView(Poly);
        rlMatrix.addView(new MatrixSetRectToRect(this));
        findViewById(R.id.llBt).setVisibility(View.GONE);
    }

    private void initFindViewById() {
        rlMatrix = (RelativeLayout) findViewById(R.id.rlMatrix);
        btZero = (Button) findViewById(R.id.btZero);
        btOne = (Button) findViewById(R.id.btOne);
        btTwo = (Button) findViewById(R.id.btTwo);
        btThree = (Button) findViewById(R.id.btThree);
        btFour = (Button) findViewById(R.id.btFour);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btZero:
                Poly.setTestPoint(0);
                break;
            case R.id.btOne:
                Poly.setTestPoint(1);
                break;
            case R.id.btTwo:
                Poly.setTestPoint(2);
                break;
            case R.id.btThree:
                Poly.setTestPoint(3);
                break;
            case R.id.btFour:
                Poly.setTestPoint(4);
                break;
            default:
                break;
        }
    }
}
