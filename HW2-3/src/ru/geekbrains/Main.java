package ru.geekbrains;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static List<Person> phoneBook = new ArrayList<>();
    //1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    // Посчитать сколько раз встречается каждое слово.
    //2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
    // В этот телефонный справочник с помощью метода add() можно добавлять записи.
    // С помощью метода get() искать номер телефона по фамилии.
    // Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    // тогда при запросе такой фамилии должны выводиться все телефоны.

    public static void main(String[] args) {

        task1();
        task2();






    }

    public static void task1(){
        String[] myArray = {"Apple", "Door", "Cent", "Human", "Apple", "Man", "River", "Cent", "Moon", "True", "Apple"};
        List<String> myList = new ArrayList<>();
        Collections.addAll(myList, myArray);
        Set<String> mySet = new LinkedHashSet<>();

//        Collections.addAll(mySet, myArray);
//        for (String itVar: myArray
//        ) {
//            System.out.print(itVar + " ");
//        }
//        System.out.println();
//        System.out.println(mySet);

        for (String itVar: myList
        ) {
            mySet.add(itVar);
        }
        System.out.println(myList);
        System.out.println(mySet);

        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word: myArray
        ){
            if (!wordCount.containsKey(word)){
                wordCount.put(word,0);
            }
            wordCount.put(word, wordCount.get(word) + 1);
        }
        for (String word:wordCount.keySet()
             ) {
            System.out.println(word + " " + wordCount.get(word));
            System.out.println();
            System.out.println();
        }
    }

    public static void task2(){
//        List<Person> phoneBook = new ArrayList<>();
        System.out.println("Task 2");
        System.out.println();
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addBooks("Vovan", "345"); //Добовляем
        phoneBook.addBooks("Sovan", "346");
        phoneBook.addBooks("Dovan", "347");
        phoneBook.addBooks("Vovan", "345");

        System.out.println(phoneBook); // проверяем добавление
        System.out.println();

        phoneBook.getBook("Vovan"); // ищем



    }



}
