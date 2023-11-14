package com.extensionlab.jinropartybackend.service;

import java.util.List;

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

    // /**
    // * リスト登録
    // */
    // @Transactional
    // public void registryFromList(List<DropoutPlayerData> dropoutPlayerDataList) {
    // this.repository.saveAll(dropoutPlayerDataList);
    // return;
    // }

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

    public DropoutPlayerData getData() {
        List<DropoutPlayerData> dropoutPlayerDataList = this.getAllDropoutPlayerDataList();
        DropoutPlayerData dropoutPlayerData = dropoutPlayerDataList.get(0);
        return dropoutPlayerData;
    }

}