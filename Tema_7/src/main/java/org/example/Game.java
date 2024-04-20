package org.example;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private int currentPlayerIndex = 0;
    private final Object lock = new Object();
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
    private List<Integer>[] adjList;
    private boolean[] visited;
    public int n;
    public Game(int n) {
        this.n = n;
        this.bag=new Bag(n);
        adjList = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];
        for (int i = 0; i < 3; i++) {
            players.add(new Player("Player " + (i + 1), this));
        }
    }

    public void buildGraph(Player player) {
        int n = this.n ;
        adjList = new List[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (Tile tile : player.tiles) {
            int from = tile.getNumber1();
            int to = tile.getNumber2();
            adjList[from].add(to);
        }
    }

    public int findLongestCycle(Player player) {
        buildGraph(player);
        int longestCycle = 0;
        for (int i = 1; i < adjList.length; i++) {
            if (!adjList[i].isEmpty()) {
                int cycleLength = dfs(i, i, 0);
                longestCycle = Math.max(longestCycle, cycleLength);
            }
        }
        return longestCycle;
    }

    private int dfs(int start, int node, int length) {
        if (visited[node]) return 0;
        visited[node] = true;

        int maxCycleLength = 0;
        for (int neighbor : adjList[node]) {
            if (neighbor == start && length >= 1) {
                maxCycleLength = Math.max(maxCycleLength, length + 1);
            } else {
                maxCycleLength = Math.max(maxCycleLength, dfs(start, neighbor, length + 1));
            }
        }

        visited[node] = false;
        return maxCycleLength;
    }


    public void play() {
        Thread timeKeeperThread = new Thread(new TimeKeeper(10));
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread playerThread=new Thread(player);
            System.out.println("incepe jocul");
            player.setRunning(true);
            playerThread.start();
            threads.add(playerThread);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("A thread was interrupted: " + e.getMessage());
            }
        }

        this.calculatePoints();

    }

    public Bag getBag() {
        return bag;
    }

    public boolean isMyTurn(Player player) {
        return players.get(currentPlayerIndex).equals(player);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public void calculatePoints()
    {
        for (Player player:players) {
            System.out.println(player.getName()+" a acumulat "+findLongestCycle(player)+" puncte.");
        }
    }



    public Object getLock() {
        return lock;
    }
    public static void main(String args[]) {
        Game game = new Game(10);
        game.play();

    }

    private void addPlayer(Player player) {
        players.add(player);
    }
}
