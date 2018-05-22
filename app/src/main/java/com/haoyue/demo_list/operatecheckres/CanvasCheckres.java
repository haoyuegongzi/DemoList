package com.haoyue.demo_list.operatecheckres;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.haoyue.demo_list.R;

/**
 * Created by BONC on 2017/12/14.
 */

public class CanvasCheckres extends View {
    private Context mcontext;
    private Bitmap bitmap = null;
    private Rect areaSrc = null;
    private Rect perDst = null;
    private int viewX;
    private int viewY;
    //图片一共13个小部分组成
    private int position = -1;
    private int canvasItemWid;
    private Paint mPaint;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public CanvasCheckres(Context context) {
        super(context);
        mcontext = context;
        bitmap = BitmapFactory.decodeResource(mcontext.getResources(), R.mipmap.checkres);
        canvasItemWid = bitmap.getHeight();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffFF5317);
    }

    public CanvasCheckres(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewX = w;
        viewY = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(viewX/2, viewY/2);
        canvas.drawCircle(0, 0, canvasItemWid+30, mPaint);
        areaSrc = new Rect(canvasItemWid * (position - 1), 0, canvasItemWid * position, canvasItemWid);
        perDst = new Rect(-100, -100, 100, 100);
        canvas.drawBitmap(bitmap, areaSrc, perDst, new Paint());
    }
}
