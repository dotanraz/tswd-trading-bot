package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.Utils.TradeUtils;
import com.theswdeveloper.tradingbot.indicators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Technical Analysis Bot iteration flow:
 * 1. fetch symbol price.
 * 2. update taData price.
 * 3. calculate technical indicators (e.g: SMA7 and SMA25) and update taData -
 *  at this point the taData will hold the following values: time, price and indicators
 * 4. check for price trend.
 * 5. check for buy/sell signal.
 * 6. open trade if signal is on.
 * 7. sleep for a given interval.
 */
public class TaBot {

    private static final Logger logger = LoggerFactory.getLogger(TaBot.class);
    TaBotPlan plan;
    private List<TaData> taDataList = new LinkedList<>();
    private AtomicInteger currentIndex = new AtomicInteger(0);
    MovingAverage MovingAverage = new MovingAverage();
    RSI RSI = new RSI();
    MACD macd = new MACD();
    Trade trade = null;
    Strategies strategies = new Strategies();
    TrendService trendService = new TrendService();


    public TaBot(TaBotPlan taBotPlan) {
        this.plan = taBotPlan;
        logger.info("initiating TaBot with plan: {}", plan.toString());
    }

    public void run() throws InterruptedException {
        TaData taData = new TaData();
        String currencyLastPrice = plan.getTradingPlatformApi().getCurrencyLastPrice(plan.getSymbol());

        taData.setPrice((Double.parseDouble(currencyLastPrice)));
        taData.setTime(System.currentTimeMillis());
        taDataList.add(taData);
        taData.setFastSma(MovingAverage.calcSMA(taDataList, currentIndex.get(), 4));
        taData.setShortSMA(MovingAverage.calcSMA(taDataList, currentIndex.get(), 9));
        taData.setLongSMA(MovingAverage.calcSMA(taDataList, currentIndex.get(), 50));
        taData.setEma12(MovingAverage.calcEMA(taDataList, currentIndex.get(), 12));
        taData.setEma26(MovingAverage.calcEMA(taDataList, currentIndex.get(), 26));
        taData.setRSI14(RSI.calcRSI(taDataList, currentIndex.get(), 14));
        taData.setMACD(macd.calcMACD(taDataList, currentIndex.get()));
        taData.setTrend(trendService.getCurrentTrend(taDataList));
        taData.setSmaCrossed(strategies.smaTrendCross(taDataList));
        taDataList.set(currentIndex.get(), taData);

        logger.info(taData.toString());

        Signal smaCrossSignal = strategies.runStrategy(StrategyType.SHORT_LONG_SMA_CROSS, taDataList);
        if (trade == null || (trade != null && !trade.isTradeOpen())) {
            //trade is closed. let's see if can be opened.
            if (smaCrossSignal == Signal.BUY) {
                double currentPrice = taDataList.get(currentIndex.get()).getPrice();
                trade = new Trade(
                        TradeType.LONG,
                        TradeUtils.calcStopLimit(currentPrice, TradeType.LONG, plan.getStopLimitPct()),
                        TradeUtils.calcStopLoss(currentPrice, TradeType.LONG, plan.getStopLossPct()),
                        plan.getTradingPlatformApi());
                trade.open(currentPrice);
            }

            if (smaCrossSignal == Signal.SELL) {
                double currentPrice = taDataList.get(currentIndex.get()).getPrice();
                trade = new Trade(
                        TradeType.SHORT,
                        TradeUtils.calcStopLimit(currentPrice, TradeType.SHORT, plan.getStopLimitPct()),
                        TradeUtils.calcStopLoss(currentPrice, TradeType.SHORT, plan.getStopLossPct()),
                        plan.getTradingPlatformApi());
                trade.open(currentPrice);
            }

        }

        if (trade != null && trade.isTradeOpen()) {
            //trade is open. check if can be closed.
            trade.closeTradeIfNeeded(taDataList.get(currentIndex.get()).getPrice());
        }

        OutputService.storeDataToCsv(
                taDataList,
                currentIndex.get(),
                trade != null ? trade.getTradeType().toString() : TradeType.NOT_IN_TRADE.toString(),
                taDataList.get(currentIndex.get()).getTrend());

        currentIndex.getAndIncrement(); //keep this always at the end
        Thread.sleep(plan.getInterval());
    }


}
