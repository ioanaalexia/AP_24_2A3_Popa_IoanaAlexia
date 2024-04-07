package org.example;
import java.io.Serializable;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private String[][] stoneGrid;
    private boolean[][] horizontalSticks;
    private boolean[][] verticalSticks;
    private String currentPlayer;

    public GameState(String[][] stoneGrid, boolean[][] horizontalSticks, boolean[][] verticalSticks, String currentPlayer) {
        this.stoneGrid = stoneGrid;
        this.horizontalSticks = horizontalSticks;
        this.verticalSticks = verticalSticks;
        this.currentPlayer = currentPlayer;
    }

    public String[][] getStoneGrid() {
        return stoneGrid;
    }

    public boolean[][] getHorizontalSticks() {
        return horizontalSticks;
    }

    public boolean[][] getVerticalSticks() {
        return verticalSticks;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

}
