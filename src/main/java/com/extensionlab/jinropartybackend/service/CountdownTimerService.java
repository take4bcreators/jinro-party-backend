package com.extensionlab.jinropartybackend.service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.TimerState;
import com.extensionlab.jinropartybackend.model.common.CountdownTimer;

@Service
public class CountdownTimerService {

    private CountdownTimer countdownTimer;

    public CountdownTimerService() {
        this.countdownTimer = new CountdownTimer();
        this.countdownTimer.setTimerState(TimerState.Stop);
    }

    /**
     * タイマー実行開始
     * 
     * @param endTask
     *            終了後に行う処理
     * @param setTimeMSec
     *            時間（ミリ秒）
     */
    public void start(int setTimeMSec, Runnable endTask) {
        if (setTimeMSec <= 0) {
            return;
        }
        if (this.countdownTimer.getTimerState() == TimerState.Start) {
            return;
        }

        // タイマー初期化
        this.countdownTimer.setStartTimeMSec(setTimeMSec);
        this.countdownTimer.setCurrentTimeMSec(setTimeMSec);

        // 途中再開用の情報登録
        this.countdownTimer.setEndTask(endTask);

        // 終了時のタスク
        this.countdownTimer.setServiceEndTask(() -> {
            this.countdownTimer.setCurrentTimeMSec(0);
            this.countdownTimer.setTimerState(TimerState.Stop);
            // Debug
            this.printTimerInfo("TIMER END");
        });

        // スタート
        this.startTimer(this.countdownTimer.getStartTimeMSec());

        // Debug
        this.printTimerInfo("TIMER START");
    }

    /**
     * タイマー一時停止
     */
    public void pause() {
        TimerState timerState = this.countdownTimer.getTimerState();
        if (timerState == TimerState.Pause) {
            return;
        }
        if (timerState == TimerState.Stop) {
            return;
        }
        Timer timer = this.countdownTimer.getTimer();
        if (timer == null) {
            return;
        }

        // 登録スケジュール解除
        timer.cancel();

        // 途中再開用の情報登録
        long endedTime = System.currentTimeMillis();
        long elapsedTime = endedTime - this.countdownTimer.getStartedTime();
        long currentTimeMSec = this.countdownTimer.getCurrentTimeMSec();
        this.countdownTimer.setCurrentTimeMSec(currentTimeMSec - elapsedTime);

        // 状態変更
        this.countdownTimer.setTimerState(TimerState.Pause);

        // Debug
        this.printTimerInfo("TIMER PAUSE");
    }

    /**
     * タイマー再開
     */
    public void resume() {
        TimerState timerState = this.countdownTimer.getTimerState();
        if (timerState == TimerState.Start) {
            return;
        }
        if (timerState == TimerState.Stop) {
            return;
        }
        long currentTimeMSec = this.countdownTimer.getCurrentTimeMSec();
        if (currentTimeMSec <= 0) {
            return;
        }

        // 途中再開用の情報再登録
        this.countdownTimer.setStartedTime(System.currentTimeMillis());

        // 開始
        this.startTimer(currentTimeMSec);

        // Debug
        this.printTimerInfo("TIMER RESUME");
    }

    private void startTimer(long startTime) {
        // 途中再開用に現在時刻を登録
        this.countdownTimer.setStartedTime(System.currentTimeMillis());

        // 終了時のタスクを取得
        Runnable serviceEndTask = this.countdownTimer.getServiceEndTask();
        Runnable endTask = this.countdownTimer.getEndTask();

        // タイマー開始
        this.countdownTimer.setTimer(new Timer());
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                endTask.run();
                serviceEndTask.run();
            }
        };
        this.countdownTimer.getTimer().schedule(task, startTime);

        // 状態の変更
        this.countdownTimer.setTimerState(TimerState.Start);
    }

    private void printTimerInfo(String label) {
        System.out.println("debug: " + label);
        System.out.println("this.countdownMSec: " + this.countdownTimer.getStartTimeMSec());
        System.out.println("this.elapsedMSec: " + this.countdownTimer.getCurrentTimeMSec());
        System.out.println("this.timerState: " + this.countdownTimer.getTimerState());
    }

}
