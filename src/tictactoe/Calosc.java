package tictactoe;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Created by pwilkin on 15-Nov-18.
 */
public class Calosc {

    @FXML
    protected GridPane grid;
    private Controller boardController;
    private Stats statsController;

    public void initialize() {
        try {
            FXMLLoader ticTacToeLoader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));
            Node plansza = ticTacToeLoader.load();
            boardController = ticTacToeLoader.getController();
            boardController.setMainController(this);
            FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("stats.fxml"));
            Node statystyki = statsLoader.load();
            statsController = statsLoader.getController();
            statsController.setMainController(this);
            grid.add(plansza, 0, 0);
            grid.add(statystyki, 1, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informAboutWin(Player wins) {
        statsController.updateWins(wins);
    }

    public void restartGame() {
        boardController.startNewGame();
    }
}
