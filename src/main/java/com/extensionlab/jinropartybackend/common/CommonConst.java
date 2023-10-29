package com.extensionlab.jinropartybackend.common;

import java.util.Map;
import static java.util.Map.entry;
import com.extensionlab.jinropartybackend.enums.GameState;

public class CommonConst {

    public static final Map<GameState, Integer> GameStateTime = Map.ofEntries(
            entry(GameState.Empty, 0),
            entry(GameState.PreGame, 0),
            entry(GameState.PlayerJoining, 0),
            entry(GameState.PlayerJoiningEnded, 0),
            // entry(GameState.PlayerListDisplay, 3000),
            // entry(GameState.RoleAssignment, 0),
            entry(GameState.PlayerListDisplay, 5000),
            entry(GameState.RoleAssignment, 5000),
            entry(GameState.DayPhaseStart, 3000),
            entry(GameState.DayPhase, 120000),
            entry(GameState.DayPhaseEnd, 3000),
            entry(GameState.Voting, 0),
            entry(GameState.VotingEnd, 3000),
            entry(GameState.VoteResult, 5000),
            entry(GameState.ExileAnnouncement, 10000),
            entry(GameState.FinalExileAnnouncement, 3000),
            entry(GameState.NightPhaseStart, 3000),
            entry(GameState.NightPhase, 60000),
            entry(GameState.NightPhaseEnd, 3000),
            entry(GameState.MorningPhaseStart, 3000),
            entry(GameState.NightActionResult, 5000),
            entry(GameState.GameEnd, 3000),
            entry(GameState.FinalResult, 5000),
            entry(GameState.RoleReveal, 0));

}
