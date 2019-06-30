package View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class CostomCard implements Initializable {
	public Button minion;
	public Button spell;
	public Button buff;
	public Button target;
	public Button menu;
	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				minion.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "creatMinion" );
					}
				} );
				spell.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "creatSpell" );
					}
				} );
				target.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "creatTarget" );
					}
				} );
				buff.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "creatBuff" );
					}
				} );
				menu.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "MainMenu" );
					}
				} );
			}
		};
		animationTimer.start ();
	}
}
