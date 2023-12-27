package com.extensionlab.jinropartybackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.enums.EntryPlayerState;
import com.extensionlab.jinropartybackend.model.entity.EntryPlayerInfo;
// import com.extensionlab.jinropartybackend.model.entity.EntryPlayerInfoPK;

public interface EntryPlayerInfoRepository extends JpaRepository<EntryPlayerInfo, String> {
        // public interface EntryPlayerInfoRepository extends
        // JpaRepository<EntryPlayerInfo, EntryPlayerInfoPK> {

        // @note
        // 複合主キーを「@IdClass」で指定しているため、本来 <EntryPlayerInfo, EntryPlayerInfoPK> となるはずだが、
        // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
        // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

        boolean existsByGameDataIdAndDeviceIdAndEntryPlayerState(String gameDataId, String deviceId,
                        EntryPlayerState entry);

        boolean existsByGameDataIdAndPlayerName(String gameDataId, String playerName);

        Optional<EntryPlayerInfo[]> findAllByGameDataIdAndEntryPlayerState(String gameDataId,
                        EntryPlayerState entryPlayerState);

        void deleteByGameDataId(String gameDataId);

        void deleteByGameDataIdAndDeviceId(String gameDataId, String deviceId);

        long countByGameDataIdAndEntryPlayerState(String gameDataId, EntryPlayerState entry);
}
