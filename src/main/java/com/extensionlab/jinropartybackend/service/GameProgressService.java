package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gameprogressstate.GameProgressState;
import com.extensionlab.jinropartybackend.model.gameprogressstate.PlayerListDisplay;
import com.extensionlab.jinropartybackend.model.gameprogressstate.RoleAssignment;

@Service
public class GameProgressService {

    @Autowired
    CountdownTimerService timerService;

    @Autowired
    PlayerListDisplay playerListDisplay;

    @Autowired
    RoleAssignment roleAssignment;

    public void startStateTask(GameState gameState) {
        System.out.println("startStateTask");
        GameProgressState gameProgressState = null;
        switch (gameState) {
            case PlayerListDisplay:
                gameProgressState = playerListDisplay;
                break;
            case RoleAssignment:
                gameProgressState = roleAssignment;
                break;
            default:
                System.out.println("warning: gameState is other");
                break;
        }
        if (gameProgressState == null) {
            return;
        }
        this.startStateTask(gameProgressState);
    }

    private void startStateTask(GameProgressState gameProgressState) {
        int time = gameProgressState.getGameStateTime();
        if (time > 0) {
            timerService.start(time, () -> {
                gameProgressState.runEndTask();
            });
        }
        System.out.println("runStartTask");
        gameProgressState.runStartTask();
    }

    public void pauseTimer() {
        this.timerService.pause();
    }

    public void resumeTimer() {
        this.timerService.resume();
    }

}
