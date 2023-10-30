package com.extensionlab.jinropartybackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfoPK;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, PlayerInfoPK> {

    void deleteByGameDataId(String gameDataId);

    Optional<PlayerInfo[]> findAllByGameDataId(String gameDataId);

    long countByGameDataId(String gameDataId);

    long countByGameDataIdAndSelfRoleCheck(String gameDataId, boolean b);

    long countByGameDataIdAndPlayerState(String gameDataId, PlayerState alive);

}
