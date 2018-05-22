package com.haoyue.demo_list.linear_gradient;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by chen1 on 2017/8/9.
 */

public class LinearGradientText extends View {
    private Paint mPaint;
    private int mDx;
    android.graphics.LinearGradient mLinearGradient;
    int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00, 0xff00ffff};
    float[]  pos = {0f, 0.2f, 0.4f, 0.6f, 1.0f};

    public LinearGradientText(Context context) {
        super(context);
        mPaint =new Paint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ValueAnimator animator = ValueAnimator.ofInt(0,2*getMeasuredWidth());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (Integer) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(3000);
        animator.start();
        mLinearGradient = new android.graphics.LinearGradient(0, getMeasuredHeight()/4,
                        getMeasuredWidth()/4, 0, colors, pos, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        matrix.setTranslate(mDx, 0);
        mLinearGradient.setLocalMatrix(matrix);
        mPaint.setShader(mLinearGradient);
        mPaint.setTextSize(50);
        canvas.drawText("天佑中华",50,50,mPaint);
    }
}
