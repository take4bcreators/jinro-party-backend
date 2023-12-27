package com.extensionlab.jinropartybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
// import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerDataPK;

public interface DropoutPlayerDataRepository extends JpaRepository<DropoutPlayerData, String> {
    // public interface DropoutPlayerDataRepository extends
    // JpaRepository<DropoutPlayerData,
    // DropoutPlayerDataPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <DropoutPlayerData, DropoutPlayerDataPK>
    // となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    List<DropoutPlayerData> findAllByGameDataId(String gameDataId);

}
