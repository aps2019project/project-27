package Moudle;

import Controller.ControlBox;
import View.View;

import java.util.ArrayList;

public class Battle {
	private static Battle currentBattle;
	private Fighter selectedFighter;
	private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<> ( );
	private Ground ground;
	private int currentTurn;
	private Player player1;
	private Player player2;
	private Player playerInTurn;
	private ArrayList<Fighter> fighters;
	private Card selectedCard;
	private Item selectedItem;
	private int battleType;
	private ArrayList<Item> flags;
	private int numberOfFlags;
	private Item mainFlag;
	private Fighter heroP1;
	private Fighter heroP2;
	private ArrayList<Buff> buffs = new ArrayList<> ( );

	public Ground getGround () {
		return ground;
	}

	public static void input ( ControlBox controllBox ) {
		String in = controllBox.getType ( );
		if ( in.equals ( "game info" ) ) {
			currentBattle.showInfo ( );
			return;
		}
		if ( in.equals ( "show my minions" ) ) {
			currentBattle.playerInTurn.showFighters ( );
		}
		if ( in.equals ( "show my opponent minions" ) ) {
			currentBattle.offTurn ( ).showFighters ( );
		}
		if ( in.equals ( "show card info" ) ) {
			currentBattle.showCardInfo ( controllBox.getCardID ( ) );
		}
		if ( in.equals ( "select card" ) ) {
			currentBattle.setInGroundCard ( controllBox.getCardID ( ) );
		}
		if ( in.equals ( "end turn" ) ){
			currentBattle.nextTurn ();
		}
		if ( in.equals ( "use special power" ) ){
			//todo
		}
		if ( in.equals ( "show hand" ) ){
			//todo
		}
		if ( in.equals ( "insert" ) ){
			currentBattle.insert ( controllBox.getCardName (),controllBox.getX (),controllBox.getY () );
		}
		if ( in.equals ( "move" ) ) {
			currentBattle.move ( controllBox.getX ( ) , controllBox.getY ( ) ,
					currentBattle.selectedFighter.getX ( ) , currentBattle.selectedFighter.getY ( ) );
		}
		if ( in.equals ( "attack" ) ){
			Fighter opponent = currentBattle.findFighter ( controllBox.getCardID (),currentBattle.offTurn () );
			if ( opponent == null ) {
				//fosh
				return;
			}
			currentBattle.attack ( currentBattle.selectedFighter,opponent );
		}
	}

	private void setInGroundCard ( String cardID ) {
		Fighter fighter = findFighter ( cardID , playerInTurn );
		if ( fighter == null ) {
			//fosh
		} else {
			selectedFighter = fighter;
			//accept;
		}
	}

	private Fighter findFighter ( String cardID ) {
		for ( Fighter fighter : fighters ) {
			if ( fighter.getID ( ).equals ( cardID ) ) {
				return fighter;
			}
		}
		return null;
	}

	private Fighter findFighter ( String cardID , Player player ) {
		for ( Fighter fighter : player.getFighters ( ) ) {
			if ( fighter.getID ( ).equals ( cardID ) )
				return fighter;
		}
		return null;
	}

	private void showCardInfo ( String cardID ) {
		Fighter fighter = findFighter ( cardID );
		if ( fighter == null ) {
			//fosh
			return;
		}
		View.showFighter ( fighter );
	}

	private Player offTurn () {
		if ( player1 == playerInTurn )
			return player2;
		return player1;
	}

	public void insert ( String cardName ,int x , int y ) {
		Card card = null;//todo cardName
		int type = card.getCardType ( );
		if ( type == 0 ) {
			Spell spell = ( Spell ) card;
			if ( ! spell.getTarget ( ).isValidTarget ( this , x , y , playerInTurn ) ) {
				System.out.println ( "invalid target" );
				return;
			}
			ArrayList<Fighter> fighters = spell.getTarget ( ).targetFighters ( this , x , y , playerInTurn );
			for ( Buff buff : spell.getMainBuffs ( ) ) {
				buff ( buff , fighters , x , y );
			}
			for ( Buff buff : spell.getBuffs ( ) ) {
				buff ( buff , fighters , x , y );
				buffs.add ( buff );
			}
		}
		if ( type == 1 ) {
			MinionAndHero minionAndHero = ( MinionAndHero ) card;
			if ( ! isValidNewFighter ( minionAndHero , playerInTurn , x , y ) ) {
				return;
			}
			minionAndHeroes.add ( minionAndHero );
			Fighter fighter = new Fighter ( minionAndHero , minionAndHeroes , playerInTurn );
			executeOnSpawnBuff ( fighter );
			ground.getCell ( x , y ).moveInCell ( fighter );
			fighter.setLocation ( x , y );
		}
	}

