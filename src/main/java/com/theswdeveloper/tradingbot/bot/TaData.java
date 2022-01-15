package com.theswdeveloper.tradingbot.bot;

/**
 * Holds the technical analysis points for a given price
 */
public class TaData {

    private long time;
    private double price;
    private double SMA4;
    private double SMA9;
    private double SMA50;
    private double SMA50SMA9DIFF;
    private double RSI14;
    private double MACD;
    private Trend shortTrend = Trend.NATURAL;
    private Trend longTrend = Trend.NATURAL;
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

    public double getSMA9() {
        return SMA9;
    }

    public void setSMA9(double SMA9) {
        this.SMA9 = SMA9;
    }

    public double getSMA50() {
        return SMA50;
    }

    public void setSMA50(double SMA50) {
        this.SMA50 = SMA50;
    }

    public Trend getShortTrend() {
        return shortTrend;
    }

    public void setShortTrend(Trend shortTrend) {
        this.shortTrend = shortTrend;
    }

    public Trend getLongTrend() {
        return longTrend;
    }

    public void setLongTrend(Trend longTrend) {
        this.longTrend = longTrend;
    }

    public double getSMA4() {
        return SMA4;
    }

    public void setSMA4(double SMA4) {
        this.SMA4 = SMA4;
    }

    public Trend getSmaCrossed() {
        return smaCrossed;
    }

    public void setSmaCrossed(Trend smaCrossed) {
        this.smaCrossed = smaCrossed;
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

    public double getSMA50SMA9DIFF() {
        return SMA50SMA9DIFF;
    }

    public void setSMA50SMA9DIFF(double SMA50SMA9DIFF) {
        this.SMA50SMA9DIFF = SMA50SMA9DIFF;
    }

    @Override
    public String toString() {
        return "TaData{" +
                "time=" + time +
                ", price=" + price +
                ", SMA4=" + SMA4 +
                ", SMA9=" + SMA9 +
                ", SMA50=" + SMA50 +
                ", SMA50SMA9DIFF=" + SMA50SMA9DIFF +
                ", RSI14=" + RSI14 +
                ", MACD=" + MACD +
                ", shortTrend=" + shortTrend +
                ", longTrend=" + longTrend +
                ", smaCrossed=" + smaCrossed +
                '}';
    }
}
