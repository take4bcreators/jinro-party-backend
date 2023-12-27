package com.extensionlab.jinropartybackend.controller.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIMultiNightActionData;
import com.extensionlab.jinropartybackend.model.api.APINightActionData;
import com.extensionlab.jinropartybackend.model.entity.NightAction;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.NightActionService;

@RestController
@CrossOrigin
public class FetchNightActionController {

    @Autowired
    NightActionService nightActionService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-night-action")
    public APIMultiNightActionData get() {
        List<NightAction> actionsRow = this.nightActionService.getAllNightActionList();
        List<APINightActionData> actions = this.dataTransferService.toAPINightActionDataList(actionsRow);
        var replay = new APIMultiNightActionData(actions);
        return replay;
    }

}
