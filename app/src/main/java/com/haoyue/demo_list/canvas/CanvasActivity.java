package com.haoyue.demo_list.canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.haoyue.demo_list.R;

import java.util.ArrayList;

public class CanvasActivity extends AppCompatActivity {
    LinearLayout llCanvas, llPathCanvas, llPictureCanvas, llOperateCanvas, llCanvaspieView;
//    MyCanvas mCanvas;

    ArrayList<PieData> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_canvas);
        String text = "wujinjianzhegeshabi";
////////////////////////////////////////////////////////////////////////////////////////
        llCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        MyCanvas mCanvas = new MyCanvas(CanvasActivity.this);
        mCanvas.setTextContent(text);
        llCanvas.addView(mCanvas);
//        llCanvas.setVisibility(View.GONE);
////////////////////////////////////////////////////////////////////////////////////////
        llCanvaspieView = (LinearLayout) findViewById(R.id.llCanvaspieView);
        mData.add(new PieData("成都",40f, 40f, Color.BLUE,144f));
        mData.add(new PieData("达州",25f, 25f, Color.CYAN,90f));
        mData.add(new PieData("绵阳",20f, 20f, Color.GREEN,72f));
        mData.add(new PieData("南充",10f, 10f, Color.GRAY,36f));
        mData.add(new PieData("遂宁",5f, 5f, Color.YELLOW,18f));
        PieView pieView = new PieView(CanvasActivity.this);
        pieView.setData(mData);
        llCanvaspieView.addView(pieView);
//        llCanvaspieView.setVisibility(View.GONE);
////////////////////////////////////////////////////////////////////////////////////////
        llOperateCanvas = (LinearLayout) findViewById(R.id.llOperateCanvas);
        llOperateCanvas.addView(new OperateCanvas(CanvasActivity.this));
//        llOperateCanvas.setVisibility(View.GONE);
////////////////////////////////////////////////////////////////////////////////////////
        llPictureCanvas = (LinearLayout) findViewById(R.id.llPictureCanvas);
        llPictureCanvas.addView(new PictureCanvas(CanvasActivity.this));
////////////////////////////////////////////////////////////////////////////////////////

        llPathCanvas = (LinearLayout) findViewById(R.id.llPathCanvas);
        llPathCanvas.addView(new PathCanvas(CanvasActivity.this));
    }
}
