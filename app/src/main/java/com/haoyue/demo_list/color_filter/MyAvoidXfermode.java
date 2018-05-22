//package com.haoyue.demo_list.color_filter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.view.View;
//
//import com.haoyue.demo_list.R;
//
///**
// * Created by chen1 on 2017/8/9.
// */
//
//public class MyAvoidXfermode extends View {
//    private Paint mPaint;
//    private Bitmap mBmp;
//
//    public MyAvoidXfermode(Context context) {
//        super(context);
//        mPaint = new Paint();
//        mBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.dog);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//    }
//
//    @SuppressLint("DrawAllocation")
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        int width  = 500;
//        int height = width * mBmp.getHeight()/mBmp.getWidth();
//        mPaint.setColor(Color.RED);
//
//        int layerID = canvas.saveLayer(0, 0, width, height, mPaint, Canvas.ALL_SAVE_FLAG);
//
//        canvas.drawBitmap(mBmp,null,new Rect(0, 0, width, height), mPaint);
//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE, 100, AvoidXfermode.Mode.TARGET));
//        canvas.drawRect(0, 0, width, height, mPaint);
//        canvas.restoreToCount(layerID);
//    }
//}
