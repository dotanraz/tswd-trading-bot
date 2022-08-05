TSWD Trading BOT

PLEASE NOTE - 
This BOT is for tests only, it wasn't design for real trade, only for test data.
The project was founded from a learning perspective.

Currently support Binance Java API: https://github.com/binance-exchange/binance-java-api

Before your run the program:
In order to run the bot you must provide a Binance API_KEY and SECRET.
In order to generate API pair you need to setup a Binance account, then generate API pair 
from the API Management under your user management section.
Binance website: https://www.binance.com/

The bot calculates:
1. Moving Average - sma4, sma9, sma50
2. RSI 14
3. MACD

The bot knows all the time the direction of the market, and execute a trade once there's an indicator from one of the signals.


How To run the program:
Linux: run the following command from the root of the project -  
    export binance_key=your_binance_key;binance_secret=your_binance_secret && ./gradlew bootRun

By default the bot is running on test data.
see Main.java -

TaBotPlan plan = new TaBotPlan("ETHUSDT", new PriceMockApi(), 10, 0.2, 0.2, TaBotPlan.Mode.TEST_DATA);

All test data are located at resources/static folder.


Output Example

![alt text](https://github.com/dotanraz/tswd-trading-bot/blob/master/output_examples/sma_strategy_chart.png?raw=true)


![alt text](https://github.com/dotanraz/tswd-trading-bot/blob/master/output_examples/data_output.png?raw=true)
