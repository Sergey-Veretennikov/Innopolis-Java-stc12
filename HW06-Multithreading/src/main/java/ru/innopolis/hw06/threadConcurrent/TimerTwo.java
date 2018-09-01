package ru.innopolis.hw06.threadConcurrent;

public class TimerTwo implements Runnable {
    private final CounterTwo counterTwo;
    private int timeOfWork;
    private int step;

    public TimerTwo(CounterTwo counterTwo, int step, int timeOfWork) {
        this.counterTwo = counterTwo;
        this.step = step;
        this.timeOfWork = timeOfWork;
    }

    public void run() {
        do {
            try {
                Thread.sleep(step);

                counterTwo.getLock().lock();
                counterTwo.incrementCounter();
                System.out.println("Прошло времени: " + counterTwo.getCounter());

                if (counterTwo.getCounter() == timeOfWork) {
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
