package com.haoyue.demo_list.canvas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chen1 on 2017/12/18.
 * TO DO:
 */

public class PathCanvasBean implements Parcelable {
    float x;
    float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public PathCanvasBean(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(this.y);
    }

    public PathCanvasBean() {
    }

    protected PathCanvasBean(Parcel in) {
        this.x = in.readFloat();
        this.y = in.readFloat();
    }

    public static final Parcelable.Creator<PathCanvasBean> CREATOR = new Parcelable.Creator<PathCanvasBean>() {
        @Override
        public PathCanvasBean createFromParcel(Parcel source) {
            return new PathCanvasBean(source);
        }

        @Override
        public PathCanvasBean[] newArray(int size) {
            return new PathCanvasBean[size];
        }
    };
}
