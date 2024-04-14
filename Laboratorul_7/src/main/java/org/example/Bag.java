package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bag {
    private final LinkedList<Tile> tiles = new LinkedList<>();

    public Bag(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany && !tiles.isEmpty(); i++) {
            extracted.add(tiles.poll());
        }
        return extracted;
    }
}