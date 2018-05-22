package com.haoyue.demo_list.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.haoyue.demo_list.DisplayUtils;

/**
 * Created by chen1 on 2017/7/10.
 * TODO: 基本图形：点、线、(圆角)矩形、(椭)圆、(圆)弧的绘制
 */

public class MyCanvas extends View {
    public static int widthSize = 0;
    public static int heightSize = 0;
    public static Paint mPaint;
    private String textContent;
    private float textSize = 0f;
    private float minTextSize = 16f;
    private Context mContext;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public MyCanvas(Context context) {
        super(context);
        mContext = context;
        initPaint();//实例化画笔对象；
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();//实例化画笔对象；
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        widthSize = MeasureSpec.getSize(widthMeasureSpec);//屏幕宽度
//        //屏幕高度-标题栏的高度-ActionBar的高度（如果设置了ActionBar.hide，则不减）
//        heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int wid = getMeasuredWidth();//屏幕宽度
//        //屏幕高度-标题栏的高度-ActionBar的高度（如果设置了ActionBar.hide，则不减）
//        int hei = getMeasuredHeight();

        int widSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heiSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heiSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        if (widSpecMode == MeasureSpec.AT_MOST && heiSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(metrics.widthPixels, DisplayUtils.dpToPx(mContext, 300));
        }else if(widSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(metrics.widthPixels, heiSpecSize);
        }else if(heiSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthMeasureSpec, DisplayUtils.dpToPx(mContext, 300));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//int w, int h：自定义的View的最终大小
        super.onSizeChanged(widthSize, heightSize, oldw, oldh);
        widthSize = w;
        heightSize = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawColor(canvas);//绘制纯色(界面为某个指定的颜色)
//        drawPoint(canvas);//绘制点（一系列的点）
        drawLine(canvas);//绘制直线（包含一系列的）
//        drawRect(canvas);//绘制矩形(有三个方法，结果都一样)
//        drawRoundRect(canvas);//绘制圆角矩形(其实既可以画圆角矩形，也可以画椭圆，还有画圆)
//        drawCircle(canvas);//绘制圆(含圆环)
//        drawArc(canvas);//绘制圆弧
//        drawTraslate(canvas);//未有的累加
//        drawTextView(canvas);
    }

    private void drawTextView(Canvas canvas){
        if (TextUtils.isEmpty(textContent)){
            return;
        }
        mPaint.setTextSize(15);
/**     mPaint.setTextSkewX(-0.3f);//斜体，为正：向左倾斜；为负，向右倾斜
        mPaint.setTextScaleX(2);  */
        float[] charWidth = new float[textContent.length()];
        int mayTextWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        Rect rect = new Rect();
        mPaint.getTextBounds(textContent, 0, textContent.length(), rect);
        int maybeWidth = rect.width();
        int maybeHeight = rect.height();
        int textNum = 0;
        while (maybeWidth > mayTextWidth){
            if(textSize > minTextSize){
                textSize--;
                mPaint.setTextSize(textSize);
                textNum = mPaint.getTextWidths(textContent, charWidth);
                maybeWidth = 0;
                for (int i = 0; i < textNum; i++){
                    maybeWidth += charWidth[i];
                }
            }
        }
        canvas.drawText(textContent, 210, 160, mPaint);
        mPaint.setColor(Color.RED);

//        mPaint.setStrokeWidth(8);//下划线宽度
//        canvas.drawLine(0, 120, 1000,120, mPaint);
    }

