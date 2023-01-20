package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShiftCalendar {

	public static void main(String[] args) {
		SpringApplication.run(ShiftCalendar.class, args);
//		Parser parser = new Parser();
//		try {
//			parser.parseData("Zmena A", 0);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

//	@Bean
//	public ThymeleafViewResolver thymeleafViewResolver() {
//		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//		TemplateEngine templateEngine =  new TemplateEngine();
//		resolver.setTemplateEngine((ISpringTemplateEngine) templateEngine);
//		resolver.setCharacterEncoding("UTF-8");
//		return resolver;
//	}

}
