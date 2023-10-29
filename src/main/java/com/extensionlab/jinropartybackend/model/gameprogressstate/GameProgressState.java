package com.extensionlab.jinropartybackend.model.gameprogressstate;

import com.extensionlab.jinropartybackend.common.CommonConst;
import com.extensionlab.jinropartybackend.enums.GameState;

public abstract class GameProgressState {

    protected GameState gameState;
    protected int gameStateTime;

    public GameProgressState(GameState gameState) {
        this.gameState = gameState;
        this.gameStateTime = CommonConst.GameStateTime.get(gameState);
    }

    public int getGameStateTime() {
        return this.gameStateTime;
    }

    public abstract void runStartTask();

    public abstract void runEndTask();

}
