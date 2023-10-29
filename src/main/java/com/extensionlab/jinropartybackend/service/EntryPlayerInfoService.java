package com.extensionlab.jinropartybackend.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.EntryPlayerState;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfo;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfoPK;
import com.extensionlab.jinropartybackend.repository.EntryPlayerInfoRepository;

import jakarta.transaction.Transactional;

@Service
public class EntryPlayerInfoService {

    @Autowired
    EntryPlayerInfoRepository repository;

    /**
     * デバイスID登録存在確認
     * 
     * @param deviceId
     *            確認対象のデバイスID
     * @return 存在結果の真偽値（true：存在する）
     */
    public boolean existsEntryDataByDeviceId(String deviceId) {
        var gameDataId = "gd00001";
        boolean exists = this.repository.existsByGameDataIdAndDeviceIdAndEntryPlayerState(gameDataId, deviceId,
                EntryPlayerState.Entry);
        return exists;
    }

    /**
     * プレイヤー名存在確認
     * 
     * @param playerName
     *            プレイヤー名
     * @return 存在結果の真偽値（true：存在する）
     */
    public boolean existsByPlayerName(String playerName) {
        var gameDataId = "gd00001";
        boolean exists = this.repository.existsByGameDataIdAndPlayerName(gameDataId, playerName);
        return exists;
    }

    /**
     * エントリープレイヤー仮登録
     * 
     * @param deviceId
     * @param playerName
     * @param playerIcon
     */
    @Transactional
    public void upsertNotEntryData(String deviceId, String playerName, String playerIcon) {
        var entryPlayerState = EntryPlayerState.NotEntry;
        this.upsert(deviceId, playerName, playerIcon, entryPlayerState);
        return;
    }

    /**
     * エントリープレイヤー本登録
     * 
     * @param deviceId
     * @param playerName
     * @param playerIcon
     */
    @Transactional
    public void upsertEntryData(String deviceId, String playerName, String playerIcon) {
        var entryPlayerState = EntryPlayerState.Entry;
        this.upsert(deviceId, playerName, playerIcon, entryPlayerState);
        return;
    }

    @Transactional
    private void upsert(String deviceId, String playerName, String playerIcon, EntryPlayerState entryPlayerState) {
        var gameDataId = "gd00001";
        var playerInfo = new EntryPlayerInfo(
                gameDataId,
                deviceId,
                "",
                playerName,
                playerIcon,
                entryPlayerState);
        this.repository.save(playerInfo);
        return;
    }

    /**
     * 登録データ削除
     * 
     * @param deviceId
     */
    @Transactional
    public void deleteByDeviceId(String deviceId) {
        var gameDataId = "gd00001";
        this.repository.deleteById(new EntryPlayerInfoPK(gameDataId, deviceId));
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
     * 全エントリー済みデータ配列取得
     * 
     * @return 全エントリー済みデータ配列
     */
    public EntryPlayerInfo[] getAllEntryData() {
        var gameDataId = "gd00001";
        var entryPlayerState = EntryPlayerState.Entry;
        Optional<EntryPlayerInfo[]> record = this.repository.findAllByGameDataIdAndEntryPlayerState(gameDataId,
                entryPlayerState);
        if (record.isEmpty()) {
            EntryPlayerInfo[] rtn = {};
            return rtn;
        }
        return record.get();
    }

    /**
     * 全エントリー済みデータリスト取得
     * 
     * @return 全エントリー済みデータリスト
     */
    public ArrayList<EntryPlayerInfo> getAllEntryDataToList() {
        EntryPlayerInfo[] entryPlayerInfos = getAllEntryData();
        var entryPlayerInfoList = new ArrayList<EntryPlayerInfo>(Arrays.asList(entryPlayerInfos));
        return entryPlayerInfoList;
    }

}
