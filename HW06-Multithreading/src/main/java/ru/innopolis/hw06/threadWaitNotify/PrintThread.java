package ru.innopolis.hw06.threadWaitNotify;

public class PrintThread implements Runnable {
    private final Counter counter;
    private final int timePrint;

    public PrintThread(Counter counter, int timePrint) {
        this.counter = counter;
        this.timePrint = timePrint;
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
                if (counter.getCounter() != 0 && counter.getCounter() % timePrint == 0) {
                    System.out.println(nameThread);
                }
            }
        } while (!counter.isInter());
        System.out.println("PrintThread End");
    }
}
