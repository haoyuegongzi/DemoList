package com.haoyue.demo_list.Water_ripple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by chen1 on 2017/8/10.
 * 水波纹效果
 */

public class WaterRipple extends View {
    private Paint mPaint;
    private int mRadius = 100;
    int wid, hei;

    public WaterRipple(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wid = w;
        hei = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        RadialGradient mRadialGradient01 = new RadialGradient(wid/2,hei/2,mRadius,0xffff0000, 0xff00ff00, Shader.TileMode.REPEAT);
//        mPaint.setShader(mRadialGradient01);
//        canvas.drawCircle(getWidth()/2, getHeight()/2, mRadius, mPaint);
//        mPaint.reset();

//        int[]   colors = new int[]{0xff5ed9f0, 0xff8f06c3, 0xff76dfcb, 0xff616ef,
//                                       0xff428ddd, 0xff43c5dd, 0xff43d99c, 0xffcfc3ed};
//        float[] stops  = new float[]{0f, 0.15f, 0.3f, 0.45f, 0.6f, 0.75f, 0.9f, 1.0f};
//        RadialGradient mRadialGradient02 = new RadialGradient(wid/2, hei/2,mRadius,colors,stops, Shader.TileMode.REPEAT);
//        mPaint.setShader(mRadialGradient02);
//        canvas.drawCircle(getWidth()/2,getHeight()/2,mRadius,mPaint);
//        mPaint.reset();

//        RadialGradient mRadialGradient03 = new RadialGradient(wid/2, hei/2, mRadius,0xffff0000,0xff00ff00, Shader.TileMode.CLAMP);
//        mPaint.setShader(mRadialGradient03);
//        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
//        mPaint.reset();

//        RadialGradient mRadialGradient04 = new RadialGradient(wid/2,hei/2,mRadius,0xffff0000,0xff00ff00, Shader.TileMode.REPEAT);
//        mPaint.setShader(mRadialGradient04);
//        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
//        mPaint.reset();

//        RadialGradient mRadialGradient05 = new RadialGradient(wid/2,hei/2,mRadius,0xffff0000,0xff00ff00, Shader.TileMode.MIRROR);
//        mPaint.setShader(mRadialGradient05);
//        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
//        mPaint.reset();

        RadialGradient mRadialGradient06 = new RadialGradient(wid/2,hei/2,mRadius,0xffff0000,0xff00ff00, Shader.TileMode.REPEAT);
        mPaint.setShader(mRadialGradient06);
        canvas.drawRect(0,0,300,300,mPaint);
        mPaint.reset();
    }
}
