package client;

import javafx.beans.property.SimpleStringProperty;

public class Element {
    private boolean isModified = false;

    SimpleStringProperty id;
    SimpleStringProperty pictureName;
    SimpleStringProperty name;

    public Element(int id, String picName, String name) {
        this.id          = new SimpleStringProperty(String.valueOf(id));
        this.pictureName = new SimpleStringProperty(picName);
        this.name        = new SimpleStringProperty(name);
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

    public String getPictureName() {
        return pictureName.get();
    }

    public SimpleStringProperty pictureNameProperty() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName.set(pictureName);
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
