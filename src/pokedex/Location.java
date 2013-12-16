package pokedex;

public class Location {
    private final int id;
    private final String elementIds;
    private final String name;

    public Location(int id, String elementIds, String name) {
        this.id = id;
        this.elementIds = elementIds;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getElementIds() {
        return elementIds;
    }

    public String getName() {
        return name;
    }
}
