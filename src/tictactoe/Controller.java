package tictactoe;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller {

    protected Calosc mainController;

    public void setMainController(Calosc mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected GridPane grid;

    protected boolean gameEnded;
    protected Board board;

    public void initialize() {
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            child.setOnMouseClicked(event -> handleMove(row, column));
        }
        startNewGame();
    }

    private void handleMove(Integer row, Integer column) {
        if (!gameEnded) {
            if (board.canYouMakeAMove(row, column)) {
                board.makeMove(row, column);
                if (!checkVictoryShowAndRegister()) {
                    board.makeComputerMove();
                }
            }
            drawBoard();
            if (!gameEnded) {
                checkVictoryShowAndRegister();
            }
        }
    }

    private boolean checkVictoryShowAndRegister() {
        Player wins = board.checkVictory();
        if (wins != null) {
            gameEnded = true;
            showVictoryMessage(wins);
            mainController.informAboutWin(wins);
            return true;
        }
        return false;
    }

    private void drawBoard() {
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            Pane pane = (Pane) child;
            pane.getChildren().clear();
            Player inCell = board.getPlayerForField(row, column);
            if (inCell != null) {
                Label label = new Label(inCell.getSign());
                label.setPrefWidth(30.0);
                label.setPrefHeight(30.0);
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
            }
        }
    }

    private void showVictoryMessage(Player wins) {
        Alert alert = new Alert(AlertType.WARNING, "Player " + wins + " wins!", ButtonType.OK);
        alert.showAndWait();
    }

    public void startNewGame() {
        gameEnded = false;
        board = new Board();
        drawBoard();
    }
}
