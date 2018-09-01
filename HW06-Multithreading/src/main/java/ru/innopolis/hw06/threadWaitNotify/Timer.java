package ru.innopolis.hw06.threadWaitNotify;

public class Timer implements Runnable {
    private final Counter counter;
    private int timeOfWork;
    private int step;

    public Timer(Counter counter, int step, int timeOfWork) {
        this.counter = counter;
        this.step = step;
        this.timeOfWork = timeOfWork;
    }

    public void run() {
        do {
            try {
                Thread.sleep(step);
                synchronized (counter) {
                    counter.incrementCounter();
                    System.out.println("Прошло времени: " + counter.getCounter());
                    if (counter.getCounter() == timeOfWork) {
                        counter.setInter(true);
                    }
                    counter.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!counter.isInter());
        System.out.println("Timer End");
    }
}
