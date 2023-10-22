package com.extensionlab.jinropartybackend.model;

import com.extensionlab.jinropartybackend.enums.WsDestinationType;
import com.extensionlab.jinropartybackend.enums.WsRequestAction;
import com.extensionlab.jinropartybackend.enums.WsSenderType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName("destinationType")
    @Expose
    private WsDestinationType destinationType;

    /** 送信先のデバイスID */
    @SerializedName("destinationDeviceId")
    @Expose
    private String destinationDeviceId;

    /** 送信元の種別 */
    @SerializedName("senderType")
    @Expose
    private WsSenderType senderType;

    /** 送信元のデバイスID */
    @SerializedName("senderDeviceId")
    @Expose
    private String senderDeviceId;

    /** リクエストするアクション名 */
    @SerializedName("requestAction")
    @Expose
    private WsRequestAction requestAction;

    /** アクションに渡すパラメータ1 */
    @SerializedName("actionParameter01")
    @Expose
    private String actionParameter01;

    /** アクションに渡すパラメータ2 */
    @SerializedName("actionParameter02")
    @Expose
    private String actionParameter02;

    /** アクションに渡すパラメータ3 */
    @SerializedName("actionParameter03")
    @Expose
    private String actionParameter03;
}
