package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.entity.WerewolfActionExecuterData;
import com.extensionlab.jinropartybackend.repository.WerewolfActionExecuterDataRepository;

import jakarta.transaction.Transactional;

@Service
public class WerewolfActionExecuterDataService {

    @Autowired
    WerewolfActionExecuterDataRepository repository;

    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }

    @Transactional
    public void registryPlayerData(WerewolfActionExecuterData playerData) {
        this.repository.save(playerData);
        return;
    }

    @Transactional
    public void registryPlayerData(String deviceId, String playerName, String playerIcon) {
        var gameDataId = "gd00001";
        this.repository.save(new WerewolfActionExecuterData(gameDataId, deviceId, playerName, playerIcon));
        return;
    }

    public boolean existsData(String deviceId) {
        var gameDataId = "gd00001";
        boolean result = this.repository.existsByGameDataIdAndDeviceId(gameDataId, deviceId);
        return result;
    }

}
