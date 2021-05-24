package ru.geekbrains;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Person> phoneBookList = new ArrayList<>();

    public PhoneBook() {
    }

    public void addBooks(String name, String phone){
        Person person = new Person(name, phone);
        phoneBookList.add(person);
    }

    public void getBook(String name){
        for (Person p: phoneBookList
             ) {
            if (name.equals(p.getName())){
                System.out.println(p);
            }
        }
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookList=" + phoneBookList +
                '}';
    }
}

