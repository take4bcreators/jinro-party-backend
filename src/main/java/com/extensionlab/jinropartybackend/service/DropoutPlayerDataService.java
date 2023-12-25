package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.repository.DropoutPlayerDataRepository;

import jakarta.transaction.Transactional;

@Service
public class DropoutPlayerDataService {

    @Autowired
    DropoutPlayerDataRepository repository;

    /**
     * 全登録データ削除
     */
    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }

    @Transactional
    public void registryData(DropoutPlayerData dropoutPlayerData) {
        this.repository.save(dropoutPlayerData);
        return;
    }

    private List<DropoutPlayerData> getAllDropoutPlayerDataList() {
        var gameDataId = "gd00001";
        List<DropoutPlayerData> dropoutPlayerDataList = this.repository.findAllByGameDataId(gameDataId);
        return dropoutPlayerDataList;
    }

    public Optional<DropoutPlayerData> getData() {
        List<DropoutPlayerData> dropoutPlayerDataList = this.getAllDropoutPlayerDataList();
        if (dropoutPlayerDataList.size() == 0) {
            Optional<DropoutPlayerData> empty = Optional.empty();
            return empty;
        }
        Optional<DropoutPlayerData> dropoutPlayerData = Optional.of(dropoutPlayerDataList.get(0));
        return dropoutPlayerData;
    }

}
