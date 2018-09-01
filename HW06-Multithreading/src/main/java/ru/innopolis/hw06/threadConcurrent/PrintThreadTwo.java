package ru.innopolis.hw06.threadConcurrent;

public class PrintThreadTwo implements Runnable {
    private final CounterTwo counterTwo;
    private final int timePrint;

    public PrintThreadTwo(CounterTwo counterTwo, int timePrint) {
        this.counterTwo = counterTwo;
        this.timePrint = timePrint;
    }

    public void run() {
        String nameThread = Thread.currentThread().getName();
        do {
            counterTwo.getLock().lock();
            try {
                counterTwo.getCondition().await();

                if (counterTwo.getCounter() != 0 && counterTwo.getCounter() % timePrint == 0) {
                    System.out.println(nameThread);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                counterTwo.getLock().unlock();
            }
        } while (!counterTwo.isInter());
        System.out.println("PrintThreadTwo End");
    }
}
