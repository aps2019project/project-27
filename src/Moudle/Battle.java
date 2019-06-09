package Moudle;

import Controller.ControlBox;
import Controller.Controller;
import View.View;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
	private static Battle currentBattle;
	private Fighter selectedFighter;
	private static Account secondAccount;
	private int gift = 1000;
	private static final Random random = new Random ( );
	private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<> ( );
	private Ground ground;
	private int currentTurn;
	private Player player1;
	private Player player2;
	private Player playerInTurn;
	private ArrayList<Fighter> fighters = new ArrayList<> ( );
	private Card selectedCard;
	private Item selectedItem;
	//0:hero    1:lastflag  2:flags
	private int battleType;
	private int numberOfFlags;
	private Item lastFlag;
	private Fighter heroP1;
	private Fighter heroP2;
	private ArrayList<Buff> buffs = new ArrayList<> ( );
	private static ArrayList<Item> collectibleItems = new ArrayList<> (  );

	public Ground getGround () {
		return ground;
	}
	public static void setCollectibleItems(ArrayList<Item> items){
		for ( Item item:items ){
			if ( item.isCollectible () ){
				collectibleItems.add ( item );
			}
		}
	}
	public static void input ( ControlBox controllBox ) {
		String in = controllBox.getType ( );
		if ( in.equals ( "game info" ) ) {
			currentBattle.showInfo ( );
			return;
		}
		if ( in.equals ( "help" ) ) {
			help ( );
		}
		if ( in.equals ( "graveyard" ) ){
			currentBattle.playerInTurn.showGraveYard ();
		}
		if ( in.equalsIgnoreCase ( "select user" ) ) {
			Account account = Account.findAccount ( controllBox.getUserName ( ) );
			if ( account == null ) {
				System.out.println ( "there is no account with this user name" );

			} else {
				System.out.println ( "selected!" );
				secondAccount = account;
			}

		}
		if ( in.equalsIgnoreCase ( "new mp" ) ) {
			currentBattle = new Battle ( Account.getMainAccount ( ) , secondAccount , controllBox.getBattleType ( ) , controllBox.getNumberOfFlags ( ) );
		}
		if ( in.equals ( "show item" ) ) {
			currentBattle.showItem ( controllBox.getCardName ( ) );
		}
		if ( in.equals ( "use item" ) ) {
			Item item = Item.findItem ( controllBox.getCardName ( ) , currentBattle.playerInTurn.getCollectedItems ( ) );
			if ( item == null ) {
				System.out.println ( "you havent this item" );
				return;
			}
			currentBattle.useItem ( item , controllBox.getX ( ) , controllBox.getY ( ) );
		}
		if ( in.equals ( "show items" ) ) {
			currentBattle.playerInTurn.showCollectables ( );
		}
		if ( in.equals ( "show my minions" ) ) {
			currentBattle.playerInTurn.showFighters ( );
		}
		if ( in.equals ( "show opponent minions" ) ) {
			currentBattle.offTurn ( ).showFighters ( );
		}
		if ( in.equals ( "show card info" ) ) {
			currentBattle.showCardInfo ( controllBox.getCardID ( ) );
		}
		if ( in.equals ( "next card" ) ){
			currentBattle.playerInTurn.getHand ().showNextCard ();
		}
		if ( in.equals ( "select card" ) ) {
			currentBattle.setInGroundCard ( controllBox.getCardID ( ) );
		}
		if ( in.equals ( "end turn" ) ) {
			currentBattle.nextTurn ( );
		}
		if ( in.equals ( "use special power" ) ) {
			currentBattle.useSpecialPowerHero ( controllBox.getX ( ) , controllBox.getY ( ) );
		}
		if ( in.equals ( "show hand" ) ) {
			currentBattle.playerInTurn.showHand ( );

		}
		if ( in.equals ( "insert" ) ) {
			currentBattle.insert ( controllBox.getCardName ( ) , controllBox.getX ( ) , controllBox.getY ( ) );
		}
		if ( in.equals ( "move" ) ) {
			if ( currentBattle.selectedFighter == null ) {
				System.out.println ( "no card selected!" );
				return;
			}
			currentBattle.move ( controllBox.getX ( ) , controllBox.getY ( ) , currentBattle.selectedFighter );
		}
		if ( in.equals ( "attack" ) ) {
			Fighter opponent = currentBattle.findFighter ( controllBox.getCardID ( ) , currentBattle.offTurn ( ) );
			if ( opponent == null ) {
				System.out.println ( "There is no opponent to attack!" );
				return;
			}
			if ( currentBattle.selectedFighter == null ) {
				System.out.println ( "fighter dont selected" );
			}
			currentBattle.attack ( currentBattle.selectedFighter , opponent );
		}
	}

	public static void help () {
		System.out.printf ( "Game info\nShow my minions\nShow opponent minions\nShow card info [card id]\nSelect [card id]\nMove to ([x],[y])\n" +
				"Attack [opponent card id]\nAttack combo [opponent card id][my card id][my card id][...]\nUse special power (x,y)\n" +
				"Show hand\nInsert [card name] in (x,y)\nEnd turn\nShow collectables\nSelect [collectable id]\nShow info\nUse [location x,y]\n" +
				"Show Next Card\nEnter graveyard\nShow info [card id]\nShow cards\nHelp\nExit\nShow menu" );
	}

	private void showItem ( String name ) {
		Item item = Item.findItem ( name , playerInTurn.getCollectedItems ( ) );
		if ( item == null ) {
			System.out.println ( "you havent this item" );
		} else {
			item.showItem ( );
		}
	}

	private void setInGroundCard ( String cardID ) {
		Fighter fighter = findFighter ( cardID , playerInTurn );
		if ( fighter == null ) {
			System.out.println ( "You don't have this fighter!" );
		} else {
			selectedFighter = fighter;
			System.out.println ( "card selected!" );
		}
	}

	private void showHand () {
		playerInTurn.showHand ( );
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
			System.out.println ( "You don't have this fighter!" );
			return;
		}
		View.showFighter ( fighter );
	}

	private Player offTurn () {
		if ( player1 == playerInTurn )
			return player2;
		return player1;
	}

	private boolean isValidInsert ( Card card ) {
		if ( card.getManaPrice ( ) <= playerInTurn.getMana ( ) ) {
			playerInTurn.decreaseMana ( card.getManaPrice ( ) );
			return true;
		}
		return false;
	}

	private void useItem ( Item item , int x , int y ) {
		if ( ! item.getTarget ( ).isValidTarget ( currentBattle , x , y , playerInTurn ) ) {
			System.out.println ( "invalid target" );
			return;
		}
		ArrayList<Fighter> fighters = item.getTarget ( ).targetFighters ( currentBattle , x , y , playerInTurn );
		for ( Buff buff : item.getBuffs ( ) ) {
			buff ( buff , fighters , item.getTarget ( ) , x , y );
			if ( buff.isExeptABuff ( ) )
				buffs.add ( buff );
		}

	}

	public void insert ( String cardName , int x , int y ) {
		Card card = Card.findCard ( cardName , playerInTurn.getHand ( ).getCards ( ) );
		if ( card == null ) {
			System.out.println ( "You don't have this card!" );
			return;
		}
		if ( !isValidInsert ( card ) ) {
			System.out.println ("you have not enough mana" );
		return;
		}
		int type = card.getCardType ( );
		if ( type == 0 ) {
			if ( insertSpell ( x , y , card ) ) return;
		}
		if ( type == 1 ) {
			insertMinion ( x , y , card );
		}
	}

	public boolean insertSpell ( int x , int y , Card card ) {
		Spell spell = ( Spell ) card;
		if ( ! spell.getTarget ( ).isValidTarget ( this , x , y , playerInTurn ) ) {
			System.out.println ( "invalid target" );
			return true;
		}
		playerInTurn.getHand ( ).removeCard ( card );
		ArrayList<Fighter> fighters = spell.getTarget ( ).targetFighters ( this , x , y , playerInTurn );
		for ( Buff buff : spell.getBuffs ( ) ) {
			buff ( buff , fighters , spell.getTarget ( ) , x , y );
			if ( buff.isExeptABuff ( ) )
				buffs.add ( buff );
		}
		System.out.println ("inserted!" );
		return false;
	}

	public void insertMinion ( int x , int y , Card card ) {
		MinionAndHero minionAndHero = ( MinionAndHero ) card;
		if ( ground.getCell ( x , y ).getCardOnCell ( ) != null ) {
			System.out.println ( "There is already a card there!" );
			return;
		}
		if ( ground.getCell ( x , y ).getItemOnCell ( ) != null ) {
			playerInTurn.addItem ( ground.getCell ( x , y ).getItemOnCell ( ) );
		}
		minionAndHeroes.add ( minionAndHero );
		Fighter fighter = new Fighter ( minionAndHero , minionAndHeroes , playerInTurn );
		fighters.add ( fighter );
		playerInTurn.addFighter ( fighter );
		executeOnSpawnBuff ( fighter );
		newPlaceItem ( x , y , fighter );
		ground.getCell ( x , y ).moveInCell ( fighter );
		playerInTurn.getHand ( ).removeCard ( card );
		for ( Buff buff : ground.getCell ( x , y ).getCellEffect ( ) ) {
			if ( buff.getCellEffectType ( ) == 2 ) {
				fighter.decreaseHP ( 1 );
			}
		}
		System.out.println ("inserted!" );
		fighter.setLocation ( x , y );
	}

	public void attack ( Fighter fighter , Fighter opponent ) {
		int targetX = opponent.getX ( );
		int targetY = opponent.getY ( );
		int baseX = fighter.getX ( );
		int baseY = fighter.getY ( );
		if ( ! isValidDistanceForAttack ( baseX , baseY , targetX , targetY , fighter ) ) {
			System.out.println ( "distance is not valid" );
			return;
		}
		if ( ! fighter.CanAttack ( ) ) {
			System.out.println ( "this fighter cant attack now" );
			return;
		}
		fighter.disableCanAttack ( );
		fighter.disableCanMove ( );
		int cellHolyFighter = 0;
		int cellHolyOpponent = 0;
		for ( Buff buff : ground.getCell ( fighter.getX ( ) , fighter.getY ( ) ).cellEffect ) {
			if ( buff.getCellEffectType ( ) == 1 ) {
				cellHolyFighter = 1;
			}
		}
		for ( Buff buff : ground.getCell ( opponent.getX ( ) , opponent.getY ( ) ).cellEffect ) {
			if ( buff.getCellEffectType ( ) == 1 ) {
				cellHolyOpponent = 1;
			}
		}
		opponent.decreaseHP ( fighter.getAP ( ) - opponent.getHolyDefence ( ) - cellHolyFighter );
		if ( opponent.isCanCounterAttack ( ) ) {
			fighter.decreaseHP ( opponent.getAP ( ) - fighter.getHolyDefence ( ) - cellHolyOpponent );
		}
		fighter.addAttackedFighter ( opponent );
		executeOnAttackAndDeBuff ( fighter , opponent );
		isDeath ( opponent );
		isDeath ( fighter );
	}

	private void executeOnAttackAndDeBuff ( Fighter offender , Fighter defender ) {
		if ( offender.getSpecialPowerType ( ) == 3 ) {
			Fighter target = null;
			if ( offender.getSpecialPowerTarget ( ).getTargetType ( ) == 7 ) {
				target = defender;
			} else if ( offender.getSpecialPowerTarget ( ).getTargetType ( ) == 8 )
				target = offender;
			for ( Buff buff : offender.getSpecialPowers ( ) ) {
				if ( buff.getPlusDamageToAttacked ( ) > 0 ) {
					defender.decreaseHP ( buff.getPlusDamageToAttacked ( ) * offender.howManyAttacked ( defender ) );
				}
				if ( buff.noHolynessForOpponent ( ) ) {
					defender.decreaseHP ( defender.getHolyDefence ( ) );
				}
				target.addToBuff ( buff );
				if ( buff.isExeptABuff ( ) ) {
					buffs.add ( buff );
				}
			}
		}
		if ( defender.getSpecialPowerType ( ) == 4 ) {
			Fighter target = null;
			if ( defender.getSpecialPowerTarget ( ).getTargetType ( ) == 7 ) {
				target = offender;
			} else if ( defender.getSpecialPowerTarget ( ).getTargetType ( ) == 7 ) {
				target = defender;
			}
			for ( Buff buff : offender.getSpecialPowers ( ) ) {
				defender.addToBuff ( buff );
				if ( buff.isExeptABuff ( ) ) {
					buffs.add ( buff );
				}
			}
		}
	}

	private void useSpecialPowerHero ( int x , int y ) {
		Fighter hero;
		if ( playerInTurn == player1 )
			hero = heroP1;
		else hero = heroP2;
		if ( hero.getSpecialPowerType ( ) != 8 ) {
			return;
		}
		if ( hero.getSpecialPowerCoolDown ( ) < ( ( MinionAndHero ) hero ).getSpecialPowerCoolDown ( ) ) {
			System.out.println ( "you cant use it now because of cool down" );
			return;
		}
		if ( ! hero.getSpecialPowerTarget ( ).isValidTarget ( this , x , y , playerInTurn ) ) {
			System.out.println ( "Target is not valid!" );
			return;
		}
		if ( playerInTurn.getMana ( ) >= hero.getSpecialPowerMana ( ) ) {
			playerInTurn.decreaseMana ( hero.getSpecialPowerMana ( ) );
			hero.resetSpecialPowerCoolDown ( );
			if ( hero.getSpecialPowerTarget ( ).getTargetType ( ) == 8 ) {
				for ( Buff buff : hero.getSpecialPowers ( ) ) {
					hero.addToBuff ( buff );
					if ( buff.isExeptABuff ( ) ) {
						buffs.add ( buff );
					}
				}
			} else {
				ArrayList<Fighter> targets = hero.getSpecialPowerTarget ( ).targetFighters ( this , x , y , playerInTurn );
				for ( Fighter fighter : targets ) {
					for ( Buff buff : hero.getSpecialPowers ( ) ) {
						fighter.addToBuff ( buff );
						if ( buff.isExeptABuff ( ) ) {
							buffs.add ( buff );
						}
					}
				}
			}
		} else {
			System.out.println ( "You don't have enough mana!" );
			return;
		}

	}

	private void executeOnSpawnBuff ( Fighter fighter ) {
		if ( fighter.getSpecialPowerType ( ) != 0 )
			return;
		executeSpecialBuffs ( fighter );
	}

	private void executeOnDeathBuff ( Fighter fighter ) {
		if ( fighter.getSpecialPowerType ( ) != 2 )
			return;
		executeSpecialBuffs ( fighter );
	}

	private void executeSpecialBuffs ( Fighter fighter ) {
		for ( Buff buff : fighter.getSpecialPowers ( ) ) {
			ArrayList<Fighter> targets = fighter.getSpecialPowerTarget ( ).
					targetFighters ( this , fighter.getX ( ) , fighter.getY ( ) , playerInTurn );
			buff ( buff , targets , fighter.getSpecialPowerTarget ( ) , fighter.getX ( ) , fighter.getY ( ) );
		}
	}

	private boolean isDeath ( Fighter fighter ) {
		if ( fighter.getHP ( ) < 1 ) {
			ground.getCell ( fighter.getX ( ) , fighter.getY ( ) ).moveFromCell ( );
			fighter.getPlayer ( ).addToGraveYard ( fighter );
			executeOnDeathBuff ( fighter );
			if ( battleType == 0 ) {
				if ( fighter.isHero ( ) ) {
					if ( fighter.getPlayer ( ) == player1 )
						winner ( player2 );
					winner ( player1 );
					return true;
				}
			}
			if ( fighter.isHaveLastFlag ( ) ) {
				fighter.setHaveLastFlag ( false );
				fighter.getPlayer ().setHaveLastFlag ( false );
				ground.getCell ( fighter.getX ( ) , fighter.getY ( ) ).setItemOnCell ( lastFlag );
			}
			System.out.printf ("%s is dead\n",fighter.getID () );
			return true;
		}
		return false;
	}

	private boolean isValidDistanceForAttack ( int baseX , int baseY , int tarX , int tarY , Fighter fighter ) {
		if ( fighter.getRange ( ) == 0 ) {
			if ( Ground.getDistance ( tarX , tarY , baseX , baseY ) != 1 ) {
				return false;
			}
		}
		if ( fighter.getRange ( ) == 1 ) {
			if ( Ground.getDistance ( tarX , tarY , baseX , baseY ) < 2 || Ground.getDistance ( tarX , tarY , baseX , baseY ) > fighter.getRange ( ) ) {
				return false;
			}
		}
		if ( fighter.getRange ( ) == 2 ) {
			return Ground.getDistance ( tarX , tarY , baseX , baseY ) <= fighter.getRange ( );
		}
		return true;
	}


	private void buff ( Buff buff , ArrayList<Fighter> fighters , Target target , int x , int y ) {
		if ( buff.getIsCellBuff ( ) ) {
			for ( Cell cell : target.targetCells ( this , x , y ) ) {
				cell.addCellEffect ( buff );
				buff.addToCell ( cell );
			}
			ground.getCell ( x , y ).addCellEffect ( buff.getCellBuff ( ) );

		} else {
			buff.setFighters ( fighters );
			for ( Fighter fighter : fighters ) {
				fighter.addToBuff ( buff );
			}
		}
	}

	private boolean isValidMove ( int x1 , int y1 , int x2 , int y2 ) {
		if ( Ground.getDistance ( x1 , y1 , x2 , y2 ) > 2 )
			return false;
		if ( ground.getCell ( x2 , y2 ).getCardOnCell ( ) != null ) {
			System.out.println ( "This cell is occupied!" );
			return false;
		}
		if ( Ground.getDistance ( x1 , y1 , x2 , y2 ) == 2 ) {
			if ( x1 == x2 ) {
				if ( y1 > y2 ) {
					if ( ground.getCell ( x1 , y1 - 1 ).getCardOnCell ( ) != null || ground.getCell ( x1 , y1 - 2 ).getCardOnCell ( ) != null ) {
						System.out.println ( "This cell is occupied!" );
						return false;
					}
				}
				if ( y2 > y1 ) {
					if ( ground.getCell ( x1 , y1 + 1 ).getCardOnCell ( ) != null || ground.getCell ( x1 , y1 + 2 ).getCardOnCell ( ) != null ) {
						System.out.println ( "This cell is occupied!" );
						return false;
					}
				}
			} else if ( y1 == y2 ) {
				if ( x1 > x2 ) {
					if ( ground.getCell ( x1 - 1 , y1 ).getCardOnCell ( ) != null || ground.getCell ( x1 - 2 , y1 ).getCardOnCell ( ) != null ) {
						System.out.println ( "This cell is occupied!" );
						return false;
					}
				}
				if ( x2 > x1 ) {
					if ( ground.getCell ( x1 + 1 , y1 ).getCardOnCell ( ) != null || ground.getCell ( x1 + 2 , y1 ).getCardOnCell ( ) != null ) {
						System.out.println ( "This cell is occupied!" );
						return false;
					}
				}
			}
		}
		return true;
	}

	public void checkWinner () {
		if ( battleType == 2 ) {
			if ( player1.getFlagInHand ( ) >= 6 )
				winner ( player1 );
			if ( player2.getFlagInHand ( ) >= 6 )
				winner ( player2 );
		}
		if ( battleType == 1 ) {
			int victoryNumber;
			if ( numberOfFlags%2==0 ){
				victoryNumber = numberOfFlags/2+1;
			}
			else {
				victoryNumber = (numberOfFlags-1)/2+1;
			}
			if ( player1.getFlagInHand ( ) >= victoryNumber )
				winner ( player1 );
			if ( player2.getFlagInHand ( ) >= victoryNumber )
				winner ( player2 );
		}
	}

	public void setMana () {
		if ( currentTurn % 2 == 0 ) {
			player2.setMana ( currentTurn / 2 + 2 );
		} else {
			player1.setMana ( ( currentTurn - 1 ) / 2 + 2 );
		}
	}

	public Battle ( Account account , Account account2 , int battleType , int numberOfFlags ) {
		if ( account2 == null ) {
			System.out.println ( "second player dont selected" );
			return;
		}
		player1 = new Player ( account );
		player2 = new Player ( account2 );
		if ( ! player1.getHand ( ).getDeck ( ).isValidDeck ( ) || ! player2.getHand ( ).getDeck ( ).isValidDeck ( ) ) {
			return;
		}
		ground = new Ground ( );
		for ( Card card : player1.getHand ( ).getDeck ( ).getCards ( ) ) {
			if ( card.getCardType ( ) == 1 ) {
				MinionAndHero minionAndHero = ( MinionAndHero ) card;
				if ( minionAndHero.isHero ( ) ) {
					heroP1 = new Fighter ( minionAndHero , minionAndHeroes , player1 );
					player1.addFighter ( heroP1 );
					fighters.add ( heroP1 );
					ground.getCell ( 2 , 0 ).moveInCell ( heroP1 );
					heroP1.setLocation ( 2 , 0 );
				}
			}
		}
		for ( Card card : player2.getHand ( ).getDeck ( ).getCards ( ) ) {
			if ( card.getCardType ( ) == 1 ) {
				MinionAndHero minionAndHero = ( MinionAndHero ) card;
				if ( minionAndHero.isHero ( ) ) {
					heroP2 = new Fighter ( minionAndHero , minionAndHeroes , player2 );
					player2.addFighter ( heroP2 );
					fighters.add ( heroP2);
					heroP2.setLocation ( 2 , 8 );
					ground.getCell ( 2 , 8 ).moveInCell ( heroP2 );
				}
			}
		}
		Battle.currentBattle = this;
		currentTurn = 0;
		if ( battleType == 2 ) {
			lastFlag = Item.getLastFlag ( );
			ground.getCell ( random.nextInt ( 5 ) , random.nextInt ( 9 ) ).setItemOnCell ( lastFlag );
		}
		if ( battleType == 1 ) {
			this.numberOfFlags = numberOfFlags;
			for ( int i = 0 ; i < numberOfFlags ; i++ ) {
				boolean placed = false;
				while ( ! placed ) {
					int x = random.nextInt ( 5 );
					int y = random.nextInt ( 9 );
					if ( ground.getCell ( x , y ).getItemOnCell ( ) == null&&ground.getCell ( x , y ).getCardOnCell ()==null ) {
						ground.getCell ( x , y ).setItemOnCell ( Item.getNormalFlag ( ) );
						placed = true;
					}
				}
			}
		}
		this.battleType = battleType;
		nextTurn ( );
		System.out.println ( "started!" );
	}

	private void move ( int targetX , int targetY , Fighter fighter ) {
		int x = fighter.getX ( );
		int y = fighter.getY ( );
		if ( ! isValidMove ( x , y , targetX , targetY ) ) {
			System.out.println ( "Your move isn't valid!" );
			return;
		}
		if ( ! isValidTargetForMove ( targetX , targetY ) ) {
			System.out.println ( "Your target for moving isn't valid!" );
			return;
		}
		if ( fighter.isCanMove ( ) ) {
			System.out.println ( "Moved!" );
		} else {
			System.out.println ("this fighter cant move now" );
			return;
		}
		newPlaceItem ( targetX , targetY , fighter );
		ground.getCell ( targetX , targetY ).moveInCell ( fighter );
		fighter.setLocation ( targetX , targetY );
		if ( ground.getCell ( x , y ).getItemOnCell ( ) != null ) {
			playerInTurn.addItem ( ground.getCell ( x , y ).getItemOnCell ( ) );
		}
		ground.getCell ( x , y ).moveFromCell ( );
		for ( Buff buff : ground.getCell ( targetX , targetY ).getCellEffect ( ) ) {
			if ( buff.getCellEffectType ( ) == 2 ) {
				fighter.decreaseHP ( 1 );
			}
		}
	}

	private void newPlaceItem ( int x , int y , Fighter fighter ) {
		if ( ground.getCell ( x , y ).getItemOnCell ( ) != null ) {
			Item item = ground.getCell ( x , y ).getItemOnCell ( );
			if ( item.isFlag ( ) ) {
				playerInTurn.increaseFlagInHand ( );
				System.out.println ("flag collected!" );
				ground.getCell ( x , y ).removeItem ();
			} else if ( item.isLastFlag ( ) ) {
				playerInTurn.setHaveLastFlag ( true );
				System.out.println ("flag collected!" );
				ground.getCell ( x , y ).removeItem ();
				fighter.setHaveLastFlag ( true );
			} else if ( item.isCollectible ( ) ) {
				playerInTurn.addItem ( item );
				System.out.printf ("item %s collected!\n",item.getName () );
				ground.getCell ( x , y ).removeItem ();
			}
		}
	}

	private boolean isValidTargetForMove ( int x , int y ) {
		//if (ground.getCell ( x, y ).getCardOnCell () == null) {
		return true;
		//}
		//return false;
	}

	private void checkBuffs () {
		for ( Buff buff : buffs ) {
			if ( buff.getAgeType ( ) == 1 ) {
				if ( buff.getAge ( ) < 1 ) {
					removeBuff ( buff );
				} else buff.decreesAge ( );
			}
			if ( buff.getAgeType ( ) == 3 ) {
				if ( buff.getAge ( ) < 1 ) {
					for ( Fighter fighter : buff.getFighters ( ) ) {
						fighter.addToBuff ( buff );
					}
				}
			}
		}
	}

	private void removeBuff ( Buff buff ) {
		if ( buff.getIsCellBuff ( ) ) {
			for ( Cell cell : buff.getCells ( ) )
				cell.removeCellEffect ( buff );
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
		Cell[][] cells = getGround ( ).getCells ( );
		for ( int i = 0 ; i < 5 ; i++ ) {
			for ( int j = 0 ; j < 9 ; j++ ) {
				if ( cells[ i ][ j ].getCellEffect ( ).size ( ) != 0 ) {
					Fighter fighter = ( Fighter ) cells[ i ][ j ].getCardOnCell ( );
					if ( fighter == null ) {
						break;
					}
					for ( Buff buff : cells[ i ][ j ].getCellEffect ( ) ) {
						if ( buff.getCellEffectType ( ) == 0 ) {
							fighter.decreaseHP ( 2 );
							isDeath ( fighter );
						}
					}
				}
			}
		}
		if ( battleType == 2 ) {
			if ( player1.isHaveLastFlag ( ) ) {
				player1.increaseFlagInHand ( );
			}
			if ( player2.isHaveLastFlag ( ) ) {
				player2.increaseFlagInHand ( );
			}
		}
		player1.preTurnProcess ( );
		player2.preTurnProcess ( );
		checkBuffs ( );
		currentTurn++;
		setMana ( );
		setPlayerInTurn ( );
		checkWinner ( );
		selectedFighter = null;
		selectedItem = null;
		if (random.nextInt ( 1 ) == 0){
			insertCollectibleItem ();
		}
		System.out.printf ("start turn %d player : %s\n",currentTurn,playerInTurn.getUserName ());
	}
	private void insertCollectibleItem(){
		int size = collectibleItems.size ();
		Item item = collectibleItems.get (random.nextInt ( size ));
		Cell cell = ground.getCell ( random.nextInt ( 5 ),random.nextInt ( 9 ) );
		if ( cell.getCardOnCell ()==null&&cell.getItemOnCell ()==null ){
			cell.setItemOnCell ( item );
		}
	}
	public void showInfo () {
		if ( battleType == 0 ) {
			System.out.printf ( "%s hp: %d\n" , heroP1.getID ( ) , heroP1.getHP ( ) );
			System.out.printf ( "%s hp: %d\n" , heroP2.getID ( ) , heroP2.getHP ( ) );
		} else if ( battleType == 2 ) {
			int x = 0;
			int y = 0;
			if ( player1.isHaveLastFlag ( ) ) {
				for ( Fighter fighter : player1.getFighters ( ) ) {
					if ( fighter.isHaveLastFlag ( ) ) {
						System.out.printf ( "%s have last flag\n" , player1.getUserName ( ) );
						x = fighter.getX ( );
						y = fighter.getY ( );
					}
				}
			}
			else if ( player2.isHaveLastFlag ( ) ) {
				for ( Fighter fighter : player2.getFighters ( ) ) {
					if ( fighter.isHaveLastFlag ( ) ) {
						System.out.printf ( "%s have last flag  \n" , player2.getUserName ( ) );
						x = fighter.getX ( );
						y = fighter.getY ( );
					}
				}
			}
			else for ( int i = 0 ; i < 5 ; i++ ) {
				for ( int j = 0 ; j < 8 ; j++ ) {
					if ( ground.getCell ( i , j ).getItemOnCell ( ) == lastFlag ) {
						x = i;
						y = j;
						break;
					}

				}
			}
			System.out.printf ("last flag location : x: %d  y: %d\n",x,y );
		}
		else if ( battleType==1 ){
			System.out.printf ("%s collected %d flags\n",player1.getUserName (),player1.getFlagInHand () );
			System.out.printf ("%s collected %d flags\n",player2.getUserName (),player2.getFlagInHand () );
			for ( int i = 0 ; i <5  ; i++ ) {
				for ( int j = 0 ; j <9  ; j++ ) {
					if (ground.getCell ( i,j ).getItemOnCell ()==Item.getNormalFlag ()){
						System.out.printf ( "there is a flag in (%d,%d)\n",i,j);
					}
				}
			}
		}
	}
	private void winner ( Player player ) {
		player.getAccount ( ).increaseWins ( );
		player.getAccount ( ).addMoney ( gift );
		if ( player == player1 )
			player2.getAccount ( ).increaseLosses ( );
		else player1.getAccount ( ).increaseLosses ( );
		System.out.printf ( "%s wins\n",player.getUserName () );
		currentBattle = null;
		Controller.input ();
	}

	private void setPlayerInTurn () {
		if ( playerInTurn == ( player1 ) )
			playerInTurn = player2;
		else
			playerInTurn = player1;
	}
}
