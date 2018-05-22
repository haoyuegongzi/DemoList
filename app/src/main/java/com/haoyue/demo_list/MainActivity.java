package com.haoyue.demo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haoyue.demo_list.CanvasTest.CanvasLineActivity;
import com.haoyue.demo_list.operatecheckres.OperateCheckresActivity;
import com.haoyue.demo_list.Water_ripple.WaterRippleActivity;
import com.haoyue.demo_list.animation.AnimationActivity;
import com.haoyue.demo_list.beziers.BezierActivity;
import com.haoyue.demo_list.beziers.BezierHeartActivity;
import com.haoyue.demo_list.beziers.BezierThreeActivity;
import com.haoyue.demo_list.canvas.CanvasActivity;
import com.haoyue.demo_list.checkview.CheckViewActivity;
import com.haoyue.demo_list.color_filter.ColorFilterActivity;
import com.haoyue.demo_list.date.DateActivity;
import com.haoyue.demo_list.dialog.TestDialogActivity;
import com.haoyue.demo_list.dialog2.CustomDialogActivity;
import com.haoyue.demo_list.dialog_haoyue.HaoyueDialogActivity;
import com.haoyue.demo_list.download.DownLoadServiceActivity;
import com.haoyue.demo_list.finger_path.FingerPathActivity;
import com.haoyue.demo_list.gesture.GestureDetectorActivity;
import com.haoyue.demo_list.linear_gradient.LinearGradientActivity;
import com.haoyue.demo_list.matrix.MatrixActivity;
import com.haoyue.demo_list.matrix_animation.MatrixCameraActivity;
import com.haoyue.demo_list.motionevent.MotionEventActivity;
import com.haoyue.demo_list.pathmeasure.PathMeasureActivity;
import com.haoyue.demo_list.receiver.ReceiverActivity;
import com.haoyue.demo_list.service.TestServiceActivity;
import com.haoyue.demo_list.thread.ThreadActivity;
import com.haoyue.demo_list.wave.WaveActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dealUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.i("TAG", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.i("TAG", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.i("TAG", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Log.i("TAG", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.i("TAG", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.i("TAG", "onDestroy");
    }

    public void dealUI(){
        findViewById(R.id.btCanvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
            }
        });
        findViewById(R.id.btCheckView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CheckViewActivity.class));
            }
        });
        findViewById(R.id.btThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThreadActivity.class));
            }
        });
        findViewById(R.id.btScrollViewHorizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CanvasLineActivity.class));
            }
        });
        findViewById(R.id.btBezier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BezierActivity.class));
            }
        });
        findViewById(R.id.btPathMeasure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PathMeasureActivity.class));
            }
        });
        findViewById(R.id.btMatrix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MatrixActivity.class));
            }
        });
        findViewById(R.id.btService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestServiceActivity.class));
            }
        });
        findViewById(R.id.btDownLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DownLoadServiceActivity.class));
            }
        });
        findViewById(R.id.btReceiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ReceiverActivity.class));
            }
        });
        findViewById(R.id.btMatrixCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MatrixCameraActivity.class));
            }
        });
        findViewById(R.id.btMotionEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MotionEventActivity.class));
            }
        });
        findViewById(R.id.btGestureDetector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GestureDetectorActivity.class));
            }
        });
        findViewById(R.id.btDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DateActivity.class));
            }
        });
        findViewById(R.id.btBezierThree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BezierThreeActivity.class));
            }
        });
        findViewById(R.id.btBezierHeart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BezierHeartActivity.class));
            }
        });
        findViewById(R.id.btFingerPath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FingerPathActivity.class));
            }
        });

        findViewById(R.id.btWave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WaveActivity.class));
            }
        });
        findViewById(R.id.btColorFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ColorFilterActivity.class));
            }
        });
        findViewById(R.id.btLinearGradient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinearGradientActivity.class));
            }
        });
        findViewById(R.id.btWaterRipple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WaterRippleActivity.class));
            }
        });
        findViewById(R.id.btAnimation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
            }
        });
        findViewById(R.id.btTestDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestDialogActivity.class));
            }
        });
        findViewById(R.id.btCustomDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CustomDialogActivity.class));
            }
        });
        findViewById(R.id.btHaoyueDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HaoyueDialogActivity.class));
            }
        });
        findViewById(R.id.btOperateCheckre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OperateCheckresActivity.class));
            }
        });

    }
}
