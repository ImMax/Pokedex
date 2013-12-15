package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Pokemon> list = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        TableView tableView = (TableView) scene.lookup("#pokemonTable");
        tableView.getColumns().get(0);

        TableColumn evolutionId = (TableColumn) tableView.getColumns().get(0);
        evolutionId.setCellValueFactory(
                new PropertyValueFactory<Pokemon, Integer>("evolutionId"));
        evolutionId.setCellFactory(TextFieldTableCell.forTableColumn());
        evolutionId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setEvolutionId(t.getNewValue());
                    }
                }
        );

        TableColumn speciesId = (TableColumn) tableView.getColumns().get(1);
        speciesId.setCellValueFactory(
                new PropertyValueFactory<Pokemon, Integer>("speciesId"));
        speciesId.setCellFactory(TextFieldTableCell.forTableColumn());
        speciesId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setSpeciesId(t.getNewValue());
                    }
                }
        );

        TableColumn abilities = (TableColumn) tableView.getColumns().get(2);
        abilities.setCellValueFactory(
                new PropertyValueFactory<Pokemon, String>("abilities"));
        abilities.setCellFactory(TextFieldTableCell.forTableColumn());
        abilities.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setAbilities(t.getNewValue());
                    }
                }
        );

        TableColumn elements = (TableColumn) tableView.getColumns().get(3);
        elements.setCellValueFactory(
                new PropertyValueFactory<Pokemon, String>("elements"));
        elements.setCellFactory(TextFieldTableCell.forTableColumn());
        elements.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setElements(t.getNewValue());
                    }
                }
        );

        TableColumn weaknesses = (TableColumn) tableView.getColumns().get(4);
        weaknesses.setCellValueFactory(
                new PropertyValueFactory<Pokemon, String>("weaknesses"));
        weaknesses.setCellFactory(TextFieldTableCell.forTableColumn());
        weaknesses.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setWeaknesses(t.getNewValue());
                    }
                }
        );

        TableColumn name = (TableColumn) tableView.getColumns().get(5);
        name.setCellValueFactory(
                new PropertyValueFactory<Pokemon, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName(t.getNewValue());
                    }
                }
        );

        TableColumn gender = (TableColumn) tableView.getColumns().get(6);
        gender.setCellValueFactory(
                new PropertyValueFactory<Pokemon, String>("gender"));
        gender.setCellFactory(TextFieldTableCell.forTableColumn());
        gender.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setGender(t.getNewValue());
                    }
                }
        );

        TableColumn height = (TableColumn) tableView.getColumns().get(7);
        height.setCellValueFactory(
                new PropertyValueFactory<Pokemon, Integer>("height"));
        height.setCellFactory(TextFieldTableCell.forTableColumn());
        height.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setHeight(t.getNewValue());
                    }
                }
        );

        TableColumn weight = (TableColumn) tableView.getColumns().get(8);
        weight.setCellValueFactory(
                new PropertyValueFactory<Pokemon, Integer>("weight"));
        weight.setCellFactory(TextFieldTableCell.forTableColumn());
        weight.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setWeight(t.getNewValue());
                    }
                }
        );

        TableColumn level = (TableColumn) tableView.getColumns().get(9);
        level.setCellValueFactory(
                new PropertyValueFactory<Pokemon, Integer>("level"));
        level.setCellFactory(TextFieldTableCell.forTableColumn());
        level.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Pokemon, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pokemon, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setLevel(t.getNewValue());
                    }
                }
        );

        tableView.setItems(list);

        Button button = new Button("Hello");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                list.add(new Pokemon(99, 1, "GH", "JKJ", "fdsf", "HUIIN", "A", 1, 34, 34));
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
