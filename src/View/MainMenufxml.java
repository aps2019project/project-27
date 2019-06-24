package View;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenufxml implements Initializable {
	public Button Account;
	public Button CostumeCard;
	public Button Battle;
	public Button Shop;
	public Button Exit;
	public Button Collection;
	public Label AccountLable;
	public Button Help;

	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		Moudle.Account account = Moudle.Account.getMainAccount ( );
		if ( account != null ) {
			AccountLable.setText ( account.getUserName ( ) );
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
				Help.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "HelpMenu" );
					}
				} );
				Collection.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "Collection" );
					}
				} );
				Shop.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "Shop" );
					}
				} );
				Battle.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "BattleMenu" );
					}
				} );
				CostumeCard.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						Graphic.setRegion ( "CostomCard" );
					}
				} );
			}
		};
		animationTimer.start ( );
	}

	;

	@FXML
	private void closeButtonAction () {
		Stage stage = ( Stage ) Exit.getScene ( ).getWindow ( );
		stage.close ( );
	}
}
