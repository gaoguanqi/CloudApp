package com.maple.cloud.utils;

import android.os.CountDownTimer;

public class MyCountDownTimer extends CountDownTimer {

    private long mMillisInFuture;
    private long mCountDownInterval;
    private TimerCallback mCallback;
    /**
     * @param millisInFuture 倒计时的总时间, 单位ms.
     * @param countDownInterval 倒计时的间隔时间, 单位ms.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval,TimerCallback callback) {
        super(millisInFuture, countDownInterval);
        this.mMillisInFuture = millisInFuture;
        this.mCountDownInterval = countDownInterval;
        this.mCallback = callback;
        this.start();
        mCallback.onTimerStart();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCallback.onTimerTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        mCallback.onTimerFinish();
        this.cancel();
    }

    public interface TimerCallback{
        void onTimerStart();
        void onTimerTick(long millisUntilFinished);
        void onTimerFinish();
    }
}
