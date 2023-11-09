package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.api.APISendVotePlayerData;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
import com.extensionlab.jinropartybackend.model.entity.Votes;
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

    public List<VoteReceivers> getMaxCountReceivers() {
        var gameDataId = "gd00001";
        List<VoteReceivers> maxCountPlayers = this.repository.findMaxCountReceivers(gameDataId);
        return maxCountPlayers;
    }

    public VoteReceivers getMaxCountReceiver() {
        List<VoteReceivers> maxCountPlayers = this.getMaxCountReceivers();
        Optional<VoteReceivers> maxCountReceiver = maxCountPlayers.stream().findFirst();
        return maxCountReceiver.get();
    }

    @Transactional
    public void registryVotesFromAPI(APISendVotePlayerData apiData) {
        var gameDataId = "gd00001";
        var voteData = new Votes(
                gameDataId,
                apiData.getVoterDeviceId(),
                apiData.getVoterPlayerName(),
                apiData.getVoterPlayerIcon(),
                apiData.getReceiverDeviceId(),
                apiData.getReceiverPlayerName(),
                apiData.getReceiverPlayerIcon());
        this.repository.save(voteData);
        return;
    }

    @Transactional
    public void registryVotesList(List<Votes> votesList) {
        this.repository.saveAll(votesList);
        return;
    }

    public List<Votes> getAllVoteList() {
        var gameDataId = "gd00001";
        List<Votes> voteList = this.repository.findAllByGameDataId(gameDataId);
        return voteList;
    }

}
