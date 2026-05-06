package com.example.demo;

public class CurrencyRVModal {
    private String name, symbol, iconUrl;
    private double price, change1h, change24h, change7d, change30d;

    public CurrencyRVModal(String name, String symbol, double price, double change1h,
                           double change24h, double change7d, double change30d, String iconUrl) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change1h = change1h;
        this.change24h = change24h;
        this.change7d = change7d;
        this.change30d = change30d;
        this.iconUrl = iconUrl;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public double getChange1h() { return change1h; }
    public double getChange24h() { return change24h; }
    public double getChange7d() { return change7d; }
    public double getChange30d() { return change30d; }
    public String getIconUrl() { return iconUrl; }
}
