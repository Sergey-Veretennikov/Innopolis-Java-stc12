package ru.innopolis.hw06.threadWaitNotify;

public class Counter {
    private int counter;
    private boolean inter = false;

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public boolean isInter() {
        return inter;
    }

    public void setInter(boolean inter) {
        this.inter = inter;
    }
}
