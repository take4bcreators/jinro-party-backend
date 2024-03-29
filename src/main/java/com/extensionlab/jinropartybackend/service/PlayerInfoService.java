package com.extensionlab.jinropartybackend.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.entity.EntryPlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
// import com.extensionlab.jinropartybackend.model.entity.PlayerInfoPK;
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
    public void registryPlayerInfoList(List<PlayerInfo> playerInfoList) {
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
            // @note プレイヤーデータの初期値
            PlayerInfo playerInfo = new PlayerInfo(
                    entryPlayerInfo.getGameDataId(),
                    entryPlayerInfo.getDeviceId(),
                    entryPlayerInfo.getSessionId(),
                    entryPlayerInfo.getPlayerName(),
                    entryPlayerInfo.getPlayerIcon(),
                    PlayerRole.Empty,
                    PlayerTeam.Empty,
                    false,
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
        // Optional<PlayerInfo> record = this.repository.findById(new
        // PlayerInfoPK(gameDataId, deviceId));
        Optional<PlayerInfo> record = this.repository.findByGameDataIdAndDeviceId(gameDataId, deviceId);
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
        // Optional<PlayerInfo> record = this.repository.findById(new
        // PlayerInfoPK(gameDataId, deviceId));
        Optional<PlayerInfo> record = this.repository.findByGameDataIdAndDeviceId(gameDataId, deviceId);
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
        // Optional<PlayerInfo> record = this.repository.findById(new
        // PlayerInfoPK(gameDataId, deviceId));
        Optional<PlayerInfo> record = this.repository.findByGameDataIdAndDeviceId(gameDataId, deviceId);
        if (record.isEmpty()) {
            return false;
        }
        if (record.get().getPlayerState().equals(PlayerState.Alive)) {
            return true;
        }
        return false;
    }

    /**
     * 全プレイヤーデータリスト取得
     * 
     * @return 全プレイヤーデータリスト
     */
    public List<PlayerInfo> getAllPlayerData() {
        var gameDataId = "gd00001";
        List<PlayerInfo> allPlayerData = this.repository.findByGameDataId(gameDataId);
        return allPlayerData;
    }

    /**
     * * 全プレイヤーデータAPI用リスト取得
     * 
     * @return 全プレイヤーデータAPI用リスト
     */
    public ArrayList<APIReplyPlayerData> getAllPlayerDataToListForAPI() {
        List<PlayerInfo> allPlayerData = this.getAllPlayerData();
        var allPlayerDataForAPI = new ArrayList<APIReplyPlayerData>();
        for (PlayerInfo playerData : allPlayerData) {
            var playerDataForAPI = new APIReplyPlayerData(
                    playerData.getDeviceId(),
                    playerData.getPlayerName(),
                    playerData.getPlayerIcon(),
                    playerData.getPlayerRole(),
                    playerData.getPlayerTeam(),
                    playerData.getPlayerState());
            allPlayerDataForAPI.add(playerDataForAPI);
        }
        return allPlayerDataForAPI;
    }

    /**
     * 役職確認済み更新
     * 
     * @param deviceId
     *            更新対象のデバイスID
     */
    @Transactional
    public void updateSelfRoleCheck(String deviceId) {
        PlayerInfo playerInfo = this.getPlayerInfo(deviceId);
        playerInfo.setSelfRoleCheck(true);
        this.repository.save(playerInfo);
        return;
    }

    /**
     * 全プレイヤー確認済み確認
     * 
     * @return
     */
    public boolean allPlayerChecked() {
        String gameDataId = "gd00001";
        long allCount = this.repository.countByGameDataId(gameDataId);
        long checkedCount = this.repository.countByGameDataIdAndSelfRoleCheck(gameDataId, true);
        if (allCount == checkedCount) {
            return true;
        }
        return false;
    }

    /**
     * プレイヤー生存人数取得
     * 
     * @return
     */
    public int getAlivePlayerCount() {
        String gameDataId = "gd00001";
        long allCount = this.repository.countByGameDataIdAndPlayerState(gameDataId, PlayerState.Alive);
        int allCountInt = Math.toIntExact(allCount);
        return allCountInt;
    }

    /**
     * プレイヤーデータ取得
     * 
     * @param deviceId
     *            デバイスID
     * @return プレイヤーデータ
     */
    public APIReplyPlayerData getPlayerDataForAPI(String deviceId) {
        var gameDataId = "gd00001";
        // Optional<PlayerInfo> record = this.repository.findById(new
        // PlayerInfoPK(gameDataId, deviceId));
        Optional<PlayerInfo> record = this.repository.findByGameDataIdAndDeviceId(gameDataId, deviceId);
        if (record.isEmpty()) {
            return new APIReplyPlayerData(
                    "",
                    "",
                    "",
                    PlayerRole.Empty,
                    PlayerTeam.Empty,
                    PlayerState.Empty);
        }
        PlayerInfo playerInfo = record.get();
        var apiReplyPlayerData = new APIReplyPlayerData(
                playerInfo.getDeviceId(),
                playerInfo.getPlayerName(),
                playerInfo.getPlayerIcon(),
                playerInfo.getPlayerRole(),
                playerInfo.getPlayerTeam(),
                playerInfo.getPlayerState());
        return apiReplyPlayerData;
    }

    /**
     * 全生存プレイヤーデータリスト取得
     * 
     * @return 全生存プレイヤーデータリスト
     */
    public List<PlayerInfo> getAllAlivePlayerData() {
        var gameDataId = "gd00001";
        var playerState = PlayerState.Alive;
        List<PlayerInfo> allAlivePlayerData = this.repository.findByGameDataIdAndPlayerState(gameDataId, playerState);
        return allAlivePlayerData;
    }

    public List<PlayerInfo> getAlivePlayerListExcludeBy(String deviceId) {
        List<PlayerInfo> alivePlayerList = this.getAllAlivePlayerData();
        List<PlayerInfo> excludeList = alivePlayerList
                .stream()
                .filter(e -> !e.getDeviceId().equals(deviceId))
                .collect(Collectors.toList());
        return excludeList;
    }

    public List<PlayerInfo> getAlivePlayerListExcludeWerewolf() {
        List<PlayerInfo> alivePlayerList = this.getAllAlivePlayerData();
        List<PlayerInfo> excludeList = alivePlayerList
                .stream()
                .filter(e -> !e.getPlayerRole().equals(PlayerRole.Werewolf))
                .collect(Collectors.toList());
        return excludeList;
    }

    public List<PlayerInfo> getAlivePlayerListOnlyWerewolf() {
        List<PlayerInfo> alivePlayerList = this.getAllAlivePlayerData();
        List<PlayerInfo> excludeList = alivePlayerList
                .stream()
                .filter(e -> e.getPlayerRole().equals(PlayerRole.Werewolf))
                .collect(Collectors.toList());
        return excludeList;
    }

}
