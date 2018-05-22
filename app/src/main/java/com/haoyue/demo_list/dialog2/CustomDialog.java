package com.haoyue.demo_list.dialog2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoyue.demo_list.R;

/**
 * Created by chen1 on 2017/9/22.
 */

public class CustomDialog extends Dialog {
    private Button confirmBtn;//确定按钮
    private Button cancelBtn;//取消按钮
    private TextView titleTextView;//消息标题文本
    private ImageView messageTextView;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String confirmStr;
    private String cancelStr;

    private cancelOnclickListener mCancelOnclickListener;//取消按钮被点击了的监听器
    private confirmOnclickListener mConfirmOnclickListener;//确定按钮被点击了的监听器

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);// 使用自定义样式
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        // 按空白处不能取消
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }

    //初始化UI
    private void initView() {
        confirmBtn = findViewById(R.id.confirm);
        cancelBtn = findViewById(R.id.cancel);
        titleTextView = findViewById(R.id.dialog_title);
        messageTextView = findViewById(R.id.dialog_message);
    }

    //初始化数据
    private void initData() {
        if (titleStr != null) {
            titleTextView.setText(titleStr);
        }

        if (messageStr != null) {
            messageTextView.setImageResource(R.mipmap.dog);
        }

        if (confirmStr != null) {
            confirmBtn.setText(confirmStr);
        }

        if (cancelStr != null) {
            cancelBtn.setText(cancelStr);
        }
    }

    //初始化事件 确定按钮和取消按钮的监听事件分发
    private void initEvent() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmOnclickListener != null) {
                    mConfirmOnclickListener.confirmClick();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancelOnclickListener != null) {
                    mCancelOnclickListener.cancelClick();
                }
            }
        });
    }

    //设置自定义标题
    public void setTitle(String title) {
        titleStr = title;
    }

    //设置自定义内容
    public void setMessage(String message) {
        messageStr = message;
    }

    //设置取消按钮的显示内容和监听
    public void setCancelOnclickListener(String str, cancelOnclickListener cancelOnclickListener) {
        if (str != null) {
            cancelStr = str;
        }
        this.mCancelOnclickListener = cancelOnclickListener;
    }

    //设置确定按钮的显示内容和监听
    public void setConfirmOnclickListener(String str, confirmOnclickListener confirmOnclickListener) {
        if (str != null) {
            confirmStr = str;
        }
        this.mConfirmOnclickListener = confirmOnclickListener;
    }

    //设置确定按钮和取消被点击的接口
    public interface confirmOnclickListener {
        public void confirmClick();
    }

    public interface cancelOnclickListener {
        public void cancelClick();
    }
}
