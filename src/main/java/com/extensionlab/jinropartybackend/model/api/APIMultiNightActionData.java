package com.extensionlab.jinropartybackend.model.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 複数夜アクションデータ APIデータ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIMultiNightActionData {
    /** 夜アクションデータ */
    private List<APINightActionData> allData;
}
