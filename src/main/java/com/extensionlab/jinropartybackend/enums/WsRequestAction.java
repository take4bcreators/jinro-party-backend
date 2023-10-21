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
    /** カウントダウンタイマー開始 */
    CountdownTimerStart,
    /** カウントダウンタイマー一時停止 */
    CountdownTimerPause,
    /** カウントダウンタイマー再開 */
    CountdownTimerResume,
    /** ゲーム状態確認 */
    ReturnCurrentGameState,
    /** 役職確認登録 */
    RoleRegistration,
    /** 追放者投票登録 */
    ExileVoteRegistration,
    /** アンケート登録 */
    SurveyRegistration,
    /** 占い師のアクション実行 */
    SeerActionExecute,
    /** 霊能者のアクション実行 */
    MediumActionExecute,
    /** 狩人のアクション実行 */
    HunterActionExecute,
    /** 人狼による襲撃者投票 */
    WerewolfAttackVote,
    /** 生存者情報展開 */
    SurvivorInfoDisplay,
    /** 人狼襲撃者投票情報展開 */
    WerewolfAttackVoteInfoDisplay,
    /** 各プレイヤー向け脱落情報展開 */
    PlayerEliminationInfoDisplay,
    /** モニター向け脱落情報展開 */
    SpectatorEliminationInfoDisplay,
    /** 投票結果情報展開 */
    VoteResultInfoDisplay,
    /** 最終結果情報展開 */
    FinalResultInfoDisplay,
    /** 役職一覧情報展開 */
    RoleListInfoDisplay,
    /** ゲームマスター向けゲーム情報展開 */
    GameMasterGameInfoDisplay,
    /** ゲーム終了 */
    GameEnd,
}
