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
    private static String[] HEADERS = new String[]{"Time", "Price", "SMA4", "SMA9", "SMA50", "SMA9-SMA50", "RSI14", "MACD", "Short Trend", "Long Trend", "Trade", "Strategy", "SMA Cross", "Profit"};
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
                    String.valueOf(taData.get(index).getSMA4()),
                    String.valueOf(taData.get(index).getSMA9()),
                    String.valueOf(taData.get(index).getSMA50()),
                    String.valueOf(taData.get(index).getSMA50SMA9DIFF()),
                    String.valueOf(taData.get(index).getRSI14()),
                    String.valueOf(taData.get(index).getMACD()),
                    String.valueOf(taData.get(index).getShortTrend().getNumber()),
                    String.valueOf(taData.get(index).getLongTrend().getNumber()),
                    trade != null ? String.valueOf(trade.getTradeType().getNumber()) : "0",
                    trade != null ? trade.getStrategyType().toString() : "",
                    String.valueOf(taData.get(index).getSmaCrossed()),
                    String.valueOf(totalProfit)
            );
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }




}
