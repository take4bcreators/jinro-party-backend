package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.component.gamestate.GameStateComponent;
import com.extensionlab.jinropartybackend.component.gamestate.PlayerListDisplayComponent;
import com.extensionlab.jinropartybackend.component.gamestate.RoleAssignmentComponent;
import com.extensionlab.jinropartybackend.component.gamestate.DayPhaseStartComponent;
import com.extensionlab.jinropartybackend.component.gamestate.DayPhaseComponent;
import com.extensionlab.jinropartybackend.component.gamestate.DayPhaseEndComponent;

@Service
public class GameStateRoutingService {

    @Autowired
    PlayerListDisplayComponent playerListDisplayComponent;

    @Autowired
    RoleAssignmentComponent roleAssignmentComponent;

    @Autowired
    DayPhaseStartComponent dayPhaseStartComponent;

    @Autowired
    DayPhaseComponent dayPhaseComponent;

    @Autowired
    DayPhaseEndComponent dayPhaseEndComponent;

    public Optional<GameStateComponent> getThisGameStateComponent(GameState gameState) {
        // @note 新しく状態が追加されたらここにも追加する
        GameStateComponent[] gameStateComponents = {
                playerListDisplayComponent,
                roleAssignmentComponent,
                dayPhaseStartComponent,
                dayPhaseComponent,
                dayPhaseEndComponent,
        };
        for (GameStateComponent gameStateComponent : gameStateComponents) {
            if (gameState.equals(gameStateComponent.getThisGameState())) {
                return Optional.of(gameStateComponent);
            }
        }
        System.out.println("warning: gameState is other");
        return Optional.empty();
    }
}
