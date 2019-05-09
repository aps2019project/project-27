package Moudle;

import Controller.ControlBox;
import View.View;

import java.util.ArrayList;

public class Battle {
    private static Battle currentBattle;
    private Fighter selectedFighter;
    private static Account secoundAccoun;
    private int gift = 1000;
    private ArrayList<MinionAndHero> minionAndHeroes = new ArrayList<>();
    private Ground ground;
    private int currentTurn;
    private Player player1;
    private Player player2;
    private Player playerInTurn;
    private ArrayList<Fighter> fighters = new ArrayList<>();
    private Card selectedCard;
    private Item selectedItem;
    //0:hero    1:lastflag  2:flags
    private int battleType;
    private int numberOfFlags;
    private Item lastFlag;
    private Fighter heroP1;
    private Fighter heroP2;
    private ArrayList<Buff> buffs = new ArrayList<>();

    public Ground getGround() {
        return ground;
    }

    public static void input(ControlBox controllBox) {
        String in = controllBox.getType();
        if (in.equals("game info")) {
            currentBattle.showInfo();
            return;
        }
        if (in.equals("help")) {
            help();
        }
        if (in.equalsIgnoreCase("select user")) {
            Account account = Account.findAccount(controllBox.getUserName());
            if (account == null) {
                System.out.println("there is no account with this user name");

            } else {
                System.out.println("selected!");
                secoundAccoun = account;
            }

        }
        if (in.equalsIgnoreCase("new mp")) {
            currentBattle = new Battle(Account.getMainAccount(), secoundAccoun, controllBox.getBattleType(), controllBox.getNumberOfFlags());
            System.out.println("started!");
        }
        if (in.equals("show item")) {
            currentBattle.showItem(controllBox.getCardName());
        }
        if (in.equals("use item")) {
            Item item = Item.findItem(controllBox.getCardName(), currentBattle.playerInTurn.getCollectedItems());
            if (item == null) {
                System.out.println("you havent this item");
                return;
            }
            currentBattle.useItem(item, controllBox.getX(), controllBox.getY());
        }
        if (in.equals("show items")) {
            currentBattle.playerInTurn.showCollectables();
        }
        if (in.equals("show my minions")) {
            currentBattle.playerInTurn.showFighters();
        }
        if (in.equals("show my opponent minions")) {
            currentBattle.offTurn().showFighters();
        }
        if (in.equals("show card info")) {
            currentBattle.showCardInfo(controllBox.getCardID());
        }
        if (in.equals("select card")) {
            currentBattle.setInGroundCard(controllBox.getCardID());
        }
        if (in.equals("end turn")) {
            currentBattle.nextTurn();
        }
        if (in.equals("use special power")) {
            //todo
            currentBattle.useSpecialPowerHero(controllBox.getX(), controllBox.getY());
        }
        if (in.equals("show hand")) {
            currentBattle.playerInTurn.showHand();

        }
        if (in.equals("insert")) {
            currentBattle.insert(controllBox.getCardName(), controllBox.getX(), controllBox.getY());
        }
        if (in.equals("move")) {
            currentBattle.move(currentBattle.selectedFighter.getX(), currentBattle.selectedFighter.getY()
                    , controllBox.getX(), controllBox.getY());
        }
        if (in.equals("attack")) {
            Fighter opponent = currentBattle.findFighter(controllBox.getCardID(), currentBattle.offTurn());
            if (opponent == null) {
                System.out.println("There is no opponent to attack!");
                return;
            }
            currentBattle.attack(currentBattle.selectedFighter, opponent);
        }
    }

    public static void help(){
        System.out.printf("Game info\nShow my minions\nShow opponent minions\nShow card info [card id]\nSelect [card id]\nMove to ([x],[y])\n" +
                "Attack [opponent card id]\nAttack combo [opponent card id][my card id][my card id][...]\nUse special power (x,y)\n" +
                "Show hand\nInsert [card name] in (x,y)\nEnd turn\nShow collectables\nSelect [collectable id]\nShow info\nUse [location x,y]\n" +
                "Show Next Card\nEnter graveyard\nShow info [card id]\nShow cards\nHelp\nExit\nShow menu");
    }

