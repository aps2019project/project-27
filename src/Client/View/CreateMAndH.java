package Client.View;

import Client.Controller.Controller;
import ControlBox.ControlBox;
import Server.Moudle.Buff;
import Server.Moudle.MinionAndHero;
import Server.Moudle.Target;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateMAndH implements Initializable {
	public TextField name;
	public TextField AP;
	public TextField HP;
	public TextField AType;
	public TextField range;
	public TextField SPMana;
	public TextField SPType;
	public TextField buff;
	public TextField target;
	public TextField image;
	public TextField cost;
	public TextField mana;
	public Button selectBuff;
	public Button selectTarget;
	public Button creat;
	public Label buffLable;
	public Label targetLable;
	private Buff selectedBuff;
	private Target selectedTarget;
	public CheckBox isHero;
	public Button back;
	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				selectBuff.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						String name = buff.getText ( );
						Buff buff = Buff.getFromCreated ( name );
						if ( buff != null ) {
							buffLable.setText ( name );
							selectedBuff = buff;
						}
					}
				} );
				selectTarget.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						String name = target.getText ( );
						Target target = Target.findCreated ( name );
						if ( target != null ) {
							targetLable.setText ( name );
							selectedTarget = target;
						}
					}
				} );
				back.setOnAction ( event -> Graphic.setRegion ( "CostomCard" ) );

				creat.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						MinionAndHero minionAndHero = new MinionAndHero ( name.getText ( ) ,
								AP.getText ( ) ,
								HP.getText ( ) ,
								AType.getText ( ) ,
								range.getText ( ) ,
								SPMana.getText ( ) ,
								SPType.getText ( ) ,
								selectedBuff ,
								selectedTarget ,
								image.getText ( ) ,
								cost.getText ( ) ,
								mana.getText ( ) ,
								isHero.isSelected ()
						);
						ControlBox controlBox = new ControlBox (  );
						controlBox.setRegion ( "add" );
						controlBox.setType ( "Card" );
						controlBox.setCard ( minionAndHero );
						Controller.giveFromGraphic ( controlBox );
						Graphic.setRegion ( "CostomCard" );
					}
				} );
			}
		};
		animationTimer.start ( );
	}

	private boolean isValid () {
		return true;
	}
}
