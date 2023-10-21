package com.extensionlab.jinropartybackend.enums;

/**
 * WebSocket宛先 (列挙型)
 */
public enum WsDestinationType {
    /** バックエンドサーバーを含めたすべて */
    All,
    /** バックエンドサーバー向け */
    Server,
    /** 各サイト向け */
    AllSite,
    /** ゲームマスターサイト向け */
    GameMasterSite,
    /** モニターサイト向け */
    MonitorSite,
    /** プレイヤーサイト向け */
    PlayerSite,
    /** 特定のプレイヤー向け */
    Player,
    /** 宛先なし */
    None,
    /** 未設定（送信には使用しない） */
    Empty,
}
