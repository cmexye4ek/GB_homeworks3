package ru.geekbrains.homework3_7;

public class Test2 {
    @Test(priority = 2)
    private void doSomething() {
        System.out.println("doSomething");
    }

    @Test
    private void doSomethingTwo() {
        System.out.println("doSomethingTwo");
    }

    @Test(priority = 4)
    private void exampleMethod() {
        System.out.println("exampleMethod");
    }

    @BeforeSuite
    private void exampleMethodTwo() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    private void someMethodTwo() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 3)
    private void someMethod() {
        System.out.println("someMethod");
    }



    private void exampleMethod666() {
        System.out.println("exampleMethod666");
    }

}
