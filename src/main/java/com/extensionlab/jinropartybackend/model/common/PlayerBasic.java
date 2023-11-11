package com.extensionlab.jinropartybackend.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBasic {
    private String gameDataId;
    private String deviceId;
    private String playerName;
    private String playerIcon;
}
