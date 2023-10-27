package com.extensionlab.jinropartybackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 重複確認結果 レスポンスAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyDuplicationResult {
    /** 重複確認した結果（TRUE で重複有り） */
    private boolean existsDuplicate;
}
