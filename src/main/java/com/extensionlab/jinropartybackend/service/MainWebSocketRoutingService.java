package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.model.api.APIWsData;

@Service
public class MainWebSocketRoutingService {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameProgressService gameProgressService;

    @Autowired
    GameStateService gameStateService;

    public MainWebSocketRoutingService() {
    }

    public void routeReceivedData(String receiveText) {
        // オブジェクトに変換
        APIWsData receiveData = this.mainWebSocketProcessService.convertJsonToWsData(receiveText);

        // 宛先判定
        boolean isReturn = false;
        switch (receiveData.getDestinationType()) {
            case All:
            case AllSite:
            case GameMasterSite:
            case MonitorSite:
            case PlayerSite:
            case Player:
                this.mainWebSocketProcessService.sendMessageAll(receiveText);
                isReturn = true;
                break;
            case None:
            case Empty:
                isReturn = true;
                break;
            case Server:
                break;
            default:
                break;
        }
        if (isReturn) {
            return;
        }

        // 命令判定
        switch (receiveData.getRequestAction()) {
            case GameStateUpdate:
                var gameState = this.mainWebSocketProcessService.extractGameState(receiveData);
                this.gameStateService.execChangeStateTask(gameState);
                break;
            case CountdownTimerStart:
                // @todo
                break;
            case CountdownTimerPause:
                this.gameProgressService.pauseTimer();
                this.mainWebSocketProcessService.countdownTimerPause();
                break;
            case CountdownTimerResume:
                this.gameProgressService.resumeTimer();
                this.mainWebSocketProcessService.countdownTimerResume();
                break;
            case ReturnCurrentGameState:
                this.mainWebSocketProcessService.returnCurrentGameState(receiveData);
                break;
            case RoleRegistration:
                // @todo
                break;
            case ExileVoteRegistration:
                // @todo
                break;
            case SurveyRegistration:
                // @todo
                break;
            case SeerActionExecute:
                // @todo
                break;
            case MediumActionExecute:
                // @todo
                break;
            case HunterActionExecute:
                // @todo
                break;
            case WerewolfAttackVote:
                // @todo
                break;
            case GameEnd:
                // @todo
                break;
            default:
                break;
        }
        return;
    }
}
