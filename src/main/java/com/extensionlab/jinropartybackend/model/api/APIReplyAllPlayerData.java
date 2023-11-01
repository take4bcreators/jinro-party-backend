package com.extensionlab.jinropartybackend.model.api;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全プレイヤーデータ レスポンス APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyAllPlayerData {
    /** 全プレイヤーデータ */
    private ArrayList<APIReplyPlayerData> allPlayerData;
}
