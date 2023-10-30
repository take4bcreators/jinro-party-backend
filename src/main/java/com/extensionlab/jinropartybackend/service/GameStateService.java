package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameState;

@Service
public class GameStateService {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameTimerService gameTimerService;

    /**
     * ゲーム状態変更タスク実行
     * 
     * @param gameState
     */
    public void execChangeStateTask(GameState gameState) {
        this.gameDataService.updateGameState(gameState);
        this.mainWebSocketProcessService.gameScreenChange(gameState);
        this.gameTimerService.startStateTask(gameState, this);
    }

}
