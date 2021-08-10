package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import java.util.List;

public class MACD {

    public double calcMACD(List<TaData> taDataList, int index) {
        return NumberUtils.round2DecimalDigits(
                taDataList.get(index).getEma12() - taDataList.get(index).getEma26());
    }

}
