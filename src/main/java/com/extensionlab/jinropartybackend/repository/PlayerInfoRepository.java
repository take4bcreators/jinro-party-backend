package com.extensionlab.jinropartybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfoPK;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, PlayerInfoPK> {

    void deleteByGameDataId(String gameDataId);

    long countByGameDataId(String gameDataId);

    long countByGameDataIdAndSelfRoleCheck(String gameDataId, boolean b);

    long countByGameDataIdAndPlayerState(String gameDataId, PlayerState alive);

    List<PlayerInfo> findByGameDataId(String gameDataId);

}
