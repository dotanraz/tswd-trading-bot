package com.theswdeveloper.tradingbot.binance;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.Order;
import com.theswdeveloper.tradingbot.io.CSV;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class PriceMockApi implements ITradingPlatformApi{

    private static final Logger logger = LoggerFactory.getLogger(PriceMockApi.class);
//    String testData = "src/main/resources/static/testData.csv";
    String testData = "src/main/resources/static/testData_eth_usdt_8hours.csv";
    CSV csv = null;
    Deque<String> priceDeque = new ArrayDeque<>();

    public PriceMockApi() {
        loadTestData();
    }

    private void loadTestData() {
        csv = new CSV(this.testData);
        try {
            Iterable<CSVRecord> records = csv.readFromCsv(new String[]{"time", "price"});
            records.forEach(record -> priceDeque.add(record.get("price")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCurrencyLastPrice(String symbol) {
        String price = priceDeque.poll();
        if (price != null ) {
            return price;
        } else {
            logger.warn("price mock data = null");
            System.exit(0);
            return null;
        }
    }

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public String openBuyTrade(String symbol, String quantity) {
        return null;
    }

    @Override
    public String openSellTrade(String symbol, String quantity) {
        return null;
    }

    @Override
    public Long placeLimitBuy(String symbol, String quantity, String price) {
        return null;
    }

    @Override
    public Long placeLimitSell(String symbol, String quantity, String price) {
        return null;
    }

    @Override
    public List<Order> getAllOrders(String symbol) {
        return null;
    }

    @Override
    public BinanceApiRestClient getClient() {
        return null;
    }

    @Override
    public boolean isMock() {
        return true;
    }
}
