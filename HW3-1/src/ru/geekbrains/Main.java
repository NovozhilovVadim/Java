package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
/*1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки,
зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
 которую подадут в compare в качестве параметра, true - если их веса равны,
 false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
 нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
 а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.*/

    public static void main(String[] args) {

        System.out.println("TASK1");
        System.out.println("ArrayList:");
        ArrayList arrayList = new ArrayList();
        arrayList.add("venom");
        arrayList.add("bounty");
        for (Object o : arrayList) {
            System.out.println(o);
        }
        Object[] objects = arrayList.toArray();
        task1(objects);//меняем местами
        System.out.println("Array swap");
        for (Object o : objects) {
            System.out.println(o);
        }
        System.out.println();
        System.out.println();
        //Task2 (не стал выносить в отдельный метод)
        List<Object> newArray = new ArrayList<>(); //новый список
        newArray = Arrays.asList(objects);//закидываем туда массив из Task1
        System.out.println("Create ArrayList from array objects ");
        for (Object o : newArray) {
            System.out.println(o);
        }
        System.out.println();
        System.out.println();

        // Задание 3
        Apple apple1 = new Apple();//Создаём фрукты
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();

        Box<Apple> box1 = new Box<Apple>();//создаём типизированую коробку
        Box<Orange> box2 = new Box<Orange>();
        box1.add(apple1);//добавляем в коробку
        box1.add(apple2);
        box2.add(orange1);
        box2.add(orange2);
        box1.compare(box2);
        System.out.println(box1.compare(box2));//сравниваем вес
        Box<Orange> box3 = new Box<Orange>();//создаём ещё коробку
        box2.transfer(box3);//перекладываем фрукты
    }

    public static void task1(Object[] arrayList){
        ArrayList tmp = new ArrayList();//временный список
        tmp.add(arrayList[1]);// берём вторую ячейку массива и пишем под индексом 0 в tmp
        arrayList[1] = arrayList [0];
        arrayList[0] = tmp.get(0);//возвращаем обратно в массив
    }
}
