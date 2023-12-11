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
        TimerState currentTimerState = this.getCurrentTimerState();
        if (currentTimerState == TimerState.Start) {
            System.out.println("warn: timer is started already");
            return;
        }

        // タイマーオブジェクトに必要な情報を登録
        this.countdownTimer.setStartTimeMSec(setTimeMSec);
        this.countdownTimer.setCurrentTimeMSec(setTimeMSec);
        this.countdownTimer.setEndTask(endTask);

        // 終了時に実行するタスクを設定
        this.countdownTimer.setServiceEndTask(() -> {
            // this.countdownTimer.setCurrentTimeMSec(0);
            // this.countdownTimer.setTimerState(TimerState.Stop);
            // // Debug
            // this.printTimerInfo("TIMER END");
            this.endTimer();
        });

        // タイマースタート
        // 時間は先に初期化で設定した時間を指定
        this.startTimer(this.countdownTimer.getStartTimeMSec());

        // Debug
        this.printTimerInfo("TIMER START");
    }

    /**
     * タイマー一時停止
     */
    public void pause() {
        TimerState currentTimerState = this.getCurrentTimerState();
        if (currentTimerState == TimerState.Pause) {
            return;
        }
        if (currentTimerState == TimerState.Stop) {
            return;
        }
        Timer timer = this.countdownTimer.getTimer();
        if (timer == null) {
            return;
        }

        // 登録していたスケジュールを解除
        timer.cancel();

        // 現在のカウントを取得して、途中再開のために登録
        // long endedTimeStamp = System.currentTimeMillis();
        // long startTimeStamp = this.countdownTimer.getStartedTimeStamp();
        // long elapsedTimeMSec = endedTimeStamp - startTimeStamp;
        // long currentSetTimeMSec = this.countdownTimer.getCurrentTimeMSec();
        // long newSetTimeMSec = currentSetTimeMSec - elapsedTimeMSec;
        long newSetTimeMSec = this.getCurrentTimeCountMSec();
        this.countdownTimer.setCurrentTimeMSec(newSetTimeMSec);

        // 状態を「一時停止」に変更
        this.countdownTimer.setTimerState(TimerState.Pause);

        // Debug
        this.printTimerInfo("TIMER PAUSE");
    }

    /**
     * タイマー再開
     */
    public void resume() {
        TimerState currentTimerState = this.getCurrentTimerState();
        if (currentTimerState == TimerState.Start) {
            return;
        }
        if (currentTimerState == TimerState.Stop) {
            return;
        }
        long currentTimeMSec = this.countdownTimer.getCurrentTimeMSec();
        if (currentTimeMSec <= 0) {
            return;
        }

        // 途中再開用の情報再登録
        // this.countdownTimer.setStartedTimeStamp(System.currentTimeMillis());

        // 開始
        this.startTimer(currentTimeMSec);

        // Debug
        this.printTimerInfo("TIMER RESUME");
    }

    /**
     * タイマー開始
     * 
     * @param startTime
     */
    private void startTimer(long startTime) {
        // 現在時刻を開始時のタイムスタンプとして登録
        long currentTimeStamp = System.currentTimeMillis();
        this.countdownTimer.setStartedTimeStamp(currentTimeStamp);

        // 終了時に実行するタスクを取得
        Runnable serviceEndTask = this.countdownTimer.getServiceEndTask();
        Runnable endTask = this.countdownTimer.getEndTask();

        // タイマー開始
        this.countdownTimer.setTimer(new Timer());
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                serviceEndTask.run();
                endTask.run();
            }
        };
        this.countdownTimer.getTimer().schedule(task, startTime);

        // 状態を「開始」に変更
        this.countdownTimer.setTimerState(TimerState.Start);
    }

    /**
     * タイマー終了
     */
    private void endTimer() {
        // 現在のカウントを 0 に設定
        this.countdownTimer.setCurrentTimeMSec(0);

        // 状態を「停止」に変更
        this.countdownTimer.setTimerState(TimerState.Stop);

        // Debug
        this.printTimerInfo("TIMER END");
    }

    /**
     * タイマー情報出力
     * 
     * @param label
     */
    private void printTimerInfo(String label) {
        System.out.println("debug: " + label);
        System.out.println("this.countdownMSec: " + this.countdownTimer.getStartTimeMSec());
        System.out.println("this.elapsedMSec: " + this.countdownTimer.getCurrentTimeMSec());
        System.out.println("this.timerState: " + this.countdownTimer.getTimerState());
    }

    /**
     * 現在のタイムカウントをMSecで取得
     * 
     * @return
     */
    public long getCurrentTimeCountMSec() {
        TimerState currentTimerState = this.getCurrentTimerState();
        if (currentTimerState == TimerState.Stop) {
            return 0;
        }
        if (currentTimerState == TimerState.Pause) {
            long currentSetTimeMSec = this.countdownTimer.getCurrentTimeMSec();
            return currentSetTimeMSec;
        }
        long currentTimeStamp = System.currentTimeMillis();
        long startTimeStamp = this.countdownTimer.getStartedTimeStamp();
        long elapsedTimeMSec = currentTimeStamp - startTimeStamp;
        long currentSetTimeMSec = this.countdownTimer.getCurrentTimeMSec();
        long currentTimeCountMSec = currentSetTimeMSec - elapsedTimeMSec;
        return currentTimeCountMSec;
    }

    /**
     * 現在のタイマー状態を取得
     * 
     * @return
     */
    public TimerState getCurrentTimerState() {
        TimerState timerState = this.countdownTimer.getTimerState();
        return timerState;
    }

}
