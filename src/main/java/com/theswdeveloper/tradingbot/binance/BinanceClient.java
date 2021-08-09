package com.theswdeveloper.tradingbot.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.theswdeveloper.tradingbot.environment.Constants;

public class BinanceClient {

    private static BinanceClient instance = null;
    private BinanceApiRestClient client;

    public static BinanceClient getInstance() {
        if (instance == null) {
            instance = new BinanceClient();
        }
        return instance;
    }

    private BinanceClient() {
        this.client = initClient();
    }

    private BinanceApiRestClient initClient() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(Constants.BINANCE_API_KEY, Constants.BINANCE_API_SECRET);
        BinanceApiRestClient client = factory.newRestClient();
        client.ping();
        return client;
    }

    public BinanceApiRestClient getClient() {
        return client;
    }
}
