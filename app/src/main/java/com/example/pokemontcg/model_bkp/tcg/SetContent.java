package com.example.pokemontcg.model_bkp.tcg;

import com.example.pokemontcg.model.tcg.Abbreviation;
import com.example.pokemontcg.model.tcg.Card;
import com.example.pokemontcg.model.tcg.CardCount;
import com.example.pokemontcg.model.tcg.Legal;
import com.example.pokemontcg.model.tcg.Serie;

import java.util.ArrayList;

public class SetContent {
    private com.example.pokemontcg.model.tcg.CardCount cardCount;
    private ArrayList<com.example.pokemontcg.model.tcg.Card> cards;
    private String id;
    private com.example.pokemontcg.model.tcg.Legal legal;
    private String logo;
    private String name;
    private String releaseDate;
    private com.example.pokemontcg.model.tcg.Serie serie;
    private String symbol;
    private String tcgOnline;
    private Abbreviation abbreviation;

    public com.example.pokemontcg.model.tcg.CardCount getCardCount() {
        return cardCount;
    }

    public void setCardCount(CardCount cardCount) {
        this.cardCount = cardCount;
    }

    public ArrayList<com.example.pokemontcg.model.tcg.Card> getCards() {
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

    public com.example.pokemontcg.model.tcg.Legal getLegal() {
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

    public com.example.pokemontcg.model.tcg.Serie getSerie() {
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
