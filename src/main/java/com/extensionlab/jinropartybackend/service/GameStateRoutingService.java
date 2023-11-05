package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.component.gamestate.GameStateComponent;
import com.extensionlab.jinropartybackend.component.gamestate.MorningPhaseStartComponent;
import com.extensionlab.jinropartybackend.component.gamestate.NightActionResultComponent;
import com.extensionlab.jinropartybackend.component.gamestate.NightPhaseComponent;
import com.extensionlab.jinropartybackend.component.gamestate.NightPhaseEndComponent;
import com.extensionlab.jinropartybackend.component.gamestate.NightPhaseStartComponent;
import com.extensionlab.jinropartybackend.component.gamestate.PlayerListDisplayComponent;
import com.extensionlab.jinropartybackend.component.gamestate.RoleAssignmentComponent;
import com.extensionlab.jinropartybackend.component.gamestate.RoleRevealComponent;
import com.extensionlab.jinropartybackend.component.gamestate.VoteResultComponent;
import com.extensionlab.jinropartybackend.component.gamestate.VotingComponent;
import com.extensionlab.jinropartybackend.component.gamestate.VotingEndComponent;
import com.extensionlab.jinropartybackend.component.gamestate.DayPhaseStartComponent;
import com.extensionlab.jinropartybackend.component.gamestate.ExileAnnouncementComponent;
import com.extensionlab.jinropartybackend.component.gamestate.FinalExileAnnouncementComponent;
import com.extensionlab.jinropartybackend.component.gamestate.FinalResultComponent;
import com.extensionlab.jinropartybackend.component.gamestate.GameEndComponent;
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

    @Autowired
    VotingComponent votingComponent;

    @Autowired
    VotingEndComponent votingEndComponent;

    @Autowired
    VoteResultComponent voteResultComponent;

    @Autowired
    ExileAnnouncementComponent exileAnnouncementComponent;

    @Autowired
    NightPhaseStartComponent nightPhaseStartComponent;

    @Autowired
    NightPhaseComponent nightPhaseComponent;

    @Autowired
    NightPhaseEndComponent nightPhaseEndComponent;

    @Autowired
    MorningPhaseStartComponent morningPhaseStartComponent;

    @Autowired
    NightActionResultComponent nightActionResultComponent;

    @Autowired
    FinalExileAnnouncementComponent finalExileAnnouncementComponent;

    @Autowired
    FinalResultComponent finalResultComponent;

    @Autowired
    GameEndComponent gameEndComponent;

    @Autowired
    RoleRevealComponent roleRevealComponent;

    public Optional<GameStateComponent> getThisGameStateComponent(GameState gameState) {
        // @note 新しく状態が追加されたらここにも追加する
        GameStateComponent[] gameStateComponents = {
                playerListDisplayComponent,
                roleAssignmentComponent,
                dayPhaseStartComponent,
                dayPhaseComponent,
                dayPhaseEndComponent,
                votingComponent,
                votingEndComponent,
                voteResultComponent,
                exileAnnouncementComponent,
                nightPhaseStartComponent,
                nightPhaseComponent,
                nightPhaseEndComponent,
                morningPhaseStartComponent,
                nightActionResultComponent,
                finalExileAnnouncementComponent,
                finalResultComponent,
                gameEndComponent,
                roleRevealComponent,
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
