package ru.innopolis.hw06;

import ru.innopolis.hw06.threadConcurrent.CounterTwo;
import ru.innopolis.hw06.threadConcurrent.PrintThreadTwo;
import ru.innopolis.hw06.threadConcurrent.TimerTwo;
import ru.innopolis.hw06.threadWaitNotify.Counter;
import ru.innopolis.hw06.threadWaitNotify.PrintThread;
import ru.innopolis.hw06.threadWaitNotify.Timer;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new PrintThread(counter, 5), "Wait 5 seconds").start();
        new Thread(new PrintThread(counter, 7), "Wait 7 seconds").start();
        new Thread(new Timer(counter, 18)).start();
        System.out.println("Start");

        CounterTwo counter2 = new CounterTwo();
        new Thread(new PrintThreadTwo(counter2, 5), "Wait 5 seconds").start();
        new Thread(new PrintThreadTwo(counter2, 7), "Wait 7 seconds").start();
        new Thread(new TimerTwo(counter2, 18)).start();
        System.out.println("Start");
    }
}
