package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.model.interfaces.NightActionBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 夜アクションデータ APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APINightActionData implements NightActionBase {
    /** デバイスID */
    private String deviceId;
    /** 役職 */
    private PlayerRole playerRole;
    /** 対象プレイヤーデバイスID */
    private String receiverDeviceId;
    /** 対象プレイヤー名 */
    private String receiverPlayerName;
    /** 対象プレイヤーアイコン */
    private String receiverPlayerIcon;
    /** 対象プレイヤー役職 */
    private PlayerRole receiverPlayerRole;
}
