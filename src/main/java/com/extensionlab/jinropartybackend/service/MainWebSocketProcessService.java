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

    public void sendJSONTextAll(String sendJSONText) {
        try {
            this.generalWebSocketService.sendMessageAll(WebSocketGroup.Main, sendJSONText);
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
        this.sendJSONTextAll(sendDataJSON);
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
        this.sendJSONTextAll(returnJson);
    }

    public GameState extractGameState(APIWsData apiWsData) {
        String param = apiWsData.getActionParameter01();
        GameState gameState = this.convertStringToGameState(param);
        return gameState;
    }

    public void sendAllSite(WsRequestAction action, String param01, String param02, String param03) {
        if (param01 == null) {
            param01 = "";
        }
        if (param02 == null) {
            param02 = "";
        }
        if (param03 == null) {
            param03 = "";
        }
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
        this.sendJSONTextAll(returnJson);
    }

    public void sendAllSite(WsRequestAction action, String param01, String param02) {
        this.sendAllSite(action, param01, param02, "");
    }

    public void sendAllSite(WsRequestAction action, String param01) {
        this.sendAllSite(action, param01, "", "");
    }

    public void sendAllSite(WsRequestAction action) {
        this.sendAllSite(action, "", "", "");
    }

    public void sendMTAndGMSite(WsRequestAction action, String param01, String param02, String param03) {
        if (param01 == null) {
            param01 = "";
        }
        if (param02 == null) {
            param02 = "";
        }
        if (param03 == null) {
            param03 = "";
        }
        APIWsData returnData = new APIWsData(
                WsDestinationType.MonitorSite,
                "",
                WsSenderType.Server,
                "",
                action,
                param01,
                param02,
                param03);
        String returnJsonToMT = this.convertWsDataToJson(returnData);
        this.sendJSONTextAll(returnJsonToMT);
        returnData.setDestinationType(WsDestinationType.GameMasterSite);
        String returnJsonToGM = this.convertWsDataToJson(returnData);
        this.sendJSONTextAll(returnJsonToGM);
    }

    public void sendMTAndGMSite(WsRequestAction action, String param01, String param02) {
        this.sendMTAndGMSite(action, param01, param02, "");
    }

    public void sendMTAndGMSite(WsRequestAction action, String param01) {
        this.sendMTAndGMSite(action, param01, "", "");
    }

    public void sendMTAndGMSite(WsRequestAction action) {
        this.sendMTAndGMSite(action, "", "", "");
    }

    public void sendGMSite(WsRequestAction action, String param01, String param02, String param03) {
        if (param01 == null) {
            param01 = "";
        }
        if (param02 == null) {
            param02 = "";
        }
        if (param03 == null) {
            param03 = "";
        }
        APIWsData returnData = new APIWsData(
                WsDestinationType.GameMasterSite,
                "",
                WsSenderType.Server,
                "",
                action,
                param01,
                param02,
                param03);
        String returnJsonToGM = this.convertWsDataToJson(returnData);
        this.sendJSONTextAll(returnJsonToGM);
    }

    public void sendGMSite(WsRequestAction action, String param01, String param02) {
        this.sendGMSite(action, param01, param02, "");
    }

    public void sendGMSite(WsRequestAction action, String param01) {
        this.sendGMSite(action, param01, "", "");
    }

    public void sendGMSite(WsRequestAction action) {
        this.sendGMSite(action, "", "", "");
    }

    public void sendMTSite(WsRequestAction action, String param01, String param02, String param03) {
        if (param01 == null) {
            param01 = "";
        }
        if (param02 == null) {
            param02 = "";
        }
        if (param03 == null) {
            param03 = "";
        }
        APIWsData returnData = new APIWsData(
                WsDestinationType.MonitorSite,
                "",
                WsSenderType.Server,
                "",
                action,
                param01,
                param02,
                param03);
        String returnJsonToMT = this.convertWsDataToJson(returnData);
        this.sendJSONTextAll(returnJsonToMT);
    }

    public void sendMTSite(WsRequestAction action, String param01, String param02) {
        this.sendMTSite(action, param01, param02, "");
    }

    public void sendMTSite(WsRequestAction action, String param01) {
        this.sendMTSite(action, param01, "", "");
    }

    public void sendMTSite(WsRequestAction action) {
        this.sendMTSite(action, "", "", "");
    }

    // public void countdownTimerStart(int time) {
    // String timeText = String.valueOf(time);
    // this.sendMTAndGMSite(WsRequestAction.CountdownTimerStart, timeText);
    // }

    // public void countdownTimerPause() {
    // this.sendMTAndGMSite(WsRequestAction.CountdownTimerPause);
    // }

    // public void countdownTimerResume() {
    // this.sendMTAndGMSite(WsRequestAction.CountdownTimerResume);
    // }

    public void timerStateChange() {
        GameState currentGameState = this.gameDataService.getGameState();
        this.sendMTAndGMSite(WsRequestAction.TimerStateChange, currentGameState.toString());
    }

    public void returnEntryPlayerCount(int playerCount) {
        String playerCountStr = String.valueOf(playerCount);
        this.sendMTSite(WsRequestAction.ReturnEntryPlayerCount, playerCountStr);
    }

    public void returnIfVote() {
        this.sendGMSite(WsRequestAction.VoteTableChange);
    }

}
