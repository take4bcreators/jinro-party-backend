package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;

@Service
public class GameProgressUtilService {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    VotesService votesService;

    @Autowired
    VoteReceiversService voteReceiversService;

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
        List<PlayerRole> roleList = this.getConcatList(specialRoleList, citizenList);

        // リストをシャッフル
        // このあと人狼を入れて改めてシャッフルすることになるが、
        // 一旦シャッフル→切り取りを行わないと、人狼が役職に入らない可能性がある
        List<PlayerRole> shuffledRoleList = this.getShuffleList(roleList);

        // リストを人数分切り取る
        List<PlayerRole> subRoleList = this.getSubList(shuffledRoleList, 0, notWerewolfCount);

        // 人狼リストとの結合
        List<PlayerRole> allRoleList = this.getConcatList(subRoleList, werewolfList);

        // 人狼が入ったリストで改めてシャッフル
        List<PlayerRole> allShuffledRoleList = this.getShuffleList(allRoleList);

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

    public <T> List<T> getConcatList(List<T> listA, List<T> listB) {
        List<T> immutableConcatList = Stream.concat(listA.stream(), listB.stream()).toList();
        List<T> mutableConcatList = new ArrayList<>(immutableConcatList);
        return mutableConcatList;
    }

    public <T> List<T> getShuffleList(List<T> list) {
        List<T> copyList = new ArrayList<>(list);
        Collections.shuffle(copyList);
        return copyList;
    }

    public <T> List<T> getSubList(List<T> list, int fromIndex, int toIndex) {
        List<T> subList = new ArrayList<>(list.subList(fromIndex, toIndex));
        return subList;
    }

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

    // @remind 未着手
    public void prepareReVoteTables() {
        // 投票対象プレイヤーテーブルの既存データ削除
        voteReceiversService.deleteAll();

        // 対象プレイヤーの情報を取得
        // List<PlayerInfo> alivePlayers = playerInfoService.getAllAlivePlayerData();

        // 対象プレイヤーの情報から投票対象テーブル用データの作成
        // List<VoteReceivers> voteReceiversList = new ArrayList<>();
        // for (PlayerInfo playerInfo : alivePlayers) {
        // var voteReceivers = new VoteReceivers(
        // playerInfo.getGameDataId(),
        // playerInfo.getDeviceId(),
        // playerInfo.getPlayerName(),
        // playerInfo.getPlayerIcon());
        // voteReceiversList.add(voteReceivers);
        // }

        // 投票対象テーブルデータ挿入
        // voteReceiversService.registryFromList(voteReceiversList);

        // 投票テーブルの既存データ削除
        votesService.deleteAll();
    }

}
