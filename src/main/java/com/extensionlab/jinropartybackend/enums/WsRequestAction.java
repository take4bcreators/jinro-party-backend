package com.extensionlab.jinropartybackend.enums;

/**
 * WebSocket要求アクション (列挙型)
 */
public enum WsRequestAction {
    /** 未設定 */
    Empty,
    /** アクションなし（デバッグ用） */
    NoAction,
    /** ゲーム状態更新 */
    GameStateUpdate,
    /** ゲーム画面変更 */
    GameScreenChange,
    // /** カウントダウンタイマー開始 */
    // CountdownTimerStart,
    // /** カウントダウンタイマー一時停止 */
    // CountdownTimerPause,
    // /** カウントダウンタイマー再開 */
    // CountdownTimerResume,
    /** タイマー状態変更 */
    TimerStateChange,
    /** ゲーム状態確認 */
    ReturnCurrentGameState,
    /** エントリープレイヤー数 */
    ReturnEntryPlayerCount,
    /** 投票テーブル変更 */
    VoteTableChange,
    /** 役職確認更新 */
    SelfRoleCheckUpdate,
    /** 夜アクション更新 */
    NightActionUpdate,
}
