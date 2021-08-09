package com.theswdeveloper.tradingbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaBotRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaBotRunner.class);

    public void run() throws InterruptedException {
        TaBot taBot = new TaBot("ETHUSDT");
        while (true) {
            taBot.run();
        }
    }
}
