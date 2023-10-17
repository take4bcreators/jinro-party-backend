package com.extensionlab.jinropartybackend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.extensionlab.jinropartybackend.model.GameData;
import com.extensionlab.jinropartybackend.model.GameState;
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
        Optional<GameData> record = this.repository.findById(gameDataId);
        GameData newGameData = record.get();
        newGameData.setGameState(gameState);
        this.repository.save(newGameData);
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
