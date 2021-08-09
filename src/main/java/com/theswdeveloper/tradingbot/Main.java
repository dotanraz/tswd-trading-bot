package com.theswdeveloper.tradingbot;

import com.theswdeveloper.tradingbot.bot.TaBotRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("running profile: " + environment.getActiveProfiles()[0]);
		TaBotRunner taBotRunner = new TaBotRunner();
		taBotRunner.run();


	}
}
