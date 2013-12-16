package pokedex;

public class Ability {
    private final int id;
    private final int element_id;
    private final String name;

    public Ability(int id, int element_id, String name) {
        this.id = id;
        this.element_id = element_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getElement_id() {
        return element_id;
    }

    public String getName() {
        return name;
    }
}
