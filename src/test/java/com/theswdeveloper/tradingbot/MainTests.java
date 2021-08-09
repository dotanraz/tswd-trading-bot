package com.theswdeveloper.tradingbot;

import com.theswdeveloper.tradingbot.bot.TaBotRunner;
import com.theswdeveloper.tradingbot.environment.Env;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class MainTests {

	@Autowired
	Environment environment;

	@Test
	void contextLoads() throws InterruptedException {
		Env.getInstance();
		Env.getInstance().setEnvironment(environment.getActiveProfiles()[0]);
		TaBotRunner taBotRunner = new TaBotRunner();
		taBotRunner.run();
		System.out.println("teteteetete");
	}

}
