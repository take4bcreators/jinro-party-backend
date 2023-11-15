package com.extensionlab.jinropartybackend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameMode;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.entity.GameData;
import com.extensionlab.jinropartybackend.repository.GameDataRepository;
import jakarta.transaction.Transactional;

@Service
public class GameDataService {

    @Autowired
    GameDataRepository repository;

    /**
     * ゲーム状態取得
     * 
     * @return ゲーム状態を表すGameState列挙型
     */
    public GameState getGameState() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        return record.get().getGameState();
    }

    /**
     * ゲーム状態設定
     * 
     * @param gameState
     *            ゲーム状態を表すGameState列挙型
     */
    @Transactional
    public void updateGameState(GameState gameState) {
        String gameDataId = "gd00001";
        boolean existsRecord = this.repository.existsById(gameDataId);
        GameData newGameData = null;
        if (existsRecord) {
            Optional<GameData> record = this.repository.findById(gameDataId);
            newGameData = record.get();
            newGameData.setGameState(gameState);
        } else {
            GameMode gameMode = GameMode.Normal;
            boolean initEnd = false;
            int initCurrentTurn = 0;
            int initTurnVoteCount = 0;
            PlayerTeam initWinTeam = PlayerTeam.Empty;
            newGameData = new GameData(gameDataId, gameState, gameMode, initEnd, initCurrentTurn, initTurnVoteCount,
                    initWinTeam);
        }
        this.repository.save(newGameData);
        return;
    }

    /**
     * ゲームモード設定
     * 
     * @param gameMode
     *            ゲームモードを表すGameState列挙型
     */
    @Transactional
    public void upsertGameMode(GameMode gameMode) {
        String gameDataId = "gd00001";
        boolean existsRecord = this.repository.existsById(gameDataId);
        GameData newGameData = null;
        if (existsRecord) {
            Optional<GameData> record = this.repository.findById(gameDataId);
            newGameData = record.get();
            newGameData.setGameMode(gameMode);
        } else {
            GameState gameState = GameState.PreGame;
            boolean initEnd = false;
            int initCurrentTurn = 0;
            int initTurnVoteCount = 0;
            PlayerTeam initWinTeam = PlayerTeam.Empty;
            newGameData = new GameData(gameDataId, gameState, gameMode, initEnd, initCurrentTurn, initTurnVoteCount,
                    initWinTeam);
        }
        this.repository.save(newGameData);
        return;
    }

    /**
     * ゲーム終了フラグ取得
     * 
     * @return ゲーム終了フラグを表すブール値（true: ゲーム終了済み）
     */
    public boolean isEnd() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        return record.get().isEnd();
    }

    /**
     * ゲーム終了フラグ設定
     * 
     * @param isEnd
     *            ゲーム終了フラグを表すブール値（true: ゲーム終了済み）
     */
    @Transactional
    public void updateEnd(boolean isEnd) {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        newGameData.setEnd(isEnd);
        this.repository.save(newGameData);
    }

    @Transactional
    public void updateCurrentTurn(int turnNumber) {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        newGameData.setCurrentTurn(turnNumber);
        this.repository.save(newGameData);
        return;
    }

    public int getCurrentTurn() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        return record.get().getCurrentTurn();
    }

    @Transactional
    public void incrementCurrentTurn() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        int currentTurn = newGameData.getCurrentTurn();
        newGameData.setCurrentTurn(currentTurn + 1);
        this.repository.save(newGameData);
        return;
    }

    @Transactional
    public void resetCurrentTurn() {
        this.updateCurrentTurn(0);
        return;
    }

    @Transactional
    public void updateTurnVoteCount(int voteCount) {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        newGameData.setTurnVoteCount(voteCount);
        this.repository.save(newGameData);
        return;
    }

    public int getTurnVoteCount() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        return record.get().getTurnVoteCount();
    }

    @Transactional
    public void incrementTurnVoteCount() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        int turnVoteCount = newGameData.getTurnVoteCount();
        newGameData.setTurnVoteCount(turnVoteCount + 1);
        this.repository.save(newGameData);
        return;
    }

    @Transactional
    public void resetTurnVoteCount() {
        this.updateTurnVoteCount(0);
        return;
    }

    public PlayerTeam getWinningTeam() {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        return record.get().getWinningTeam();
    }

    @Transactional
    public void updateWinningTeam(PlayerTeam winningTeam) {
        String gameDataId = "gd00001";
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        newGameData.setWinningTeam(winningTeam);
        this.repository.save(newGameData);
        return;
    }

    @Transactional
    public void resetAll() {
        String gameDataId = "gd00001";
        var resetGameData = new GameData(gameDataId, GameState.PreGame, GameMode.Normal, false, 0, 0, PlayerTeam.Empty);
        this.repository.save(resetGameData);
    }

}
