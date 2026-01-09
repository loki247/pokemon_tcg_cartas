package com.example.pokemontcg.model.tcg;

import java.util.List;

public class Attack {
    private List<String> cost;
    private String name;
    private String effect;

    public List<String> getCost() {
        return cost;
    }

    public void setCost(List<String> cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
