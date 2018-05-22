package com.haoyue.demo_list.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by chen1 on 2017/7/28.
 */

public class MatrixView extends View {
    Paint mPaint;
    int wid,hei;

    public MatrixView(Context context) {
        super(context);
        initPaint();
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wid = w;
        hei = h;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        myMapPoint();
//        mapPoints();
//        mapPoints3();
//        mapRadius(canvas);
        mapRectF01(canvas);
    }

    private void mapRectF01(Canvas canvas){
        RectF rect = new RectF(100, 100, 400, 300);
        // 构造一个matrix
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        Log.i("TAG", "mapRadius: "+rect.toString());
        boolean result = matrix.mapRect(rect);
        Log.i("TAG", "mapRadius: "+rect.toString());
        Log.e("TAG", "isRect: "+ result);
        canvas.drawRect(rect, mPaint);
    }

    private void mapRadius(Canvas canvas){
        canvas.translate(wid / 2, hei / 2);
        float radius = 100;
        float result = 0;
        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        Log.i("TAG", "mapRadius: "+radius);
        result = matrix.mapRadius(radius);
        Log.i("TAG", "mapRadius: "+result);

        Path path = new Path();
        path.addOval(-radius, -result, radius, result, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }

    private void mapPoints3(){
        //        初始数据为三个点 (0, 0)(80, 100)(400, 300)
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];
        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        // 输出计算之前数据
        Log.i("TAG", "before: src="+ Arrays.toString(src) + "***dst="+ Arrays.toString(dst));
        // 调用map方法计算(最后一个2表示两个点，即四个数值,并非两个数值)
        matrix.mapPoints(dst, 0, src, 2, 2);
        // 输出计算之后数据
        Log.i("TAG", "after : src="+ Arrays.toString(src) + "***dst="+ Arrays.toString(dst));
    }

    private void mapPoints(){
        //        初始数据为三个点 (0, 0)(80, 100)(400, 300)
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);//x坐标缩放0.5
        // 输出计算之前数据
        Log.i("TAG", "before: src="+ Arrays.toString(src) + "***dst="+ Arrays.toString(dst));
        matrix.mapPoints(dst,src);// 调用map方法计算
        // 输出计算之后数据
        Log.i("TAG", "after : src="+ Arrays.toString(src) + "***dst="+ Arrays.toString(dst));
    }

    public void myMapPoint(){
        //        初始数据为三个点 (0, 0)(80, 100)(400, 300)
        float[] pts = new float[]{50, 0, 80, 100, 400, 300};
        Matrix matrix = new Matrix();// 构造一个matrix，x坐标缩放0.5
        matrix.setScale(0.5f, 2f);
        Log.i("TAG", "before: "+ Arrays.toString(pts));// 输出pts计算之前数据
        matrix.mapPoints(pts);// 调用map方法计算
        Log.i("TAG", "after : "+ Arrays.toString(pts));// 输出pts计算之后数据
    }






    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
