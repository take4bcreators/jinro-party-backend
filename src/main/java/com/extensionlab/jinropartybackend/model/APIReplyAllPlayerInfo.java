package com.extensionlab.jinropartybackend.model;

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
public class APIReplyAllPlayerInfo {
    /** 全プレイヤーデータ */
    private ArrayList<APIAllPlayerInfo> apiAllPlayerInfo;
}
