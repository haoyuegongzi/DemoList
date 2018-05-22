package com.haoyue.demo_list.dialog_haoyue;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.haoyue.demo_list.R;

/**
 * Created by chen1 on 2017/9/21.
 */

public class HaoYueDialog extends Dialog {
    public View mVLeft;
    public View mVRight;

    public TextView mTvDialogTitle;
    public TextView mTvDialogMessage;

    public TextView mTvDialogLeftButton;
    public TextView mTvDialogMiddleButton;
    public TextView mTvDialogRightButton;

    private LeftButtOnclickListener leftListener;
    private MiddleButtOnclickListener middleListener;
    private RightButtOnclickListener rightListener;

    public HaoYueDialog(Context context) {
        super(context, R.style.CustomDialog);// 使用自定义样式
        setContentView(R.layout.dialog_haoyue);
        setCanceledOnTouchOutside(false);
        initId();
        setButtonListener();
    }

    public HaoYueDialog(Context context, int Theme){
        super(context, Theme);
    }

    private void initId(){
        mVLeft = (View)findViewById(R.id.vLeft);
        mVRight = (View)findViewById(R.id.vRight);

        mTvDialogTitle = (TextView)findViewById(R.id.tvDialogTitle);
        mTvDialogMessage = (TextView)findViewById(R.id.tvDialogMessage);

        mTvDialogLeftButton = (TextView)findViewById(R.id.tvDialogLeftButton);
        mTvDialogMiddleButton = (TextView)findViewById(R.id.tvDialogMiddleButton);
        mTvDialogRightButton = (TextView)findViewById(R.id.tvDialogRightButton);
    }

    private void setButtonListener(){
        mTvDialogLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( leftListener != null){
                    leftListener.leftButtclick();
                }
            }
        });
        mTvDialogMiddleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( middleListener != null){
                    middleListener.middleButtclick();
                }
            }
        });
        mTvDialogRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( rightListener != null){
                    rightListener.rightButtclick();
                }
            }
        });
    }

    public void setLeftButtOnclickListener(String left, LeftButtOnclickListener leftButtOnclickListener){
        if(!TextUtils.isEmpty(left)){
            mTvDialogLeftButton.setText(left);
        }
        leftListener = leftButtOnclickListener;
    }

    public void setMiddleButtOnclickListener(String middle, MiddleButtOnclickListener middleButtOnclickListener){
        if(!TextUtils.isEmpty(middle)){
            mTvDialogMiddleButton.setText(middle);
        }
        middleListener = middleButtOnclickListener;
    }

    public void setRightButtOnclickListener(String right, RightButtOnclickListener rightButtOnclickListener){
        if(!TextUtils.isEmpty(right)){
            mTvDialogRightButton.setText(right);
        }
        rightListener = rightButtOnclickListener;
    }

    public void setButtonCount(int buttonsCount) {
        if(buttonsCount > 0 && buttonsCount < 4){
            hiddeSomeView(buttonsCount);
        }
    }

    private void hiddeSomeView(int count){
        switch (count){
            case 1:
                mTvDialogLeftButton.setVisibility(View.GONE);
                mVLeft.setVisibility(View.GONE);
                mTvDialogMiddleButton.setVisibility(View.GONE);
                mVRight.setVisibility(View.GONE);
                mTvDialogRightButton.setVisibility(View.VISIBLE);
                break;
            case 2:
                mTvDialogLeftButton.setVisibility(View.GONE);
                mVLeft.setVisibility(View.GONE);
                mTvDialogMiddleButton.setVisibility(View.VISIBLE);
                mVRight.setVisibility(View.GONE);
                mTvDialogRightButton.setVisibility(View.VISIBLE);
                break;
            case 3:
                mTvDialogLeftButton.setVisibility(View.VISIBLE);
                mVLeft.setVisibility(View.VISIBLE);
                mTvDialogMiddleButton.setVisibility(View.VISIBLE);
                mVRight.setVisibility(View.VISIBLE);
                mTvDialogRightButton.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }


    public void setTitle(String title) {
        if( !TextUtils.isEmpty(title)){
            mTvDialogTitle.setText(title);
            Log.i("TAG", "setDialogMessage: title ===" + title);
        }
    }

    public void setMessage(String message) {
        if( !TextUtils.isEmpty(message.trim())){
            mTvDialogMessage.setText(message);
            mTvDialogMessage.setTextColor(Color.parseColor("#000000"));
            Log.i("TAG", "setDialogMessage: message ===" + message);
        }
    }
}
