package com.extensionlab.jinropartybackend.model.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 複数プレイヤー完全データ APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIMultiPlayerFullData {
    /** プレイヤー完全データ */
    private List<APIPlayerFullData> allData;
}
