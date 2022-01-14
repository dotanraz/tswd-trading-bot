package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.Utils.TradeUtils;
import com.theswdeveloper.tradingbot.indicators.IndicatorService;
import com.theswdeveloper.tradingbot.indicators.Strategies;
import com.theswdeveloper.tradingbot.indicators.StrategyType;
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
    private TaBotPlan plan;
    private List<TaData> taDataList = new LinkedList<>();
    private AtomicInteger currentIndex = new AtomicInteger(0);
    private Trade trade = null;
    private Strategies strategies = new Strategies();
    private TrendService trendService = new TrendService();
    private IndicatorService indicatorService = new IndicatorService();
    private double totalProfit;

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

        if (plan.getMode() != TaBotPlan.Mode.COLLECTOR) {
            taDataList.set(currentIndex.get(), calculateIndicators(taData));
            Signal smaCrossSignal = strategies.runStrategy(StrategyType.SHORT_LONG_SMA_CROSS, taDataList);
            Signal rsi = strategies.runStrategy(StrategyType.RSI, taDataList);
            handleTrade(smaCrossSignal, StrategyType.SHORT_LONG_SMA_CROSS);
            handleTrade(rsi, StrategyType.RSI);
        }

        logger.info(taData.toString());

        OutputService.storeDataToCsv(
                taDataList,
                currentIndex.get(),
                trade,
                totalProfit
                );

        currentIndex.getAndIncrement(); //keep this always at the end
        Thread.sleep(plan.getInterval());
    }

    private TaData calculateIndicators(TaData taData) {
        taData.setFastSma(indicatorService.getMa().calcSMA(taDataList, currentIndex.get(), 4));
        taData.setShortSMA(indicatorService.getMa().calcSMA(taDataList, currentIndex.get(), 9));
        taData.setLongSMA(indicatorService.getMa().calcSMA(taDataList, currentIndex.get(), 50));
        taData.setRSI14(indicatorService.getRsi().calc(taDataList, currentIndex.get(), 14));
        taData.setMACD(indicatorService.getMacd().calc12_26(taDataList, currentIndex.get()));
        taData.setMACD_DIGNAL(indicatorService.getMacd().calcSignalLine(taDataList, currentIndex.get(), 9));
        taData.setShortTrend(trendService.getSortTrend(taDataList));
        taData.setLongTrend(trendService.getLongTrend(taDataList));
        taData.setSmaCrossed(strategies.smaTrendCross(taDataList));
        return taData;
    }

    private void handleTrade(Signal signal, StrategyType strategyType) {
        if (trade == null || (trade != null && !trade.isTradeOpen())) {
            //trade is closed. let's see if can be opened.
            if (signal == Signal.BUY) {
                double currentPrice = taDataList.get(currentIndex.get()).getPrice();
                trade = new Trade(
                        TradeType.LONG,
                        TradeUtils.calcStopLimit(currentPrice, TradeType.LONG, plan.getStopLimitPct()),
                        TradeUtils.calcStopLoss(currentPrice, TradeType.LONG, plan.getStopLossPct()),
                        strategyType);
                trade.open(currentPrice);
            }

            if (signal == Signal.SELL) {
                double currentPrice = taDataList.get(currentIndex.get()).getPrice();
                trade = new Trade(
                        TradeType.SHORT,
                        TradeUtils.calcStopLimit(currentPrice, TradeType.SHORT, plan.getStopLimitPct()),
                        TradeUtils.calcStopLoss(currentPrice, TradeType.SHORT, plan.getStopLossPct()),
                        strategyType);
                trade.open(currentPrice);
            }

        }

        if (trade != null && trade.isTradeOpen()) {
            //trade is open. check if can be closed.
            trade.closeTradeIfNeeded(taDataList.get(currentIndex.get()).getPrice());
            if (!trade.isTradeOpen()) {
                totalProfit += trade.getProfit();
                logger.info("total profit: {}", totalProfit);
            }

        }
    }

}
