package com.contaazul.bankslips.service;

import com.contaazul.bankslips.config.EnvironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckStatus {

    @Autowired
    private EnvironmentConfig environment;

    public String check() {
        return environment.getStatus();
    }

}
