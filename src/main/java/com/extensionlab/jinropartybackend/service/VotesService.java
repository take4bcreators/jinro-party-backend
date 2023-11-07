package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.repository.VotesRepository;

import jakarta.transaction.Transactional;

@Service
public class VotesService {

    @Autowired
    VotesRepository repository;

    /**
     * 全登録データ削除
     */
    @Transactional
    public void deleteAll() {
        var gameDataId = "gd00001";
        this.repository.deleteByGameDataId(gameDataId);
        return;
    }
}
