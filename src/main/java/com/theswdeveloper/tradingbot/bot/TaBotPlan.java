package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.binance.ITradingPlatformApi;

public class TaBotPlan {

    private String symbol;
    long interval;
    private double stopLimitPct;
    private double stopLossPct;
    ITradingPlatformApi tradingPlatformApi;

    public TaBotPlan(String symbol, ITradingPlatformApi tradingPlatformApi,long interval, double stopLimitPct, double stopLossPct) {
        this.symbol = symbol;
        this.interval = interval;
        this.stopLimitPct = stopLimitPct;
        this.stopLossPct = stopLossPct;
        this.tradingPlatformApi = tradingPlatformApi;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public double getStopLimitPct() {
        return stopLimitPct;
    }

    public void setStopLimitPct(double stopLimitPct) {
        this.stopLimitPct = stopLimitPct;
    }

    public double getStopLossPct() {
        return stopLossPct;
    }

    public void setStopLossPct(double stopLossPct) {
        this.stopLossPct = stopLossPct;
    }

    public ITradingPlatformApi getTradingPlatformApi() {
        return tradingPlatformApi;
    }

    public void setTradingPlatformApi(ITradingPlatformApi tradingPlatformApi) {
        this.tradingPlatformApi = tradingPlatformApi;
    }

    @Override
    public String toString() {
        return "TaBotPlan{" +
                "symbol='" + symbol + '\'' +
                ", interval=" + interval +
                ", stopLimitPct=" + stopLimitPct +
                ", stopLossPct=" + stopLossPct +
                ", tradingPlatformApi=" + tradingPlatformApi.getClass().getSimpleName() +
                '}';
    }

}
