package com.extensionlab.jinropartybackend.model.gamestate;

import com.extensionlab.jinropartybackend.common.CommonConst;
import com.extensionlab.jinropartybackend.enums.GameState;

public abstract class BaseGameState {

    protected GameState gameState;
    protected int gameStateTime;

    public BaseGameState(GameState gameState) {
        this.gameState = gameState;
        this.gameStateTime = CommonConst.GameStateTime.get(gameState);
    }

    public int getGameStateTime() {
        return this.gameStateTime;
    }

    public abstract void runStartTask();

    public abstract void runEndTask();

}
