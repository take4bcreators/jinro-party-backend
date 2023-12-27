package com.extensionlab.jinropartybackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
import com.extensionlab.jinropartybackend.repository.VoteReceiversRepository;

import jakarta.transaction.Transactional;

@Service
public class VoteReceiversService {

    @Autowired
    VoteReceiversRepository repository;

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
     * リスト登録
     */
    @Transactional
    public void registryFromList(List<VoteReceivers> voteReceiversList) {
        this.repository.saveAll(voteReceiversList);
        return;
    }

    public List<VoteReceivers> getAllVoteReceiversList() {
        var gameDataId = "gd00001";
        List<VoteReceivers> voteReceiversList = this.repository.findAllByGameDataId(gameDataId);
        return voteReceiversList;
    }

}
