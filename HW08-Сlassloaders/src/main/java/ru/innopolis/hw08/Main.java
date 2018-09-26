package ru.innopolis.hw08;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Human humanProxy = (Human) Proxy.newProxyInstance(EuropeanHuman.class.getClassLoader(),
                new Class[]{Human.class}, new HumanInvoker());
        System.out.println(humanProxy.saySmth());
    }
}
