package com.haoyue.demo_list.CanvasTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasLineActivity extends AppCompatActivity {
    @BindView(R.id.llCanvas) LinearLayout mLlCanvas;

    DrawLines mDrawLines = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_line);
        ButterKnife.bind(this);
        float[] lines = drawLine();
//        mDrawLines = new DrawLines(this, lines);
//        mDrawLines.setLineArray(lines);

        mLlCanvas.addView(new DrawLines(this, lines));
    }

    private float[] drawLine(){
        float[] linesArray = new float[]{10,12,60,20, 110,28,160,60, 210,100,260,230,
                                         310,300,360,280, 410,200,460,110, 510,180,560,160,
                                         610,80,660,75, 710,120,760,70, 810,50,860,33,
                                         910,107,960,14};
        return linesArray;
    }

    @OnClick(R.id.llCanvas)
    public void onClick() {


    }
}
