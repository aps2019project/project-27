package View;

import Moudle.Buff;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBuff implements Initializable {
	public TextField name;
	public TextField CAP;
	public TextField CHP;
	public TextField CMana;
	public TextField CHolly;
	public TextField CMove;
	public TextField CcanAttack;
	public TextField CCounterAttack;
	public TextField poison;
	public TextField ageType;
	public TextField age;
	public Button create;

	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				create.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						if ( isValidCreate ( ) )
							new Buff ( name.getText ( ) ,
									CAP.getText ( ) ,
									CHP.getText ( ) ,
									CMana.getText ( ) ,
									CHolly.getText ( ) ,
									CMove.getText ( ) ,
									CcanAttack.getText ( ) ,
									CCounterAttack.getText ( ) ,
									poison.getText ( ) ,
									ageType.getText ( ) ,
									age.getText ( )
							);
					}
				} );
			}
		};
		animationTimer.start ( );
	}

	private boolean isValidCreate () {
		return true;
	}
}
