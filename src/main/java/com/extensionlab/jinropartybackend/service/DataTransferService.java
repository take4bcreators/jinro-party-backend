package com.extensionlab.jinropartybackend.service;

import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.common.PlayerBasic;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;

@Service
public class DataTransferService {

    public PlayerBasic toPlayerBasic(VoteReceivers voteReceivers) {
        return new PlayerBasic(
                voteReceivers.getGameDataId(),
                voteReceivers.getDeviceId(),
                voteReceivers.getPlayerName(),
                voteReceivers.getPlayerIcon());
    }

    public DropoutPlayerData toDropoutPlayerData(PlayerBasic playerBasic) {
        return new DropoutPlayerData(
                playerBasic.getGameDataId(),
                playerBasic.getDeviceId(),
                playerBasic.getPlayerName(),
                playerBasic.getPlayerIcon());
    }

    public DropoutPlayerData toDropoutPlayerData(VoteReceivers voteReceivers) {
        return new DropoutPlayerData(
                voteReceivers.getGameDataId(),
                voteReceivers.getDeviceId(),
                voteReceivers.getPlayerName(),
                voteReceivers.getPlayerIcon());
    }
}
