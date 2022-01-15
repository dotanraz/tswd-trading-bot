package com.theswdeveloper.tradingbot.bot;

public enum TradeType {
    LONG(1),
    SHORT(-1),
    NOT_IN_TRADE(0);

    private int number;

    TradeType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
