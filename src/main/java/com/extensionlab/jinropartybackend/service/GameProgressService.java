package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
import com.extensionlab.jinropartybackend.model.entity.Votes;

@Service
public class GameProgressService {

    @Autowired
    CollectionUtilService collectionUtilService;

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    VotesService votesService;

    @Autowired
    VoteReceiversService voteReceiversService;

    @Autowired
    DropoutPlayerDataService dropoutPlayerDataService;

    @Autowired
    DataTransferService dataTransferService;

    @Autowired
    NightActionService nightActionService;

    public void assignPlayerRoleAndTeam() {
        List<PlayerInfo> playerList = this.playerInfoService.getAllPlayerData();
        int playerCount = playerList.size();

        // 人狼の数
        // @remind 設定値で変えられるようにする
        int werewolfCount = 1;
        if (playerCount >= 8) {
            werewolfCount = 2;
        }

        // 人狼リストの生成
        List<PlayerRole> werewolfList = this.generateWerewolfList(werewolfCount);

        // 人狼以外のプレイヤー数
        int notWerewolfCount = playerCount - werewolfCount;

        // 特殊役職リストの取得
        List<PlayerRole> specialRoleList = this.getSpecialRoleList(notWerewolfCount);

        // 市民リストの生成
        List<PlayerRole> citizenList = this.generateCitizenList(notWerewolfCount);

        // 特殊役職リストと市民リストの結合
        List<PlayerRole> roleList = this.collectionUtilService.getConcatList(specialRoleList, citizenList);

        // リストをシャッフル
        // このあと人狼を入れて改めてシャッフルすることになるが、
        // 一旦シャッフル→切り取りを行わないと、人狼が役職に入らない可能性がある
        List<PlayerRole> shuffledRoleList = this.collectionUtilService.getShuffleList(roleList);

        // リストを人数分切り取る
        List<PlayerRole> subRoleList = this.collectionUtilService.getSubList(shuffledRoleList, 0, notWerewolfCount);

        // 人狼リストとの結合
        List<PlayerRole> allRoleList = this.collectionUtilService.getConcatList(subRoleList, werewolfList);

        // 人狼が入ったリストで改めてシャッフル
        List<PlayerRole> allShuffledRoleList = this.collectionUtilService.getShuffleList(allRoleList);

        // 役職・チームをアサイン
        for (int i = 0; i < playerCount; i++) {
            PlayerRole assignRole = allShuffledRoleList.get(i);
            PlayerTeam assignTeam;
            switch (assignRole) {
                case Citizen:
                case Seer:
                case Medium:
                case Hunter:
                    assignTeam = PlayerTeam.Townsfolk;
                    break;
                case Madman:
                case Werewolf:
                    assignTeam = PlayerTeam.WerewolfPack;
                    break;
                default:
                    assignTeam = PlayerTeam.Empty;
                    break;
            }
            playerList.get(i).setPlayerRole(assignRole);
            playerList.get(i).setPlayerTeam(assignTeam);
        }

        this.playerInfoService.registryPlayerInfoList(playerList);
    }

    public List<PlayerRole> generateWerewolfList(int werewolfCount) {
        List<PlayerRole> werewolfList = new ArrayList<>();
        for (int i = 0; i < werewolfCount; i++) {
            werewolfList.add(PlayerRole.Werewolf);
        }
        return werewolfList;
    }

    public List<PlayerRole> getSpecialRoleList(int notWerewolfCount) {
        List<PlayerRole> specialRoleList;
        if (notWerewolfCount <= 2) {
            // プレイヤー数が3 (人狼以外の数が2) の場合は、残りは市民にする
            specialRoleList = new ArrayList<PlayerRole>(Arrays.asList(PlayerRole.Citizen, PlayerRole.Citizen));
        } else if (notWerewolfCount == 3) {
            // プレイヤー数が4 (人狼以外の数が3) の場合は、狂人を除外する
            specialRoleList = new ArrayList<PlayerRole>(
                    Arrays.asList(PlayerRole.Seer, PlayerRole.Medium, PlayerRole.Hunter));
        } else {
            specialRoleList = new ArrayList<PlayerRole>(
                    Arrays.asList(PlayerRole.Seer, PlayerRole.Medium, PlayerRole.Hunter, PlayerRole.Madman));
        }
        return specialRoleList;
    }

    public List<PlayerRole> generateCitizenList(int notWerewolfCount) {
        List<PlayerRole> citizenList = new ArrayList<>();
        for (int i = 0; i < notWerewolfCount; i++) {
            citizenList.add(PlayerRole.Citizen);
        }
        return citizenList;
    }

    /**
     * 投票用テーブル準備 (通常用)
     */
    public void prepareVoteTables() {
        // 投票テーブルの既存データ削除
        votesService.deleteAll();

        // 投票対象プレイヤーテーブルの既存データ削除
        voteReceiversService.deleteAll();

        // 生存プレイヤーの情報を取得
        List<PlayerInfo> alivePlayers = playerInfoService.getAllAlivePlayerData();

        // 生存プレイヤーの情報から投票対象テーブル用データの作成
        List<VoteReceivers> voteReceiversList = new ArrayList<>();
        for (PlayerInfo playerInfo : alivePlayers) {
            var voteReceivers = new VoteReceivers(
                    playerInfo.getGameDataId(),
                    playerInfo.getDeviceId(),
                    playerInfo.getPlayerName(),
                    playerInfo.getPlayerIcon());
            voteReceiversList.add(voteReceivers);
        }

        // 投票対象テーブルデータ挿入
        voteReceiversService.registryFromList(voteReceiversList);
    }

