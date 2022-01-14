package com.theswdeveloper.tradingbot.bot;

public enum Trend {
    UP(1),
    DOWN(-1),
    NATURAL(0);

    private int number;

    Trend(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
