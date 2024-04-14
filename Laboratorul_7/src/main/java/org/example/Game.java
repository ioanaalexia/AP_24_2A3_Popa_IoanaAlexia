package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();

    public Game(int n) {
        this.bag = new Bag(n);
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public Bag getBag() {
        return bag;
    }

    public static void main(String[] args) {
        Game game = new Game(5); // Example initialization
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }
}