    private void drawTraslate(Canvas canvas){
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

    private void drawArc(Canvas canvas){
        //绘制正方形背景
        RectF rectF01 = new RectF(50,50,250,250);
        mPaint.setColor(Color.parseColor("#00ff00"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectF01, mPaint);
        //绘制圆弧
        mPaint.setColor(Color.GRAY);
        canvas.drawArc(rectF01, 0, 160, false, mPaint);

        //绘制长方形背景
        RectF rectF02 = new RectF(300,300,700,580);
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawRect(rectF02, mPaint);
        //绘制圆弧
        mPaint.setColor(Color.GRAY);
        canvas.drawArc(rectF02, 0, 160, true, mPaint);
        /**
         * 总结：
         * 1、false:不用圆心，直接是连接圆弧的起点和终点，形成的一个玄弧封闭区域，
         *    true:用到圆心，是链接圆弧的起点、中心/圆心、圆弧终点所形成的一个扇形
         * 2、画圆弧，则需要RectF对应的矩形背景为正方形，画椭圆弧，则需要RectF对应的矩形背景为长方形，方形
         */
    }

    private void drawCircle(Canvas canvas){
        //绘制圆环的两种方式：
        //方式一：mPaint画笔style设为描边STROKE，画笔的width加大
        //但这样有个缺陷：mPaint的width会均分在圆的内外两侧，使得所画出的圆比预期的大；比如下方：
        //圆的半径150，mPaint的width = 100，画出来的结果是mPaint的width有一半（50）分布在圆半径150的外侧，另一半分布在内侧，
        //这样画出来的圆直径比预期大了50（mPaint的width的一半），因此该方法尽可能少用；
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 200, 100, mPaint);

        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(400, 400, 150, mPaint);
        //方式二：画两个半径不一样的同心圆
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(400, 400, 100, mPaint);

        //画圆
        int CircleCenter = widthSize < heightSize ? widthSize : heightSize;
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(widthSize / 2, heightSize / 2, (CircleCenter * 4) / 10, mPaint);
    }

    private void drawRoundRect(Canvas canvas){
        //.drawRoundRect()绘制圆角矩形(其实该方法既可以画圆角矩形，也可以画椭圆，还有画圆)
        //绘制圆角矩形方法一：
        RectF rectF1 = new RectF(550,550,880,880);
        canvas.drawRoundRect(rectF1,40,40,mPaint);//两个40的含义：圆角在X、Y两个方向上的半径
        //绘制圆角矩形方法二：
        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(600, 600, 850, 850, 40, 40, mPaint);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //画圆（条件：在正方形的基础上绘制才行）：
        mPaint.setColor(Color.YELLOW);
        //圆角在X、Y两个方向上的半径分别为矩形(实际是正方形)长和宽的一半
        RectF rectF3 = new RectF(150,150,450,450);
        canvas.drawRoundRect(rectF3,150,150,mPaint);

        //画椭圆1（条件：在矩形的基础上绘制才行）：
        mPaint.setColor(Color.GRAY);
        RectF rectF4 = new RectF(200,150,400,450);
        canvas.drawRoundRect(rectF4,100,150,mPaint);

        //画椭圆2（条件：在矩形的基础上绘制才行）：
        mPaint.setColor(Color.BLACK);
        RectF rectF5 = new RectF(200,250,400,350);
        canvas.drawRoundRect(rectF5,100,50,mPaint);
    }

    private void drawRect(Canvas canvas){
        //方法一：
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(100,100,500,500,mPaint);
        //方法二：
        Rect rect = new Rect(150,150,450,450);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect, mPaint);
        //方法三：
        RectF rectF = new RectF(200,200,400,400);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(rectF, mPaint);
        //Rect和RectF两种的区别：最大的区别就是精度不同，Rect是int的，而RectF是float的;两种提供的方法也稍微存在差别
        //参见官方文档 Rect 和 RectF
    }

    private void drawLine(Canvas canvas){
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(100,220,480,230, mPaint);
        canvas.drawLines(new float[]{300,320,420,430,
                420,430,580,600,
                580,600,328,400,
                328,400,700,650},mPaint);
    }

    private void drawPoint(Canvas canvas){
        //一系列的点
        mPaint.setStrokeWidth(12);
        mPaint.setColor(Color.CYAN);
        canvas.drawPoints(new float[]{28,28,56,56,84,84,112,112,140,140}, mPaint);

        mPaint.setColor(Color.BLUE);
        //单个点
        canvas.drawPoint(500, 500, mPaint);
    }

    private void drawColor(Canvas canvas){
//        canvas.drawColor(Color.BLUE);
        canvas.drawColor(Color.parseColor("#99ff00ff"));
    }

    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    public void initPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint paint = new Paint(mPaint);//复制一个与mPaint一模一样的paint对象
        mPaint.setColor(Color.parseColor("#99ff00ff"));
        mPaint.setStyle(Paint.Style.FILL);
        //Paint.Style.STROKE:线条轮廓
        //Paint.Style.FILL:填充
        mPaint.setStrokeWidth(5f);
    }
}
