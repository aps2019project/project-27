package View;

import Moudle.Target;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTarget implements Initializable {
	public TextField name;
	public TextField type;
	public TextField length;
	public TextField friendType;
	public TextField degree;
	public Button create;

	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				create.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						if ( isValidCreate ( ) ) {
							new Target (
									name.getText ( ) ,
									type.getText ( ) ,
									length.getText ( ) ,
									friendType.getText ( ) ,
									degree.getText ( )
							);
						}
					}
				} );
			}
		};
	}

	private boolean isValidCreate () {
		return true;
	}
}