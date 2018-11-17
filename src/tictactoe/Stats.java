package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by pwilkin on 15-Nov-18.
 */
public class Stats {

    int krzyzykWins = 0;
    int kolkoWins = 0;

    protected Calosc mainController;

    public void setMainController(Calosc mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected Label krzyzyk;

    @FXML
    protected Label kolko;

    public void newGame(ActionEvent actionEvent) {
        mainController.restartGame();
    }

    public void updateWins(Player wins) {
        if (wins == Player.CROSS) {
            krzyzykWins++;
        } else {
            kolkoWins++;
        }
        updateLabels();
    }

    private void updateLabels() {
        krzyzyk.setText("Gracz X: " + krzyzykWins);
        kolko.setText("Gracz O: " + kolkoWins);
    }
}
