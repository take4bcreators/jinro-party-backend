package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.interfaces.PlayerFullBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * プレイヤー完全データ APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIPlayerFullData implements PlayerFullBase {
    /** デバイスID */
    private String deviceId;
    /** セッションID */
    private String sessionId;
    /** プレイヤー名 */
    private String playerName;
    /** プレイヤーアイコン */
    private String playerIcon;
    /** 役職 */
    private PlayerRole playerRole;
    /** 所属チーム */
    private PlayerTeam playerTeam;
    /** 役職確認 */
    private boolean selfRoleCheck;
    /** プレイヤー状態 */
    private PlayerState playerState;
}
