package com.extensionlab.jinropartybackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.enums.EntryPlayerState;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfo;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfoPK;

public interface EntryPlayerInfoRepository extends JpaRepository<EntryPlayerInfo, EntryPlayerInfoPK> {

    boolean existsByGameDataIdAndDeviceIdAndEntryPlayerState(String gameDataId, String deviceId,
            EntryPlayerState entry);

    boolean existsByGameDataIdAndPlayerName(String gameDataId, String playerName);

    Optional<EntryPlayerInfo[]> findAllByGameDataIdAndEntryPlayerState(String gameDataId,
            EntryPlayerState entryPlayerState);

    void deleteByGameDataId(String gameDataId);
}
