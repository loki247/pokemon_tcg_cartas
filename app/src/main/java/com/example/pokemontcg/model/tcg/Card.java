package com.example.pokemontcg.model.tcg;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable {
    private String category;
    private Integer id;
    private String cardId;
    private String illustrator;
    private String image;
    private String localId;
    private String name;
    private String rarity;
    private Set set;
    private Variants variants;
    private List<VariantsDetailed> variantsDetailed;
    private String effect;
    private String energyType;
    private List<Integer> dexId;
    private Integer hp;
    private List<String> types;
    private String evolveFrom;
    private String description;
    private String stage;
    private List<Ability> abilities;
    private List<Attack> attacks;
    private List<Weakness> weaknesses;
    private Integer retreat;
    private String regulationMark;
    private Legal legal;
    private Integer idTcgPlayer;

    public Integer getIdTcgPlayer() {
        return idTcgPlayer;
    }

    public void setIdTcgPlayer(Integer idTcgPlayer) {
        this.idTcgPlayer = idTcgPlayer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public List<VariantsDetailed> getVariantsDetailed() {
        return variantsDetailed;
    }

    public void setVariantsDetailed(List<VariantsDetailed> variantsDetailed) {
        this.variantsDetailed = variantsDetailed;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public List<Integer> getDexId() {
        return dexId;
    }

    public void setDexId(List<Integer> dexId) {
        this.dexId = dexId;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getEvolveFrom() {
        return evolveFrom;
    }

    public void setEvolveFrom(String evolveFrom) {
        this.evolveFrom = evolveFrom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public List<Weakness> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Integer getRetreat() {
        return retreat;
    }

    public void setRetreat(Integer retreat) {
        this.retreat = retreat;
    }

    public String getRegulationMark() {
        return regulationMark;
    }

    public void setRegulationMark(String regulationMark) {
        this.regulationMark = regulationMark;
    }

    public Legal getLegal() {
        return legal;
    }

    public void setLegal(Legal legal) {
        this.legal = legal;
    }
}
