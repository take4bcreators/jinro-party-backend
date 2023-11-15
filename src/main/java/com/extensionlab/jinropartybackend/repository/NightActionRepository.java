package com.extensionlab.jinropartybackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.extensionlab.jinropartybackend.model.entity.NightActionPK;
import com.extensionlab.jinropartybackend.model.entity.NightAction;

public interface NightActionRepository extends JpaRepository<NightAction, String> {
    // public interface NightActionRepository extends JpaRepository<NightAction,
    // NightActionPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <NightAction, NightActionPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    Optional<NightAction> findByGameDataIdAndDeviceId(String gameDataId, String deviceId);

    boolean existsByGameDataIdAndDeviceId(String gameDataId, String deviceId);

    List<NightAction> findAllByGameDataId(String gameDataId);

}
