package client;

import javafx.beans.property.SimpleStringProperty;

public class Location {
    private boolean isModified = false;

    SimpleStringProperty id;
    SimpleStringProperty elementIds;
    SimpleStringProperty locName;

    public Location(int id, String elementIds, String name) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.elementIds = new SimpleStringProperty(elementIds);
        this.locName = new SimpleStringProperty(name);
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

    public String getElementIds() {
        return elementIds.get();
    }

    public SimpleStringProperty elementIdsProperty() {
        return elementIds;
    }

    public void setElementIds(String elementIds) {
        this.elementIds.set(elementIds);
    }

    public String getLocName() {
        return locName.get();
    }

    public SimpleStringProperty locNameProperty() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName.set(locName);
    }
}
