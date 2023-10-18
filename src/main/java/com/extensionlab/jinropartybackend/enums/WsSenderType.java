package com.extensionlab.jinropartybackend.enums;

/**
 * WebSocket送信元 (列挙型)
 */
public enum WsSenderType {
    /** バックエンドサーバー */
    Server,
    /** ゲームマスターサイト */
    GameMasterSite,
    /** モニターサイト */
    MonitorSite,
    /** プレイヤーサイト */
    PlayerSite,
}
