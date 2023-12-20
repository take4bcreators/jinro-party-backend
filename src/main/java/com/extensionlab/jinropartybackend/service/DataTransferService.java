package com.extensionlab.jinropartybackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerBasicData;
import com.extensionlab.jinropartybackend.model.api.APIPlayerBasicData;
import com.extensionlab.jinropartybackend.model.api.APIReplyAllVotePlayerData;
import com.extensionlab.jinropartybackend.model.api.APIReplyVotePlayerData;
import com.extensionlab.jinropartybackend.model.common.PlayerBasic;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.model.interfaces.GamePlayer;
import com.extensionlab.jinropartybackend.model.interfaces.PlayerBase;
import com.extensionlab.jinropartybackend.model.interfaces.VotePlayersBase;

@Service
public class DataTransferService {

    public <T extends GamePlayer> PlayerBasic toPlayerBasic(T src) {
        return new PlayerBasic(
                src.getGameDataId(),
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public <T extends GamePlayer> DropoutPlayerData toDropoutPlayerData(T src) {
        return new DropoutPlayerData(
                src.getGameDataId(),
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public <T extends PlayerBase> APIPlayerBasicData toAPIPlayerBasicData(T src) {
        return new APIPlayerBasicData(
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public <T extends PlayerBase> APIMultiPlayerBasicData toAPIMultiPlayerBasicData(List<T> src) {
        List<APIPlayerBasicData> playerList = new ArrayList<>();
        for (T playerInfo : src) {
            playerList.add(new APIPlayerBasicData(playerInfo.getDeviceId(), playerInfo.getPlayerName(),
                    playerInfo.getPlayerIcon()));
        }
        return new APIMultiPlayerBasicData(playerList);
    }

    public <T extends VotePlayersBase> List<APIReplyVotePlayerData> toAPIReplyVotePlayerDataList(List<T> srcList) {
        List<APIReplyVotePlayerData> newList = new ArrayList<>();
        for (T src : srcList) {
            newList.add(new APIReplyVotePlayerData(
                    src.getVoterDeviceId(),
                    src.getVoterPlayerName(),
                    src.getVoterPlayerIcon(),
                    src.getReceiverDeviceId(),
                    src.getReceiverPlayerName(),
                    src.getReceiverPlayerIcon()));
        }
        return newList;
    }

    public <T extends VotePlayersBase> APIReplyAllVotePlayerData toAPIReplyAllVotePlayerData(List<T> srcList) {
        var newList = this.toAPIReplyVotePlayerDataList(srcList);
        var replyData = new APIReplyAllVotePlayerData(newList);
        return replyData;
    }

}
