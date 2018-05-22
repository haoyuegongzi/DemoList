package com.haoyue.demo_list.wave;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by chen1 on 2017/8/8.
 */

public class WaveView extends View {
    private Paint mPaint;
//    private Path mPath;
    private Path mPath2;
//    private int mItemWaveLength = 600;//波长
    private int mItemWaveLength2 = 1200;//波长
    private int dx = 0;

    public WaveView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

//        mPath = new Path();
        mPath2 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPath.reset();
        mPath2.reset();
        int originY = 500;//波峰距离控件顶部的距离
//        int halfWaveLen = mItemWaveLength / 2;
        int halfWaveLength = mItemWaveLength2 / 2;

        float Height = 200;//波浪的高度的一半(波峰与波谷的垂直距离)
        //将mPath的起始位置向左移一个波长：
//        mPath.moveTo(-mItemWaveLength*1.5f + dx, originY-dx);
        float absY = Math.abs(dx);
        mPath2.moveTo(-mItemWaveLength2 + dx, originY + absY / 5);
        //for循环画出当前屏幕中可能容得下的所有波(因将mPath的起始位置向左移一个波长，同理也向右移一个波长，故乘2)
        for (int i = -mItemWaveLength2; i <= getWidth() + mItemWaveLength2 * 2; i += mItemWaveLength2){
            //画的是一个波长中的前半个波
            mPath2.rQuadTo(halfWaveLength/2, Height / 2, halfWaveLength, 0);//波的高度
            //画的是一个波长中的后半个波
            mPath2.rQuadTo(halfWaveLength/2, -Height / 2, halfWaveLength, 0);//波的高度
        }

//        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength * 2; i += mItemWaveLength){
//            //画的是一个波长中的前半个波
//            mPath.rQuadTo(halfWaveLen / 2, Height / 2, halfWaveLen, 0);//波的高度
//            //画的是一个波长中的后半个波
//            mPath.rQuadTo(halfWaveLen / 2, -Height / 2, halfWaveLen, 0);//波的深度，与高度合起来就是波高
//        }
//        mPath.lineTo(getWidth(), getHeight());
//        mPath.lineTo(0, getHeight());
//        mPath.close();

        mPath2.lineTo(getWidth(), getHeight());
        mPath2.lineTo(0, getHeight());
        mPath2.close();

//        mPaint.setColor(Color.parseColor("#428ddd"));
//        canvas.drawPath(mPath,mPaint);
//        mPaint.setAlpha(20);
        mPaint.setColor(Color.parseColor("#43c5dd"));
        canvas.drawPath(mPath2,mPaint);
    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength2);
        animator.setDuration(2000);//动画时间
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
