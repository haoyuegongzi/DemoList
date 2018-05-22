package com.haoyue.demo_list.dialog_haoyue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haoyue.demo_list.R;

public class HaoyueDialogActivity extends AppCompatActivity implements LeftButtOnclickListener,
                                        MiddleButtOnclickListener, RightButtOnclickListener {

    HaoYueDialog haoyue;
    Button btDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_haoyue_dialog);

        btDialog = (Button) findViewById(R.id.btDialog);
        haoyue = new HaoYueDialog(this);
        initDialog();
    }

    private void initDialog(){
        btDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haoyue.setTitle("开心通知");
                haoyue.setMessage("今天我们提前下班，为伟大的祖国母亲68诞辰做贡献");
                haoyue.setLeftButtOnclickListener("取消", HaoyueDialogActivity.this);
                haoyue.setMiddleButtOnclickListener("忽略", HaoyueDialogActivity.this);
                haoyue.setRightButtOnclickListener("确定", HaoyueDialogActivity.this);
                haoyue.show();
            }
        });
    }

    @Override
    public void leftButtclick() {
        haoyue.dismiss();
        Toast.makeText(HaoyueDialogActivity.this, "leftButtonClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void middleButtclick() {
        haoyue.dismiss();
        Toast.makeText(HaoyueDialogActivity.this, "middleButtonClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightButtclick() {
        haoyue.dismiss();
        Toast.makeText(HaoyueDialogActivity.this, "rightButtonClick", Toast.LENGTH_SHORT).show();
    }
}
