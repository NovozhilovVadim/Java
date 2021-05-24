package ru.geekbrains;

public class MyArrayDataException extends Exception {
    public int i;
    public int j;

    MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;

    }

    @Override
    public String toString() {
        return "MyArrayDataException{" +
                " Неправильное значение в ячейке "
                 + i +
                "х" + j +
                '}';
    }
}
