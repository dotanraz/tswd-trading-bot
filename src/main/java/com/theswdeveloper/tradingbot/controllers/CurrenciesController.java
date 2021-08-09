package com.theswdeveloper.tradingbot.controllers;

import com.binance.api.client.domain.market.TickerPrice;
import com.theswdeveloper.tradingbot.binance.BinanceApi;
import com.theswdeveloper.tradingbot.binance.BinanceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api")
@RestController
public class CurrenciesController {

    private static final Logger logger = LoggerFactory.getLogger(CurrenciesController.class);

    @Autowired
    public CurrenciesController() {

    }

    //http://localhost:8080/api/all-prices
    @GetMapping(path = "/all-prices")
    public List<TickerPrice> getAllPrices() {
        logger.info("fetching all prices...");
        BinanceApi binanceApi = new BinanceApi(BinanceClient.getInstance().getClient());
        List<TickerPrice> allCurrenciesLastPrice = binanceApi.getAllCurrenciesLastPrice();
        return allCurrenciesLastPrice;
    }


    //http://localhost:8080/api/last-price/BTCUSDT
    @GetMapping(path = "/last-price/{symbol}")
    public String getAllPrices(@PathVariable String symbol) {
        logger.info("fetching last price for {}", symbol);
        BinanceApi binanceApi = new BinanceApi(BinanceClient.getInstance().getClient());
        return binanceApi.getCurrencyLastPrice(symbol);
    }

}
