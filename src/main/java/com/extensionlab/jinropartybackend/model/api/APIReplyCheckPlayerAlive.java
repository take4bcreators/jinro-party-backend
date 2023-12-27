package com.extensionlab.jinropartybackend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * プレイヤー生存確認レスポンスAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyCheckPlayerAlive {
    /** プレイヤー生存確認結果（TRUE で生存） */
    private boolean aliveStatus;
}
