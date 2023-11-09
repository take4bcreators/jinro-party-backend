package com.extensionlab.jinropartybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
// import com.extensionlab.jinropartybackend.model.entity.VotesPK;
import com.extensionlab.jinropartybackend.model.entity.Votes;

public interface VotesRepository extends JpaRepository<Votes, String> {
    // public interface VotesRepository extends JpaRepository<Votes,
    // VotesPK> {

    // @note
    // 複合主キーを「@IdClass」で指定しているため、本来 <Votes, VotesPK> となるはずだが、
    // Springのバージョンアップ後、 String を要求されるため、暫定的に String で対応する
    // (→ @id で指定した型を読み取っている？バグ？ or @IdClass 自体が非推奨？)

    void deleteByGameDataId(String gameDataId);

    @Query(value = """
            WITH
                tmp01 AS (
                    SELECT
                        game_data_id,
                        receiver_device_id,
                        receiver_player_name,
                        receiver_player_icon,
                        COUNT(*) AS vote_count
                    FROM
                        votes
                    WHERE
                        game_data_id = ?1
                    GROUP BY
                        game_data_id,
                        receiver_device_id,
                        receiver_player_name,
                        receiver_player_icon
                )
            SELECT
                game_data_id AS gameDataId,
                receiver_device_id AS deviceId,
                receiver_player_name AS playerName,
                receiver_player_icon AS playerIcon,
            FROM
                tmp01
            WHERE
                vote_count = (
                    SELECT
                        MAX(vote_count) AS max_vote_count
                    FROM
                        tmp01
                )
                """, nativeQuery = true)
    List<VoteReceivers> findMaxCountReceivers(String gameDataId);

    List<Votes> findAllByGameDataId(String gameDataId);

}
