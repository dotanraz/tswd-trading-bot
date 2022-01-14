package com.theswdeveloper.tradingbot;

import com.theswdeveloper.tradingbot.binance.BinanceApi;
import com.theswdeveloper.tradingbot.binance.BinanceClient;
import com.theswdeveloper.tradingbot.binance.PriceMockApi;
import com.theswdeveloper.tradingbot.bot.TaBotPlan;
import com.theswdeveloper.tradingbot.bot.TaBotRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		TaBotPlan plan = new TaBotPlan("BTCUSDT", new BinanceApi(BinanceClient.getInstance().getClient()), 60000, 0.1, 0.1, TaBotPlan.Mode.COLLECTOR);
		TaBotPlan plan = new TaBotPlan("ETHUSDT", new PriceMockApi(), 10, 0.2, 0.2, TaBotPlan.Mode.TEST_DATA);
		TaBotRunner taBotRunner = new TaBotRunner();
		taBotRunner.run(plan);
	}
}
