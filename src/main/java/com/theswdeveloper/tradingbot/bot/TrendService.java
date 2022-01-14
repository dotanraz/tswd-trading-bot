package com.theswdeveloper.tradingbot.bot;

import java.util.List;

/**
 * The purpose of this class is to return the current direction of the market.
 * N - the range size to determine the trend. the trend is calculated on a fast SMA (3/4/5 periods).
 */
public class TrendService {

    public TrendService() {
    }

    public Trend getSortTrend(List<TaData> taDataList) {
        return calcTrend(taDataList, 4);
    }

    public Trend getLongTrend(List<TaData> taDataList) {
        return calcTrend(taDataList, 50);
    }

    private Trend calcTrend(List<TaData> taDataList, int N) {
        int size = taDataList.size();
        if (size <= N) {
            //not enough data to determine trend
            return Trend.NATURAL;
        }

        double currentPriceFastSma = taDataList.get(size-1).getFastSma();
        double sumPricesFastSma = 0;
        for (int i = 0; i < N; i++) {
            sumPricesFastSma = sumPricesFastSma + taDataList.get(size-1 -i).getFastSma();
        }
        double meanFastSmaPrice = sumPricesFastSma/N;

        if (currentPriceFastSma > meanFastSmaPrice) {
            return Trend.UP;
        }

        if (currentPriceFastSma < meanFastSmaPrice) {
            return Trend.DOWN;
        }

        return Trend.NATURAL;
    }

}
