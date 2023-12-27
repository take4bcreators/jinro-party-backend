package com.extensionlab.jinropartybackend.enums;

/**
 * ゲーム状態 (列挙型)
 */
public enum GameState {
    /** 未設定 */
    Empty,
    /** ゲーム開始前 */
    PreGame,
    /** プレイヤー受付中 */
    PlayerJoining,
    /** プレイヤー受付終了・設定 */
    PlayerJoiningEnded,
    /** プレイヤーリスト発表 */
    PlayerListDisplay,
    /** 役職決定 */
    RoleAssignment,
    /** 昼フェーズ開始 */
    DayPhaseStart,
    /** 昼フェーズ */
    DayPhase,
    /** 昼フェーズ終了 */
    DayPhaseEnd,
    /** 投票 */
    Voting,
    /** 投票終了 */
    VotingEnd,
    /** 投票結果発表 */
    VoteResult,
    /** 追放者発表 */
    ExileAnnouncement,
    /** 追放者発表（ゲーム終了直前） */
    FinalExileAnnouncement,
    /** 夜フェーズ開始 */
    NightPhaseStart,
    /** 夜フェーズ */
    NightPhase,
    /** 夜フェーズ終了 */
    NightPhaseEnd,
    /** 朝フェーズ開始 */
    MorningPhaseStart,
    /** 襲撃者発表 */
    NightActionResult,
    /** ゲーム終了 */
    GameEnd,
    /** 結果発表 */
    FinalResult,
    /** 役職公開 */
    RoleReveal,
}
