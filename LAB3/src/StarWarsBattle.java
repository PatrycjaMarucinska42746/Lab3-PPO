//Patrycja Marucińska 42746 
//Lab 3
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Player {
    String name;
    int healthPoints = 100;

    public Player(String name) {
        this.name = name;
    }
    public int attack(Player target) {
        int hitPoints = new Random().nextInt(3) + 10;
        target.takeHit(hitPoints);
        return hitPoints;
    }
    public void takeHit(int hitPoints) {
        this.healthPoints -= hitPoints;
    }
    public boolean isDead() {
        return healthPoints <= 0;
    }
}
public class StarWarsBattle {
    public static Player getRandomPlayer(List<Player> players) {
        return players.get(new Random().nextInt(players.size()));
    }
    public static List<Player> generatePlayers(int numPlayers) {
        List<Player> players = new ArrayList<>();
        String[] starWarsNames = {"Luke Skywalker", "Darth Vader", "Princess Leia", "Han Solo", "Obi-Wan Kenobi",
                "Yoda", "Chewbacca", "R2-D2", "C-3PO", "Padmé Amidala",
                "Anakin Skywalker", "Mace Windu", "Qui-Gon Jinn", "Rey", "Finn"};

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(starWarsNames[i]));
        }
        return players;
    }
    public static void displayPlayers(List<Player> players) {
        System.out.println("Players:");
        System.out.printf("%-20s %-15s%n", "Name", "Health Points");
        System.out.println("-----------------------------");
        for (Player player : players) {
            System.out.printf("%-20s %-15s%n", player.name, player.healthPoints);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int numPlayers = 15;
        List<Player> players = generatePlayers(numPlayers);
        int roundNumber = 1;
        boolean ongoing = true;
        while (ongoing) {
            Player attackingPlayer = getRandomPlayer(players);
            Player targetPlayer = getRandomPlayer(players);
            //Upewnienie, że gracz nie zaatakuje samego siebie
            while (attackingPlayer == targetPlayer) {
                targetPlayer = getRandomPlayer(players);
            }
            int hitPoints = attackingPlayer.attack(targetPlayer);
            System.out.printf("Round %d: %s attacked %s with %d points.%n", roundNumber, attackingPlayer.name, targetPlayer.name, hitPoints);
            displayPlayers(players);
            for (Player player : new ArrayList<>(players)) {
                if (player.isDead()) {
                    System.out.println(player.name + " died.");
                    players.remove(player);
                }
            }
            if (players.size() == 1) {
                System.out.println(players.get(0).name + " is the last player standing. Game over!");
                ongoing = false;
            }
            roundNumber++;
        }
    }
}
