package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.Utils.NumberUtils;
import com.theswdeveloper.tradingbot.binance.BinanceApi;
import com.theswdeveloper.tradingbot.binance.ITradingPlatformApi;
import com.theswdeveloper.tradingbot.indicators.StrategyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trade {

    private static final Logger logger = LoggerFactory.getLogger(Trade.class);
    private boolean isTradeOpen = false;
    private TradeType tradeType;
    private double enterTradePrice;
    private double stopLimit;
    private double stopLoss;
    private double currentPrice;
    private long binanceOrderId;
    private double profit;
    StrategyType strategyType;

    public Trade(TradeType tradeType, double stopLimit, double stopLoss, StrategyType strategyType) {
        this.tradeType = tradeType;
        this.stopLimit = stopLimit;
        this.stopLoss = stopLoss;
        this.strategyType = strategyType;
    }

    public void open(double currentPrice) {
        this.enterTradePrice = currentPrice;
        this.isTradeOpen = true;
        //todo need to open trade
        logger.info("trade opened! type: {}, enteredPrice: {}, stopLimit: {}, stopLoss: {}", tradeType, enterTradePrice, stopLimit, stopLoss);
    }

    public void close() {
        this.isTradeOpen = false;
        calcProfit();
        this.tradeType = TradeType.NOT_IN_TRADE;
        this.strategyType = StrategyType.NA;
        //todo need to close trade
        logger.info("trade closed! type: {}, buy: {}, sell: {}, profit: {}", tradeType, enterTradePrice, currentPrice, profit);
    }

    /**
     * Position should be closed by the trading platform, using the provided stop loss and stop limit.
     * However, for testing purpose we should be able to trigger close event internally.
     * @param currentPrice
     */
    public void closeTradeIfNeeded(double currentPrice) {
        this.currentPrice = currentPrice;
        logger.info("checking if trade should be closed...");
        if (tradeType == TradeType.LONG) {
            if((currentPrice >= stopLimit) || (currentPrice <= stopLoss)) {
                close();
            }
        }

        if (tradeType == TradeType.SHORT) {
            if((currentPrice <= stopLimit) || (currentPrice >= stopLoss)) {
                close();
            }
        }

        if (isTradeOpen) {
            logger.info("trade is still open. currentPrice: {}, stopLimit: {}, stopLoss: {}", currentPrice, stopLimit, stopLoss);
        }

    }

    private void calcProfit() {
        if (tradeType == TradeType.LONG) {
            this.profit = NumberUtils.round2DecimalDigits(this.currentPrice - this.enterTradePrice);
        }
        if (tradeType == TradeType.SHORT) {
            this.profit = NumberUtils.round2DecimalDigits(this.enterTradePrice - this.currentPrice);
        }

    }

    public void updateCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isTradeOpen() {
        return isTradeOpen;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setTradeOpen(boolean tradeOpen) {
        isTradeOpen = tradeOpen;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public double getEnterTradePrice() {
        return enterTradePrice;
    }

    public void setEnterTradePrice(double enterTradePrice) {
        this.enterTradePrice = enterTradePrice;
    }

    public double getStopLimit() {
        return stopLimit;
    }

    public void setStopLimit(double stopLimit) {
        this.stopLimit = stopLimit;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public long getBinanceOrderId() {
        return binanceOrderId;
    }

    public void setBinanceOrderId(long binanceOrderId) {
        this.binanceOrderId = binanceOrderId;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }
}
