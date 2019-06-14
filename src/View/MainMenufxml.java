package View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenufxml implements Initializable {
	public Button Account;
	public Button CostomCard;
	public Button Battle;
	public Button Shop;
	public Button Exit;
	public Button Collection;
	public Label AccountLable;
	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		Moudle.Account account = Moudle.Account.getMainAccount ();
		if ( account !=null ){
			AccountLable.setText ( account.getUserName () );
		}
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				Account.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "Account" );
					}
				}
				);
				Battle.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "BattleMenu" );
					}
				} );
			}
		};
		animationTimer.start ();
	}
}
