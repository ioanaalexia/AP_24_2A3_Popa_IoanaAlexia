package org.example;
public class TimeKeeper implements Runnable {
    private final long timeLimit; // timpul limită în secunde

    public TimeKeeper(int seconds) {
        this.timeLimit = seconds * 1000; // convertim în milisecunde
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeLimit) {
            try {
                Thread.sleep(1);  // Așteaptă o secundă între verificări
                System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
            } catch (InterruptedException e) {
                System.out.println("TimeKeeper interrupted.");
                Thread.currentThread().interrupt(); // Menține starea întreruptă
                return;
            }
        }
        System.out.println("Time limit exceeded. Stopping game.");
        System.exit(0); // Oprește întreaga aplicație
    }

}