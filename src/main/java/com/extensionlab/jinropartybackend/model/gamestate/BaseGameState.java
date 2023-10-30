package com.extensionlab.jinropartybackend.model.gamestate;

import com.extensionlab.jinropartybackend.enums.GameState;

public abstract class BaseGameState {

    protected GameStateSettings gameStateConf;

    public BaseGameState(GameStateSettings gameStateConf) {
        this.gameStateConf = gameStateConf;
    }

    public void runInitTask() {
    };

    public abstract void runStartTask();

    public abstract void runEndTask();

    public GameState getThisGameState() {
        return this.gameStateConf.getThisGameState();
    }

    protected void setCountdownTime(int time) {
        this.gameStateConf.setCountdownTime(time);
        return;
    }

    public int getCountdownTime() {
        return this.gameStateConf.getCountdownTime();
    }

    public GameState getPrevGameState() {
        return this.gameStateConf.getPrevGameState();
    }

    public GameState getNexGameState() {
        return this.gameStateConf.getNexGameState();
    }

}
