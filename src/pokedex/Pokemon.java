package pokedex;

public final class Pokemon {
    private final int id;
    private final int evolutionId;
    private final int speciesId;
    private final String picName;
    private final String abilityIds;
    private final String elementIds;
    private final String weaknessesIds;
    private final String name;
    private final char gender;
    private final int height;
    private final int weight;
    private final int lvl;

    public Pokemon(final int id, final int evolutionId, final int speciesId, final String picName,
                   final String abilityIds, final String elementIds, final String weaknessesIds, final String name,
                   final char gender, final int height, final int weight, final int lvl) {
        this.id = id;
        this.evolutionId = evolutionId;
        this.speciesId = speciesId;
        this.picName = picName;
        this.abilityIds = abilityIds;
        this.elementIds = elementIds;
        this.weaknessesIds = weaknessesIds;
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.lvl = lvl;
    }

    public int getId() {
        return id;
    }

    public int getEvolutionId() {
        return evolutionId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public String getPicName() {
        return picName;
    }

    public String getAbilityIds() {
        return abilityIds;
    }

    public String getElementIds() {
        return elementIds;
    }

    public String getWeaknessesIds() {
        return weaknessesIds;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getLvl() {
        return lvl;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", evolutionId=" + evolutionId +
                ", speciesId=" + speciesId +
                ", picName='" + picName + '\'' +
                ", abilityIds='" + abilityIds + '\'' +
                ", elementIds='" + elementIds + '\'' +
                ", weaknessesIds='" + weaknessesIds + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                ", lvl=" + lvl +
                '}';
    }
}
