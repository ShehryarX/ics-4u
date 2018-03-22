package Logic;

import java.util.ArrayList;
import java.util.List;

public class Session {
    List<Player> players;

    public Session() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Player selectPlayer(int x) {
        return this.players.get(x);
    }

    public int getIndexOfPlayer(Player player) {
        return this.players.indexOf(player);
    }
    
    public void startGame() {
    }   
    
    public void displayInstructions() {
    }
    
    public void endGame() {
    }
    
    public void restart() {
    }
}
