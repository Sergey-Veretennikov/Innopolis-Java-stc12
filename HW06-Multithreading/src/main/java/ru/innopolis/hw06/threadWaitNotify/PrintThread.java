package ru.innopolis.hw06.threadWaitNotify;

public class PrintThread implements Runnable {
    private final Counter counter;
    private final int time;

    public PrintThread(Counter counter, int time) {
        this.counter = counter;
        this.time = time;
    }

    public void run() {
        String nameThread = Thread.currentThread().getName();
        do {
            synchronized (counter) {
                try {
                    counter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (counter.getCounter() % time == 0) {
                    System.out.println(nameThread);
                }
            }
        } while (!counter.isInter());
        System.out.println("PrintThreadTwo End");
    }
}
