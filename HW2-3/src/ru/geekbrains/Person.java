package ru.geekbrains;


public class Person{
    private String name;
    private String phone;


    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        Person another = (Person)o;
        if (this.name.equals(another.name))
        return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}