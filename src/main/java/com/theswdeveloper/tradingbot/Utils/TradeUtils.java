package com.theswdeveloper.tradingbot.Utils;

import com.theswdeveloper.tradingbot.bot.TradeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeUtils {

    private static final Logger logger = LoggerFactory.getLogger(TradeUtils.class);

    public static double calcStopLimit(double price, TradeType tradeType, double stopPct) {
        return tradeType == TradeType.LONG ? (price + ((price * stopPct) / 100)) : (price - ((price * stopPct) / 100));
    }

    public static double calcStopLoss(double price, TradeType tradeType, double stopPct) {
        return tradeType == TradeType.LONG ? (price - ((price * stopPct) / 100)) : (price + ((price * stopPct) / 100));
    }

}
