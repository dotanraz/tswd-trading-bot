package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import java.util.List;

public class MACD extends Indicator {

    public double calc(List<TaData> taDataList, int index, int shortPeriod, int longPeriod) {
        return NumberUtils.round2DecimalDigits(calcEMA(taDataList, index, shortPeriod) - calcEMA(taDataList, index, longPeriod));
    }


    @Deprecated
    @Override
    public double calc(List<TaData> taDataList, int index, int period) {
        return 0;
    }

    public double calc12_26(List<TaData> taDataList, int index) {
        return calc(taDataList, index, 12, 26);
    }

}