    private void showItem(String name) {
        Item item = Item.findItem(name, playerInTurn.getCollectedItems());
        if (item == null) {
            System.out.println("you havent this item");
        } else {
            item.showItem();
        }
    }

    private void setInGroundCard(String cardID) {
        Fighter fighter = findFighter(cardID, playerInTurn);
        if (fighter == null) {
            System.out.println("You don't have this fighter!");
        } else {
            selectedFighter = fighter;
            //accept;
        }
    }

    private void showHand() {
        playerInTurn.showHand();
    }

    private Fighter findFighter(String cardID) {
        for (Fighter fighter : fighters) {
            if (fighter.getID().equals(cardID)) {
                return fighter;
            }
        }
        return null;
    }

    private Fighter findFighter(String cardID, Player player) {
        for (Fighter fighter : player.getFighters()) {
            if (fighter.getID().equals(cardID))
                return fighter;
        }
        return null;
    }

    private void showCardInfo(String cardID) {
        Fighter fighter = findFighter(cardID);
        if (fighter == null) {
            System.out.println("You don't have this fighter!");
            return;
        }
        View.showMinion(fighter);
    }

    private Player offTurn() {
        if (player1 == playerInTurn)
            return player2;
        return player1;
    }

    private boolean isValidInsert(Card card) {
        if (card.getManaPrice() <= playerInTurn.getMana()) {
            playerInTurn.decreaseMana(card.getManaPrice());
            return true;
        }
        return false;
    }

    private void useItem(Item item, int x, int y) {
        if (!item.getTarget().isValidTarget(currentBattle, x, y, playerInTurn)) {
            System.out.println("invalid target");
            return;
        }
        ArrayList<Fighter> fighters = item.getTarget().targetFighters(currentBattle, x, y, playerInTurn);
        for (Buff buff : item.getBuffs()) {
            buff(buff, fighters, item.getTarget(), x, y);
            if (buff.isExeptABuff())
                buffs.add(buff);
        }

    }

    public void insert(String cardName, int x, int y) {
        Card card = Card.findCard(cardName, playerInTurn.getHand().getCards());
        if (card == null) {
            System.out.println("You don't have this card!");
            return;
        }
        if (isValidInsert(card)) {
            System.out.println("Inserted");
            return;
        }
        int type = card.getCardType();
        if (type == 0) {
            Spell spell = (Spell) card;
            if (!spell.getTarget().isValidTarget(this, x, y, playerInTurn)) {
                System.out.println("invalid target");
                return;
            }
            ArrayList<Fighter> fighters = spell.getTarget().targetFighters(this, x, y, playerInTurn);
            for (Buff buff : spell.getBuffs()) {
                buff(buff, fighters, spell.getTarget(), x, y);
                if (buff.isExeptABuff())
                    buffs.add(buff);
            }
        }
        if (type == 1) {
            MinionAndHero minionAndHero = (MinionAndHero) card;
            if (!isValidNewFighter(minionAndHero, playerInTurn, x, y)) {
                System.out.println("This fighter isn't valid!");
                return;
            }
            if (ground.getCell(x, y).getCardOnCell() != null) {
                System.out.println("There is already a card there!");
                return;
            }
            if (ground.getCell(x, y).getItemOnCell() != null) {
                playerInTurn.addItem(ground.getCell(x, y).getItemOnCell());
            }
            minionAndHeroes.add(minionAndHero);
            Fighter fighter = new Fighter(minionAndHero, minionAndHeroes, playerInTurn);
            fighters.add(fighter);
            executeOnSpawnBuff(fighter);
            newPlaceItem(x, y, fighter);
            ground.getCell(x, y).moveInCell(fighter);
            for (Buff buff : ground.getCell(x, y).getCellEffect()) {
                if (buff.getCellEffectType() == 2) {
                    fighter.decreaseHP(1);
                }
            }
            fighter.setLocation(x, y);
        }
    }

