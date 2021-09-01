package com.daniel.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.daniel.os.services.DBService;

@Configuration
@Profile("dev")
public class DesenvolvimentoConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean
	public boolean instanciaDB() {

		if (ddl.equals("create")){
			this.dbService.instanciaDB();
		}
		
		return false;
	}
}
