package ru.geekbrains.homework3_5;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final int TUNNEL_CAPACITY = CARS_COUNT / 2;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Semaphore tunnelCapacity = new Semaphore(TUNNEL_CAPACITY);
        CountDownLatch startRaceCondition = new CountDownLatch(CARS_COUNT);
        CountDownLatch winRaceCondition = new CountDownLatch(1);
        CyclicBarrier prepareCondition = new CyclicBarrier(CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(tunnelCapacity), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch endRaceCondition = new CountDownLatch(CARS_COUNT * race.getStages().size());
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), prepareCondition, startRaceCondition, endRaceCondition, winRaceCondition);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();

        }
        try {
            startRaceCondition.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            winRaceCondition.await();
            for (int i = 0; i < cars.length; i++) {
                if (cars[i].getFinishPos() == 1) {
                    System.out.println(cars[i].getName()+ " пришел первым и победил в гонке");
                }
            }
            endRaceCondition.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}