    public void attack(Fighter fighter, Fighter opponent) {
        int targetX = opponent.getX();
        int targetY = opponent.getY();
        int baseX = fighter.getX();
        int baseY = fighter.getY();
        if (!isValidDistanceForAttack(baseX, baseY, targetX, targetY, fighter)) {
            System.out.println("distance is not valid");
            return;
        }
        if (!fighter.CanAttack()) {
            System.out.println("this fighter cant attack now");
            return;
        }
        fighter.disableCanAttack();
        fighter.disableCanMove();
        int cellHolyFighter = 0;
        int cellHolyOpponent = 0;
        for (Buff buff : ground.getCell(fighter.getX(), fighter.getY()).cellEffect) {
            if (buff.getCellEffectType() == 1) {
                cellHolyFighter = 1;
            }
        }
        for (Buff buff : ground.getCell(opponent.getX(), opponent.getY()).cellEffect) {
            if (buff.getCellEffectType() == 1) {
                cellHolyOpponent = 1;
            }
        }
        opponent.decreaseHP(fighter.getAP() - opponent.getHolyDefence() - cellHolyFighter);
        if (opponent.isCanCounterAttack()) {
            fighter.decreaseHP(opponent.getAP() - fighter.getHolyDefence() - cellHolyOpponent);
        }
        fighter.addAttackedFighter(opponent);
        executeOnAttackAndDeBuff(fighter, opponent);
        isDeath(opponent);
        isDeath(fighter);
    }

    private void executeOnAttackAndDeBuff(Fighter offenser, Fighter defender) {
        if (offenser.getSpecialPowerType() == 3) {
            Fighter target = null;
            if (offenser.getSpecialPowerTarget().getTargetType() == 7) {
                target = defender;
            } else if (offenser.getSpecialPowerTarget().getTargetType() == 8)
                target = offenser;
            for (Buff buff : offenser.getSpecialPowers()) {
                if (buff.getPlusDamageToAttacked() > 0) {
                    defender.decreaseHP(buff.getPlusDamageToAttacked() * offenser.howManyAttacked(defender));
                }
                if (buff.noHolynessForOpponent()) {
                    defender.decreaseHP(defender.getHolyDefence());
                }
                target.addToBuff(buff);
                if (buff.isExeptABuff()) {
                    buffs.add(buff);
                }
            }
        }
        if (defender.getSpecialPowerType() == 4) {
            Fighter target = null;
            if (defender.getSpecialPowerTarget().getTargetType() == 7) {
                target = offenser;
            } else if (defender.getSpecialPowerTarget().getTargetType() == 7) {
                target = defender;
            }
            for (Buff buff : offenser.getSpecialPowers()) {
                defender.addToBuff(buff);
                if (buff.isExeptABuff()) {
                    buffs.add(buff);
                }
            }
        }
    }

    private void useSpecialPowerHero(int x, int y) {
        Fighter hero;
        if (playerInTurn == player1)
            hero = heroP1;
        else hero = heroP2;
        if (hero.getSpecialPowerType() != 8) {
            return;
        }
        if (hero.getSpecialPowerCoolDown() < ((MinionAndHero) hero).getSpecialPowerCoolDown()) {
			System.out.println ("you cant use it now because of cool down" );
            return;
        }
        if (!hero.getSpecialPowerTarget().isValidTarget(this, x, y, playerInTurn)) {
            System.out.println("Target is not valid!");
            return;
        }
        if (playerInTurn.getMana() >= hero.getSpecialPowerMana()) {
            playerInTurn.decreaseMana(hero.getSpecialPowerMana());
            hero.resetSpecialPowerCoolDown();
            if (hero.getSpecialPowerTarget().getTargetType() == 8) {
                for (Buff buff : hero.getSpecialPowers()) {
                    hero.addToBuff(buff);
                    if (buff.isExeptABuff()) {
                        buffs.add(buff);
                    }
                }
            } else {
                ArrayList<Fighter> targets = hero.getSpecialPowerTarget().targetFighters(this, x, y, playerInTurn);
                for (Fighter fighter : targets) {
                    for (Buff buff : hero.getSpecialPowers()) {
                        fighter.addToBuff(buff);
                        if (buff.isExeptABuff()) {
                            buffs.add(buff);
                        }
                    }
                }
            }
        } else {
            System.out.println("You don't have enough mana!");
            return;
        }

    }

