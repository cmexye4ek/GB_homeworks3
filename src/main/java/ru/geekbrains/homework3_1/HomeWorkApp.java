package ru.geekbrains.homework3_1;

import java.util.Arrays;
import java.util.List;

public class HomeWorkApp {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5};
//        String[] array = {"one", "two","three", "four", "five"};
        System.out.println(Arrays.toString(array));
        changeElements(array, 1, 3);
        System.out.println(Arrays.toString(array));

        List<?> list = convertToList(array);
        System.out.println(list);

        Box<Orange> orangeBox_one = new Box<>();
        Box<Orange> orangeBox_two = new Box<>();
        Box<Apple> appleBox_one = new Box<>();
        Box<Apple> appleBox_two = new Box<>();

        for (int i = 0; i < 6; i++) {
            appleBox_one.addToBox(new Apple());
        }
        for (int i = 0; i < 4; i++) {
            orangeBox_one.addToBox(new Orange());
        }
        for (int i = 0; i < 5; i++) {
            appleBox_two.addToBox(new Apple());
            orangeBox_two.addToBox(new Orange());
        }
        orangeBox_one.boxInfo();
        orangeBox_two.boxInfo();
        appleBox_one.boxInfo();
        appleBox_two.boxInfo();

        System.out.println(orangeBox_one.getWeight()); //взвешиваем коробку

        System.out.println(orangeBox_one.boxCompare(appleBox_one)); //сравниваем вес коробок
        System.out.println(orangeBox_two.boxCompare(appleBox_two));

        orangeBox_one.moveToOtherBox(orangeBox_two); //пересыпаем апельсины из первой коробки во вторую
        orangeBox_one.boxInfo();
        orangeBox_two.boxInfo();


    }
    public static <T> void changeElements (T[] array, int index_one, int index_two) {
        T temp = array[index_one];
        array[index_one] = array[index_two];
        array[index_two] = temp;
    }

    private static <E> List<E> convertToList(E[] array) {
        return Arrays.asList(array);
    }
}
