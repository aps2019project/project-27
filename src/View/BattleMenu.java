package View;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleMenu implements Initializable {
	public ChoiceBox cb;

	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		cb = new ChoiceBox (  );
	}
}
