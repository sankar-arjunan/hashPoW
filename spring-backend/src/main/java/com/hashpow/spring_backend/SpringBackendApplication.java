package com.hashpow.spring_backend;

import com.hashpow.spring_backend.security.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}


	@Bean
	public JwtFilter jwtFilterBean() {
		return new JwtFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter(JwtFilter filter) {
		FilterRegistrationBean<JwtFilter> reg = new FilterRegistrationBean<>();
		reg.setFilter(filter);
		reg.addUrlPatterns("/api/task", "/api/task/*");
		return reg;
	}

}
