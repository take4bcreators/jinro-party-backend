package com.extensionlab.jinropartybackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.extensionlab.jinropartybackend.model.entity.SeerActionPK;
import com.extensionlab.jinropartybackend.model.entity.SeerAction;

public interface SeerActionRepository extends JpaRepository<SeerAction, String> {
    // public interface SeerActionRepository extends JpaRepository<SeerAction,
    // SeerActionPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <SeerAction, SeerActionPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    Optional<SeerAction> findByGameDataIdAndSeerDeviceId(String gameDataId, String seerDeviceId);

    boolean existsByGameDataIdAndSeerDeviceId(String gameDataId, String seerDeviceId);

}
