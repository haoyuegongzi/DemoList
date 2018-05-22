package com.haoyue.demo_list.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.haoyue.demo_list.R;

import java.util.Arrays;

/**
 * Created by chen1 on 2017/7/31.
 */

public class MatrixSetRectToRect extends View {
    private int mViewWidth, mViewHeight;

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mRectMatrix;         // 测试etRectToRect用的Matrix

    public MatrixSetRectToRect(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sunlu23);
        mRectMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#9abcde"));
        RectF src= new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight() );
        RectF dst = new RectF(0, 0, mViewWidth, mViewHeight );
        // 核心要点
        mRectMatrix.setRectToRect(src,dst, Matrix.ScaleToFit.CENTER);
        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mRectMatrix, new Paint());



        float[] values = new float[9];
        int[] location1 = new int[2];

        Matrix matrix = canvas.getMatrix();
        matrix.getValues(values);

        location1[0] = (int) values[2];
        location1[1] = (int) values[5];
        Log.i("TAG", "location1 = " + Arrays.toString(location1));

        int[] location2 = new int[2];
        this.getLocationOnScreen(location2);
        Log.i("TAG", "location2 = " + Arrays.toString(location2));
    }
}
