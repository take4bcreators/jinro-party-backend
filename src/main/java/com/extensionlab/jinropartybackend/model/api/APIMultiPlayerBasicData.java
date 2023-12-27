package com.extensionlab.jinropartybackend.model.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 複数プレイヤー基本データ APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIMultiPlayerBasicData {
    /** プレイヤーデータ */
    private List<APIPlayerBasicData> allData;
}
