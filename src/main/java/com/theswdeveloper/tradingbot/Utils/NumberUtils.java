package com.theswdeveloper.tradingbot.Utils;

public class NumberUtils {

    public static double round2DecimalDigits(double number) {
        return (Math.round(number * 100.0) / 100.0);
    }
}
