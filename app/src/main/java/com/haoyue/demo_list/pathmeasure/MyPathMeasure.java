package com.haoyue.demo_list.pathmeasure;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Created by chen1 on 2017/7/27.
 */

public class MyPathMeasure extends PathMeasure {

    public MyPathMeasure() {
    }

    public MyPathMeasure(Path path, boolean forceClosed) {
        super(path, forceClosed);
    }

    @Override
    public void setPath(Path path, boolean forceClosed) {
        super.setPath(path, forceClosed);
    }

    @Override
    public boolean isClosed() {
        return super.isClosed();
    }

    @Override
    public float getLength() {
        return super.getLength();
    }

    @Override
    public boolean nextContour() {
        return super.nextContour();
    }

    @Override
    public boolean getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo) {
        return super.getSegment(startD, stopD, dst, startWithMoveTo);
    }

    @Override
    public boolean getPosTan(float distance, float[] pos, float[] tan) {
        return super.getPosTan(distance, pos, tan);
    }

    @Override
    public boolean getMatrix(float distance, Matrix matrix, int flags) {
        return super.getMatrix(distance, matrix, flags);
    }

}
