package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.model.entity.NightAction;
import com.extensionlab.jinropartybackend.repository.NightActionRepository;

import jakarta.transaction.Transactional;

@Service
public class NightActionService {

    @Autowired
    NightActionRepository repository;

    /**
     * 全登録データ削除
     */
    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }

    public boolean existsRecord(String deviceId) {
        var gameDataId = "gd00001";
        boolean result = this.repository.existsByGameDataIdAndDeviceId(gameDataId, deviceId);
        return result;
    }

    public NightAction findRecord(String deviceId) {
        var gameDataId = "gd00001";
        Optional<NightAction> result = this.repository.findByGameDataIdAndDeviceId(gameDataId, deviceId);
        return result.get();
    }

    @Transactional
    public void registryNightAction(NightAction nightAction) {
        this.repository.save(nightAction);
        return;
    }

    @Transactional
    public void registryNightAction(
            String deviceId, PlayerRole playerRole, String receiverDeviceId, String receiverPlayerName,
            String receiverPlayerIcon, PlayerRole receiverPlayerRole) {
        var gameDataId = "gd00001";
        var nightAction = new NightAction(
                gameDataId,
                deviceId,
                playerRole,
                receiverDeviceId,
                receiverPlayerName,
                receiverPlayerIcon,
                receiverPlayerRole);
        this.registryNightAction(nightAction);
    }

}
