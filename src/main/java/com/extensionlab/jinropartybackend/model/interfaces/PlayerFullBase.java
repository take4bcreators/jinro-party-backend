package com.extensionlab.jinropartybackend.model.interfaces;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;

public interface PlayerFullBase extends PlayerBase {
    public String getSessionId();

    public PlayerRole getPlayerRole();

    public PlayerTeam getPlayerTeam();

    public boolean isSelfRoleCheck();

    public PlayerState getPlayerState();
}
