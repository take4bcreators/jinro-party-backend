package com.extensionlab.jinropartybackend.model.common;

import com.extensionlab.jinropartybackend.model.interfaces.GamePlayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBasic implements GamePlayer {
    private String gameDataId;
    private String deviceId;
    private String playerName;
    private String playerIcon;
}
