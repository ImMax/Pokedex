package client;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private static final ObservableList<Pokemon> pokemonsList = FXCollections.observableArrayList();
    private static final ObservableList<Ability> abilitiesList = FXCollections.observableArrayList();
    private static final ObservableList<Element> elementsList = FXCollections.observableArrayList();
    private static final ObservableList<Species> speciesList = FXCollections.observableArrayList();
    private static final ObservableList<Location> locationsList = FXCollections.observableArrayList();
    private static RemoteDB db;
    public TableView pokemon_table;
    public TabPane tab_pane;
    private List<Button> buttons;

    public TableColumn evolutionId;

    public TableColumn level;
    public TableColumn weight;
    public TableColumn height;
    public TableColumn gender;
    public TableColumn name;
    public TableColumn weaknesses;
    public TableColumn elements;
    public TableColumn abilities;
    public TableColumn speciesId;
    public Button disconnect_button;
    public Button connect_button;
    public Button commit_button;
    public Button all_pokemon;
    public Button add_button;

    public static ObservableList getPokemonsList() {
        return pokemonsList;
    }

    public static ObservableList<Ability> getAbilitiesList() {
        return abilitiesList;
    }

    public static ObservableList<Element> getElementsList() {
        return elementsList;
    }

    public static ObservableList<Species> getSpeciesList() {
        return speciesList;
    }

    public static ObservableList<Location> getLocationsList() {
        return locationsList;
    }

    public void sayHello(ActionEvent event) {
        pokemonsList.add(new Pokemon(0, 0, 0, "0", "0", "0", "Default Name", "a", 0, 0, 0));
    }

    public void commit(ActionEvent event) throws SQLException, ClassNotFoundException {
        for(Pokemon p : pokemonsList) {
            if(p.isModefied()) {
                db.updatePokemon(p);
            }
        }
    }

    public void getAllPokemon(ActionEvent event){
        try {
            if (db != null) {
                for (Tab t : tab_pane.getTabs()) {
                    if (t.isSelected()) {
                        switch(t.getId()) {
                            case "pokemon_tab": {
                                pokemonsList.clear();
                                pokemonsList.addAll(db.getAllPokemon());
                                System.out.println(t.getId() + "\n");
                            } break;
                            case "ability_tab": {
                                abilitiesList.clear();
                                abilitiesList.addAll(db.getAllAbilities());
                                System.out.println(t.getId() + "\n");
                            } break;
                            case "element_tab": {
                                elementsList.clear();
                                elementsList.addAll(db.getAllElements());
                                System.out.println(t.getId() + "\n");
                            } break;
                            case "species_tab": {
                                speciesList.clear();
                                speciesList.addAll(db.getAllSpecies());
                                System.out.println(t.getId() + "\n");
                            } break;
                            case "location_tab": {
                                locationsList.clear();
                                locationsList.addAll(db.getAllLocations());
                                System.out.println(t.getId() + "\n");
                            } break;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            showError("SQL Error: " + e.getMessage());
        }
    }

    public void connectToBD(ActionEvent event) {
        buttons = Arrays.asList(connect_button, commit_button, all_pokemon, add_button, disconnect_button);
        try {
            System.out.println(pokemon_table.getScene().toString());
            db = RemoteDB.getInstance();
            for(ButtonBase b : buttons) {
                if(connect_button.equals(b)) b.setDisable(true);
                else b.setDisable(false);
            }
        } catch (SQLException e) {
            showError("SQL Error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void disconnectFromDB(ActionEvent event) {
        try {
            db.disconnect();
            for(ButtonBase b : buttons) {
                if(connect_button.equals(b)) b.setDisable(false);
                else b.setDisable(true);
            }
        } catch (SQLException e) {
            showError("SQL Error: " + e.getMessage());
        }
    }

    private static void showError(String text) {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(text)).
                alignment(Pos.CENTER).padding(new Insets(10)).build()));
        stage.show();
    }
}
