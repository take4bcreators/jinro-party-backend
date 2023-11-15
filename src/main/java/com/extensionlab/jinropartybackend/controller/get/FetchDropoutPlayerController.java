package com.extensionlab.jinropartybackend.controller.get;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIPlayerBasicData;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.DropoutPlayerDataService;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchDropoutPlayerController {

    @Autowired
    VotesService service;

    @Autowired
    DropoutPlayerDataService dropoutPlayerDataService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-dropout-player")
    public APIPlayerBasicData get() {
        Optional<DropoutPlayerData> dropOutPlayerWrap = this.dropoutPlayerDataService.getData();
        if (dropOutPlayerWrap.isEmpty()) {
            // @todo ここの戻り値検討
            return new APIPlayerBasicData(
                    "",
                    "",
                    "");
        }
        DropoutPlayerData dropOutPlayer = dropOutPlayerWrap.get();
        APIPlayerBasicData apiPlayerBasicData = this.dataTransferService.toAPIPlayerBasicData(dropOutPlayer);
        return apiPlayerBasicData;
    }

}
