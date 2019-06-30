package View;

import Moudle.Buff;
import Moudle.Spell;
import Moudle.Target;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatSpell implements Initializable {
	public TextField name;
	public TextField image;
	public TextField cost;
	public TextField mana;
	public TextField buff;
	public TextField target;
	public Button creat;
	public Button selectTarget;
	public Button selectBuff;
	public Label targetLable;
	public Label buffLable;
	private Buff selectedBuff;
	private Target selectedTarget;
	public Button back;
	@Override
	public void initialize ( URL location , ResourceBundle resources ) {
		AnimationTimer animationTimer = new AnimationTimer ( ) {
			@Override
			public void handle ( long now ) {
				selectBuff.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						String name = buff.getText ();
						Buff buff = Buff.getFromCreated (name);
						if ( buff != null ) {
							buffLable.setText ( name );
							selectedBuff = buff;
						}
					}
				} );
				back ( );
				selectTarget.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						String name = target.getText ();
						Target target = Target.findCreated ( name );
						if ( target != null ) {
							targetLable.setText ( name );
							selectedTarget = target;
						}
					}
				} );
				creat.setOnAction ( new EventHandler<ActionEvent> ( ) {
					@Override
					public void handle ( ActionEvent event ) {
						new Spell (
								 name.getText (),
								 image.getText (),
								 cost.getText (),
								 mana.getText (),
								 selectedBuff,
								 selectedTarget
						);
						Graphic.setRegion ( "CostomCard" );
					}
				} );
			}

			public void back () {
				back.setOnAction ( event -> Graphic.setRegion ( "CostomCard" ) );
			}
		};
		animationTimer.start ();
	}
}
