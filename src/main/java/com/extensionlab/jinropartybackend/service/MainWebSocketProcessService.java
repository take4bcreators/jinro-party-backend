package com.extensionlab.jinropartybackend.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.enums.WebSocketGroup;
import com.extensionlab.jinropartybackend.enums.WsDestinationType;
import com.extensionlab.jinropartybackend.enums.WsRequestAction;
import com.extensionlab.jinropartybackend.enums.WsSenderType;
import com.extensionlab.jinropartybackend.model.api.APIWsData;
import com.google.gson.Gson;

@Service
public class MainWebSocketProcessService {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    GeneralWebSocketService generalWebSocketService;

    public MainWebSocketProcessService() {
    }

    // 汎用処理 -------------------------------------------------------------
    public APIWsData convertJsonToWsData(String receiveText) {
        var gson = new Gson();
        APIWsData receiveData = gson.fromJson(receiveText, APIWsData.class);
        return receiveData;
    }

    public String convertWsDataToJson(APIWsData sendData) {
        var gson = new Gson();
        String sendJson = gson.toJson(sendData);
        return sendJson;
    }

    public GameState convertStringToGameState(String srcText) {
        GameState gameState = GameState.valueOf(srcText);
        return gameState;
    }

    public void sendMessageAll(String sendText) {
        try {
            this.generalWebSocketService.sendMessageAll(WebSocketGroup.Main, sendText);
        } catch (IOException e) {
            System.err.println("WebSocket送信時にエラーが発生しました");
            e.printStackTrace();
        }
    }

    public void gameScreenChange(GameState gameState) {
        APIWsData sendData = new APIWsData(
                WsDestinationType.AllSite,
                "",
                WsSenderType.Server,
                "",
                WsRequestAction.GameScreenChange,
                gameState.toString(),
                "",
                "");
        String sendDataJSON = this.convertWsDataToJson(sendData);
        this.sendMessageAll(sendDataJSON);
    }

    public void returnCurrentGameState(APIWsData receiveData) {
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

    public GameState extractGameState(APIWsData apiWsData) {
        String param = apiWsData.getActionParameter01();
        GameState gameState = this.convertStringToGameState(param);
        return gameState;
    }

    private void sendAllSite(WsRequestAction action, String param01, String param02, String param03) {
        APIWsData returnData = new APIWsData(
                WsDestinationType.AllSite,
                "",
                WsSenderType.Server,
                "",
                action,
                param01,
                param02,
                param03);
        String returnJson = this.convertWsDataToJson(returnData);
        this.sendMessageAll(returnJson);
    }

    private void sendAllSite(WsRequestAction action) {
        this.sendAllSite(action, "", "", "");
    }

    public void countdownTimerPause() {
        this.sendAllSite(WsRequestAction.CountdownTimerPause);
    }

    public void countdownTimerResume() {
        this.sendAllSite(WsRequestAction.CountdownTimerResume);
    }

}
