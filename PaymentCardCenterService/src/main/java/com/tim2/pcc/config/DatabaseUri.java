package com.tim2.pcc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUri {

	@Value("${spring.data.bank}")
	private String bankUri;

	public String getBankUri() {
		return bankUri;
	}

	public void setBankUri(String bankUri) {
		this.bankUri = bankUri;
	}
	
}
