package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
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
@Table(name = "player_info", schema = "prd")
@IdClass(value = PlayerInfoPK.class)
public class PlayerInfo {

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
    @Column(name = "player_role")
    private PlayerRole playerRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_team")
    private PlayerTeam playerTeam;

    @Column(name = "self_role_check")
    private boolean selfRoleCheck;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_state")
    private PlayerState playerState;

}
