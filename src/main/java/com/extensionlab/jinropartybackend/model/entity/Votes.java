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
@Table(name = "votes", schema = "prd")
@IdClass(value = VotesPK.class)
public class Votes {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Id
    @Column(name = "voter_device_id")
    private String voterDeviceId;

    @Column(name = "voter_player_name")
    private String voterPlayerName;

    @Column(name = "voter_player_icon")
    private String voterPlayerIcon;

    @Column(name = "receiver_device_id")
    private String receiverDeviceId;

    @Column(name = "receiver_player_name")
    private String receiverPlayerName;

    @Column(name = "receiver_player_icon")
    private String receiverPlayerIcon;

}
