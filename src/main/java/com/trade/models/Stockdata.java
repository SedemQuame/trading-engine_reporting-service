package com.trade.models;


public class Stockdata {
    private double Bid_Price;
    private double Ask_Price;
    private int Buy_Limit;
    private  String  Ticker;
    private  int Sell_Limit;
    private  double Last_Traded_Price;
    private  double Max_Price_Shift;

    public  Stockdata(){

    }

    public Stockdata(double bid_Price, double ask_Price, int buy_Limit, String ticker, int sell_Limit, double last_Traded_Price, double max_Price_Shift) {
        Bid_Price = bid_Price;
        Ask_Price = ask_Price;
        Buy_Limit = buy_Limit;
        Ticker = ticker;
        Sell_Limit = sell_Limit;
        Last_Traded_Price = last_Traded_Price;
        Max_Price_Shift = max_Price_Shift;
    }

    public double getBid_Price() {
        return Bid_Price;
    }

    public void setBid_Price(double bid_Price) {
        Bid_Price = bid_Price;
    }

    public double getAsk_Price() {
        return Ask_Price;
    }

    public void setAsk_Price(double ask_Price) {
        Ask_Price = ask_Price;
    }

    public int getBuy_Limit() {
        return Buy_Limit;
    }

    public void setBuy_Limit(int buy_Limit) {
        Buy_Limit = buy_Limit;
    }

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public int getSell_Limit() {
        return Sell_Limit;
    }

    public void setSell_Limit(int sell_Limit) {
        Sell_Limit = sell_Limit;
    }

    public double getLast_Traded_Price() {
        return Last_Traded_Price;
    }

    public void setLast_Traded_Price(double last_Traded_Price) {
        Last_Traded_Price = last_Traded_Price;
    }

    public double getMax_Price_Shift() {
        return Max_Price_Shift;
    }

    public void setMax_Price_Shift(double max_Price_Shift) {
        Max_Price_Shift = max_Price_Shift;
    }
}
