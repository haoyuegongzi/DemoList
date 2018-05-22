package com.haoyue.demo_list.dialog2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.haoyue.demo_list.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialogActivity extends AppCompatActivity implements CustomDialog.cancelOnclickListener,
                                                                        CustomDialog.confirmOnclickListener{
    CustomDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        ButterKnife.bind(this);
        dialog = new CustomDialog(this);
    }

    @OnClick(R.id.btCustomDialog)
    public void onClick() {
        dialog.setTitle("温馨提示");
        dialog.setMessage("今天我们提前下班，为伟大的祖国母亲68诞辰做贡献.");
        dialog.setCancelOnclickListener("取消", this);
        dialog.setConfirmOnclickListener("确定", this);
        dialog.show();
    }

    @Override
    public void confirmClick() {
        dialog.dismiss();
        Toast.makeText(CustomDialogActivity.this,"click confirm", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelClick() {
        Toast.makeText(CustomDialogActivity.this,"click cancel", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
