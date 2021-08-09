package com.theswdeveloper.tradingbot.Utils;

import com.theswdeveloper.tradingbot.bot.TradeType;

public class TradeUtils {

    public static double calcStopLimit(double price, TradeType tradeType, double stopPct) {
        return tradeType == TradeType.LONG ? (price + ((price * stopPct) / 100)) : (price - ((price * stopPct) / 100));
    }

    public static double calcStopLoss(double price, TradeType tradeType, double stopPct) {
        return tradeType == TradeType.LONG ? (price - ((price * stopPct) / 100)) : (price + ((price * stopPct) / 100));
    }

}
