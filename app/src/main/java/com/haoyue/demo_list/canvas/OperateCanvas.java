package com.haoyue.demo_list.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by chen1 on 2017/7/11.
 * TODO: 操作画布：移动、缩放、选转。
 */

public class OperateCanvas extends View {
    int width = 0;
    int height = 0;
    Paint mPaint;
    public OperateCanvas(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawTranslate(canvas);//移动
//        drawScale(canvas);//缩放
//        drawRotate(canvas);//旋转
        drawSkew(canvas);
    }

    /**
     * 错切
     * @param canvas
     */
    private void drawSkew(Canvas canvas){
        //错切也是可叠加的
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        canvas.translate(width/2, height/2);

        RectF rect = new RectF(0,0,100,100);   // 矩形区域
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        canvas.skew(1f,2f);//越接近于0，则错切之后的图形越接近于原图，数字越大，错切度越大
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }

    /**
     * 选转
     * @param canvas
     */
    private void drawRotate(Canvas canvas){
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        canvas.translate(width/2, height/2);//移动坐标中心至View的正中间。
//        RectF rectF = new RectF(0, -150, 200, 0);
//        canvas.drawRect(rectF, mPaint);
//        canvas.save();
//
//        canvas.rotate(180);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.restore();
//        canvas.save();
//        canvas.rotate(180,150, 0);
//        canvas.drawRect(rectF, mPaint);
//
//        canvas.restore();
//        canvas.save();
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(4);
//        for(int i = 0; i < 10; i++){
//            canvas.drawRect(rectF, mPaint);
//            canvas.rotate(20);
//        }

        mPaint.setColor(Color.parseColor("#93a5f4"));
//        canvas.drawCircle(0,0,260,mPaint);          // 绘制两个圆形
//        canvas.drawCircle(0,0,230,mPaint);
//        canvas.rotate(95);
        for (int i = 0; i <= 270; i += 3){
            if(i > 250){
                mPaint.setColor(Color.RED);
            }
            // 绘制圆形之间的连接线
            canvas.drawLine(0, 235, 0, 255, mPaint);//此处划线：链接内圆和外圆
            canvas.rotate(3);
        }
    }

    private void drawScale(Canvas canvas){
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.translate(width/2, height/2);
        canvas.save();

        RectF rectF = new RectF(0, -220, 350, 0);
        canvas.drawRect(rectF, mPaint);

        //缩放是可以叠加的。
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.5f, 100, -100);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);

        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.GREEN);
        RectF rectF2 = new RectF(0, -200, 200, 0);
        canvas.drawRect(rectF2, mPaint);

        mPaint.setColor(Color.LTGRAY);
        canvas.scale(-0.5f, -0.5f);
        canvas.drawRect(rectF2, mPaint);

        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.DKGRAY);
        canvas.scale(-0.5f, -0.5f, 100, 0);
        canvas.drawRect(rectF2, mPaint);
        canvas.restore();
        canvas.save();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        RectF rectF3 = new RectF(0, -280, -280, 0);
        canvas.drawRect(rectF3, mPaint);
        for (int i = 0; i < 15; i++){//缩放叠加；
            canvas.scale(0.9f, -0.9f);
            canvas.drawRect(rectF3, mPaint);
        }
    }

    private void drawTranslate(Canvas canvas){
        canvas.save();
        mPaint.setColor(Color.CYAN);
        canvas.drawCircle(100, 100, 60, mPaint);
        //位移(translate)是可以叠加的。
        canvas.translate(width/2, height/2);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(0, 0, 60, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(width/2 - 100, height/2 - 100);
        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(0, 0, 60, mPaint);

        canvas.restore();//restore()需要与canvas.save();连用；否则报异常：IllegalStateException
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(500, 100, 60, mPaint);

        canvas.translate(150, 150);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 75, mPaint);

        canvas.translate(150, 150);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(0, 0, 75, mPaint);

        canvas.translate(200, -120);
        mPaint.setColor(Color.CYAN);
        canvas.drawCircle(0, 0, 75, mPaint);

    }
}
