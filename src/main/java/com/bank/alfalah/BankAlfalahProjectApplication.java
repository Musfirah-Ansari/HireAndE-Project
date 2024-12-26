package com.bank.alfalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bank.alfalah"})
@EntityScan("com.bank.alfalah")
@EnableAutoConfiguration

//@EnableJpaRepositories("com.bank.alfalah.feature.userAuthentication.repository")
public class BankAlfalahProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BankAlfalahProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
		return builder.sources(BankAlfalahProjectApplication.class);
	}
}
