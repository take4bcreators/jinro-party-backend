package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Votes> voteList = this.repository.findAllByGameDataId(gameDataId);
        Map<String, List<Votes>> groupingVote = voteList
                .stream()
                .collect(Collectors.groupingBy(Votes::getReceiverDeviceId));
        Map<String, Integer> voteCountMap = groupingVote
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
        Integer maxVoteCount = voteCountMap
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getValue();
        List<String> maxVotePlayerIds = voteCountMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == maxVoteCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<VoteReceivers> maxVotePlayers = voteList
                .stream()
                .filter(e -> maxVotePlayerIds.contains(e.getReceiverDeviceId()))
                .map(e -> {
                    return new VoteReceivers(
                            e.getGameDataId(),
                            e.getReceiverDeviceId(),
                            e.getReceiverPlayerName(),
                            e.getReceiverPlayerIcon());
                })
                .distinct()
                .collect(Collectors.toList());
        return maxVotePlayers;
    }

    public VoteReceivers getMaxCountReceiver() {
        List<VoteReceivers> maxCountPlayers = this.getMaxCountReceivers();
        VoteReceivers maxCountReceiver = maxCountPlayers.get(0);
        return maxCountReceiver;
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
