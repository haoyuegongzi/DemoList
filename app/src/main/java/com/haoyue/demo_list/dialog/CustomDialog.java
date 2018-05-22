package com.haoyue.demo_list.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.haoyue.demo_list.R;

/**
 * Created by chen1 on 2017/9/22.
 */

public class CustomDialog extends Dialog {
    Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        this.setContentView(R.layout.dialog_bike_finsh);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.setContentView(R.layout.dialog_bike_finsh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
