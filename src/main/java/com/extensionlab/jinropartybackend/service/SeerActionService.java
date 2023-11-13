package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.model.entity.SeerAction;
import com.extensionlab.jinropartybackend.repository.SeerActionRepository;

import jakarta.transaction.Transactional;

@Service
public class SeerActionService {

    @Autowired
    SeerActionRepository repository;

    /**
     * 全登録データ削除
     */
    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }

    public boolean existsRecord(String seerDeviceId) {
        var gameDataId = "gd00001";
        boolean result = this.repository.existsByGameDataIdAndSeerDeviceId(gameDataId, seerDeviceId);
        return result;
    }

    public SeerAction findRecord(String seerDeviceId) {
        var gameDataId = "gd00001";
        Optional<SeerAction> result = this.repository.findByGameDataIdAndSeerDeviceId(gameDataId, seerDeviceId);
        return result.get();
    }

    @Transactional
    public void registrySeerAction(SeerAction seerAction) {
        this.repository.save(seerAction);
        return;
    }

    @Transactional
    public void registrySeerAction(
            String seerDeviceId, String receiverDeviceId, String receiverPlayerName,
            String receiverPlayerIcon, PlayerRole receiverPlayerRole) {
        var gameDataId = "gd00001";
        var seerAction = new SeerAction(
                gameDataId,
                seerDeviceId,
                receiverDeviceId,
                receiverPlayerName,
                receiverPlayerIcon,
                receiverPlayerRole);
        this.registrySeerAction(seerAction);
    }

}
