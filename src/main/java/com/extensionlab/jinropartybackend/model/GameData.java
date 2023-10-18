package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.GameState;
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
@Table(name = "game_data", schema = "prd")
public class GameData {

    @Id
    @Column(name = "game_data_id")
    private String gameDataId;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")
    private GameState gameState;

    @Column(name = "is_end")
    private boolean isEnd;

}
