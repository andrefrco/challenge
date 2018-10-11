package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.service.CheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

    @Autowired
    CheckStatus checkStatus;

    @GetMapping
    public String status() {
        return checkStatus.check();
    }
}
