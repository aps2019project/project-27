package View;
import Controller.ControlBox;
import Controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Accountfxml implements Initializable {
	public Button save;
	public Button logout;
	public Button help;
	public Button createAccount;
	public Button login;
	public Button showLeaderBoard;
	public TextField userName;
	public TextField passWord;
	public Button Menu;

	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		ControlBox controlBox = new ControlBox (  );
		controlBox.setRegion ( "Account" );
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				createAccount.setOnAction ( event -> {
					if ( isValidPress ( userName,passWord ) ){
						controlBox.setUserName ( userName.getText () );
						controlBox.setPass ( passWord.getText () );
						controlBox.setType ( "create account" );
						ControlBox answer =  Controller.giveFromGraphic ( controlBox );
						if ( answer.isSucces () ){
							Graphic.setRegion ( "MainMenu" );
						}
					}
				} );
				login.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						controlBox.setUserName ( userName.getText () );
						controlBox.setPass ( passWord.getText () );
						controlBox.setType ( "login" );
						ControlBox answer = Controller.giveFromGraphic ( controlBox );
						if ( answer.isSucces () ){
							Graphic.setRegion ( "MainMenu" );
						}
					}
				} );
				Menu.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "MainMenu" );
					}
				} );
			}
		};
		animationTimer.start ();
	}
	private static boolean isValidPress ( TextField userName , TextField passWord ){
		return !(passWord.getText ().isEmpty ()&&userName.getText ().isEmpty ());
	}
}