    private void executeOnSpawnBuff(Fighter fighter) {
        if (fighter.getSpecialPowerType() != 0)
            return;
        executeSpecialBuffs(fighter);
    }

    private void executeOnDeathBuff(Fighter fighter) {
        if (fighter.getSpecialPowerType() != 2)
            return;
        executeSpecialBuffs(fighter);
    }

    private void executeSpecialBuffs(Fighter fighter) {
        for (Buff buff : fighter.getSpecialPowers()) {
            ArrayList<Fighter> targets = fighter.getSpecialPowerTarget().
                    targetFighters(this, fighter.getX(), fighter.getY(), playerInTurn);
            buff(buff, targets, fighter.getSpecialPowerTarget(), fighter.getX(), fighter.getY());
        }
    }

    private boolean isDeath(Fighter fighter) {
        if (fighter.getHP() < 1) {
            ground.getCell(fighter.getX(), fighter.getY()).moveFromCell();
            fighter.getPlayer().addToGraveYard(fighter);
            executeOnDeathBuff(fighter);
            if (battleType == 0) {
                if (fighter.isHero()) {
                    if (fighter.getPlayer() == player1)
                        winner(player2);
                    winner(player1);
                    return true;
                }
            }
            if (fighter.isHaveLastFlag()) {
                fighter.setHaveLastFlag(false);
                ground.getCell(fighter.getX(), fighter.getY()).setItemOnCell(lastFlag);
            }
            return true;
        }
        return false;
    }

    private boolean isValidDistanceForAttack(int baseX, int baseY, int tarX, int tarY, Fighter fighter) {
        if (fighter.getRange() == 0) {
            if (Ground.getDistance(tarX, tarY, baseX, baseY) != 1) {
                return false;
            }
        }
        if (fighter.getRange() == 1) {
            if (Ground.getDistance(tarX, tarY, baseX, baseY) < 2 || Ground.getDistance(tarX, tarY, baseX, baseY) > fighter.getRange()) {
                return false;
            }
        }
        if (fighter.getRange() == 2) {
            if (Ground.getDistance(tarX, tarY, baseX, baseY) > fighter.getRange()) {
                return false;
            }
        }
        return true;
    }

    private Fighter isValidTargetForAttack(int targetX, int targetY) {
        Fighter fighter;
        fighter = (Fighter) ground.getCell(targetX, targetY).getCardOnCell();
        if (fighter.getPlayer() == playerInTurn)
            return null;
        return fighter;
    }

    private boolean isValidNewFighter(MinionAndHero minionAndHero, Player player, int x, int y) {
        //todo
        return true;
    }

    private void buff(Buff buff, ArrayList<Fighter> fighters, Target target, int x, int y) {
        if (buff.getIsCellBuff()) {
            for (Cell cell : target.targetCells(this, x, y)) {
                cell.addCellEffect(buff);
                buff.addToCell(cell);
            }
            ground.getCell(x, y).addCellEffect(buff.getCellBuff());

        } else {
            buff.setFighters(fighters);
            for (Fighter fighter : fighters) {
                fighter.addToBuff(buff);
            }
        }
    }

