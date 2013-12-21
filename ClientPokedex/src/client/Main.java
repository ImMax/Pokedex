package client;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private TableView pokemonTable;
    private TableView abilityTable;
    private TableView elementTable;
    private TableView speciesTable;
    private TableView locationTable;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Map<String, TableView> tables = new HashMap<>();
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Pokedex");
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        TabPane tabPane = (TabPane) scene.lookup("#tab_pane");
//        ((AnchorPane) tabPane.getTabs().size()get(0);
//        System.out.println(((AnchorPane)tabPane.getTabs().get(0).getContent()).getChildren());

        ObservableList<Tab> tabs = tabPane.getTabs();
        for (Tab t : tabs) {
            TableView table = (TableView) ((AnchorPane)t.getContent()).getChildren().get(0);
            tables.put(table.getId(), table);
        }

        pokemonTable = tables.get("pokemon_table");
        abilityTable = tables.get("ability_table");
        elementTable = tables.get("element_table");
        speciesTable = tables.get("species_table");
        locationTable = tables.get("location_table");

        initPokemonTable();
        initAbilityTable();
        initElementTable();
        initSpeciesTable();
        initLocationTable();

        primaryStage.show();
    }

    private void initPokemonTable() {
        pokemonTable.setEditable(true);

        TableColumn id = (TableColumn) pokemonTable.getColumns().get(0);
        id.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setId(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn evolutionId = (TableColumn) pokemonTable.getColumns().get(1);
        evolutionId.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("evolutionId"));
        evolutionId.setCellFactory(TextFieldTableCell.forTableColumn());
        evolutionId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setEvolutionId(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn speciesId = (TableColumn) pokemonTable.getColumns().get(2);
        speciesId.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("speciesId"));
        speciesId.setCellFactory(TextFieldTableCell.forTableColumn());
        speciesId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setSpeciesId(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn abilities = (TableColumn) pokemonTable.getColumns().get(3);
        abilities.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("abilities"));
        abilities.setCellFactory(TextFieldTableCell.forTableColumn());
        abilities.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setAbilities(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn elements = (TableColumn) pokemonTable.getColumns().get(4);
        elements.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("elements"));
        elements.setCellFactory(TextFieldTableCell.forTableColumn());
        elements.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setElements(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn weaknesses = (TableColumn) pokemonTable.getColumns().get(5);
        weaknesses.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("weaknesses"));
        weaknesses.setCellFactory(TextFieldTableCell.forTableColumn());
        weaknesses.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setWeaknesses(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn name = (TableColumn) pokemonTable.getColumns().get(6);
        name.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setName(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn gender = (TableColumn) pokemonTable.getColumns().get(7);
        gender.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("gender"));
        gender.setCellFactory(TextFieldTableCell.forTableColumn());
        gender.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setGender(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn height = (TableColumn) pokemonTable.getColumns().get(8);
        height.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("height"));
        height.setCellFactory(TextFieldTableCell.forTableColumn());
        height.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setHeight(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn weight = (TableColumn) pokemonTable.getColumns().get(9);
        weight.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("weight"));
        weight.setCellFactory(TextFieldTableCell.forTableColumn());
        weight.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setWeight(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        TableColumn level = (TableColumn) pokemonTable.getColumns().get(10);
        level.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("level"));
        level.setCellFactory(TextFieldTableCell.forTableColumn());
        level.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        Pokemon pokemon = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        pokemon.setLevel(t.getNewValue());
                        pokemon.setModified(true);
                    }
                }
        );

        pokemonTable.setItems(Controller.getPokemonsList());
    }

    private void initAbilityTable() {
        abilityTable.setEditable(true);

        TableColumn id = (TableColumn) abilityTable.getColumns().get(0);
        id.setCellValueFactory(new PropertyValueFactory<Ability, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ability, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Ability, String> t) {
                        Ability ability = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ability.setId(t.getNewValue());
                        ability.setModified(true);
                    }
                }
        );

        TableColumn elementId = (TableColumn) abilityTable.getColumns().get(1);
        elementId.setCellValueFactory(new PropertyValueFactory<Ability, String>("elementId"));
        elementId.setCellFactory(TextFieldTableCell.forTableColumn());
        elementId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ability, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Ability, String> t) {
                        Ability ability = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ability.setElementId(t.getNewValue());
                        ability.setModified(true);
                    }
                }
        );

        TableColumn name = (TableColumn) abilityTable.getColumns().get(2);
        name.setCellValueFactory(new PropertyValueFactory<Ability, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ability, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Ability, String> t) {
                        Ability ability = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        ability.setName(t.getNewValue());
                        ability.setModified(true);
                    }
                }
        );

        abilityTable.setItems(Controller.getAbilitiesList());
    }

    private void initElementTable() {
        elementTable.setEditable(true);

        TableColumn id = (TableColumn) elementTable.getColumns().get(0);
        id.setCellValueFactory(new PropertyValueFactory<Element, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Element, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Element, String> t) {
                        Element element = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        element.setId(t.getNewValue());
                        element.setModified(true);
                    }
                }
        );

        TableColumn pictureName = (TableColumn) elementTable.getColumns().get(1);
        pictureName.setCellValueFactory(new PropertyValueFactory<Element, String>("pictureName"));
        pictureName.setCellFactory(TextFieldTableCell.forTableColumn());
        pictureName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Element, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Element, String> t) {
                        Element element = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        element.setPictureName(t.getNewValue());
                        element.setModified(true);
                    }
                }
        );

        TableColumn name = (TableColumn) elementTable.getColumns().get(2);
        name.setCellValueFactory(new PropertyValueFactory<Element, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Element, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Element, String> t) {
                        Element element = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        element.setName(t.getNewValue());
                        element.setModified(true);
                    }
                }
        );

        elementTable.setItems(Controller.getElementsList());
    }

    private void initSpeciesTable() {
        speciesTable.setEditable(true);

        TableColumn id = (TableColumn) speciesTable.getColumns().get(0);
        id.setCellValueFactory(new PropertyValueFactory<Species, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Species, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Species, String> t) {
                        Species species = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        species.setId(t.getNewValue());
                        species.setModified(true);
                    }
                }
        );

        TableColumn name = (TableColumn) speciesTable.getColumns().get(1);
        name.setCellValueFactory(new PropertyValueFactory<Species, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Species, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Species, String> t) {
                        Species species = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        species.setName(t.getNewValue());
                        species.setModified(true);
                    }
                }
        );

        speciesTable.setItems(Controller.getSpeciesList());
    }

    private void initLocationTable() {
        locationTable.setEditable(true);

        TableColumn id = (TableColumn) locationTable.getColumns().get(0);
        id.setCellValueFactory(new PropertyValueFactory<Location, String>("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Location, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Location, String> t) {
                        Location location = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        location.setId(t.getNewValue());
                        location.setModified(true);
                    }
                }
        );

        TableColumn elementIds = (TableColumn) locationTable.getColumns().get(1);
        elementIds.setCellValueFactory(new PropertyValueFactory<Location, String>("elementIds"));
        elementIds.setCellFactory(TextFieldTableCell.forTableColumn());
        elementIds.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Location, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Location, String> t) {
                        Location location = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        location.setElementIds(t.getNewValue());
                        location.setModified(true);
                    }
                }
        );

        TableColumn name = (TableColumn) locationTable.getColumns().get(2);
        name.setCellValueFactory(new PropertyValueFactory<Location, String>("locName"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Location, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Location, String> t) {
                        Location location = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        location.setLocName(t.getNewValue());
                        location.setModified(true);
                    }
                }
        );

        locationTable.setItems(Controller.getLocationsList());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
