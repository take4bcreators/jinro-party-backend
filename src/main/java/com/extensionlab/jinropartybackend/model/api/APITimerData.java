package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.TimerState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APITimerData {
    /** 現在のタイムカウント */
    long timeCountMSec;
    /** タイマー状態 */
    TimerState timerState;
}
