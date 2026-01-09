package com.example.pokemontcg.model.tcg;

import java.util.ArrayList;

public class SetContent {
    private CardCount cardCount;
    private ArrayList<Card> cards;
    private String id;
    private Legal legal;
    private String logo;
    private String name;
    private String releaseDate;
    private Serie serie;
    private String symbol;
    private String tcgOnline;
    private Abbreviation abbreviation;

    public CardCount getCardCount() {
        return cardCount;
    }

    public void setCardCount(CardCount cardCount) {
        this.cardCount = cardCount;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Legal getLegal() {
        return legal;
    }

    public void setLegal(Legal legal) {
        this.legal = legal;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTcgOnline() {
        return tcgOnline;
    }

    public void setTcgOnline(String tcgOnline) {
        this.tcgOnline = tcgOnline;
    }

    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(Abbreviation abbreviation) {
        this.abbreviation = abbreviation;
    }
}
