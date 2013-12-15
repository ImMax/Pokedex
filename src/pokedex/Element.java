package pokedex;

public final class Element {
    private final int id;
    private final String picName;
    private final String name;

    public Element(final int id, final String picName, final String name) {
        this.id = id;
        this.picName = picName;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getPicName() {
        return picName;
    }

    public String getName() {
        return name;
    }
}
