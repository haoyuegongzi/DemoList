package com.haoyue.demo_list.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.View;

import com.haoyue.demo_list.R;

/**
 * Created by chen1 on 2017/7/12.
 * TODO：图片文字操作
 */

public class PictureCanvas extends View{
    Picture mPicture;// 1.创建Picture
    Context mContext;
    int wid = 0;
    int hei = 0;
    Paint mPaint;
    String string = "皓月公子";
    public PictureCanvas(Context context) {
        super(context);
        mContext = context;
        if (mPaint == null) {
            initPaint();
        }
        drawPictrue01();// 调用录制
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(wid/2, hei/2);
//        mPicture.draw(canvas);// 将Picture中的内容绘制在Canvas上
//        canvas.drawPicture(mPicture,new RectF(0,0,400,160));
//        drawDrawable(canvas);
//        drawPictrue02(canvas);
        drawText(canvas);
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(16);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        mPicture = new Picture();
    }

    private void drawText(Canvas canvas){
        canvas.save();
        //文字的绘制以起始坐标左对齐
        mPaint.setTextAlign(Paint.Align.LEFT);
        Rect rect = new Rect();
        //获取文本所占用的最小矩形并将参数保存在rect中
        mPaint.getTextBounds(string, 0, string.length(), rect);
//        rect.left+rect.top+rect.left+rect.right;
//        rect.height()+rect.width();
        //指定绘制字符串的起始坐标（300，,150）
        canvas.drawText(string, 300, 150, mPaint);
        canvas.drawText(string, 1,3,300, 300, mPaint);

        float size = mPaint.measureText(string);
        int height = rect.bottom - rect.top;
        int width = rect.right - rect.left;
        int L = rect.width();
        int H = rect.height();
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        Log.i("TAG", "drawText: height ==" + height + "\n" + "width ==" + width + "\n" + "L ==" + L + "\n" + "H ==" + H +
                     "\n" + "centerX =="  + centerX +"\n" + "centerY ==" + centerY + "\n" + "size ==" + size);

        Log.i("TAG",  "heightView == H  isTrue?" + (height == H) + "\nwidthView == L  isTrue?" + (width == L));

        //文字的绘制以起始坐标右对齐
//        mPaint.setTextAlign(Paint.Align.RIGHT);
//        String rightStirng = "皓月";
        //前面两个float参数指定绘制从字符串指定位置截取的内容（此处0,2表示截取String字符串0~2之间的内容，但包前不包后），
        //后两个float参数指定字符串绘制的起始坐标
//        canvas.drawText(rightStirng, 300, 300, mPaint);
//        float sizeRIGHT = mPaint.measureText(rightStirng);
//
//        //文字的绘制以起始坐标居中对齐(文字均匀分布在坐标的两边)
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        mPaint.setTextSize(50);
//        String centerString = "神魔一念，一剑不悔";
//        canvas.drawText(centerString, 300, 450, mPaint);
//        float sizeCENTER = mPaint.measureText(centerString);
//        Log.i("TAG", "drawText: size ==" + sizeCENTER + "||||||sizeRIGHT ==" + sizeRIGHT+ "||||||sizeLEFT ==" + sizeLEFT);
    }

    private void drawPictrue02(Canvas canvas){
//        canvas.save();
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.translate(25, 25);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zero01);
//        canvas.drawBitmap(bitmap, new Matrix(), new Paint());//法一
//        canvas.restore();

//        canvas.save();
//        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.zero04);
//        canvas.drawBitmap(bitmap2, 50f, 50f, mPaint);//法二
//        canvas.restore();
//
//        canvas.save();
        // 指定图片绘制区域(左上角的四分之一)
        Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.view10);
        Rect src = new Rect(0, 0, bitmap3.getWidth() * 3 / 4, bitmap3.getHeight());
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(10,10,700,550);
        canvas.drawBitmap(bitmap3, src, dst,mPaint);//法三

    }

    /**
     * 2.录制内容方法
     */
    private void drawPictrue01(){
        Canvas canvas = mPicture.beginRecording(wid, hei);
        canvas.save();
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.translate(wid / 2, hei / 2);
        canvas.drawCircle(0, 0, 150, mPaint);
        mPicture.endRecording();
    }

    private void drawDrawable(Canvas canvas){
        canvas.translate(wid/2,hei/2);
        // 包装成为Drawable
        PictureDrawable drawable = new PictureDrawable(mPicture);
        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        drawable.setBounds(0, 0, mPicture.getWidth(), mPicture.getHeight());
        // 绘制
        drawable.draw(canvas);
    }
}
