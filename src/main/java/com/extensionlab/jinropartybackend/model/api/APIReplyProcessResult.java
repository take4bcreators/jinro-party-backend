package com.extensionlab.jinropartybackend.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 処理結果 レスポンスAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyProcessResult {
    /** 処理した結果（TRUE で成功） */
    private boolean result;
}
