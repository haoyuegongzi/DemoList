package com.haoyue.demo_list.finger_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by chen1 on 2017/8/7.
 * 用moveTo和LineTo画手势轨迹
 */

public class FingerPath extends View {
    private Paint mPaint;
    private Path mPath;
    private float mPreX,mPreY;
    private boolean lineOrQuad = true;//true:调用LineTo方法；false：调用QuadTo方法
    public FingerPath(Context context) {
        super(context);
        if(mPaint == null){
            mPaint = new Paint();
            mPaint.setStrokeWidth(20);
            mPaint.setAntiAlias(true);//防锯齿
            mPaint.setColor(Color.GREEN);
            mPaint.setStyle(Paint.Style.STROKE);
            mPath = new Path();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                if(lineOrQuad){
                    mPath.moveTo(event.getX(), event.getY());
                }else {
                    mPath.moveTo(event.getX(),event.getY());
                    //mPreX，mPreY表示手指的前一个点
                    mPreX = event.getX();
                    mPreY = event.getY();
                }
                return true;
            }
            case MotionEvent.ACTION_MOVE:
                if(lineOrQuad){
                    mPath.lineTo(event.getX(), event.getY());
                }else {
                    //除以2的原因：mPreX/mPreY其实是很接近event.getX()/event.getY()的，
                    //除以2取平均值，让每个点之间的波动更小。
                    float endX = (mPreX + event.getX())/2;
                    float endY = (mPreY + event.getY())/2;
                    mPath.quadTo(mPreX, mPreY, endX, endY);
                    //mPreX，mPreY表示手指的前一个点
                    mPreX = event.getX();
                    mPreY = event.getY();
                }
                /**
                 * 注意：这里用的是postInvalidate(),不是Invalidate();关乎到线程安全
                 * Invalidate()一定要在UI线程执行，否则就会报错；
                 * postInvalidate()则没有那么多讲究，它可以在任何线程中执行，而不必一定要是主线程，
                 * 其实postInvalidate()是利用handler给主线程发送刷新界面的消息来实现的，所以它是可以在任何线程中执行
                 * 由此带来的影响是在UI界面刷新的时候，postInvalidate()没有Invalidate()快。
                 */
                invalidate();
                postInvalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawPath(mPath, mPaint);
    }

    public void reset(){
        mPath.reset();
        postInvalidate();
    }

    public void setQuadOrLine(boolean quadOrLine){
        this.lineOrQuad = quadOrLine;
    }
}
