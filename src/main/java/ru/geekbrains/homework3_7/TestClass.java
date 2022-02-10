package ru.geekbrains.homework3_7;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class TestClass {

    public TestClass() {
    }

    public static void start(Class clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Method[] methods = clazz.getDeclaredMethods();
        if (Arrays.stream(methods).filter(a -> a.getAnnotation(BeforeSuite.class) != null).toArray().length > 1) {
            throw new RuntimeException("В классе должна быть только одна аннотация @BeforeSuite");
        } else if (Arrays.stream(methods).filter(a -> a.getAnnotation(AfterSuite.class) != null).toArray().length > 1) {
            throw new RuntimeException("В классе должна быть только одна аннотация @AfterSuite");
        }
        Arrays.sort(methods, (m1, m2) ->{

                if (m1.getAnnotations() == null) {
                    return -1;
                }
                if (m2.getAnnotations() == null) {
                    return 1;
                }

                if (m1.getAnnotation(BeforeSuite.class) != null) {
                    return -1;
                } else if (m2.getAnnotation(BeforeSuite.class) != null) {
                    return 1;
                }

                if (m1.getAnnotation(AfterSuite.class) != null) {
                    return 1;
                } else if (m2.getAnnotation(AfterSuite.class) != null) {
                    return -1;
                }
                if (m1.getAnnotation(Test.class) != null) {
                    if (m2.getAnnotation(Test.class) != null) {
                        if (m1.getAnnotation(Test.class).priority() < m2.getAnnotation(Test.class).priority()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
                return 0;

        });

        for (Method m : methods) {
            int modifier = m.getModifiers();
            if (m.getAnnotations().length != 0) {
                if (Modifier.isPrivate(modifier)) {
                    m.setAccessible(true);
                    m.invoke(clazz.newInstance());
                    m.setAccessible(false);

                } else {
                    m.invoke(clazz.newInstance());

                }
            }
        }
    }
}