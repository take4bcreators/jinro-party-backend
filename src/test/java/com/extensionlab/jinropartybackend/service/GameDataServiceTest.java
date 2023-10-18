package com.extensionlab.jinropartybackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.GameData;
import com.extensionlab.jinropartybackend.repository.GameDataRepository;

@SpringBootTest
public class GameDataServiceTest {

    @Autowired
    GameDataService gameDataService;

    @MockBean
    GameDataRepository repository;

    @Test
    void testGetGameState() {
        // Mockito.when(モック対象メソッド).thenReturn(設定する戻り値);
        Mockito.when(repository.findById("gd00001"))
                .thenReturn(Optional.of(new GameData("gd00001", GameState.PreGame, false)));

        assertEquals(gameDataService.getGameState(), GameState.PreGame);
    }

}
