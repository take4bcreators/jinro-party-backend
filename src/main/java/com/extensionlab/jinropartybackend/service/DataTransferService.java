package com.extensionlab.jinropartybackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerBasicData;
import com.extensionlab.jinropartybackend.model.api.APIPlayerBasicData;
import com.extensionlab.jinropartybackend.model.common.PlayerBasic;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;

@Service
public class DataTransferService {

    public PlayerBasic toPlayerBasic(VoteReceivers src) {
        return new PlayerBasic(
                src.getGameDataId(),
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public DropoutPlayerData toDropoutPlayerData(PlayerBasic src) {
        return new DropoutPlayerData(
                src.getGameDataId(),
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public DropoutPlayerData toDropoutPlayerData(VoteReceivers src) {
        return new DropoutPlayerData(
                src.getGameDataId(),
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public APIPlayerBasicData toAPIPlayerBasicData(DropoutPlayerData src) {
        return new APIPlayerBasicData(
                src.getDeviceId(),
                src.getPlayerName(),
                src.getPlayerIcon());
    }

    public APIMultiPlayerBasicData toAPIMultiPlayerBasicData(List<PlayerInfo> src) {
        List<APIPlayerBasicData> playerList = new ArrayList<>();
        for (PlayerInfo playerInfo : src) {
            playerList.add(new APIPlayerBasicData(playerInfo.getDeviceId(), playerInfo.getPlayerName(),
                    playerInfo.getPlayerIcon()));
        }
        return new APIMultiPlayerBasicData(playerList);
    }
}
