package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全プレイヤーデータ （APIレスポンス用）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIAllPlayerInfo {
    /** デバイスID */
    private String deviceId;
    /** プレイヤー名 */
    private String playerName;
    /** プレイヤーアイコン */
    private String playerIcon;
    /** 役職 */
    private PlayerRole playerRole;
    /** 所属チーム */
    private PlayerTeam playerTeam;
    /** プレイヤー状態 */
    private PlayerState playerState;
}
