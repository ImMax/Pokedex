package client;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Controller {
    private static final ObservableList<Pokemon> list = FXCollections.observableArrayList();
    private static RemoteDB db;
    private List<Button> buttons;

    public TableView pokemonTable;

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
    public Button connect_button;
    public Button commit_button;
    public Button all_pokemon;
    public Button add_button;

    public static ObservableList<Pokemon> getPokemons() {
        return list;
    }

    public void sayHello(ActionEvent event) {
        list.add(new Pokemon(0, 0, 0, "0", "0", "0", "Default Name", "a", 0, 0, 0));
    }

    public void commit(ActionEvent event) throws SQLException, ClassNotFoundException {
        for(Pokemon p : list) {
            if(p.isModefied()) {
                db.updatePokemon(p);
            }
        }
    }

    public void getAllPokemon(ActionEvent event){
        try {
            if (db != null) list.addAll(db.getAllPokemon());
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

    public void connectToBD(ActionEvent event) {
        buttons = Arrays.asList(connect_button, commit_button, all_pokemon, add_button);
        try {
            db = RemoteDB.getInstance();
            for(Button b : buttons) {
                if(connect_button.equals(b)) b.setDisable(true);
                else b.setDisable(false);
            }
        } catch (SQLException e) {
            showError("SQL Error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
