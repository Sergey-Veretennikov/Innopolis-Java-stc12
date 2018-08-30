package ru.innopolis.hw06.threadConcurrent;

public class TimerTwo implements Runnable {
    private final CounterTwo counterTwo;
    private int time;

    public TimerTwo(CounterTwo counterTwo, int time) {
        this.counterTwo = counterTwo;
        this.time = time;
    }

    public void run() {
        do {
            try {
                Thread.sleep(1000);

                counterTwo.getLock().lock();
                counterTwo.incrementCounter();
                System.out.println("Прошло времени: " + counterTwo.getCounter());

                if (counterTwo.getCounter() == time) {
                    counterTwo.setInter(true);
                }

                counterTwo.getCondition().signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                counterTwo.getLock().unlock();
            }
        } while (!counterTwo.isInter());
        System.out.println("TimerTwo End");
    }
}
