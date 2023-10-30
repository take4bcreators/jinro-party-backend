package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.component.gamestate.GameStateBase;
import com.extensionlab.jinropartybackend.component.gamestate.StateDayPhase;
import com.extensionlab.jinropartybackend.component.gamestate.StateDayPhaseEnd;
import com.extensionlab.jinropartybackend.component.gamestate.StateDayPhaseStart;
import com.extensionlab.jinropartybackend.component.gamestate.StatePlayerListDisplay;
import com.extensionlab.jinropartybackend.component.gamestate.StateRoleAssignment;
import com.extensionlab.jinropartybackend.enums.GameState;

@Service
public class GameStateUtilService {

    @Autowired
    StatePlayerListDisplay statePlayerListDisplay;

    @Autowired
    StateRoleAssignment stateRoleAssignment;

    @Autowired
    StateDayPhaseStart stateDayPhaseStart;

    @Autowired
    StateDayPhase stateDayPhase;

    @Autowired
    StateDayPhaseEnd stateDayPhaseEnd;

    public Optional<GameStateBase> getThisGameStateObject(GameState gameState) {
        // @note 新しく状態が追加されたらここにも追加する
        GameStateBase[] gameStateObjects = {
                statePlayerListDisplay,
                stateRoleAssignment,
                stateDayPhaseStart,
                stateDayPhase,
                stateDayPhaseEnd,
        };
        for (GameStateBase gameStateObject : gameStateObjects) {
            if (gameState.equals(gameStateObject.getThisGameState())) {
                return Optional.of(gameStateObject);
            }
        }
        System.out.println("warning: gameState is other");
        return Optional.empty();
    }
}
