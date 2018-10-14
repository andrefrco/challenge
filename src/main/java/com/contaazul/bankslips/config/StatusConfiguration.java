package com.contaazul.bankslips.config;

import org.aeonbits.owner.Config;

public interface StatusConfiguration extends Config {

	@Config.Key("APP_STATUS")

	@DefaultValue("OK")

	String getStatus();

}
