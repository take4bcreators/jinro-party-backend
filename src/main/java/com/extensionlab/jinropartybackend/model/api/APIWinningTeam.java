package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.enums.PlayerTeam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIWinningTeam {
    /** 勝利チーム */
    private PlayerTeam winningTeam;
}
