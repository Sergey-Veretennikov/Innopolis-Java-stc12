package ru.innopolis.hw06.threadWaitNotify;

public class Timer implements Runnable {
    private final Counter counter;
    private int time;

    public Timer(Counter counter, int time) {
        this.counter = counter;
        this.time = time;
    }

    public void run() {
        do {
            try {
                Thread.sleep(1000);
                synchronized (counter) {
                    counter.incrementCounter();
                    System.out.println("Прошло времени: " + counter.getCounter());
                    if (counter.getCounter() == time) {
                        counter.setInter(true);
                    }
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!counter.isInter());
        System.out.println("TimerTwo End");
    }
}
