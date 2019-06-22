package com.dps;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.dps.config.AuditingConfig;
import com.dps.config.BatchConfig;
import com.dps.config.JpaConfig;
import com.dps.config.SchedularConfig;
import com.dps.config.SwaggerConfig;
import com.dps.config.WebConfig;
import com.dps.config.WebSecurityConfig;

@SpringBootApplication
@ComponentScan("com.dps")
@Import({WebConfig.class, WebSecurityConfig.class, SwaggerConfig.class, JpaConfig.class, BatchConfig.class, SchedularConfig.class, AuditingConfig.class})
public class PrintclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintclientApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
}
