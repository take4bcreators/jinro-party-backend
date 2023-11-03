package com.extensionlab.jinropartybackend.model.entity;

import java.io.Serializable;

// import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfoPK implements Serializable {
    private String gameDataId;
    private String deviceId;
}
