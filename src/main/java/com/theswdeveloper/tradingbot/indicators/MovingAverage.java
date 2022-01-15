package com.theswdeveloper.tradingbot.indicators;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.bot.TaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Moving Average algorithms
 */
public class MovingAverage extends Indicator {
    private static final Logger logger = LoggerFactory.getLogger(MovingAverage.class);

    public MovingAverage() {
    }


    @Deprecated
    @Override
    public double calc(List<TaData> taDataList, int index, int period) {
        return 0;
    }
}
