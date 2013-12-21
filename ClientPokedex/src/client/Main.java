package client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        TableView tableView = (TableView) scene.lookup("#pokemonTable");
        tableView.setEditable(true);
        tableView.getColumns().get(0);

        TableColumn id = (TableColumn) tableView.getColumns().get(0);
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

        TableColumn evolutionId = (TableColumn) tableView.getColumns().get(1);
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

        TableColumn speciesId = (TableColumn) tableView.getColumns().get(2);
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

        TableColumn abilities = (TableColumn) tableView.getColumns().get(3);
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

        TableColumn elements = (TableColumn) tableView.getColumns().get(4);
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

        TableColumn weaknesses = (TableColumn) tableView.getColumns().get(5);
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

        TableColumn name = (TableColumn) tableView.getColumns().get(6);
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

        TableColumn gender = (TableColumn) tableView.getColumns().get(7);
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

        TableColumn height = (TableColumn) tableView.getColumns().get(8);
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

        TableColumn weight = (TableColumn) tableView.getColumns().get(9);
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

        TableColumn level = (TableColumn) tableView.getColumns().get(10);
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

        tableView.setItems(Controller.getPokemons());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
