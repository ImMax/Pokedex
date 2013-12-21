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
    private static String ALL_ELEMENTS = "select id, pic_name, name from element";
    private static String ALL_LOCATIONS = "select id, element_ids, name from location";
    private static String ALL_ABILITY = "select id, element_id, name from ability";
    private static String ALL_SPECIES = "select id, name from species";

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

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) connection.close();
        INSTANCE = null;
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
        PreparedStatement preparedStatement = null;
        try {
            List<Pokemon> result = new ArrayList<>();
            preparedStatement = connection.prepareStatement(ALL_POKEMON);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result.add(
                        new Pokemon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                                resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getInt(11),
                                resultSet.getInt(12))
                );
            }
            return result;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public List<Element> getAllElements() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            List<Element> result = new ArrayList<>();
            preparedStatement = connection.prepareStatement(ALL_ELEMENTS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result.add(new Element(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            return result;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public List<Location> getAllLocations() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            List<Location> result = new ArrayList<>();
            preparedStatement = connection.prepareStatement(ALL_LOCATIONS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result.add(new Location(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
            return result;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public List<Ability> getAllAbilities() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            List<Ability> result = new ArrayList<>();
            preparedStatement = connection.prepareStatement(ALL_ABILITY);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result.add(new Ability(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
            }
            return result;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    public List<Species> getAllSpecies() throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            List<Species> result = new ArrayList<>();
            preparedStatement = connection.prepareStatement(ALL_SPECIES);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result.add(new Species(resultSet.getInt(1), resultSet.getString(2)));
            }
            return result;
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }
}
