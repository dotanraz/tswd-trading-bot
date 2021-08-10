package com.theswdeveloper.tradingbot.binance;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import java.util.List;

public class BinanceApi implements ITradingPlatformApi{

    private BinanceApiRestClient client;

    public BinanceApi(BinanceApiRestClient client) {
        this.client = client;
    }

    public List<TickerPrice> getAllCurrenciesLastPrice() {
        List<TickerPrice> allPrices = this.client.getAllPrices();
        return allPrices;
    }

    /**
     *
     * @param symbol example: BTCUSDT, ETHUSDT, XMRUSDT
     * @return
     */
    @Override
    public String getCurrencyLastPrice(String symbol) {
        TickerStatistics tickerStatistics = this.client.get24HrPriceStatistics(symbol.toUpperCase());
        return tickerStatistics.getLastPrice();
    }

    public Account getAccount() {
        return this.client.getAccount(50000L, System.currentTimeMillis());
    }

    // Trading bots are not supposed to open trades without stop loss and stop limit.
    public String openBuyTrade(String symbol, String quantity) {
        NewOrderResponse newOrderResponse = client.newOrder(NewOrder.marketBuy(symbol, quantity).newOrderRespType(NewOrderResponseType.FULL));
        return newOrderResponse.getClientOrderId();
    }

    public String openSellTrade(String symbol, String quantity) {
        NewOrderResponse newOrderResponse = client.newOrder(NewOrder.marketSell(symbol, quantity).newOrderRespType(NewOrderResponseType.FULL));
        return newOrderResponse.getClientOrderId();
    }

    public Long placeLimitBuy(String symbol, String quantity, String price) {
        NewOrderResponse newOrderResponse = client.newOrder(NewOrder.limitBuy(symbol, TimeInForce.GTC, quantity, price));
        return newOrderResponse.getOrderId();
    }

    public Long placeLimitSell(String symbol, String quantity, String price) {
        NewOrderResponse newOrderResponse = client.newOrder(NewOrder.limitSell(symbol, TimeInForce.GTC, quantity, price));
        return newOrderResponse.getOrderId();
    }

    public List<Order> getAllOrders(String symbol) {
        return client.getAllOrders(new AllOrdersRequest(symbol));
    }

    public BinanceApiRestClient getClient() {
        return client;
    }

    @Override
    public boolean isMock() {
        return false;
    }
}
