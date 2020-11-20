package com.example.android.cryptoview.data.repository;

import androidx.annotation.NonNull;

public class CryptoObject {
    int id;
    String name;
    String symbol;
    double price;
    double percentChange24h;
    double percentChange7d;
    double marketCap;
    String iconUrl;

    @NonNull
    @Override
    public String toString() {
        return "\n" + "id: " + id + "\n" +
                "name: " + name + "\n" +
                "symbol: " + symbol + "\n" +
                "price: " + price + "\n" +
                "percentChange24h: " + percentChange24h + "\n" +
                "percentChange7d: " + percentChange7d + "\n" +
                "marketCap: " + marketCap + "\n" +
                "iconUrl: " + iconUrl + "\n";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
