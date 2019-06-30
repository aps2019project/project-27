package Moudle;

import java.util.ArrayList;

public class Target {
	private static ArrayList<Target> createTargets = new ArrayList<> ( );
	private String name;
	//targetType 0:rectangle	1:1force	2:all forces	3:force in a row	4:force in a column		5:8cell in near		6:a random Fighter
	//7:attacked Fighter	8:herself	9:onPlayer
	private int targetType;
	private int rectangleLength;
	//targetFriendType:0:no diffrence	1:only friend	2:only enemy
	private int targetFriendType;
	//targetDegreeType:0:no diffrence	1:minion	2:hero
	private int targetDegreeType;
	//0:no defrence	1:melee	3:R&H
	private int targetAttackModeType;

	public static ArrayList<Target> getCreateTargets () {
		return createTargets;
	}

	public Target (
			String name ,
			String type ,
			String length ,
			String friendType ,
			String degree ) {
		this.name = name;
		this.targetType = Integer.parseInt ( type );
		this.rectangleLength = Integer.parseInt ( length );
		this.targetFriendType = Integer.parseInt ( friendType );
		this.targetDegreeType = Integer.parseInt ( degree );
		createTargets.add ( this );
	}
	public static Target findCreated(String name){
		for ( Target target:createTargets ){
			if ( target.name.equals ( name ) )
				return target;
		}
		return null;
	}
	public int getTargetType () {
		return targetType;
	}

	public boolean isValidTarget ( Battle battle , int x , int y , Player player ) {
		if ( targetType == 0 ) {
			if ( isOutOfSide ( x , y ) || isOutOfSide ( x + rectangleLength - 1 , y + rectangleLength - 1 ) )
				return false;
			return true;
		}
		if ( targetType == 1 ) {
			if ( battle.getGround ( ).getCell ( x , y ).getCardOnCell ( ) == null ) {
				return false;
			}
			Fighter fighter = ( Fighter ) battle.getGround ( ).getCell ( x , y ).getCardOnCell ( );
			return checkFriendAndDegreeType ( fighter , player );
		}
		if ( targetType == 2 || targetType == 4 || targetType == 3 || targetType == 5 )
			return true;
		return false;
	}

	public ArrayList<Cell> targetCells ( Battle battle , int x , int y ) {
		ArrayList<Cell> cells = new ArrayList<> ( );
		Ground ground = battle.getGround ( );
		if ( targetType == 0 ) {
			for ( int i = 0 ; i < rectangleLength ; i++ ) {
				for ( int j = 0 ; j < rectangleLength ; j++ ) {
					if ( ground.getCell ( x + i , y + j ).getCardOnCell ( ) != null ) {
						cells.add ( ground.getCell ( x + i , y + j ) );
					}
				}
			}
		}
		return cells;
	}

	public ArrayList<Fighter> targetFighters ( Battle battle , int x , int y , Player player ) {
		ArrayList<Fighter> targets = new ArrayList<> ( );
		Ground ground = battle.getGround ( );
		if ( targetType == 0 ) {
			for ( int i = 0 ; i < rectangleLength ; i++ ) {
				for ( int j = 0 ; j < rectangleLength ; j++ ) {
					if ( ground.getCell ( x + i , y + j ).getCardOnCell ( ) != null ) {
						targets.add ( ( Fighter ) ground.getCell ( x + i , y + j ).getCardOnCell ( ) );
					}
				}
			}
		}
		if ( targetType == 1 ) {
			targets.add ( ( Fighter ) ground.getCell ( x , y ).getCardOnCell ( ) );
		}
		if ( targetType == 2 ) {
			for ( int i = 0 ; i < 5 ; i++ ) {
				for ( int j = 0 ; j < 9 ; j++ ) {
					Fighter fighter = ( Fighter ) ground.getCell ( i , j ).getCardOnCell ( );
					if ( fighter == null ) {
						break;
					}
					if ( checkFriendAndDegreeType ( fighter , player ) )
						targets.add ( fighter );
				}
			}
		}
		if ( targetType == 3 ) {
			for ( int i = 0 ; i < 5 ; i++ ) {
				Fighter fighter = ( Fighter ) ground.getCell ( i , y ).getCardOnCell ( );
				if ( fighter == null ) {
					break;
				}
				if ( checkFriendAndDegreeType ( fighter , player ) )
					targets.add ( fighter );
			}
		}
		if ( targetType == 4 ) {
			for ( int j = 0 ; j < 9 ; j++ ) {
				Fighter fighter = ( Fighter ) ground.getCell ( x , j ).getCardOnCell ( );
				if ( fighter == null ) {
					break;
				}
				if ( checkFriendAndDegreeType ( fighter , player ) )
					targets.add ( fighter );
			}
		}
		if ( targetType == 5 ) {
			for ( int i = - 1 ; i < 2 ; i++ ) {
				for ( int j = - 1 ; j < 2 ; j++ ) {
					if ( isOutOfSide ( x + i , y + j ) )
						break;
					if ( i == 0 && j == 0 )
						break;
					Fighter fighter = ( Fighter ) ground.getCell ( x + i , y + j ).getCardOnCell ( );
					if ( fighter == null ) {
						break;
					}
					if ( checkFriendAndDegreeType ( fighter , player ) ) {
						targets.add ( fighter );
					}
				}
			}
		}
		if ( targetType == 6 ) {
			int i;
			int j;
			if ( Math.random ( ) % 2 == 0 ) {
				i = 1;
			} else i = - 1;
			if ( Math.random ( ) % 2 == 0 ) {
				j = 1;
			} else j = - 1;
			targets.add ( ( Fighter ) ground.getCell ( x + i , y + j ).getCardOnCell ( ) );
		}
		return targets;
	}

	private boolean isOutOfSide ( int x , int y ) {
		if ( x > 4 || x < 0 )
			return true;
		if ( y > 8 || y < 0 )
			return true;
		return false;
	}

	private boolean checkFriendAndDegreeType ( Fighter fighter , Player player ) {
		if ( targetDegreeType != 0 ) {
			if ( targetDegreeType == 1 ) {
				if ( fighter.isHero ( ) )
					return false;
			}
			if ( targetDegreeType == 2 ) {
				if ( ! fighter.isHero ( ) )
					return false;
			}
		}
		if ( targetFriendType != 0 ) {
			if ( targetFriendType == 1 ) {
				if ( fighter.getPlayer ( ) != player )
					return false;
			}
			if ( targetFriendType == 2 ) {
				if ( fighter.getPlayer ( ) == player )
					return false;
			}
		}
		return true;
	}
}
