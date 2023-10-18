package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.WsDestinationType;
import com.extensionlab.jinropartybackend.enums.WsRequestAction;
import com.extensionlab.jinropartybackend.enums.WsSenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * WebSocket送受信用インタフェース
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIWsData {
    /** 送信先の種別 */
    private WsDestinationType destinationType;
    /** 送信先のデバイスID */
    private String destinationDeviceId;
    /** 送信元の種別 */
    private WsSenderType senderType;
    /** 送信元のデバイスID */
    private String senderDeviceId;
    /** リクエストするアクション名 */
    private WsRequestAction requestAction;
    /** アクションに渡すパラメータ1 */
    private String actionParameter01;
    /** アクションに渡すパラメータ2 */
    private String actionParameter02;
    /** アクションに渡すパラメータ3 */
    private String actionParameter03;
}
