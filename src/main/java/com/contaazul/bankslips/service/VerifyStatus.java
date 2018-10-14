package com.contaazul.bankslips.service;

import com.contaazul.bankslips.config.StatusConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyStatus {

    @Autowired
    private StatusConfiguration statusConfiguration;

    public String getStatus() {
        return statusConfiguration.getStatus();
    }

}
