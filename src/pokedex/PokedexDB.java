package pokedex;

import java.sql.*;
import java.util.*;

public class PokedexDB {
    private static PokedexDB INSTANCE = null;
    private Connection connection = null;

    private PokedexDB(String url) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
//        connection = DriverManager.getConnection("jdbc:h2:pokedex", "sa", "");
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

    public List<Pokemon> searchPokemon(Map<String, String> param) throws SQLException {
        PreparedStatement statement = null;
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            String query = Queries.searchPokemon(param);
            statement = connection.prepareStatement(query);
            List<String> values = new ArrayList<>(param.values());
            for(int i=1; i <= values.size(); i++)
                statement.setString(i, values.get(i-1));
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                pokemonList.add(
                        new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4),
                                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                                resultSet.getString(9).charAt(0), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12))
                );
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return pokemonList;
    }

    public Pokemon searchByName(String name) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(Queries.FIND_POKEMON_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return buildPokemon(resultSet);
        }finally {
            if (statement != null)
                statement.close();
        }
    }

    public List<Element> searchElementById(String idStr) throws SQLException {
        PreparedStatement statement = null;
        try {
            List<Element> elements = new ArrayList<>();
            statement = connection.prepareStatement(Queries.FIND_ELEMENT_BY_ID);
            for(String id : idStr.split(",")){
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
}
