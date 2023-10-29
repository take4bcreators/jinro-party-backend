package com.extensionlab.jinropartybackend.model.common;

import java.util.Timer;

import com.extensionlab.jinropartybackend.enums.TimerState;

import lombok.Data;

@Data
public class CountdownTimer {
    /** 開始時の時間（ミリ秒） */
    private int startTimeMSec;
    /** 現在の時間（ミリ秒） */
    private long currentTimeMSec;
    /** 開始時刻 */
    private long startedTime;
    /** タイマー */
    private Timer timer;
    /** 終了時に実行するタスク */
    private Runnable endTask;
    /** サービスで使用する終了時のタスク */
    private Runnable serviceEndTask;
    /** タイマーの状態 */
    private TimerState timerState;
}
