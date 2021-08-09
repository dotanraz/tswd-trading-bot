package com.theswdeveloper.tradingbot.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    private static final Logger logger = LoggerFactory.getLogger(Constants.class);
    public static final String BINANCE_API_KEY = System.getenv("binance_key");
    public static final String BINANCE_API_SECRET = System.getenv("binance_secret");
    public static final String OUTPUT_DIRECTORY = "output";
    public static final String CSV_OUTPUT_FILE = OUTPUT_DIRECTORY + "/output.csv";

}
