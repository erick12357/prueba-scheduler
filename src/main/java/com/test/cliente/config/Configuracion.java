package com.test.cliente.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.test.mongoLib.MongoLibConfig;

@Configuration
@Import({MongoLibConfig.class})
public class Configuracion {

	@Bean
	public RestTemplate template(RestTemplateBuilder builder) {
		return builder.build();
	}
}
