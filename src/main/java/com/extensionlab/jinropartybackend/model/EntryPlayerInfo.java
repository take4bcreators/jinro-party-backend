package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.EntryPlayerState;
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
@Table(name = "entry_player_info", schema = "prd")
@IdClass(value = EntryPlayerInfoPK.class)
public class EntryPlayerInfo {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_icon")
    private String playerIcon;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_player_state")
    private EntryPlayerState entryPlayerState;

}
