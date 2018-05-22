package com.haoyue.demo_list.pathmeasure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.haoyue.demo_list.R;

/**
 * Created by chen1 on 2017/7/25.
 */

public class NewPathMeasure extends View {
    Context mContext;
    Paint mPaint = null;
    int wid = 0;
    int hei = 0;

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    public NewPathMeasure(Context context) {
        super(context);
        mContext = context;
        initPaint();
    }

    public NewPathMeasure(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    public NewPathMeasure(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void initPaint(){
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;// 缩放图片（options.inSampleSize = N，那么最后图片为原图的1/N）
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.launcher48, options);
        mMatrix = new Matrix();
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
//        drawConstructor(canvas, mPaint);
//        drawGetSegment(canvas, mPaint);
//        drawNextContour(canvas, mPaint);
//        drawGetTan(canvas, mPaint);//及其重要
        drawGetMatrix(canvas, mPaint);//及其重要；与drawGetTan的功能一样。
    }

    private void drawGetMatrix(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);// 平移坐标系
        Path path = new Path();// 创建 Path
//        RectF rectF = new RectF(-200, -200, 200, 200);//矩形
//        path.addOval(rectF, Path.Direction.CW);

//        RectF rectF = new RectF(-200, -100, 200, 100);//椭圆
//        path.addRect(rectF, Path.Direction.CW);
        path.addCircle(0, 0, 200, Path.Direction.CW);// 添加一个圆形
        PathMeasure measure = new PathMeasure(path, false);// 创建 PathMeasure
        currentValue += 0.001;//计算当前的位置在总长度上的比例,取值区间[0,1]，数字越大，速度越快
        if (currentValue >= 1) {
            currentValue = 0;
        }
        // 获取当前位置的坐标以及趋势的矩阵
        measure.getMatrix(measure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2); //将图片绘制中心调整到与当前点重合(注意:此处是preTranslate)
        canvas.drawPath(path, mPaint);// 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);// 绘制箭头
        invalidate();// 重绘页面
    }

    private void drawGetTan(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);      // 平移坐标系
        Path path = new Path();                                 // 创建 Path
        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure
        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头
        invalidate();                                                    // 重绘页面
    }

    private void drawNextContour(Canvas canvas, Paint mPain){
        canvas.translate(wid / 2, hei / 2);
        Path path = new Path();
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        path.addRect(-150, -150, 150, 150, Path.Direction.CW);
        path.addCircle(0, 0, 256, Path.Direction.CW);
        canvas.drawPath(path, mPain);
        PathMeasure measure = new PathMeasure(path, false);
        float size01 = measure.getLength();
        measure.nextContour();
        Path dir = new Path();
        measure.getSegment(600, 1105, dir, true);
        measure.nextContour();
        float size03 = measure.getLength();
        Log.i("TAG", "drawNextContour: size01 ===" + size01);
//        Log.i("TAG", "drawNextContour: size02 ===" + size02);
        Log.i("TAG", "drawNextContour: size03 ===" + size03);
    }

    private void drawGetSegment(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        Path path = new Path();
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
        Path dir = new Path();
        PathMeasure measure = new PathMeasure(path, false);
        measure.getSegment(120, 280, dir, true);
        canvas.drawPath(dir, mPaint);
    }

    private void drawConstructor(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        Path path = new Path();
//        path.addCircle(0, 0, 125, Path.Direction.CCW);
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        PathMeasure measure1 = new PathMeasure(path, true);
        PathMeasure measure2 = new PathMeasure(path, false);
        Log.e("TAG", "forceClosed=false---->"+measure1.getLength());
        Log.e("TAG", "forceClosed=true----->"+measure2.getLength());
        canvas.drawPath(path, mPaint);
    }
}
