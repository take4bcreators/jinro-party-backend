package com.extensionlab.jinropartybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * デバイスID存在確認レスポンスAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyExistsDeviceId {
    /** デバイスIDの存在確認結果（TRUE で存在） */
    private boolean exists;
}
