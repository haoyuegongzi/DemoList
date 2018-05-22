package com.haoyue.demo_list.linear_gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by chen1 on 2017/8/9.
 */

public class LinearGradient extends View {
    private Paint mPaint;

    public LinearGradient(Context context) {
        super(context);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //无论哪种Shader，都是从控件的左上角开始填充的，利用canvas.drawXXX系列函数只是用来指定显示哪一块
        int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00, 0xff00ffff};
        float[]  pos = {0f, 0.2f, 0.4f, 0.6f, 1.0f};
        //从上到下的渐变色 Color.parseColor("#ff0000ff"), Color.parseColor("#ff00ff00")
//        mPaint.setShader(new android.graphics.LinearGradient(getWidth(), 0, getWidth(), getHeight(),
//                0xff0000ff, 0xff00ff00, Shader.TileMode.CLAMP));
//        mPaint.setShader(new android.graphics.LinearGradient(getWidth(), 0, getWidth(),
//                            getHeight(), colors, pos, Shader.TileMode.CLAMP));REPEAT

//        android.graphics.LinearGradient multiGradient = new  android.graphics.LinearGradient(getWidth()/2,
//                                        getHeight()/2, 0,0, colors, pos, Shader.TileMode.CLAMP);
//        android.graphics.LinearGradient multiGradient = new  android.graphics.LinearGradient(0, 0, getWidth()/2,
//                getHeight()/2, colors, pos, Shader.TileMode.REPEAT);
                android.graphics.LinearGradient multiGradient = new  android.graphics.LinearGradient(0, 0, getWidth()/2,
                getHeight()/2, colors, pos, Shader.TileMode.MIRROR);

//        mPaint.setShader(multiGradient);
//        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), mPaint);
        mPaint.setShader(multiGradient);
        mPaint.setTextSize(50);
        canvas.drawText("欢迎关注启舰的blog", 0 , 100, mPaint);
    }
}
