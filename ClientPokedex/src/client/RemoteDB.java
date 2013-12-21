package client;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RemoteDB {
    private static RemoteDB INSTANCE = null;
    private static String URL = "jdbc:h2:tcp://146.185.168.126:9092/pokedex;IFEXISTS=TRUE";
    private static Connection connection = null;
    private static String UPDATE_POKEMON = "update pokemon set evolution_id=?, species_id=?, pic_name=?, " +
            "ability_ids=?, element_ids=?, weaknesses_ids=?, name=?, gender=?, height=?, weight=?, lvl=? where id=?";
    private static String ALL_POKEMON = "select id, evolution_id, species_id, pic_name, ability_ids, element_ids, " +
            "weaknesses_ids, name, gender, height, weight, lvl from pokemon";

    public static RemoteDB getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDB();
        }
        return INSTANCE;
    }

    private RemoteDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(URL, "sa", "");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        RemoteDB.getInstance();
    }

    public void updatePokemon(Pokemon pokemon) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POKEMON);
        preparedStatement.setInt(1, Integer.parseInt(pokemon.getEvolutionId()));
        preparedStatement.setInt(2, Integer.parseInt(pokemon.getSpeciesId()));
        preparedStatement.setString(3, pokemon.getName().toLowerCase() + ".png");
        preparedStatement.setString(4, pokemon.getAbilities());
        preparedStatement.setString(5, pokemon.getElements());
        preparedStatement.setString(6, pokemon.getWeaknesses());
        preparedStatement.setString(7, pokemon.getName());
        preparedStatement.setString(8, pokemon.getGender());
        preparedStatement.setString(9, pokemon.getHeight());
        preparedStatement.setString(10, pokemon.getWeight());
        preparedStatement.setString(11, pokemon.getLevel());
        preparedStatement.setString(12, pokemon.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Pokemon> getAllPokemon() throws SQLException {
        List<Pokemon> result = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(ALL_POKEMON);
        preparedStatement.executeQuery();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            result.add(
                    new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                            resultSet.getString(8),resultSet.getString(9), resultSet.getInt(10), resultSet.getInt(11),
                            resultSet.getInt(12))
            );
        }
        preparedStatement.close();
        return result;
    }
}
