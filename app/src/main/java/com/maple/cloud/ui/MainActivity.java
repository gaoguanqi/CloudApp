package com.maple.cloud.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.maple.cloud.R;
import com.maple.cloud.utils.MyCountDownTimer;

import io.flutter.embedding.android.FlutterFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llSplash;
    private FrameLayout anchor;
    private MyCountDownTimer mTimer;
    private long millisInFuture = 3000;
    private long countDownInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int barHeight = BarUtils.getStatusBarHeight();
        llSplash = findViewById(R.id.ll_splash);
        anchor = findViewById(R.id.anchor);

        llSplash.setPadding(0,0,0,SizeUtils.px2dp(barHeight));

        if (!BarUtils.isStatusBarLightMode(this)) {
            ImmersionBar.with(this)
                    .statusBarColor(R.color.colorPrimary)
                    .navigationBarColor(R.color.white)
                    .statusBarDarkFont(true)
                    .autoDarkModeEnable(true)
                    .init();
        }

        //页面上的按钮点击的时候 向占位符里 添加一个 flutter module
        FragmentTransaction tx = getSupportFragmentManager()
                .beginTransaction();
        //FlutterFragment中的 initialRoute方法接收一个 string参数 可以传到flutter中接收
        FlutterFragment fragment = FlutterFragment.withNewEngine().initialRoute("CloudApp").build();
        tx.replace(R.id.anchor, fragment);
        tx.commit();
        mTimer = new MyCountDownTimer(millisInFuture, countDownInterval, new MyCountDownTimer.TimerCallback() {
            @Override
            public void onTimerStart() {

            }

            @Override
            public void onTimerTick(long millisUntilFinished) {

            }

            @Override
            public void onTimerFinish() {
                if(llSplash.getVisibility() == View.VISIBLE){
                    llSplash.setVisibility(View.GONE);
                }
                if(anchor.getVisibility() == View.INVISIBLE){
                    anchor.setVisibility(View.VISIBLE);
                }
                if(!fragment.shouldAttachEngineToActivity()){
                    tx.commit();
                }
            }
        });



    }
}