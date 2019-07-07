package ControlBox;

import Server.Moudle.Account;
import Server.Moudle.Battle;

public class ControlBox {
    private String type;
    private String region;
    private String cardName;
    private String userName;
    private String cardID;
    private int x, y;
    private int battleType;
    private int numberOfFlags;
    private String deckName;
    private boolean succes;
    private String pass;
    private String description;
    private int n;
    private Account account;
    private Battle battle;

    public void setBattle ( Battle battle ) {
        this.battle = battle;
    }

    public Battle getBattle () {
        return battle;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public Account getAccount () {
        return account;
    }

    public void setN ( int n ) {
        this.n = n;
    }

    public int getN () {
        return n;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public String getDescription () {
        return description;
    }

    public void setPass ( String pass ) {
        this.pass = pass;
    }

    public String getPass () {
        return pass;
    }

    public void setSucces ( boolean succes ) {
        this.succes = succes;
    }

    public boolean isSucces () {
        return succes;
    }

    public int getBattleType () {
        return battleType;
    }
    public void setBattleType ( int battleType ) {
        this.battleType = battleType;
    }
    public int getNumberOfFlags () {
        return numberOfFlags;
    }
    public void setNumberOfFlags ( int numberOfFlags ) {
        this.numberOfFlags = numberOfFlags;
    }
    public void setDeckName( String deckName) {
        this.deckName = deckName;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ControlBox ( String region, String type) {
        this.region = region;
        this.type = type;
    }
    public ControlBox (){
        this.type="empty";
    }
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }
}
