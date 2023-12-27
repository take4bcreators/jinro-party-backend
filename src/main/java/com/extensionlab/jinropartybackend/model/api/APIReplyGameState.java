package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.GameState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ゲーム状態レスポンスAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyGameState {
    /** ゲーム状態 */
    private GameState gameState;
}
