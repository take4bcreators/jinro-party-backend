package com.extensionlab.jinropartybackend.model.interfaces;

import com.extensionlab.jinropartybackend.enums.PlayerRole;

public interface NightActionBase {
    public String getDeviceId();

    public PlayerRole getPlayerRole();

    public String getReceiverDeviceId();

    public String getReceiverPlayerName();

    public String getReceiverPlayerIcon();

    public PlayerRole getReceiverPlayerRole();
}
