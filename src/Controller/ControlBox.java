package Controller;

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

    public ControlBox(String region, String type) {
        this.region = region;
        this.type = type;
    }
    public ControlBox(){
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
