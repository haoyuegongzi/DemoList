package com.haoyue.demo_list.CanvasTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chen1 on 2017/7/17.
 */

public class DrawLines extends View {
    private int wid = 0;
    private int hei = 0;
    private Paint paint;

    private float[] lineArray = null;
    private Context mContext;

    private Class<?> tClass;

    public void setLineArray(float[] linesArray){
        lineArray = linesArray;
        Log.i("TAG", "lineArray.length ==" + lineArray.length);
    }

    public DrawLines(Context context, float[] linesArray) {
        super(context);
        Log.i("TAG", "构造方法方法");
        mContext = context;
        lineArray = linesArray;
        paint = new Paint();
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStyle(Paint.Style.STROKE);
        //Paint.Style.STROKE:线条轮廓
        //Paint.Style.FILL:填充
        paint.setStrokeWidth(5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        wid = MeasureSpec.getSize(widthMeasureSpec);
        hei = MeasureSpec.getSize(heightMeasureSpec);
        int mWid = MeasureSpec.getMode(widthMeasureSpec);
        int mHei = MeasureSpec.getMode(heightMeasureSpec);
//        Log.i("TAG", "onMeasure：wid==" + wid + "\n hei ==" + hei +
//                "\n mWid ==" + mWid + "\n mHei ==" + mHei);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("TAG", "onSizeChanged方法");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("TAG","onDraw方法执行了绘图||||lineArray.length ==" + lineArray.length);
/**
 * Path mPath = new Path();
 mPath.lineTo(100,24);
 canvas.drawPath(mPath, mPaint);
 */
        canvas.scale(1, -1);
        canvas.translate(0, -hei);
        Path mPath = new Path();
        if(lineArray.length > 0){
            for(int i = 0; i < lineArray.length; i++){
                float a = lineArray[i];//X
                float b = 0;//Y
                if((i+1) >= (lineArray.length-1) ) {
                    b = a;
                }else {
                    b = lineArray[i + 1];
                }
                mPath.lineTo(a,b);
                canvas.drawPath(mPath, paint);
            }
        }else {
            Toast.makeText(mContext, "数据为空", Toast.LENGTH_LONG).show();
        }
    }
}
