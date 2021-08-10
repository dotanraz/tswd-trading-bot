package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Moving Average algorithms
 */
public class MovingAverage {
    private static final Logger logger = LoggerFactory.getLogger(MovingAverage.class);

    public MovingAverage() {
    }

    /**
     * SMA - Simple Moving Average.
     * It is a linear moving average, meaning -
     * each index in the period (N) of the calculation gets the same weight.
     * The SMA indicator is calculated for a range of prices, however the SMA itself is calculated on a particular index.
     * Example for SMA calculation: N = 3, price-index = 47.
     * the algorithm will use price-indexes 47,46,45 for the calculation, hence SMA = (value(47) + value(46) + value(45))/3
     *
     * @param taDataList the range of prices which the SMA is calculated on.
     * @param index the particular index you want to calc an SMA for.
     * @param N the SMA period.
     * @return
     */
    public double calcSMA(List<TaData> taDataList, int index, int N) {
        if (taDataList.isEmpty()) {
            logger.warn("calcSMA: priceDataList is empty");
            return 0;
        }

        // range is the result of the relative position of the SMA period (N) and the current index in the dataset.
        int range = index - N;
        if (range < 0) { // the first indexes will always produce negative ranges
            range = 0;
        }

        double sum = 0;
        int total = 0;
        //running in reveres. going over from the highest index to the lowest index in the range
        //when the range = 0, the SMA will be equal to the index's price.
        for (int i = index; i >= range; i--) {
            sum = sum + taDataList.get(i).getPrice();
            total++;
        }
        double sma = NumberUtils.round2DecimalDigits(sum/total);
//        logger.info("Price: {}, SMA{}: {}", taDataList.get(index).getPrice(), N, sma);
        return sma;
    }

    /**
     * EMA - Exponential Moving Average
     * Average calculation considered the whole period, but assign more weight on the current price
     *
     * EMA = currentPrice*k + previousEmaPrice*(1-k).
     * k=2/(N+1)
     * @param taDataList
     * @param index
     * @param N
     * @return
     */
    public double calcEMA(List<TaData> taDataList, int index, int N) {
        if (taDataList.isEmpty()) {
            logger.warn("calcEMA: priceDataList is empty");
            return 0;
        }

        if (index < 1) {
            return taDataList.get(index).getPrice();
        }

        int k = 2/(N+1);
        double prevEma = calcSMA(taDataList, index-1, N);
        double currentPrice = taDataList.get(index).getPrice();
        double ema = NumberUtils.round2DecimalDigits(currentPrice*k + prevEma*(1-k));
//        logger.info("Price: {}, EMA{}: {}", taDataList.get(index).getPrice(), N, ema);
        return ema;
    }

}
