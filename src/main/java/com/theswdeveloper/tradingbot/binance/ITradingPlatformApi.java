package com.theswdeveloper.tradingbot.binance;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.Order;

import java.util.List;

public interface ITradingPlatformApi {
    public String getCurrencyLastPrice(String symbol);
    public Account getAccount();
    public String openBuyTrade(String symbol, String quantity);
    public String openSellTrade(String symbol, String quantity);
    public Long placeLimitBuy(String symbol, String quantity, String price);
    public Long placeLimitSell(String symbol, String quantity, String price);
    public List<Order> getAllOrders(String symbol);
    public BinanceApiRestClient getClient();
    public boolean isMock();
}
