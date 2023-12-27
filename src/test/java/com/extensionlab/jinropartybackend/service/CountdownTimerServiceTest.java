package com.extensionlab.jinropartybackend.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.Optional;

import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

import com.extensionlab.jinropartybackend.enums.GameState;

@SpringBootTest
public class CountdownTimerServiceTest {

    @Autowired
    CountdownTimerService countdownTimerService;

    @Test
    void testGetGameState() {
        this.countdownTimerService.start(GameState.Empty, 3000, null);

        System.out.println(" ----- ↓ TEST ↓ -----");
        long currentTimeCount = this.countdownTimerService.getCurrentTimeCountMSec();
        var currentTimerState = this.countdownTimerService.getCurrentTimerState();
        System.out.println(currentTimeCount);
        System.out.println(currentTimerState);
        System.out.println(" ----- ↑ TEST ↑ -----");
    }

}
