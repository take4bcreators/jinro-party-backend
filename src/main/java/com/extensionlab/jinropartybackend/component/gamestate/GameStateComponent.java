package com.extensionlab.jinropartybackend.component.gamestate;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

public abstract class GameStateComponent {

    protected GameStateSettings gameStateConf;

    public GameStateComponent(GameStateSettings gameStateConf) {
        this.gameStateConf = gameStateConf;
    }

    public void runInitTask() {
    };

    public abstract void runStartTask();

    public abstract void runEndTask(GameStateService gameStateService);

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
