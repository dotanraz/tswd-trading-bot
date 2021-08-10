package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.binance.ITradingPlatformApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaBotRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaBotRunner.class);

    public void run(TaBotPlan taBotPlan) throws InterruptedException {
        TaBot taBot = new TaBot(taBotPlan);
        while (true) {
            taBot.run();
        }
    }
}
