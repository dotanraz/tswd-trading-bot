package com.theswdeveloper.tradingbot.environment;

public class Env {

    private static Env instance = null;
    private String env;

    public static Env getInstance() {
        if (instance == null) {
            instance = new Env();
        }
        return instance;
    }

    private Env() {

    }

    public String getEnvironment() {
        return env;
    }

    public void setEnvironment(String environment) {
        this.env = env;
    }
}
