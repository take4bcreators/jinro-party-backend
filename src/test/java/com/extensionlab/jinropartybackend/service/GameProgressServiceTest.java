package com.extensionlab.jinropartybackend.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GameProgressServiceTest {

    @Autowired
    GameProgressService gameProgressService;

    @Test
    void testExecAfterNightActionTask() {
        this.gameProgressService.execAfterNightActionTask();
    }

}
