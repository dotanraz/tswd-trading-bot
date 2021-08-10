package com.theswdeveloper.tradingbot.bot;

public class TaData {

    private long time;
    private double price;
    private double fastSma;
    private double shortSMA;
    private double longSMA;
    private double ema12;
    private double ema26;
    private double RSI14;
    private double MACD;
    private Trend trend = Trend.NATURAL;
    private Trend smaCrossed = Trend.NATURAL;

    public TaData() {
    }

    public TaData(long time, double price) {
        this.time = time;
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShortSMA() {
        return shortSMA;
    }

    public void setShortSMA(double shortSMA) {
        this.shortSMA = shortSMA;
    }

    public double getLongSMA() {
        return longSMA;
    }

    public void setLongSMA(double longSMA) {
        this.longSMA = longSMA;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public double getFastSma() {
        return fastSma;
    }

    public void setFastSma(double fastSma) {
        this.fastSma = fastSma;
    }

    public Trend getSmaCrossed() {
        return smaCrossed;
    }

    public void setSmaCrossed(Trend smaCrossed) {
        this.smaCrossed = smaCrossed;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public double getRSI14() {
        return RSI14;
    }

    public void setRSI14(double RSI14) {
        this.RSI14 = RSI14;
    }

    public double getMACD() {
        return MACD;
    }

    public void setMACD(double MACD) {
        this.MACD = MACD;
    }

    @Override
    public String toString() {
        return "TaData{" +
                ", price=" + price +
                ", fastSma=" + fastSma +
                ", shortSMA=" + shortSMA +
                ", longSMA=" + longSMA +
                ", ema12=" + ema12 +
                ", ema26=" + ema26 +
                ", RSI14=" + RSI14 +
                ", MACD=" + MACD +
                ", trend=" + trend +
                ", smaCrossed=" + smaCrossed +
                '}';
    }
}
