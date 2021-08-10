package com.theswdeveloper.tradingbot;

import com.theswdeveloper.tradingbot.binance.BinanceApi;
import com.theswdeveloper.tradingbot.binance.BinanceClient;
import com.theswdeveloper.tradingbot.bot.TaBotPlan;
import com.theswdeveloper.tradingbot.bot.TaBotRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Main implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TaBotPlan plan = new TaBotPlan("ETHUSDT", new BinanceApi(BinanceClient.getInstance().getClient()), 60000, 0.1, 0.1);

		TaBotRunner taBotRunner = new TaBotRunner();
		taBotRunner.run(plan);
	}
}
