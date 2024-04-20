package org.example;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private final List<Tile> tiles=new ArrayList<>();
    int numberOfTokens;
    public Bag(int n)
    {
        this.numberOfTokens=n;
        this.fillBag(n);
    }
    public Bag()
    {

    }

    public void fillBag(int n)
    {
        int j=0;
        for(int i=1;i<=n;i++)
        {
            for(j=1;j<=n;j++)
                if(i!=j) {
                    tiles.add(new Tile(i, j));
                }

        }

        // tiles.forEach(System.out::println);
    }
    public synchronized List<Tile> extractTiles(int howMany, Player player) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(0));
        }
        if (tiles.isEmpty()) {
            player.setRunning(false); // Stop the player if no tiles left

        }
        return extracted;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }
}
