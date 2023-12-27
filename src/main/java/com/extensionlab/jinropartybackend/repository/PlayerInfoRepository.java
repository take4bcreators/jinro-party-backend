package com.extensionlab.jinropartybackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
// import com.extensionlab.jinropartybackend.model.entity.PlayerInfoPK;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, String> {
    // public interface PlayerInfoRepository extends JpaRepository<PlayerInfo,
    // PlayerInfoPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <PlayerInfo, PlayerInfoPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    long countByGameDataId(String gameDataId);

    long countByGameDataIdAndSelfRoleCheck(String gameDataId, boolean b);

    long countByGameDataIdAndPlayerState(String gameDataId, PlayerState alive);

    List<PlayerInfo> findByGameDataId(String gameDataId);

    Optional<PlayerInfo> findByGameDataIdAndDeviceId(String gameDataId, String deviceId);

    List<PlayerInfo> findByGameDataIdAndPlayerState(String gameDataId, PlayerState playerState);

}
