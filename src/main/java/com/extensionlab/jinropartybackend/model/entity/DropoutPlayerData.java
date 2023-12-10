package com.extensionlab.jinropartybackend.model.entity;

import com.extensionlab.jinropartybackend.model.interfaces.GamePlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "dropout_player_data", schema = "prd")
@IdClass(value = VoteReceiversPK.class)
public class DropoutPlayerData implements GamePlayer {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_icon")
    private String playerIcon;
}
