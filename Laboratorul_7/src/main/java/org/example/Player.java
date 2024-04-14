package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private volatile boolean running = true;
    private final List<Tile> tiles = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (running) {
            List<Tile> extractedTiles = game.getBag().extractTiles(1);
            if (extractedTiles.isEmpty()) {
                running = false; // Stop if there are no more tiles
                break;
            }
            tiles.addAll(extractedTiles);
            System.out.println(name + " extracted " + extractedTiles);
        }
    }

    public void stopRunning() {
        this.running = false;
    }
}