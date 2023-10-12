package com.extensionlab.jinropartybackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TopTestController {

    @GetMapping("/")
    public String index() {
        return "Jinro Party Project";
    }

}
