package com.extensionlab.jinropartybackend.model.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EntryPlayerInfoPK implements Serializable {
    private String gameDataId;
    private String deviceId;
}
