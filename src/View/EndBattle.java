package View;

import Moudle.Battle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EndBattle implements Initializable {
	public Button menu;
	public Label winner;
	public Label gift;
	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		winner.setText ( Battle.getWinner () );
		gift.setText ( Battle.getLastGift () );
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				menu.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "MainMenu" );
					}
				} );
			}
		};
	}
}
