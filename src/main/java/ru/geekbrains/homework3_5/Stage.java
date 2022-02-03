package ru.geekbrains.homework3_5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public abstract class Stage {
    protected int length;
    protected String description;
    protected CyclicBarrier cyclicBarrier;
    protected Semaphore semaphore;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}