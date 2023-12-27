package com.extensionlab.jinropartybackend.component.gamestate;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

public abstract class GameStateComponent {

    protected GameStateSettings gameStateSettings;

    public GameStateComponent(GameStateSettings gameStateSettings) {
        this.gameStateSettings = gameStateSettings;
    }

    public void runInitTask() {
    };

    public abstract void runStartTask();

    public abstract void runEndTask(GameStateService gameStateService);

    public GameState getThisGameState() {
        return this.gameStateSettings.getThisGameState();
    }

    protected void setCountdownTime(int time) {
        this.gameStateSettings.setCountdownTime(time);
        return;
    }

    public int getCountdownTime() {
        return this.gameStateSettings.getCountdownTime();
    }

    public GameState getPrevGameState() {
        return this.gameStateSettings.getPrevGameState();
    }

    public GameState getNexGameState() {
        return this.gameStateSettings.getNexGameState();
    }

}
