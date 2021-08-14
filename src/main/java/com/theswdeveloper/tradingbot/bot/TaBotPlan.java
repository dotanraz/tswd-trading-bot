package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.binance.ITradingPlatformApi;

public class TaBotPlan {

    private String symbol;
    private long interval;
    private double stopLimitPct;
    private double stopLossPct;
    private ITradingPlatformApi tradingPlatformApi;
    private Mode mode;

    public TaBotPlan(String symbol, ITradingPlatformApi tradingPlatformApi, long interval, double stopLimitPct, double stopLossPct, Mode mode) {
        this.symbol = symbol;
        this.interval = interval;
        this.stopLimitPct = stopLimitPct;
        this.stopLossPct = stopLossPct;
        this.tradingPlatformApi = tradingPlatformApi;
        this.mode = mode;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "TaBotPlan{" +
                "symbol='" + symbol + '\'' +
                ", interval=" + interval +
                ", stopLimitPct=" + stopLimitPct +
                ", stopLossPct=" + stopLossPct +
                ", tradingPlatformApi=" + tradingPlatformApi +
                ", mode=" + mode +
                '}';
    }


    public enum Mode {
        SIMULATION,
        COLLECTOR,
        REAL,
        TEST_DATA
    }
}
