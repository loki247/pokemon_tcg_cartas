package com.example.pokemontcg.model.tcg;

public class Set {
    private String id;
    private String name;
    private String logo;
    private String symbol;
    private CardCount cardCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CardCount getCardCount() {
        return cardCount;
    }

    public void setCardCount(CardCount cardCount) {
        this.cardCount = cardCount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
