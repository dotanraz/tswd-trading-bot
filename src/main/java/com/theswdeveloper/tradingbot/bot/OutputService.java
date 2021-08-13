package com.theswdeveloper.tradingbot.bot;

import com.theswdeveloper.tradingbot.environment.Constants;
import com.theswdeveloper.tradingbot.io.CSV;
import com.theswdeveloper.tradingbot.Utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;

public class OutputService {

    private static final Logger logger = LoggerFactory.getLogger(OutputService.class);
    private static String[] HEADERS = new String[]{"Time", "Price", "SMA4", "SMA9", "SMA50", "RSI14", "MACD", "Trend", "Trade", "Strategy", "SMA Cross", "Profit"};
    private static CSV csvFile;

    public static void storeDataToCsv(List<TaData> taData, int index, Trade trade, double totalProfit)  {
        FileUtils.createOutputDirectoryIfNotExist(Constants.OUTPUT_DIRECTORY);
        if (!FileUtils.isFileExist(Constants.CSV_OUTPUT_FILE)) {
            csvFile = new CSV(Constants.CSV_OUTPUT_FILE, HEADERS);
        } else {
            if (csvFile == null) {
                csvFile = new CSV(Constants.CSV_OUTPUT_FILE);
            }
        }

        try {
            csvFile.append(
                    HEADERS,
                    String.valueOf(taData.get(index).getTime()),
                    String.valueOf(taData.get(index).getPrice()),
                    String.valueOf(taData.get(index).getFastSma()),
                    String.valueOf(taData.get(index).getShortSMA()),
                    String.valueOf(taData.get(index).getLongSMA()),
                    String.valueOf(taData.get(index).getRSI14()),
                    String.valueOf(taData.get(index).getMACD()),
                    taData.get(index).getTrend().toString(),
                    trade != null ? trade.getTradeType().toString() : TradeType.NOT_IN_TRADE.toString(),
                    trade != null ? trade.getStrategyType().toString() : "",
                    String.valueOf(taData.get(index).getSmaCrossed()),
                    String.valueOf(totalProfit)
            );
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }




}
