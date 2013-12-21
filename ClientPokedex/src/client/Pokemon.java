package client;

import javafx.beans.property.SimpleStringProperty;

public class Pokemon {
    private boolean isModified = false;

    SimpleStringProperty id;
    SimpleStringProperty evolutionId;
    SimpleStringProperty speciesId;
    SimpleStringProperty abilities;
    SimpleStringProperty elements;
    SimpleStringProperty weaknesses;
    SimpleStringProperty name;
    SimpleStringProperty gender;
    SimpleStringProperty height;
    SimpleStringProperty weight;
    SimpleStringProperty level;
    public Pokemon(int id, int evolutionId, int speciesId, String abilities, String elements, String weaknesses, String name, String gender, int height, int weight, int level) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.evolutionId = new SimpleStringProperty(String.valueOf(evolutionId));
        this.speciesId   = new SimpleStringProperty(String.valueOf(speciesId));
        this.abilities   = new SimpleStringProperty(abilities);
        this.elements    = new SimpleStringProperty(elements);
        this.weaknesses  = new SimpleStringProperty(weaknesses);
        this.name        = new SimpleStringProperty(name);
        this.gender      = new SimpleStringProperty(gender);
        this.height      = new SimpleStringProperty(String.valueOf(height));
        this.weight      = new SimpleStringProperty(String.valueOf(weight));
        this.level       = new SimpleStringProperty(String.valueOf(level));
    }

    public void setModified(boolean flag) {
        isModified = flag;
    }

    public boolean isModefied() {
        return isModified;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getEvolutionId() {
        return evolutionId.get();
    }

    public SimpleStringProperty evolutionIdProperty() {
        return evolutionId;
    }

    public void setEvolutionId(String evolutionId) {
        this.evolutionId.set(evolutionId);
    }

    public String getSpeciesId() {
        return speciesId.get();
    }

    public SimpleStringProperty speciesIdProperty() {
        return speciesId;
    }

    public void setSpeciesId(String speciesId) {
        this.speciesId.set(speciesId);
    }

    public String getAbilities() {
        return abilities.get();
    }

    public SimpleStringProperty abilitiesProperty() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities.set(abilities);
    }

    public String getElements() {
        return elements.get();
    }

    public SimpleStringProperty elementsProperty() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements.set(elements);
    }

    public String getWeaknesses() {
        return weaknesses.get();
    }

    public SimpleStringProperty weaknessesProperty() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses.set(weaknesses);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getHeight() {
        return height.get();
    }

    public SimpleStringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public String getLevel() {
        return level.get();
    }

    public SimpleStringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }
}
