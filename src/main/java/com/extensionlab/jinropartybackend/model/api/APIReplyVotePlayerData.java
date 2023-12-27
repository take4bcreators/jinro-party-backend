package com.extensionlab.jinropartybackend.model.api;

import com.extensionlab.jinropartybackend.model.interfaces.VotePlayersBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投票用プレイヤーデータ リクエストAPIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIReplyVotePlayerData implements VotePlayersBase {
    /** 投票プレイヤーデバイスID */
    private String voterDeviceId;

    /** 投票プレイヤー名 */
    private String voterPlayerName;

    /** 投票プレイヤーID */
    private String voterPlayerIcon;

    /** 被投票プレイヤーデバイスID */
    private String receiverDeviceId;

    /** 被投票プレイヤー名 */
    private String receiverPlayerName;

    /** 被投票プレイヤーアイコン */
    private String receiverPlayerIcon;
}
