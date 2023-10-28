package com.extensionlab.jinropartybackend.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfo;
import com.extensionlab.jinropartybackend.model.PlayerInfo;
import com.extensionlab.jinropartybackend.model.PlayerInfoPK;
import com.extensionlab.jinropartybackend.repository.PlayerInfoRepository;
import jakarta.transaction.Transactional;

@Service
public class PlayerInfoService {

    @Autowired
    PlayerInfoRepository repository;

    /**
     * 全プレイヤー情報削除
     */
    @Transactional
    public void clearAllPlayerInfo() {
        this.repository.deleteAll();
        return;
    }

    /**
     * プレイヤー情報登録
     * 
     * @param playerInfo
     *            登録するプレイヤー情報
     */
    @Transactional
    public void registryPlayerInfo(PlayerInfo playerInfo) {
        this.repository.save(playerInfo);
        return;
    }

    /**
     * プレイヤーリスト登録
     * 
     * @param playerInfoList
     */
    @Transactional
    public void registryPlayerInfoList(ArrayList<PlayerInfo> playerInfoList) {
        this.repository.saveAll(playerInfoList);
        return;
    }

    /**
     * プレイヤーリスト登録（エントリーデータ受取）
     * 
     * @param entryPlayerInfoList
     */
    @Transactional
    public void registryPlayerFromEntryList(ArrayList<EntryPlayerInfo> entryPlayerInfoList) {
        var playerInfoList = new ArrayList<PlayerInfo>();
        for (EntryPlayerInfo entryPlayerInfo : entryPlayerInfoList) {
            PlayerInfo playerInfo = new PlayerInfo(
                    entryPlayerInfo.getGameDataId(),
                    entryPlayerInfo.getDeviceId(),
                    entryPlayerInfo.getSessionId(),
                    entryPlayerInfo.getPlayerName(),
                    entryPlayerInfo.getPlayerIcon(),
                    PlayerRole.Empty,
                    PlayerTeam.Empty,
                    PlayerState.Alive);
            playerInfoList.add(playerInfo);
        }
        registryPlayerInfoList(playerInfoList);
        return;
    }

    /**
     * 全登録データ削除
     */
    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }

    /**
     * プレイヤー情報取得
     * 
     * @param deviceId
     *            取得対象のデバイスID
     * @return プレイヤー情報
     */
    public PlayerInfo getPlayerInfo(String deviceId) {
        String gameDataId = "gd00001";
        Optional<PlayerInfo> record = this.repository.findById(new PlayerInfoPK(gameDataId, deviceId));
        return record.get();
    }

    /**
     * デバイスIDの存在確認
     * 
     * @param deviceId
     *            確認対象のデバイスID
     * @return 存在結果の真偽値（true：存在する）
     */
    public boolean existsDeviceId(String deviceId) {
        String gameDataId = "gd00001";
        Optional<PlayerInfo> record = this.repository.findById(new PlayerInfoPK(gameDataId, deviceId));
        if (record.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * セッションID取得
     * 
     * @param deviceId
     *            取得対象のデバイスID
     * @return セッションID
     */
    public String getSessionId(String deviceId) {
        PlayerInfo playerInfo = this.getPlayerInfo(deviceId);
        return playerInfo.getSessionId();
    }

    /**
     * セッションID更新
     * 
     * @param deviceId
     *            更新対象のデバイスID
     * @param sessionId
     *            更新後のセッションID
     */
    @Transactional
    public void updateSessionId(String deviceId, String sessionId) {
        PlayerInfo playerInfo = this.getPlayerInfo(deviceId);
        playerInfo.setSessionId(sessionId);
        this.repository.save(playerInfo);
        return;
    }

    /**
     * プレイヤー状態取得
     * 
     * @param deviceId
     *            取得対象のデバイスID
     * @return プレイヤー状態
     */
    public PlayerState getPlayerState(String deviceId) {
        PlayerInfo playerInfo = this.getPlayerInfo(deviceId);
        return playerInfo.getPlayerState();
    }

    /**
     * プレイヤー状態更新
     * 
     * @param deviceId
     *            更新対象のデバイスID
     * @param playerState
     *            更新後のプレイヤー状態
     */
    @Transactional
    public void updatePlayerState(String deviceId, PlayerState playerState) {
        PlayerInfo playerInfo = this.getPlayerInfo(deviceId);
        playerInfo.setPlayerState(playerState);
        this.repository.save(playerInfo);
        return;
    }

    /**
     * プレイヤー生存確認
     * 
     * @param deviceId
     *            生存確認対象のデバイスID
     * @return 生存確認結果
     */
    public boolean checkPlayerAlive(String deviceId) {
        String gameDataId = "gd00001";
        Optional<PlayerInfo> record = this.repository.findById(new PlayerInfoPK(gameDataId, deviceId));
        if (record.isEmpty()) {
            return false;
        }
        if (record.get().getPlayerState().equals(PlayerState.Alive)) {
            return true;
        }
        return false;
    }
}
