package com.theswdeveloper.tradingbot.bot;

import java.util.List;

/**
 * The purpose of this class is to return the current direction of the market.
 * N - the range size to determine the trend. the trend can be calculated on any SMA set
 * e.g: SMA9, SMA50, SMA9-SMA50
 */
public class TrendService {

    public TrendService() {
    }

    public Trend getSortTrend(List<TaData> taDataList) {
        return calcTrend(taDataList, 4);
    }

    public Trend getLongTrend(List<TaData> taDataList) {
        return calcTrend(taDataList, 20);
    }

    private Trend calcTrend(List<TaData> taDataList, int N) {
        int size = taDataList.size();
        if (size <= N) {
            //not enough data to determine trend
            return Trend.NATURAL;
        }

        double current = taDataList.get(size-1).getSMA50SMA9DIFF();
        double sumForPeriod = 0;
        for (int i = 0; i < N; i++) {
            sumForPeriod = sumForPeriod + taDataList.get(size-1 -i).getSMA50SMA9DIFF();
        }
        double mean = sumForPeriod/N;

        if (current > mean) {
            return Trend.UP;
        }

        if (current < mean) {
            return Trend.DOWN;
        }

        return Trend.NATURAL;
    }


}