    private boolean isValidMove(int x1, int y1, int x2, int y2) {
        if (Ground.getDistance(x1, y1, x2, y2) > 2)
            return false;
        if (ground.getCell(x2, y2).getCardOnCell() != null) {
            System.out.println("This cell is occupied!");
            return false;
        }
        if (Ground.getDistance(x1, y1, x2, y2) == 2) {
            if (x1 == x2) {
                if (y1 > y2) {
                    if (ground.getCell(x1, y1 - 1).getCardOnCell() != null || ground.getCell(x1, y1 - 2).getCardOnCell() != null) {
                        System.out.println("This cell is occupied!");
                        return false;
                    }
                }
                if (y2 > y1) {
                    if (ground.getCell(x1, y1 + 1).getCardOnCell() != null || ground.getCell(x1, y1 + 2).getCardOnCell() != null) {
                        System.out.println("This cell is occupied!");
                        return false;
                    }
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    if (ground.getCell(x1 - 1, y1).getCardOnCell() != null || ground.getCell(x1 - 2, y1).getCardOnCell() != null) {
                        System.out.println("This cell is occupied!");
                        return false;
                    }
                }
                if (x2 > x1) {
                    if (ground.getCell(x1 + 1, y1).getCardOnCell() != null || ground.getCell(x1 + 2, y1).getCardOnCell() != null) {
                        System.out.println("This cell is occupied!");
                        return false;
                    }
                }
            } else {
                // TODO: 5/5/2019
            }
        }
        return true;
    }

    private boolean isValidSelect() {
        /// TODO: 5/9/2019
        return true;
    }

    public void checkWinner() {
        if (battleType == 1) {
            if (player1.getFlagInHand() >= 6)
                winner(player1);
            if (player2.getFlagInHand() >= 6)
                winner(player2);
        }
        if (battleType == 2) {
            if (player1.getFlagInHand() >= numberOfFlags / 2)
                winner(player1);
            if (player2.getFlagInHand() >= numberOfFlags / 2)
                winner(player2);
        }
    }

    public void setMana() {
        if (currentTurn % 2 == 0) {
            player2.setMana(currentTurn / 2 + 2);
        } else {
            player1.setMana((currentTurn - 1) / 2 + 2);
        }
    }

