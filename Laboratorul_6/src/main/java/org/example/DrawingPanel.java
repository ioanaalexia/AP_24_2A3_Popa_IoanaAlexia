package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.*;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

import java.util.Random;

class DrawingPanel extends Canvas {
    private ConfigPanel configPanel;
    private int rows, cols;
    private int canvasWidth, canvasHeight;
    private int cellWidth, cellHeight;
    private int padX, padY;
    private int stoneSize = 20;

    private Color[][] stoneGrid;
    private Color currentPlayer = Color.RED;

    private boolean[][] horizontalSticks;
    private boolean[][] verticalSticks;

    private GameState gameState;

    public DrawingPanel(ConfigPanel configPanel, int width, int height, int rows, int cols) {
        this.configPanel = configPanel;
        this.canvasWidth = 400;
        this.canvasHeight = 400;
        setWidth(canvasWidth);
        setHeight(canvasHeight);


        configPanel.getCreateButton().setOnAction(event -> createBoard());
        registerMouseClickHandler();
    }
    private void createBoard() {
        this.rows = configPanel.getRowsSpinner().getValue();
        this.cols = configPanel.getColsSpinner().getValue();
        stoneGrid = new Color[rows][cols];
        horizontalSticks = new boolean[rows][cols - 1];
        verticalSticks = new boolean[rows - 1][cols];
        Random random = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols - 1; c++) {
                horizontalSticks[r][c] = random.nextBoolean();
            }
        }
        for (int r = 0; r < rows - 1; r++) {
            for (int c = 0; c < cols; c++) {
                verticalSticks[r][c] = random.nextBoolean();
            }
        }

        init();
        draw();
    }

    private void registerMouseClickHandler() {
        this.setOnMouseClicked(this::handleMouseClick);
    }

    private void handleMouseClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double centerX = padX + col * cellWidth;
                double centerY = padY + row * cellHeight;

                double distance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));

                if (distance <= stoneSize / 2) {
                    if (stoneGrid[row][col] == null) {
                        stoneGrid[row][col] = currentPlayer;
                        currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
                        draw();
                        return;
                    }
                }
            }
        }
    }

    private void init() {
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.stoneGrid = new Color[rows][cols];
    }

    private void drawStones(GraphicsContext g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (stoneGrid[row][col] != null) {
                    int x = padX + col * cellWidth;
                    int y = padY + row * cellHeight;
                    g.setFill(stoneGrid[row][col]);
                    g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                }
            }
        }
    }

    private void drawSticks(GraphicsContext g) {
        g.setStroke(Color.BLACK);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols - 1; c++) {
                if (horizontalSticks[r][c]) {
                    int x1 = padX + c * cellWidth;
                    int y = padY + r * cellHeight;
                    int x2 = x1 + cellWidth;
                    g.strokeLine(x1, y, x2, y);
                }
            }
        }
        for (int r = 0; r < rows - 1; r++) {
            for (int c = 0; c < cols; c++) {
                if (verticalSticks[r][c]) {
                    int x = padX + c * cellWidth;
                    int y1 = padY + r * cellHeight;
                    int y2 = y1 + cellHeight;
                    g.strokeLine(x, y1, x, y2);
                }
            }
        }
    }

    private void draw() {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        drawSticks(g);
        drawStones(g);
    }

    private void paintGrid(GraphicsContext g) {
        g.setStroke(Color.DARKGRAY);
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + (cols - 1) * cellWidth;
            g.strokeLine(x1, y1, x2, y1);
        }
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = padY + (rows - 1) * cellHeight;
            g.strokeLine(x1, y1, x1, y2);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setFill(Color.LIGHTGRAY);
                g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    public void saveGame() {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        WritableImage image = this.snapshot(params, null);

        File file = new File("game-board.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Game board saved as image successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save the game board as an image.");
            e.printStackTrace();
        }
    }

    private String colorToString(Color color) {
        return color != null ? color.toString() : null;
    }


    private Color stringToColor(String colorAsString) {
        return colorAsString != null ? Color.web(colorAsString) : null;
    }


    public void saveGameState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gamestate.ser"))) {
            String[][] stoneGridAsString = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    stoneGridAsString[i][j] = stoneGrid[i][j] != null ? colorToString(stoneGrid[i][j]) : null;
                }
            }

            GameState gameState = new GameState(stoneGridAsString, horizontalSticks, verticalSticks, colorToString(currentPlayer));

            out.writeObject(gameState);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save the game state.");
            e.printStackTrace();
        }
    }

    public void loadGame() {
        File file = new File("gamestate.ser");
        if (!file.exists()) {
            System.out.println("Load failed: Save file does not exist.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            GameState loadedGameState = (GameState) in.readObject();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    stoneGrid[i][j] = stringToColor(loadedGameState.getStoneGrid()[i][j]);
                }
            }
            horizontalSticks = loadedGameState.getHorizontalSticks();
            verticalSticks = loadedGameState.getVerticalSticks();
            currentPlayer = stringToColor(loadedGameState.getCurrentPlayer());

            draw();
            System.out.println("Game state loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load the game state.");
            e.printStackTrace();
        }
    }

}