package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.GameMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新規ゲーム情報保存 リクエストAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APISendNewGame {
    /** ゲームモード */
    private GameMode gameMode;
}
