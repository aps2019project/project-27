package Moudle;

import java.util.ArrayList;
import java.util.Date;

public class MatchHistory {
    private static ArrayList<MatchHistory> matchHistories;
    private boolean singlePlayer;
    private Player player1;
    private Player player2;
    private int winner;
    private String state;
    private Date dateAndTime;

    public String[] loadLastMatch(Player player) {
        return null;
    }

    public void addMatchHistory(MatchHistory match) {
        matchHistories.add(match);
    }

//    public void MatchHistory(Game game) {
//
//    }
}