package com.extensionlab.jinropartybackend.model.entity;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.model.interfaces.NightActionBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "night_action", schema = "prd")
@IdClass(value = NightActionPK.class)
public class NightAction implements NightActionBase {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_role")
    private PlayerRole playerRole;

    @Column(name = "receiver_device_id")
    private String receiverDeviceId;

    @Column(name = "receiver_player_name")
    private String receiverPlayerName;

    @Column(name = "receiver_player_icon")
    private String receiverPlayerIcon;

    @Enumerated(EnumType.STRING)
    @Column(name = "receiver_player_role")
    private PlayerRole receiverPlayerRole;
}
