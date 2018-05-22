package com.haoyue.demo_list.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen1 on 2017/7/12.
 * TODO：path路径操作
 */

public class PathCanvas extends View {
    int wid = 0;
    int hei = 0;
    Paint mPaint;
    public PathCanvas(Context context) {
        super(context);
    }

    public PathCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        initPaint();
        drawLineTo(canvas, mPaint);
//        drawMoveTo(canvas, mPaint);
//        drawLastPoint(canvas, mPaint);
//        drawClose(canvas, mPaint);
//        drawRect(canvas, mPaint);
//        drawMergePath(canvas, mPaint);
//        drawAddArc(canvas, mPaint);//画仪表图就会用到这个
//        drawArcTo(canvas, mPaint);
//        drawRxxx(canvas, mPaint);
//        drawTaiJi(canvas, mPaint);
        //子交并补
//        drawBoolean(canvas, mPaint);
//        drawCountArea(canvas, mPaint);
    }

    private void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
    }

    private void drawCountArea(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF();// 存放测量结果的矩形
        Path path = new Path();// 创建Path并添加一些内容
        path.lineTo(100, -50);
        path.lineTo(100, 50);
        path.close();
        path.addCircle(-100, 0, 100, Path.Direction.CW);
        path.computeBounds(rectF, true);// 测量Path
        canvas.drawPath(path, mPaint);// 绘制Path
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, mPaint);// 绘制边界
    }

    private void drawBoolean(Canvas canvas, Paint mPaint){
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        int r = 100;
        int x = 75;

        canvas.save();
        canvas.translate(200, 120);
        Path Leftpath1 = new Path();
        Path Leftpath2 = new Path();
        Path LpathOp = new Path();
        Leftpath1.addCircle(-x, 0, r, Path.Direction.CW);
        Leftpath2.addCircle(x, 0, r, Path.Direction.CW);
        //左差集,将Leftpath1和Leftpath2交换位置后与REVERSE_DIFFERENCE是一样的
//        LpathOp.op(Leftpath2, Leftpath1, Path.Op.DIFFERENCE);
        LpathOp.op(Leftpath1, Leftpath2, Path.Op.DIFFERENCE);
        canvas.drawPath(LpathOp, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 120);
        Path Rightpath1 = new Path();
        Path Rightpath2 = new Path();
        Path RpathOp = new Path();
        Rightpath1.addCircle(-x, 0, r, Path.Direction.CW);
        Rightpath2.addCircle(x, 0, r, Path.Direction.CW);
        RpathOp.op(Rightpath1, Rightpath2, Path.Op.REVERSE_DIFFERENCE);//右差集
        canvas.drawPath(RpathOp, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(200, 490);
        Path Crosspath1 = new Path();
        Path Crosspath2 = new Path();
        Path CrosspathOp = new Path();
        Crosspath1.addCircle(-x, 0, r, Path.Direction.CW);
        Crosspath2.addCircle(x, 0, r, Path.Direction.CW);
        CrosspathOp.op(Crosspath1, Crosspath2, Path.Op.INTERSECT);//交集
        canvas.drawPath(CrosspathOp, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(500,490);
        Path Andpath1 = new Path();
        Path Andpath2 = new Path();
        Path AndpathOp = new Path();
        Andpath1.addCircle(-x, 0, r, Path.Direction.CW);
        Andpath2.addCircle(x, 0, r, Path.Direction.CW);
        AndpathOp.op(Andpath1, Andpath2, Path.Op.UNION);//并集
        canvas.drawPath(AndpathOp, mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(wid / 2, hei / 2 -25);
        Path Orpath1 = new Path();
        Path Orpath2 = new Path();
        Path OrpathOp = new Path();
        Orpath1.addCircle(-x, 0, r, Path.Direction.CW);
        Orpath2.addCircle(x, 0, r, Path.Direction.CW);
        OrpathOp.op(Orpath1, Orpath2, Path.Op.XOR);//异或
        canvas.drawPath(OrpathOp, mPaint);
    }

    private void drawTaiJi(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);

        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, 200, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 100, 30, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0, -100, 30, mPaint);
    }

    private void drawRxxx(Canvas canvas, Paint mPaint){
        Path path = new Path();
//        path.moveTo(100, 100);
        path.lineTo(100, 200);
        canvas.drawPath(path, mPaint);

        Path path1 = new Path();
        path1.moveTo(100, 100);
        //Rxxx系列方法：相对坐标编程
        path1.rLineTo(100, 200);//相对于上一个点(100, 100)；绝对坐标下的坐标为：(200, 300)
        canvas.drawPath(path1, mPaint);

        canvas.drawCircle(200, 300, 10, mPaint);
    }

    private void drawArcTo(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);  // 移动坐标系到屏幕中心
        Path path = new Path();
        path.lineTo(141.5f, 141.5f);

        RectF oval = new RectF(-200, -200, 200, 200);
//        path.addArc(oval, 135, 270);
        //arcTo中Boolean值作用：是否形成封闭的图形
        path.arcTo(oval, 135, 270, false);      //和上面一句作用等价
        canvas.drawPath(path,mPaint);
    }

    private void drawAddArc(Canvas canvas, Paint mPaint){//画仪表图就会用到这个
        canvas.translate(wid / 2, hei / 2);// 移动坐标系到屏幕中心
        Path path = new Path();
        //画直线
        path.lineTo(141.5f, 141.5f);
        //画圆弧
        RectF oval = new RectF(-200, -200, 200, 200);
//        path.addArc(oval, 135, 270);
        path.arcTo(oval, 135, 270, true);//和上面一句作用等价

        canvas.drawPath(path, mPaint);
    }

    private void drawMergePath(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        /**翻转坐标轴**/
        canvas.scale(1, -1);

        Path mergePath = new Path();
        RectF rect = new RectF(-100,-100,100,100);
        /**矩形**/
        mergePath.addRect(rect, Path.Direction.CW);
        /****/
        Path src = new Path();
        /**圆****/
        src.addCircle(0,100,80, Path.Direction.CW);
        /***将圆添加到矩形path可以，反过来则出问题****/
        mergePath.addPath(src);

        /**标有 **** 的代码跟一下代码结果一样：***/
//        mergePath.addCircle(0,100,80, Path.Direction.CW);

        canvas.drawPath(mergePath, mPaint);
    }

    private void drawRect(Canvas canvas, Paint mPaint){
        canvas.translate(wid / 2, hei / 2);
        Path rectPath = new Path();

        rectPath.addCircle(0, 0, 100, Path.Direction.CCW);//圆

        RectF rect = new RectF(-200,-200, 200, 200);//矩形
        rectPath.addRect(rect, Path.Direction.CW);

        RectF rect1 = new RectF(-200,-100, 200, 100);//椭圆
        rectPath.addOval(rect1, Path.Direction.CW);

        RectF rect2 = new RectF(-250,-200, 250, 200);//圆角矩形
        rectPath.addRoundRect(rect2, 50, 50, Path.Direction.CCW);

        canvas.drawPath(rectPath, mPaint);
    }

    private void drawClose(Canvas canvas, Paint mPaint){
        Path closePath = new Path();
        closePath.moveTo(50,50);
        closePath.lineTo(120, 260);
        closePath.lineTo(300,400);
        closePath.lineTo(500,300);
        closePath.lineTo(400,500);
        closePath.close();
        canvas.drawPath(closePath, mPaint);
    }

    private void drawLastPoint(Canvas canvas, Paint mPaint){
        Path mPath = new Path();
        mPath.moveTo(50,50);
        mPath.lineTo(100,24);
        mPath.setLastPoint(200,320);
        mPath.lineTo(500,580);
        canvas.drawPath(mPath, mPaint);
        //setLastPoint是重置上一次操作的最后一个点，在执行完第一次的lineTo的时候，
        // 最后一个点是A(100,24),而setLastPoint更改最后一个点为C(300,220),
        // 所以在实际执行的时候，第一次的lineTo就不是从原点O到A(100,24)的连线了，
        // 而变成了从原点(50,50)到C(200,320)之间的连线了。然后再从(200,320)划线到(500,580)
    }

    private void drawMoveTo(Canvas canvas, Paint mPaint){
//        Path mPath = new Path();
//        mPath.moveTo(50, 50);
//        mPath.lineTo(500,60);
//        mPath.moveTo(180,160);
//        mPath.lineTo(520,550);
//        canvas.drawPath(mPath, mPaint);
//        //moveTo：移动起点到：x, y;
//        //含义解说：mPath.moveTo(50, 50)：折线起点/原点，
//        // mPath.lineTo(500,60):从起点/原点划线到(500,60)；
//        //然后将绘图起点移到【mPath.moveTo(180,160)】(180,160)，
        //再从(180,160)划线到(520,550)【mPath.lineTo(520,550)】;

        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100,600);
        path.lineTo(400,100);
        path.lineTo(700,900);

        paint.setColor(Color.YELLOW);
        paint.setPathEffect(new CornerPathEffect(200));
        canvas.drawPath(path,paint);
    }

    private void drawLineTo(Canvas canvas, Paint mPaint){

        //默认点为上次操作结束的点，如果没有进行过操作则默认点为坐标原点（屏幕或者控件的左上角）。
        //如下，做了canvas.translate(10, hei-10)之后，默认点为(10, hei-10)处。
        canvas.drawColor(Color.parseColor("#dfdfdf"));
        canvas.translate(10, hei-10);
        //准备数据
        float[] x = {100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600};
        float[] y = {24, 150, 380, 73, 400, 220, 245, 280, 350, 465, 500};
        List<PathCanvasBean> pathList = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            pathList.add(new PathCanvasBean(x[i], y[i]*(-1)));
        }

        //绘制折线
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        //线冒样式(相当于给原来的直线末端加上一个帽子)：圆形，方块，无；
        //http://blog.csdn.net/harvic880925/article/details/51010839
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        //设置线段连接处过渡样式:锐角过渡(尖锐的意思)，圆弧过渡，直线过渡；
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(Color.parseColor("#93A5F4"));

        Path mPath = new Path();
        //设置折线的起点位置，否则折线起点是系统默认的左上角或者View的左上角。
        mPath.moveTo(pathList.get(0).getX(), pathList.get(0).getY());

        for (int i = 0; i < x.length; i++) {
            mPath.lineTo(pathList.get(i).getX(), pathList.get(i).getY());
        }
        //圆形拐角效果
        mPaint.setPathEffect(new CornerPathEffect(200));
        canvas.drawPath(mPath, mPaint);

        mPaint.setPathEffect(null);

        float textSize = 12;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(1);

        //cirPaint用于画每段线段之间的点
        Paint cirPaint = new Paint();
        cirPaint.setAntiAlias(true);
        cirPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        cirPaint.setColor(Color.WHITE);
        for (int i = 0; i < x.length; i++) {
            //绘制折线转折出的点
            canvas.drawCircle(pathList.get(i).getX(), pathList.get(i).getY(), 5, cirPaint);

            String coordinate = "(" + pathList.get(i).getX() + ",  " + pathList.get(i).getY()*(-1) + ")";
            //绘制每个点对应的坐标值，至于为什么这里要分别加10和textSize/2，掌握了上一篇笔记的都应该知道
            canvas.drawText(coordinate, pathList.get(i).getX() + 10, pathList.get(i).getY() + textSize/2, mPaint);
        }
    }
}
