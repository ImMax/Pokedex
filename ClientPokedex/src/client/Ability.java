package client;

import javafx.beans.property.SimpleStringProperty;

public class Ability {
    private boolean isModified = false;

    SimpleStringProperty id;
    SimpleStringProperty elementId;
    SimpleStringProperty name;

    public Ability(int id, int elementId, String name) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.elementId = new SimpleStringProperty(String.valueOf(elementId));
        this.name = new SimpleStringProperty(name);
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

    public String getElementId() {
        return elementId.get();
    }

    public SimpleStringProperty elementIdProperty() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId.set(elementId);
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
}
