package ru.innopolis.hw08;

public class EuropeanHuman implements Human {
    public void eat(String what, int howMany) {
        System.out.println("Omnomnom " + what + " count " + howMany);
    }

    public void sleep(int duration) {
        System.out.println("HHRRRRRR " + duration + " hours");
    }

    public String saySmth() {
        return "I'm from London";
    }
}
