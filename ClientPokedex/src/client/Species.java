package client;

import javafx.beans.property.SimpleStringProperty;

public class Species {
    private boolean isModified = false;

    SimpleStringProperty id;
    SimpleStringProperty name;

    public Species(int id, String name) {
        this.id = new SimpleStringProperty(String.valueOf(id));
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
