package com.extensionlab.jinropartybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import com.extensionlab.jinropartybackend.model.entity.VoteReceiversPK;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;

public interface VoteReceiversRepository extends JpaRepository<VoteReceivers, String> {
    // public interface VoteReceiversRepository extends JpaRepository<VoteReceivers,
    // VoteReceiversPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <VoteReceivers, VoteReceiversPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

}
