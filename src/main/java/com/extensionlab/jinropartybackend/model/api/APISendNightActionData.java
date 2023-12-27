package com.extensionlab.jinropartybackend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 夜アクション用データ リクエストAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APISendNightActionData {
    /** デバイスID */
    private String deviceId;
    /** 対象プレイヤーのデバイスID */
    private String receiverDeviceId;
}
