package pokedex;

import java.util.HashMap;
import java.util.Map;

public final class Queries {
    private static final String NAME = "name=?";
    private static final String EVOLUTION_ID = "evolution_id=?";
    private static final String SPECIES_ID = "species_id=(select id from species where name=?)";
    private static final String GENDER = "gender=?";
    private static final String LVL = "lvl=?";

    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("name", NAME);
        map.put("evolution_id", EVOLUTION_ID);
        map.put("species", SPECIES_ID);
        map.put("gender", GENDER);
        map.put("level", LVL);
    }

    private Queries() {}

    public static final String GET_ALL_POKEMONS =
            "select id, evolution_id, species_id, pic_name, ability_ids, element_ids, weaknesses_ids, " +
                    "name, gender, height, weight, lvl from pokemon";

    public static final String FIND_POKEMON_BY_NAME =
            "select id, evolution_id, species_id, pic_name, ability_ids, element_ids, weaknesses_ids, " +
                    "name, gender, height, weight, lvl from pokemon " +
                    "where name=?";

    public static final String FIND_ELEMENT_BY_ID =
            "select id, pic_name, name from element where id=?";

    public static String searchPokemon(Map<String, String> param) {
        String query = "select id, evolution_id, species_id, pic_name, ability_ids, element_ids, weaknesses_ids, " +
                "name, gender, height, weight, lvl from pokemon ";
        if (!map.isEmpty()) query += "where ";
        for(String key : param.keySet()){
            query += map.get(key) + " and ";
        }
        query = query.substring(0, query.length()-5);
        query += ";";
        return query;
    }
}
