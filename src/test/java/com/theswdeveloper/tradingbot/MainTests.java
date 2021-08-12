package com.theswdeveloper.tradingbot;

import com.theswdeveloper.tradingbot.binance.PriceMockApi;
import com.theswdeveloper.tradingbot.bot.TaBotPlan;
import com.theswdeveloper.tradingbot.bot.TaBotRunner;
import org.junit.jupiter.api.Test;

class MainTests {

	@Test
	void runTaBot() throws InterruptedException {
		TaBotPlan plan = new TaBotPlan("ETHUSDT", new PriceMockApi(), 10, 0.2, 0.2, TaBotPlan.Mode.TEST_DATA);
		TaBotRunner taBotRunner = new TaBotRunner();
		taBotRunner.run(plan);
	}

}
