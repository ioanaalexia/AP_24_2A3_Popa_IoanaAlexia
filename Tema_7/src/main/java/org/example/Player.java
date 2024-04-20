package org.example;

import org.example.Game;
import org.example.Tile;
import org.example.Bag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Player implements Runnable {
    private String name;

    private Game game;
    private boolean running;
    public List<Tile> tiles = new ArrayList<>();

    public Player(String name,Game game) {
        this.name = name;
        this.game=game;

    }
    public void run() {
        while (running) {
            synchronized (game.getLock()) {
                try {
                    while (!game.isMyTurn(this)) {
                        game.getLock().wait();
                    }

                    System.out.println(this.name + " is extracting tiles.");
                    Random random = new Random();
                    int numberOfTiles = random.nextInt(10);
                    List<Tile> tilesExtracted = game.getBag().extractTiles(numberOfTiles, this);
                    System.out.println(this.name + " extracted:");
                    for(Tile tile:tilesExtracted)
                        this.tiles.add(tile);
                    tilesExtracted.forEach(tile -> System.out.println(tile));
                    game.nextPlayer();
                    game.getLock().notifyAll();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                    return;
                }
            }
        }
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public void setRunning(boolean value) {
        this.running = value;
    }

    public String getName() {
        return name;
    }
}
