package ru.geekbrains.homework3_1;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{
    private List<T> contents;

    public Box () {
       contents = new ArrayList<>();
    }

    public void addToBox (T object) {
        contents.add(object);
    }

    public void moveToOtherBox (Box<T> box) {
        box.getContents().addAll(contents);
        contents.clear();
    }

    public void boxInfo () {
        if (contents.isEmpty()) {
            System.out.println("В коробке пусто");
        } else {
            System.out.println("В коробке лежит " + contents.get(0).toString() +  " - " + contents.size() + "шт. ");
        }
    }

    public boolean boxCompare (Box <? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

    public float getWeight(){
        if (contents.isEmpty()) {
            return 0;
        } else {
            return contents.size() * contents.get(0).getWeight();
        }
    }

    public List<T> getContents() {
        return contents;
    }

}
