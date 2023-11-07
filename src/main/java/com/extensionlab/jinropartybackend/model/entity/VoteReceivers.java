package com.extensionlab.jinropartybackend.model.entity;

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
@Table(name = "vote_receivers", schema = "prd")
@IdClass(value = VoteReceiversPK.class)
public class VoteReceivers {

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
