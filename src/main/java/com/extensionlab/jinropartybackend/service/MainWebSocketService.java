package com.extensionlab.jinropartybackend.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.enums.WebSocketGroup;
import com.extensionlab.jinropartybackend.enums.WsDestinationType;
import com.extensionlab.jinropartybackend.enums.WsRequestAction;
import com.extensionlab.jinropartybackend.enums.WsSenderType;
import com.extensionlab.jinropartybackend.model.APIWsData;
import com.google.gson.Gson;

@Service
public class MainWebSocketService {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    GeneralWebSocketService generalWebSocketService;

    public MainWebSocketService() {
    }

    public void routeReceivedData(String receiveText) {
        // オブジェクトに変換
        APIWsData receiveData = this.convertJsonToWsData(receiveText);

        // 宛先判定
        boolean isReturn = false;
        switch (receiveData.getDestinationType()) {
            case All:
            case AllSite:
            case GameMasterSite:
            case MonitorSite:
            case PlayerSite:
            case Player:
                this.sendMessageAll(receiveText);
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
                this.gameStateUpdate(receiveData);
                break;
            case CountdownTimerStart:
                // @todo
                break;
            case CountdownTimerPause:
                // @todo
                break;
            case CountdownTimerResume:
                // @todo
                break;
            case ReturnCurrentGameState:
                this.returnCurrentGameState(receiveData);
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

    private APIWsData convertJsonToWsData(String receiveText) {
        var gson = new Gson();
        APIWsData receiveData = gson.fromJson(receiveText, APIWsData.class);
        return receiveData;
    }

    private String convertWsDataToJson(APIWsData sendData) {
        var gson = new Gson();
        String sendJson = gson.toJson(sendData);
        return sendJson;
    }

    private GameState convertStringToGameState(String srcText) {
        GameState gameState = GameState.valueOf(srcText);
        return gameState;
    }

    private void sendMessageAll(String sendText) {
        try {
            this.generalWebSocketService.sendMessageAll(WebSocketGroup.Main, sendText);
        } catch (IOException e) {
            System.err.println("WebSocket送信時にエラーが発生しました");
            e.printStackTrace();
        }
    }

    // 個々の処理 -------------------------------------------------------------

    private void gameStateUpdate(APIWsData receiveData) {
        GameState nextGameState = this.convertStringToGameState(receiveData.getActionParameter01());
        this.gameDataService.updateGameState(nextGameState);
    }

    private void returnCurrentGameState(APIWsData receiveData) {
        GameState currentGameState = this.gameDataService.getGameState();
        WsDestinationType destType = null;
        switch (receiveData.getSenderType()) {
            case GameMasterSite:
                destType = WsDestinationType.GameMasterSite;
                break;
            case MonitorSite:
                destType = WsDestinationType.MonitorSite;
                break;
            case PlayerSite:
                destType = WsDestinationType.Player;
                break;
            default:
                destType = WsDestinationType.None;
                break;
        }
        APIWsData returnData = new APIWsData(
                destType,
                receiveData.getSenderDeviceId(),
                WsSenderType.Server,
                "",
                WsRequestAction.GameScreenChange,
                currentGameState.toString(),
                "",
                "");
        String returnJson = this.convertWsDataToJson(returnData);
        this.sendMessageAll(returnJson);
    }

}
