package com.extensionlab.jinropartybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.extensionlab.jinropartybackend.model.entity.WerewolfActionExecuterDataPK;
import com.extensionlab.jinropartybackend.model.entity.WerewolfActionExecuterData;

public interface WerewolfActionExecuterDataRepository extends JpaRepository<WerewolfActionExecuterData, String> {
    // public interface WerewolfActionExecuterDataRepository extends
    // JpaRepository<WerewolfActionExecuterData,
    // WerewolfActionExecuterDataPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <WerewolfActionExecuterData,
    // WerewolfActionExecuterDataPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    List<WerewolfActionExecuterData> findAllByGameDataId(String gameDataId);

    boolean existsByGameDataIdAndDeviceId(String gameDataId, String deviceId);

}
