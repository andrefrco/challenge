package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.service.VerifyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @Autowired
    private VerifyStatus verifyStatus;

    @GetMapping
    public String status() {
        return verifyStatus.getStatus();
    }
}
