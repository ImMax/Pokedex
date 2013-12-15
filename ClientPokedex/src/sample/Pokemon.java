package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pokemon {
    SimpleIntegerProperty evolutionId;
    SimpleIntegerProperty speciesId;
    SimpleStringProperty abilities;
    SimpleStringProperty elements;
    SimpleStringProperty weaknesses;
    SimpleStringProperty name;
    SimpleStringProperty gender;
    SimpleIntegerProperty height;
    SimpleIntegerProperty weight;
    SimpleIntegerProperty level;

    public Pokemon(int evolutionId, int speciesId, String abilities, String elements, String weaknesses, String name, String gender, int height, int weight, int level) {
        this.evolutionId = new SimpleIntegerProperty(evolutionId);
        this.speciesId   = new SimpleIntegerProperty(speciesId);
        this.abilities   = new SimpleStringProperty(abilities);
        this.elements    = new SimpleStringProperty(elements);
        this.weaknesses  = new SimpleStringProperty(weaknesses);
        this.name        = new SimpleStringProperty(name);
        this.gender      = new SimpleStringProperty(gender);
        this.height      = new SimpleIntegerProperty(height);
        this.weight      = new SimpleIntegerProperty(weight);
        this.level       = new SimpleIntegerProperty(level);
    }

    public int getEvolutionId() {
        return evolutionId.get();
    }

    public void setEvolutionId(int evolutionId) {
        this.evolutionId.set(evolutionId);
    }

    public int getSpeciesId() {
        return speciesId.get();
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId.set(speciesId);
    }

    public String getAbilities() {
        return abilities.get();
    }

    public void setAbilities(String abilities) {
        this.abilities.set(abilities);
    }

    public String getElements() {
        return elements.get();
    }

    public void setElements(String elements) {
        this.elements.set(elements);
    }

    public String getWeaknesses() {
        return weaknesses.get();
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses.set(weaknesses);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }

    public int getWeight() {
        return weight.get();
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }
}
