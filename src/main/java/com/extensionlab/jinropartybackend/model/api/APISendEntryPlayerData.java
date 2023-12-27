package com.extensionlab.jinropartybackend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * エントリープレイヤーデータ リクエストAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APISendEntryPlayerData {
    /** デバイスID */
    private String deviceId;

    /** プレイヤー名 */
    private String playerName;

    /** プレイヤーアイコン */
    private String playerIcon;
}
