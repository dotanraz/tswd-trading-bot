package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RSI extends Indicator{

    private static final Logger logger = LoggerFactory.getLogger(RSI.class);

    @Override
    public double calc(List<TaData> taDataList, int index, int period) {
        double sumUp = 0;
        double sumDown = 0;

        if (taDataList.isEmpty()) {
            logger.warn("calcRSI: priceDataList is empty");
            return 0;
        }

        // range is the result of the relative position of the RSI period (N) and the current index in the dataset.
        int range = index - period;
        if (range < 0) { // the first indexes will always produce negative ranges
            return 50;
        }

        double diff;
        for (int i = index; i >= range + 1; i--) {
            diff = taDataList.get(i).getPrice() - taDataList.get(i-1).getPrice();
            if (diff > 0) {
                sumUp = sumUp + diff;
            } else {
                sumDown = sumDown + Math.abs(diff);
            }
        }

        double averageUp = sumUp/period;
        double averageDown = sumDown/period;
        double RS = averageUp/averageDown;
        double RSI = NumberUtils.round2DecimalDigits(100 - (100 / ( 1 + RS)));
//        logger.info("Price: {}, RSI{}: {}", taDataList.get(index).getPrice(), N, RSI);
        return RSI;
    }

}
