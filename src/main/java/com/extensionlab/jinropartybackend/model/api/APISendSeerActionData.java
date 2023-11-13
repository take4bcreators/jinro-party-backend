package com.extensionlab.jinropartybackend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 占い師アクション用データ リクエストAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APISendSeerActionData {
    /** 占い師自身のデバイスID */
    private String seerDeviceId;
    /** 占い対象プレイヤーのデバイスID */
    private String receiverDeviceId;
}
