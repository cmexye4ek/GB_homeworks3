package ru.geekbrains.homework3_7;

import java.lang.reflect.InvocationTargetException;

public class HomeWorkApp {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("ru.geekbrains.homework3_7.Test2");
        TestClass.start(clazz);
    }
}