	public void attack ( Fighter fighter , Fighter opponent ) {
		int targetX=opponent.getX ();
		int targetY = opponent.getY ();
		if ( ! isValidDistanceForAttack ( ) ) {
			//fosh
			return;
		}
		if ( ! fighter.CanAttack ( ) ) {
			//fosh
			return;
		}
		fighter.disableCanAttack ( );
		fighter.disableCanMove ( );
		opponent.decreaseHP ( fighter.getAP ( ) - opponent.getHolyDefence ( ) );
		if ( opponent.isCanCounterAttack ( ) ) {
			fighter.decreaseHP ( opponent.getAP ( ) - fighter.getHolyDefence ( ) );
		}
		executeOnAttackAndDeBuff ( fighter , opponent );
		isDeath ( opponent );
		isDeath ( fighter );
	}

	private void executeOnAttackAndDeBuff ( Fighter offenser , Fighter difender ) {
		/* todo */
	}

	private void executeOnSpawnBuff ( Fighter fighter ) {
		// todo
	}

	private void executeOnDeathBuff ( Fighter fighter ) {

	}

	private boolean isDeath ( Fighter fighter ) {
		if ( fighter.getHP ( ) < 1 ) {
			ground.getCell ( fighter.getX ( ) , fighter.getY ( ) ).moveFromCell ( );
			executeOnDeathBuff ( fighter );
			return true;
		}
		return false;
	}

	private boolean isValidDistanceForAttack () {
		//todo
		return true;
	}

	private Fighter isValidTargetForAttack ( int targetX , int targetY ) {
		Fighter fighter;
		fighter = ( Fighter ) ground.getCell ( targetX , targetY ).getCardOnCell ( );
		if ( fighter.getPlayer ( ) == playerInTurn )
			return null;
		return fighter;
	}

	private boolean isValidNewFighter ( MinionAndHero minionAndHero , Player player , int x , int y ) {
		return true;
	}

	private void buff ( Buff buff , ArrayList<Fighter> fighters , int x , int y ) {
		if ( buff.getIsCellBuff ( ) ) {
			ground.getCell ( x , y ).addCellEffect ( buff.getCellBuff ( ) );

		} else {
			buff.setFighters ( fighters );
			for ( Fighter fighter : fighters ) {
				fighter.addToBuff ( buff );
			}
		}
	}

	private boolean isValidMove ( int x1 , int y1 , int x2 , int y2 ) {
		return ( Ground.getDistance ( x1 , y1 , x2 , y2 ) <= 2 );
	}

	private boolean isValidSelect () {
		return true;
	}

	public void checkWinner () {
	}

	public void setMana () {

	}

	public Battle ( Player player1 , Player player2 , int battleType ) {

	}

	private void move ( int targetX , int targetY , int x , int y ) {
		if ( ! isValidMove ( targetX , targetY , x , y ) ) {
			//fosh
			return;
		}
		if ( ! isValidTargetForMove ( targetX , targetY ) ) {
			//fosh
			return;
		}
		Fighter fighter = ( Fighter ) this.ground.getCell ( x , y ).getCardOnCell ( );
		if ( fighter.isCanMove ( ) ) {
			//fosh
			return;
		}
		ground.getCell ( x , y ).moveInCell ( fighter );
		ground.getCell ( targetX , targetY ).moveFromCell ( );
	}

	private boolean isValidTargetForMove ( int x , int y ) {
		Fighter fighter = ( Fighter ) this.ground.getCell ( x , y ).getCardOnCell ( );
		if ( fighter.getPlayer ( ).equals ( this.playerInTurn ) ) {
			return true;
		}
		return false;
	}

	private void checkBuffs () {
		for ( Buff buff : buffs ) {
			if ( buff.getAgeType ( ) == 1 ) {
				if ( buff.getAge ( ) < 1 ) {
					removeBuff ( buff );
				} else buff.decreesAge ( );
			}
		}
	}

	private void removeBuff ( Buff buff ) {
		if ( buff.getIsCellBuff ( ) ) {

		} else {
			for ( Fighter fighter : buff.getFighters ( ) ) {
				fighter.removeFromBuff ( buff );
			}
		}
	}

	public void nextTurn () {
		for ( Fighter fighter : fighters ) {
			fighter.preTurnProcces ( );
		}
		currentTurn++;
		setMana ( );
		setPlayerInTurn ( );
	}

	public void showInfo () {

	}

	private void setPlayerInTurn () {
		if ( playerInTurn.equals ( player1 ) )
			playerInTurn = player2;
		playerInTurn = player1;
	}
}
