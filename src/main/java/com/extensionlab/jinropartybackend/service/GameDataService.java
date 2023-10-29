package com.extensionlab.jinropartybackend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameMode;
import com.extensionlab.jinropartybackend.enums.GameState;
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
            newGameData = new GameData(gameDataId, gameState, gameMode, false);
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
            newGameData = new GameData(gameDataId, gameState, gameMode, false);
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

}
