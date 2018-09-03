package ru.innopolis.hw07;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.innopolis.hw03.HW07.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MathBoxProxy implements InvocationHandler {
    private MathBoxInter mathBoxInter;

    public MathBoxProxy(MathBoxInter mathBoxInter) {
        this.mathBoxInter = mathBoxInter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Gson gson = new Gson();
        Method[] methods = mathBoxInter.getClass().getDeclaredMethods();
        for (Method met : methods) {
            if (met.getName().equals(method.getName())) {
                if (met.isAnnotationPresent(Logged.class)) {
                    System.out.println("Выполняется метод summator");
                }
                if (met.isAnnotationPresent(ClearData.class)) {
                    System.out.println("Выполняется метод cleanSet");
                    returnFieldSet().set(mathBoxInter, new TreeSet<>(Arrays.asList(0)));
                }
                if (met.isAnnotationPresent(UseXmlSerialization.class)) {
                    System.out.println("Выполняется метод serializationMethod()");
                    String json = gson.toJson(returnFieldSet().get(mathBoxInter));
                    writeGsonToFile(json);
                }
                if (met.isAnnotationPresent(UseXmlDeserializingMethod.class)) {
                    System.out.println("Выполняется метод deserializationMethod()");
                    Type type = new TypeToken<Set<Integer>>() {
                    }.getType();
                    Set<Integer> gs = gson.fromJson(readGsonFile(), type);
                    returnFieldSet().set(mathBoxInter, gs);
                }
                return method.invoke(mathBoxInter, args);
            }
        }
        return method.invoke(mathBoxInter, args);
    }

    private Field returnFieldSet() {
        Field fieldSet = null;
        try {
            fieldSet = mathBoxInter.getClass().getDeclaredField("set");
            fieldSet.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return fieldSet;
    }

    private void writeGsonToFile(String json) {
        try (FileWriter writer = new FileWriter("gson.txt", false)) {
            writer.write(json);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String readGsonFile() {
        String json = null;
        try (Scanner in = new Scanner(new FileReader("gson.txt"));) {
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            json = sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