    public Battle(Account account, Account account2, int battleType, int numberOfFlags) {
        player1 = new Player(account);
        player2 = new Player(account2);
        if (!player1.getHand().getDeck().isvalid() || player2.getHand().getDeck().isvalid()) {
            System.out.println("selected deck is not valid");
            return;
        }
        ground = new Ground();
        for (Card card : player1.getHand().getDeck().getCards()) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (minionAndHero.isHero()) {
                    heroP1 = new Fighter(minionAndHero, minionAndHeroes, player1);
                    ground.getCell(2, 0).moveInCell(heroP1);
                }
            }
        }
        for (Card card : player2.getHand().getDeck().getCards()) {
            if (card.getCardType() == 1) {
                MinionAndHero minionAndHero = (MinionAndHero) card;
                if (minionAndHero.isHero()) {
                    heroP2 = new Fighter(minionAndHero, minionAndHeroes, player2);
                    ground.getCell(2, 8).moveInCell(heroP2);
                }
            }
        }
        Battle.currentBattle = this;
        this.battleType = 0;
        currentTurn = 0;
        //this.battleType = battleType;
        if (battleType == 1) {
            lastFlag = Item.getLastFlag();
            ground.getCell((int) Math.random() % 5, (int) Math.random() % 9).setItemOnCell(lastFlag);
        }
        if (battleType == 2) {
            this.numberOfFlags = numberOfFlags;
            for (int i = 0; i < numberOfFlags; i++) {
                boolean placed = false;
                while (!placed) {
                    int x = (int) (Math.random() % 5);
                    int y = (int) (Math.random() % 9);
                    if (ground.getCell(x, y).getItemOnCell() == null) {
                        ground.getCell(x, y).setItemOnCell(Item.getNormalFlag());
                        placed = true;
                    }
                }
            }
        }
        nextTurn();
    }

    private void move(int targetX, int targetY, int x, int y) {
        if (!isValidMove(targetX, targetY, x, y)) {
            System.out.println("Your move isn't valid!");
            return;
        }
        if (!isValidTargetForMove(targetX, targetY)) {
            System.out.println("Your target for moving isn't valid!");
            return;
        }
        Fighter fighter = (Fighter) this.ground.getCell(x, y).getCardOnCell();
        if (fighter.isCanMove()) {
            System.out.println("Moved!");
            return;
        }
        newPlaceItem(x, y, fighter);
        ground.getCell(x, y).moveInCell(fighter);
        fighter.setLocation(x, y);
        if (ground.getCell(x, y).getItemOnCell() != null) {
            playerInTurn.addItem(ground.getCell(x, y).getItemOnCell());
        }
        ground.getCell(targetX, targetY).moveFromCell();
        for (Buff buff : ground.getCell(x, y).getCellEffect()) {
            if (buff.getCellEffectType() == 2) {
                fighter.decreaseHP(1);
            }
        }
    }

    private void newPlaceItem(int x, int y, Fighter fighter) {
        if (ground.getCell(x, y).getItemOnCell() != null) {
            Item item = ground.getCell(x, y).getItemOnCell();
            if (item.isFlag()) {
                playerInTurn.increaseFlagInHand();
            } else if (item.isLastFlag()) {
                playerInTurn.setHaveLastFlag(true);
                fighter.setHaveLastFlag(true);
            } else if (item.isCollectible()) {
                playerInTurn.addItem(item);
            }
        }
    }

    private boolean isValidTargetForMove(int x, int y) {
        Fighter fighter = (Fighter) this.ground.getCell(x, y).getCardOnCell();
        if (fighter.getPlayer().equals(this.playerInTurn)) {
            return true;
        }
        return false;
    }

    private void checkBuffs() {
        for (Buff buff : buffs) {
            if (buff.getAgeType() == 1) {
                if (buff.getAge() < 1) {
                    removeBuff(buff);
                } else buff.decreesAge();
            }
            if (buff.getAgeType() == 3) {
                if (buff.getAge() < 1) {
                    for (Fighter fighter : buff.getFighters()) {
                        fighter.addToBuff(buff);
                    }
                }
            }
        }
    }

    private void removeBuff(Buff buff) {
        if (buff.getIsCellBuff()) {
            for (Cell cell : buff.getCells())
                cell.removeCellEffect(buff);
        } else {
            for (Fighter fighter : buff.getFighters()) {
                fighter.removeFromBuff(buff);
            }
        }
    }

    public void nextTurn() {
        for (Fighter fighter : fighters) {
            fighter.preTurnProcces();
        }
        for (Cell[] cell : ground.getCells()) {
            for (Cell cell1 : cell) {
                if (cell1.getCellEffect().size() != 0) {
                    Fighter fighter = (Fighter) cell1.getCardOnCell();
                    if (fighter == null) {
                        break;
                    }
                    for (Buff buff : cell1.getCellEffect()) {
                        if (buff.getCellEffectType() == 0) {
                            fighter.decreaseHP(2);
                            isDeath(fighter);
                        }
                    }
                }
            }
        }
        if (battleType == 1) {
            if (player1.isHaveLastFlag()) {
                player1.increaseFlagInHand();
            }
            if (player2.isHaveLastFlag()) {
                player2.increaseFlagInHand();
            }
        }
        player1.preTurnProcess();
        player2.preTurnProcess();
        playerInTurn = player1;
        checkBuffs();
        currentTurn++;
        setMana();
        setPlayerInTurn();
        checkWinner();
    }

    public void showInfo() {

    }

    public static Battle getCurrentBattle() {
        return currentBattle;
    }

    private void winner(Player player) {
        player.getAccount().increaseWins();
        player.getAccount().addMoney(gift);
        if (player == player1)
            player2.getAccount().increaseLosses();
        else player1.getAccount().increaseLosses();
        System.out.println("player wins");
        currentBattle = null;
    }

    private void setPlayerInTurn() {
        if (playerInTurn.equals(player1))
            playerInTurn = player2;
        playerInTurn = player1;
    }
}
