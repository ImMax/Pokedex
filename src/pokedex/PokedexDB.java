package pokedex;

import java.sql.*;
import java.util.*;

public class PokedexDB {
    private static PokedexDB INSTANCE = null;
    private Connection connection = null;
    private static List<String> secondaryParamList = Arrays.asList("ability", "type", "weakness");

    private PokedexDB(String url) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(url, "sa", "");
    }

    public static PokedexDB getInstance(String url) throws SQLException, ClassNotFoundException {
        if (INSTANCE == null) INSTANCE = new PokedexDB(url);
        return INSTANCE;
    }

    private Pokemon buildPokemon(ResultSet resultSet) throws SQLException {
        resultSet.next();
        Pokemon pokemon = new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                resultSet.getString(9).charAt(0), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12));
        return pokemon;
    }

    private void capitalizeMap(Map<String, String> map) {
        for (Map.Entry<String, String> e : map.entrySet()) {
            String val = e.getValue();
            if (val != null)
                e.setValue(capitalize(val));
        }
    }

    private String capitalize(String original) {
        String result = original;
        if (original.length() > 1) {
            String[] words = original.split(" ");
            result = "";
            for (String word : words) {
                result += word.length() == 0 ? word : word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
            }
            if (result.length() != 0) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    public List<Pokemon> searchPokemon(Map<String, String> param) throws SQLException {
        capitalizeMap(param);
        PreparedStatement statement = null;
        List<Pokemon> pokemonList = new ArrayList<>();
        Map<String, String> secondaryParam = new HashMap<>();
        try {
            for (String str : secondaryParamList) {
                if (param.containsKey(str)) {
                    secondaryParam.put(str, param.remove(str));
                }
            }
            String query = Queries.searchPokemon(param);
            statement = connection.prepareStatement(query);
            List<String> values = new ArrayList<>(param.values());
            for (int i = 1; i <= values.size(); i++)
                statement.setString(i, values.get(i - 1));
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                pokemonList.add(
                        new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4),
                                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                                resultSet.getString(9).charAt(0), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12))
                );
            }

            if (secondaryParam.containsKey("ability")) {
                pokemonList = filterAbility(pokemonList, secondaryParam.get("ability"));
            }

            if (secondaryParam.containsKey("type")) {
                pokemonList = filterType(pokemonList, secondaryParam.get("type"));
            }

            if (secondaryParam.containsKey("weakness")) {
                pokemonList = filterWeakness(pokemonList, secondaryParam.get("weakness"));
            }

        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return pokemonList;
    }

    public List<Pokemon> filterAbility(List<Pokemon> list, String abilityName) throws SQLException {
        PreparedStatement statement = null;
        List<Pokemon> newList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(Queries.FIND_ABILITY_BY_NAME);
            statement.setString(1, abilityName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Ability ability = new Ability(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
            resultSet.close();
            for (Pokemon pokemon : list) {
                String[] pokemonAbilities = pokemon.getAbilityIds().split(",");
                for (String str : pokemonAbilities) {
                    if (ability.getId() == Integer.parseInt(str)) {
                        newList.add(pokemon);
                    }
                }
            }
        } catch (SQLException ex) {
            return newList;
        } finally {
            if (statement != null)
                statement.close();
        }

        return newList;
    }

    public List<Pokemon> filterType(List<Pokemon> list, String typeName) throws SQLException {
        PreparedStatement statement = null;
        List<Pokemon> newList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(Queries.FIND_ELEMENT_BY_NAME);
            statement.setString(1, typeName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Element element = new Element(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            for (Pokemon pokemon : list) {
                String[] pokemonElements = pokemon.getElementIds().split(",");
                for (String str : pokemonElements) {
                    if (element.getId() == Integer.parseInt(str)) {
                        newList.add(pokemon);
                    }
                }
            }
            resultSet.close();
        } catch (SQLException ex) {
            return newList;
        } finally {
            if (statement != null)
                statement.close();
        }
        return newList;
    }

    public List<Pokemon> filterWeakness(List<Pokemon> list, String weakness) throws SQLException {
        PreparedStatement statement = null;
        List<Pokemon> newList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(Queries.FIND_WEAKNESS_BY_NAME);
            statement.setString(1, weakness);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Element element = new Element(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            resultSet.close();
            for (Pokemon pokemon : list) {
                String[] pokemonElements = pokemon.getWeaknessesIds().split(",");
                for (String str : pokemonElements) {
                    if (element.getId() == Integer.parseInt(str)) {
                        newList.add(pokemon);
                    }
                }
            }
        } catch (SQLException ex) {
            return newList;
        } finally {
            if (statement != null)
                statement.close();
        }
        return newList;
    }

    public Pokemon searchByName(String name) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(Queries.FIND_POKEMON_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return buildPokemon(resultSet);
        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public List<Element> searchElementById(String idStr) throws SQLException {
        PreparedStatement statement = null;
        try {
            List<Element> elements = new ArrayList<>();
            statement = connection.prepareStatement(Queries.FIND_ELEMENT_BY_ID);
            for (String id : idStr.split(",")) {
                statement.setInt(1, Integer.parseInt(id));
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                elements.add(new Element(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
                resultSet.close();
            }
            return elements;
        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public String getSpeciesById(int speciesId) throws SQLException {
        PreparedStatement statement = null;
        try {
            String speciesName;
            statement = connection.prepareStatement(Queries.FIND_SPECIES_BY_ID);
            statement.setInt(1, speciesId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            speciesName = resultSet.getString(1);
            resultSet.close();
            return speciesName;
        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public Set<String> getPossibleLocations(List<Element> elementList) throws SQLException {
        PreparedStatement statement = null;
        try {
            List<Location> locationList = new ArrayList<>();
            Set<String> locationSet = new HashSet<>();
            statement = connection.prepareStatement(Queries.GET_ALL_LOCATIONS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                locationList.add(new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            for (Location location : locationList) {
                String[] element_ids = location.getElementIds().split(",");
                for (String elementFromLocation : element_ids) {
                    for (Element element : elementList) {
                        if (element.getId() == Integer.parseInt(elementFromLocation)) {
                            locationSet.add(location.getName());
                        }
                    }
                }
            }

            resultSet.close();
            return locationSet;
        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public List<Pokemon> getPokemonsByEvolutionId(int evolutionId) throws SQLException {
        PreparedStatement statement = null;
        try {
            List<Pokemon> pokemonList = new ArrayList<>();
            statement = connection.prepareStatement(Queries.FIND_POKEMONS_BY_EVOLUTION_ID);
            statement.setInt(1, evolutionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pokemonList.add(new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9).charAt(0), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12)));
            }
            resultSet.close();
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon o1, Pokemon o2) {
                    if (o1.getLvl() > o2.getLvl()) {
                        return 1;
                    } else if (o1.getLvl() < o2.getLvl()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            return pokemonList;
        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public List<Ability> getAbilitiesByPokemonId(int pokemonId) throws SQLException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(pokemonId));
        Pokemon pokemon = searchPokemon(params).get(0);
        List<Ability> allAbilities = new ArrayList<>();
        List<Ability> pokemonAbilities = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(Queries.GET_ALL_ABILITIES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allAbilities.add(new Ability(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
            }
            String[] abilityIds = pokemon.getAbilityIds().split(",");
            for (Ability ability : allAbilities) {
                for (String abilityStringId : abilityIds) {
                    if (ability.getId() == Integer.parseInt(abilityStringId)) {
                        pokemonAbilities.add(ability);
                    }
                }
            }

            return pokemonAbilities;
        } finally {
            if (statement != null)
                statement.close();
        }
    }

}
