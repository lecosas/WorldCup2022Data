package br.com.lecosas.WorldCup2022Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport
public class WorldCup2022DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldCup2022DataApplication.class, args);
	}

}
