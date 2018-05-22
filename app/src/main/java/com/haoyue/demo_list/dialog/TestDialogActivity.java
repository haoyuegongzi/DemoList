package com.haoyue.demo_list.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.haoyue.demo_list.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestDialogActivity extends AppCompatActivity {

    @BindView(R.id.btDialog)
    Button mBtDialog;


    private String TAG = this.getClass().getName();
    private CustomDialog mDialog;
    //设备屏幕宽高
    int widthPixels;
    int heightPixels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        ButterKnife.bind(this);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;
    }


    @OnClick(R.id.btDialog)
    public void onClick() {
        setCostomMsg();
    }

    private void setCostomMsg(){
        mDialog = new CustomDialog(this, R.style.dialog);
        Button finshDialog = (Button) mDialog.findViewById(R.id.dialog_btn_finsh);

        Window dialogWindow = mDialog.getWindow();

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow .setGravity(Gravity.CENTER);

        lp.x = widthPixels/2; // 新位置X坐标
        lp.y = heightPixels/2; // 新位置Y坐标
        dialogWindow.setAttributes(lp);
        mDialog.show();
        finshDialog.setText("Close this Dialog");
        finshDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()){
                    mDialog.dismiss();
                    Log.e(TAG,"关闭dialog");
                    Toast.makeText(TestDialogActivity.this, "close The Dialog", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
