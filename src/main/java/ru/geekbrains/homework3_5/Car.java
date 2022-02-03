package ru.geekbrains.homework3_5;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static CyclicBarrier prepareCondition;
    private static CountDownLatch startRaceCondition;
    private static CountDownLatch endRaceCondition;
    private static CountDownLatch winRaceCondition;
    private static int CARS_COUNT;
    private static int FINISH_COUNTER;
    static {
        CARS_COUNT = 0;
        FINISH_COUNTER = 0;
    }
    private int finishPos = 0;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public int getFinishPos() {
        return finishPos;
    }

    public Car(Race race, int speed, CyclicBarrier prepareCondition, CountDownLatch startRaceCondition, CountDownLatch endRaceCondition, CountDownLatch winRaceCondition) {
        this.race = race;
        this.speed = speed;
        this.prepareCondition = prepareCondition;
        this.startRaceCondition = startRaceCondition;
        this.endRaceCondition = endRaceCondition;
        this.winRaceCondition = winRaceCondition;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public synchronized void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            startRaceCondition.countDown();
            prepareCondition.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int stageCounter = 0;
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            stageCounter++;
            endRaceCondition.countDown();

            if (stageCounter == race.getStages().size()) {
                FINISH_COUNTER++;
                finishPos = FINISH_COUNTER;
                winRaceCondition.countDown();
            }
        }
    }
}