package com.theswdeveloper.tradingbot.indicators;

public class IndicatorService {

    private MACD macd = new MACD();
    private RSI rsi = new RSI();
    private MovingAverage ma = new MovingAverage();

    public MACD getMacd() {
        return macd;
    }


    public RSI getRsi() {
        return rsi;
    }

    public MovingAverage getMa() {
        return ma;
    }

}
