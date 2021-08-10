package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


public class RSI {
    private static final Logger logger = LoggerFactory.getLogger(RSI.class);

    public RSI() {
    }

    /**
     * RSI - Relative Strength Index.
     * RSI = 100 - (100/1+RS)
     * where RS = averageUp/AverageDown
     * AverageUp = (sum of all up moves)/N
     * AverageDown = (sum of all down moves)/N
     * Move = currentPrice - previousPrice
     * upMove = if move > 0
     * downMove = if move <=0
     * @param taDataList
     * @param index
     * @param N
     * @return
     */
    public double calcRSI(List<TaData> taDataList, int index, int N) {
        double sumUp = 0;
        double sumDown = 0;

        if (taDataList.isEmpty()) {
            logger.warn("calcRSI: priceDataList is empty");
            return 0;
        }

        // range is the result of the relative position of the RSI period (N) and the current index in the dataset.
        int range = index - N;
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

        double averageUp = sumUp/N;
        double averageDown = sumDown/N;
        double RS = averageUp/averageDown;
        double RSI = NumberUtils.round2DecimalDigits(100 - (100 / ( 1 + RS)));

//        logger.info("Price: {}, RSI{}: {}", taDataList.get(index).getPrice(), N, RSI);
        return RSI;
    }


}
