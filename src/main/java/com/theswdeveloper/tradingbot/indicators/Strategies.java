package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.bot.Signal;
import com.theswdeveloper.tradingbot.bot.TaData;
import com.theswdeveloper.tradingbot.bot.Trend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Strategies {

    private static final Logger logger = LoggerFactory.getLogger(Strategies.class);

    public Trend smaTrendCross(List<TaData> taDataList) {
        Trend crossTrend = Trend.NATURAL;
        int N = 2;
        int size = taDataList.size();
        if (size <= N) {
            return Trend.NATURAL;
        }
        double currentShortSma = taDataList.get(size - 1).getShortSMA();
        double currentLongSma = taDataList.get(size - 1).getLongSMA();
        double previousShortSma = taDataList.get(size - N).getShortSMA();
        double previousLongSma = taDataList.get(size - N).getLongSMA();


        if (previousShortSma/previousLongSma < 1 && currentShortSma/currentLongSma > 1) {
            crossTrend = Trend.UP;
            logger.info("short up cross!");
        }

        if (previousShortSma/previousLongSma > 1 && currentShortSma/currentLongSma < 1) {
            crossTrend = Trend.DOWN;
            logger.info("short down cross!");
        }

        return crossTrend;
    }

    /**
     * Trading strategy:
     * if the short term SMA is crossing up against the long term SMA - buy signal.
     * if the short term SMA is crossing down against the long test SMA - sell signal.
     * @return
     */
    private Signal runShortLongCrossSmaStrategy(List<TaData> taDataList) {
        int size = taDataList.size();
        if (taDataList.get(size-1).getTrend() == Trend.UP) {
            logger.info("market trend up");
            if (smaTrendCross(taDataList) == Trend.UP) {
                logger.info("signal up");
                return Signal.BUY;
            }
        }

        if (taDataList.get(size-1).getTrend() == Trend.DOWN) {
            logger.info("market trend down");
            if (smaTrendCross(taDataList) == Trend.DOWN) {
                logger.info("signal down");
                return Signal.SELL;
            }
        }


        return Signal.NO_SIGNAL;
    }

    public Signal runStrategy(StrategyType strategyType, List<TaData> taDataList) {
        switch (strategyType) {
            case SHORT_LONG_SMA_CROSS:
                return runShortLongCrossSmaStrategy(taDataList);
            default: return null;
        }
    }

}
