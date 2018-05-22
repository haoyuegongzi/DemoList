package com.haoyue.demo_list.beziers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chen1 on 2017/8/7.
 * 三阶贝塞尔曲线
 */

public class BezierThree extends View {
    private Paint mPaint;
    private PointF start, end, control1, control2;
    private boolean conFlag = true;
    private int centerX, centerY;

    public BezierThree(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.BLUE);
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        start.x = centerX - 250;
        start.y = centerY;
        end.x = centerX + 250;
        end.y = centerY;
        control1.x = centerX;
        control1.y = centerY - 100;
        control2.x = centerX;
        control2.y = centerY + 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(conFlag){
            control1.x = event.getX();
            control1.y = event.getY();
        }else {
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;//直接返回true
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(25);
        //绘制4个点
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);
        //绘制三条线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);
        //绘制贝塞尔曲线
        mPaint.setColor(Color.LTGRAY);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y,end.x, end.y);
        canvas.drawPath(path, mPaint);

    }

    public void setConFlag(boolean conFlag) {
        this.conFlag = conFlag;
    }
}
