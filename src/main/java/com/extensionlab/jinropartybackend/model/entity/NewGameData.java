package com.extensionlab.jinropartybackend.model.entity;

import com.extensionlab.jinropartybackend.enums.GameMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "new_game_data", schema = "prd")
public class NewGameData {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_mode")
    private GameMode gameMode;

    @Column(name = "setting_01")
    private String setting01;

}