    /**
     * 投票用テーブル準備 (決選投票用)
     */
    public void prepareReVoteTables() {
        // 投票対象プレイヤーテーブルの既存データ削除
        voteReceiversService.deleteAll();

        // 対象プレイヤーの情報を取得
        List<VoteReceivers> maxCountPlayers = votesService.getMaxCountReceivers();

        // 投票対象テーブルデータ挿入
        voteReceiversService.registryFromList(maxCountPlayers);

        // 投票テーブルの既存データ削除
        votesService.deleteAll();
    }

    /**
     * 未投票プレイヤーへの対応処理
     */
    public void processUnvotedPlayers() {
        // 未投票プレイヤーリスト取得
        List<PlayerInfo> unvotedPlayerList = this.getUnvotedPlayerList();
        if (unvotedPlayerList.size() == 0) {
            return;
        }

        // 生存プレイヤーのリストを取得
        List<PlayerInfo> alivePlayers = playerInfoService.getAllAlivePlayerData();

        // 投票データ作成（投票先プレイヤーはランダム）
        List<Votes> newVoteList = new ArrayList<>();
        for (PlayerInfo unvotedPlayer : unvotedPlayerList) {
            List<PlayerInfo> nonSelfPlayersList = alivePlayers
                    .stream()
                    .filter(e -> !e.getPlayerName().equals(unvotedPlayer.getPlayerName()))
                    .collect(Collectors.toList());
            PlayerInfo randomPlayer = this.collectionUtilService.getRandomElement(nonSelfPlayersList);
            newVoteList.add(new Votes(
                    unvotedPlayer.getGameDataId(),
                    unvotedPlayer.getDeviceId(),
                    unvotedPlayer.getPlayerName(),
                    unvotedPlayer.getPlayerIcon(),
                    randomPlayer.getDeviceId(),
                    randomPlayer.getPlayerName(),
                    randomPlayer.getPlayerIcon()));
        }

        // DBへ保存
        this.votesService.registryVotesList(newVoteList);
    }

    private List<PlayerInfo> getUnvotedPlayerList() {
        // 生存プレイヤーの情報を取得
        List<PlayerInfo> alivePlayers = this.playerInfoService.getAllAlivePlayerData();
        List<String> allPlayerDeviceIdList = alivePlayers
                .stream()
                .map(e -> e.getDeviceId())
                .collect(Collectors.toList());

        // 投票済みプレイヤーリスト取得
        List<Votes> allVoteList = this.votesService.getAllVoteList();
        List<String> allVoterDeviceIdList = allVoteList
                .stream()
                .map(e -> e.getVoterDeviceId())
                .collect(Collectors.toList());

        // 差分リスト（プレイヤー名のみ）取得
        List<String> minusPlayerNameList = this.collectionUtilService.getMinusList(allPlayerDeviceIdList,
                allVoterDeviceIdList);

        // 差分がなければサイズが 0 のリストを返す
        if (minusPlayerNameList.size() == 0) {
            return new ArrayList<PlayerInfo>();
        }

        // 差分リスト（名前以外含む）取得
        List<PlayerInfo> minusPlayerList = alivePlayers
                .stream()
                .filter(e -> minusPlayerNameList.contains(e.getDeviceId()))
                .collect(Collectors.toList());

        return minusPlayerList;
    }

    /**
     * 決選投票判定処理
     * 
     * @return 判定結果
     */
    public boolean isNeedRunoffVote() {
        // 対象プレイヤーの情報を取得
        List<VoteReceivers> maxCountPlayers = votesService.getMaxCountReceivers();
        if (maxCountPlayers.size() >= 2) {
            return true;
        }
        return false;
    }

    /**
     * 脱落対象プレイヤー情報テーブル更新処理
     */
    public void updateDropOutTable() {
        VoteReceivers maxCountReceiver = this.votesService.getMaxCountReceiver();
        DropoutPlayerData dropoutPlayerData = this.dataTransferService.toDropoutPlayerData(maxCountReceiver);
        this.dropoutPlayerDataService.deleteAll();
        this.dropoutPlayerDataService.registryData(dropoutPlayerData);
    }

    /**
     * 脱落処理
     */
    public void updatePlayerStateForDropOutPlayer() {
        DropoutPlayerData dropoutPlayerData = this.dropoutPlayerDataService.getData();
        this.playerInfoService.updatePlayerState(dropoutPlayerData.getDeviceId(), PlayerState.Dead);
    }

    /**
     * ランダムに投票を決定する処理
     */
    public void randomlyDecideVote() {
        List<VoteReceivers> maxCountPlayers = votesService.getMaxCountReceivers();
        VoteReceivers decidedPlayer = this.collectionUtilService.getRandomElement(maxCountPlayers);
        DropoutPlayerData dropoutPlayerData = this.dataTransferService.toDropoutPlayerData(decidedPlayer);
        this.dropoutPlayerDataService.deleteAll();
        this.dropoutPlayerDataService.registryData(dropoutPlayerData);
    }

    public void prepareNightActionTables() {
        // @remind ここに夜のアクション初期化処理を入れる

        this.nightActionService.deleteAll();

    }

}
