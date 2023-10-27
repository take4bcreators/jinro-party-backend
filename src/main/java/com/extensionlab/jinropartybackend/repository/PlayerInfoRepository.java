package com.extensionlab.jinropartybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.extensionlab.jinropartybackend.model.PlayerInfo;
import com.extensionlab.jinropartybackend.model.PlayerInfoPK;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, PlayerInfoPK> {

    void deleteByGameDataId(String gameDataId);
}
