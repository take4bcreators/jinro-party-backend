package com.extensionlab.jinropartybackend.model.gamestate;

import com.extensionlab.jinropartybackend.enums.GameState;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStateSettings {
    /** ステートID ※処理では使用しない */
    private String StateId;
    /** ゲーム状態 */
    private GameState thisGameState;
    /** カウントダウン時間 */
    private int countdownTime;
    /** 前のゲーム状態 */
    private GameState prevGameState;
    /** 次のゲーム状態 */
    private GameState nexGameState;
}
